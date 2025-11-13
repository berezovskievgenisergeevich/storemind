package brax.web.pages;

import brax.helpers.BRAXCustomerProvider;
import io.qameta.allure.Step;
import se.web.components.LoadingDialogWindow;
import se.web.data.model.Customer;
import se.web.pages.CreateNewCustomer;

import java.time.LocalDate;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.interactable;
import static com.codeborne.selenide.Selenide.$;

public class BRAXCreateNewCustomer extends CreateNewCustomer {

    @Step("fill in birthday")
    public CreateNewCustomer setBirthday(LocalDate data) {
        String day = data.getDayOfMonth() + "";
        String month = data.getMonthValue() + "";
        if (data.getDayOfMonth() <= 9)
            day = "0" + data.getDayOfMonth();

        if (data.getMonthValue() <= 9)
            month = "0" + data.getMonthValue();

        setBirthdayDay(day);
        setBirthdayMonth(month);
        setBirthdayYear(data.getYear() + "");

        return this;
    }

    @Step("fill in birthday-day: {day}")
    public BRAXCreateNewCustomer setBirthdayDay(String day) {
        $("[data-testid='birthday-day-select']").click();
        setValurInBirthdayList(day);
        return this;
    }

    @Step("fill in birthday-month: {month}")
    public BRAXCreateNewCustomer setBirthdayMonth(String month) {
        $("[data-testid='birthday-month-select']").click();
        setValurInBirthdayList(month);
        return this;
    }

    @Step("fill in birthday-year: {year}")
    public BRAXCreateNewCustomer setBirthdayYear(String year) {
        $("[data-testid='birthday-year-select']").click();
        setValurInBirthdayList(year);
        return this;
    }

    @Step("Sign form")
    public BRAXCreateNewCustomer signForm() {
        $("[data-testid='signature-box']").click();
        $("[data-testid='signature-modal']").should(exist).shouldBe(interactable);
        new LoadingDialogWindow().waitLoading();
        $("[data-testid='signature-canvas']").click();
        $("[data-testid='confirm-button']").click();
        return this;
    }


    @Step("select Legal Information check-box")
    public BRAXCreateNewCustomer selectLegalInfoCheckBox() {
        $("[data-testid='userAgreement-checkbox']").click();
        return this;
    }


    @Step("select Email check-box")
    public BRAXCreateNewCustomer selectEmailCheckBox() {
        $("[data-testid='isEmailContactAllowed-checkbox']").click();
        return this;
    }


    @Step("select Phone Contact Allowed check-box")
    public BRAXCreateNewCustomer selectPhoneContactAllowedCheckBox() {
        $("[data-testid='isPhoneContactAllowed-checkbox']").click();
        return this;
    }

    @Step("select Messenger Contact Allowed check-box")
    public BRAXCreateNewCustomer selectMessengerContactAllowedCheckBox() {
        $("[data-testid='isMessengerContactAllowed-checkbox']").click();
        return this;
    }

    @Step("fill in email: {email}")
    public BRAXCreateNewCustomer setEmail(String email) {
        inputEmail.val(email);
        return this;
    }

    @Step("fill in Mobile: {mobile}")
    public BRAXCreateNewCustomer setMobile(String mobile) {
        $("[data-testid='addressLine2-input']").val(mobile);
        return this;
    }

    @Step("Fill in all mandatory fields")
    public BRAXCreateNewCustomer fillInAllMandatoryFields(Customer customer) {
        selectSalutation(customer);
        setName(customer.getName());
        setLastName(customer.getLastName());
        setBirthday(customer.getBirthday());
        setPostalCode(customer.getZip());
        setCity(customer.getCity());
        setStreet(customer.getStreet());
        setStreetNumber(customer.getHomeNr());
        setEmail(customer.getEmail());
        signForm();
        return this;
    }

    public BRAXCreateNewCustomer fillInAllMandatoryFieldsExpectNameCityEmailBirthday(Customer customer) {
        selectSalutation(customer);
        setLastName(customer.getLastName());
        setPostalCode(customer.getZip());
        setStreet(customer.getStreet());
        setStreetNumber(customer.getHomeNr());
        signForm();
        return this;
    }

    @Step("click to 'Create Without Bonus'")
    public BRAXCreateNewCustomer clickCreateWithoutBonus() {
        $("[data-testid='submit-without-bonus-points']").click();
        return this;
    }


}
