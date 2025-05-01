package web.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import web.components.LoadingDialogWindow;
import web.data.model.Salutation;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.interactable;
import static com.codeborne.selenide.Selenide.$;

public class CreateNewCustomer {

    SelenideElement inputName = $("[data-testid='firstName-input']");
    SelenideElement inputCity = $("[data-testid='city-input']");
    SelenideElement inputEmail = $("[data-testid='email-input']");
    SelenideElement inputStreetNr = $("[data-testid='streetNumber-input']");
    SelenideElement inputLastName = $("[data-testid='lastName-input']");
    SelenideElement inputStreet = $("[data-testid='street-input']");

    @Step("select customer and click 'Next'")
    public OrderConfirmation clickNextInCreateNewOrder() {
        $("[data-testid='submit-button']").click();
        return new OrderConfirmation();
    }

    @Step("Select Salutation Mrs")
    public CreateNewCustomer selectSalutationMrs() {
        $("[data-testid='salutation-MRS']").click();
        return this;
    }

    @Step("Select Salutation MR")
    public CreateNewCustomer selectSalutationMr() {
        $("[data-testid='salutation-MR']").click();
        return this;
    }

    @Step("Select Salutation")
    public CreateNewCustomer selectSalutation(web.data.model.Customer customer) {
        if (customer.getSalutation() == Salutation.He) {
            selectSalutationMr();
            return this;
        }
        selectSalutationMrs();
        return this;
    }

    @Step("fill in name: {name}")
    public CreateNewCustomer setName(String name) {
        cleanUpField(inputName);
        inputName.val(name);
        return this;
    }

    @Step("fill in last name: {lastName}")
    public CreateNewCustomer setLastName(String lastName) {
        cleanUpField(inputLastName);
        inputLastName.val(lastName);
        return this;
    }

    @Step("fill in birthday-day: {day}")
    public CreateNewCustomer setBirthdayDay(String day) {
        $("[data-testid='day-select']").click();
        setValurInBirthdayList(day);
        return this;
    }

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

    @Step("fill in birthday-month: {month}")
    public CreateNewCustomer setBirthdayMonth(String month) {
        $("[data-testid='month-select']").click();
        setValurInBirthdayList(month);
        return this;
    }

    @Step("fill in birthday-year: {year}")
    public CreateNewCustomer setBirthdayYear(String year) {
        $("[data-testid='year-select']").click();
        setValurInBirthdayList(year);
        return this;
    }

    private void setValurInBirthdayList(String val) {
        $("ul li[data-value='" + val + "']").click();
    }


    @Step("fill in Postal Code (ZIP): {postalCode}")
    public CreateNewCustomer setPostalCode(String postalCode) {
        $("[data-testid='postalCode-input']").val(postalCode);
        return this;
    }

    @Step("fill in city: {city}")
    public CreateNewCustomer setCity(String city) {
        inputCity.val(city);
        return this;
    }

    @Step("fill in street: {street}")
    public CreateNewCustomer setStreet(String street) {
        cleanUpField(inputStreet);
        inputStreet.val(street);
        return this;
    }

    @Step("fill in street number: {streetNumber}")
    public CreateNewCustomer setStreetNumber(String streetNumber) {
        cleanUpField(inputStreetNr);
        inputStreetNr.val(streetNumber);
        return this;
    }

    @Step("fill in additional address: {additionalAddress}")
    public CreateNewCustomer setAdditionalAddress(String additionalAddress) {
        $("[data-testid='addressLine1-input']").val(additionalAddress);
        return this;
    }

    @Step("fill in phone: {phone}")
    public CreateNewCustomer setPhone(String phone) {
        $("[data-testid='phone-input']").val(phone);
        return this;
    }

    @Step("fill in email: {email}")
    public CreateNewCustomer setEmail(String email) {
        inputEmail.val(email);
        return this;
    }

    @Step("Sign form")
    public CreateNewCustomer signForm() {
        $("[data-testid='signature']").click();
        $("[data-testid='signature-modal']").should(exist).shouldBe(interactable);
        $("[data-testid='signature-canvas']").click();
        $("[data-testid='confirm-button']").click();
        return this;
    }


    @Step("select Email check-box")
    public CreateNewCustomer selectEmailCheckBox() {
        $("[data-testid='contact-agreement-title']").parent().$("[data-testid='CheckIcon']")
                .parent().click();
        return this;
    }

    @Step("select Legal Information check-box")
    public CreateNewCustomer selectLegalInfoCheckBox() {
        $("[data-testid='user-agreement-title']").parent().$("[data-testid='CheckIcon']")
                .parent().click();
        return this;
    }


    @Step("click 'Clear Fields' button")
    public CreateNewCustomer clickClearFields() {
        $("[data-testid='reset-button']").click();
        return this;
    }

    @Step("click 'Register Customer' button")
    public CreateNewCustomer clickRegisterCustomer() {
        $("[data-testid='submit-button']").click();
        return this;
    }

    @Step("click 'ok' in 'Registration successful' window")
    public CustomerInfo clickOkInSuccessWindow() {
        $("[role='dialog'] button").click();
        return new CustomerInfo();
    }

    @Step("click 'ok' in 'Registration successful' window")
    public String clickOkInSuccessWindowAndGetCustomerId() {
        String customerId = extractCustomerId(getNotificationTextElement().text());
        $("[role='dialog'] button").click();
        return customerId;
    }

    public SelenideElement getNotificationTextElement() {
        return $("[data-testid='notification-text']");
    }

    @Step("click 'ok' in 'Email Verification' window")
    public CreateNewCustomer clickOkInEmailVerificationWindow() {
        new LoadingDialogWindow().waitLoading();
        $("[data-testid='notification-text']").parent()
                .$("button").click();
        return this;
    }

    public String extractCustomerId(String input) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(input);
        return matcher.find() ? matcher.group() : "";
    }

    public SelenideElement getVerificationErrorWindow() {
        return $("[data-testid='validation-error-modal']");
    }

    @Step("Fill in all mandatory fields")
    public CreateNewCustomer fillInAllMandatoryFields(web.data.model.Customer customer) {
        selectSalutation(customer);
        setName(customer.getName());
        setLastName(customer.getLastName());
        setPostalCode(customer.getZip());
        setCity(customer.getCity());
        setStreet(customer.getStreet());
        setStreetNumber(customer.getHomeNr());
        setEmail(customer.getEmail());
        signForm();
        selectLegalInfoCheckBox();
        return this;
    }

    @Step("Fill in all mandatory fields except: Name, City, Email")
    public CreateNewCustomer fillInAllMandatoryFieldsExpectNameCityEmail(web.data.model.Customer customer) {
        selectSalutation(customer);
        setLastName(customer.getLastName());
        setPostalCode(customer.getZip());
        setStreet(customer.getStreet());
        setStreetNumber(customer.getHomeNr());
        signForm();
        selectLegalInfoCheckBox();
        return this;
    }

    @Step("approve Email verification window and click Create new customer")
    public CreateNewCustomer approveEmailVerificationAndClickCreateNewCustomer() {
        clickRegisterCustomer();
        clickOkInEmailVerificationWindow();
        clickRegisterCustomer();
        return this;
    }

    @Step("click 'ok' in 'Process will continue with existing Customer' window")
    public CreateNewCustomer clickOkInProcessContinueWithExistingCustomer() {
        $("[data-testid='notification-text']").parent()
                .$("button").click();
        return this;
    }

    @Step("continue order creation with Existing Customer")
    public OrderConfirmation continueWithExistingCustomer() {
        clickOkInProcessContinueWithExistingCustomer();
        return clickNextInCreateNewOrder();
    }

    void cleanUpField(SelenideElement element) {
        element.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
    }

}
