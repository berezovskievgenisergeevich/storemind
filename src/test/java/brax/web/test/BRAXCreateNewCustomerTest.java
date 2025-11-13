package brax.web.test;

import brax.web.pages.BRAXCreateNewCustomer;
import brax.web.pages.BRAXLogin;
import io.qameta.allure.Story;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import se.helpers.CustomerProvider;
import se.web.data.model.Customer;
import se.web.pages.Login;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

@Story("[UI] Create Customer")
public class BRAXCreateNewCustomerTest extends BraxTest {
    private String ZIP_DE = "12345";

    @Test
    @Tags({@Tag("customer"), @Tag("regression"), @Tag("ui")})
    @DisplayName("Create new customer with all information")
    void checkCustomerCreationWithAllField() {
        Customer customer = new CustomerProvider().createNewRandomCustomer();
        val newCustomer = new BRAXCreateNewCustomer();
        new BRAXLogin().doLogin(testData.stores[0]).clickCreateNewCustomer()
                .selectSalutation(customer)
                .setName(customer.getName())
                .setLastName(customer.getLastName())
                .setBirthday(customer.getBirthday())
                .setPostalCode(ZIP_DE)
                .setCity(customer.getCity())
                .setStreet(customer.getStreet())
                .setStreetNumber(customer.getHomeNr())
                .setAdditionalAddress(customer.getAdditionalAddress())
                .setPhone(customer.getPhone())
                .setEmail(customer.getEmail());
        String customerId = newCustomer.setMobile(customer.getPhone())
                .signForm()
                .selectEmailCheckBox()
                .selectPhoneContactAllowedCheckBox()
                .selectMessengerContactAllowedCheckBox()
                .selectLegalInfoCheckBox()
                .clickRegisterCustomer()
                .clickOkInSuccessWindowAndGetCustomerId();
        $("body").shouldHave(text(customerId));

    }

    @Test
    @Tags({@Tag("customer"), @Tag("regression"), @Tag("ui")})
    @DisplayName("open Create new customer page and check info message-> fields can't be empty")
    void checkEmptyFieldsException() {
        new Login().doLogin(testData.stores[0]).clickCreateNewCustomer()
                .clickRegisterCustomer()
                .getVerificationErrorWindow()
                .shouldHave(text(testData.APP_TEXT.customerErrorSalutation()))
                .shouldHave(text(testData.APP_TEXT.customerErrorName()))
                .shouldHave(text(testData.APP_TEXT.customerErrorLastName()))
                .shouldHave(text(testData.APP_TEXT.customerErrorLegalInfo()))
                .shouldHave(text(testData.APP_TEXT.customerErrorStreet()))
                .shouldHave(text(testData.APP_TEXT.customerErrorCity()))
                .shouldHave(text(testData.APP_TEXT.customerErrorZIP()))
                .shouldHave(text(testData.APP_TEXT.customerErrorHomeNr()))
                .shouldHave(text(testData.APP_TEXT.customerErrorSignature()))
                .shouldHave(text(testData.APP_TEXT.customerErrorEmail()));

    }

    @Test
    @Tags({@Tag("customer"), @Tag("regression"), @Tag("ui")})
    @DisplayName("open Create new customer page and check info message-> email already exists")
    void checkEmailAlreadyExists() {
        new BRAXLogin().doLogin(testData.stores[0]).clickCreateNewCustomer()
                .fillInAllMandatoryFields(testData.SEARCH_CUSTOMER)
                .selectEmailCheckBox()
                .selectLegalInfoCheckBox()
                .clickRegisterCustomer()
                .getNotificationTextElement()
                .shouldHave(text(testData.APP_TEXT.customerAlreadyExistsError()));
    }

    @Test
    @Tags({@Tag("customer"), @Tag("regression"), @Tag("ui")})
    @DisplayName("open Create new customer page and fill in all mandatory fields except: Name, City, Email, Birthday -> check exception message")
    void checkNameCityEmailEmptyMessage() {
        new BRAXLogin().doLogin(testData.stores[0]).clickCreateNewCustomer()
                .fillInAllMandatoryFieldsExpectNameCityEmailBirthday(testData.SEARCH_CUSTOMER)
                .selectEmailCheckBox()
                .selectLegalInfoCheckBox()
                .clickRegisterCustomer()
                .getVerificationErrorWindow()

                .shouldNotHave(text(testData.APP_TEXT.customerErrorSalutation()))
                .shouldNotHave(text(testData.APP_TEXT.customerErrorLastName()))
                .shouldNotHave(text(testData.APP_TEXT.customerErrorLegalInfo()))
                .shouldNotHave(text(testData.APP_TEXT.customerErrorStreet()))
                .shouldNotHave(text(testData.APP_TEXT.customerErrorZIP()))
                .shouldNotHave(text(testData.APP_TEXT.customerErrorHomeNr()))
                .shouldNotHave(text(testData.APP_TEXT.customerErrorSignature()))
                .shouldHave(text(testData.APP_TEXT.customerErrorEmail()))
                .shouldHave(text(testData.APP_TEXT.customerErrorName()))
                .shouldHave(text(testData.APP_TEXT.customerErrorCity()))
                .shouldHave(text(testData.APP_TEXT.customerErrorBirthdayEmpty()));
    }

    @Test
    @Tags({@Tag("customer"), @Tag("regression"), @Tag("ui")})
    @DisplayName("open Create new customer page and check ClearFields button -> message fields can't be empty")
    void checkClearFieldsButton() {
        new BRAXLogin().doLogin(testData.stores[0]).clickCreateNewCustomer()
                .fillInAllMandatoryFields(testData.SEARCH_CUSTOMER)
                .clickClearFields()
                .clickRegisterCustomer()
                .getVerificationErrorWindow()
                .shouldHave(text(testData.APP_TEXT.customerErrorSalutation()))
                .shouldHave(text(testData.APP_TEXT.customerErrorName()))
                .shouldHave(text(testData.APP_TEXT.customerErrorLastName()))
                .shouldHave(text(testData.APP_TEXT.customerErrorLegalInfo()))
                .shouldHave(text(testData.APP_TEXT.customerErrorStreet()))
                .shouldHave(text(testData.APP_TEXT.customerErrorCity()))
                .shouldHave(text(testData.APP_TEXT.customerErrorZIP()))
                .shouldHave(text(testData.APP_TEXT.customerErrorHomeNr()))
                .shouldHave(text(testData.APP_TEXT.customerErrorSignature()))
                .shouldHave(text(testData.APP_TEXT.customerErrorEmail()));

    }

    @Test
    @Tags({@Tag("customer"), @Tag("regression"), @Tag("ui")})
    @DisplayName("Checking the creation of a customer without NEWSLETTER subscription")
    void checkingCustomerCreationWithoutSubscription() {
        Customer customer = new CustomerProvider().createNewRandomCustomer();
        val newCustomer = new BRAXLogin().doLogin(testData.stores[0]).clickCreateNewCustomer();
        newCustomer
                .selectSalutation(customer)
                .setName(customer.getName())
                .setLastName(customer.getLastName())
                .setBirthday(customer.getBirthday())
                .setPostalCode(ZIP_DE)
                .setCity(customer.getCity())
                .setStreet(customer.getStreet())
                .setStreetNumber(customer.getHomeNr())
                .setEmail(customer.getEmail())
                .signForm()
                .selectLegalInfoCheckBox()
                .clickRegisterCustomer();
        $("body").shouldHave(text(testData.APP_TEXT.customerNusletterMessage()));
        String customerId = newCustomer.clickCreateWithoutBonus()
                .clickOkInSuccessWindowAndGetCustomerId();
        $("body").shouldHave(text(customerId));
    }

    @Test
    @Tags({@Tag("customer"), @Tag("regression"), @Tag("ui")})
    @DisplayName("checking the phone can't be empty if agreed to make calls")
    void checkPhoneNotEmptyIfSetCheckBox() {
        new BRAXLogin().doLogin(testData.stores[0]).clickCreateNewCustomer()
                .fillInAllMandatoryFields(testData.SEARCH_CUSTOMER)
                .selectPhoneContactAllowedCheckBox()
                .selectMessengerContactAllowedCheckBox()
                .selectEmailCheckBox()
                .selectLegalInfoCheckBox()
                .clickRegisterCustomer()
                .getNotificationTextElement()
                .shouldHave(text(testData.APP_TEXT.customerErrorPhone()))
                .shouldHave(text(testData.APP_TEXT.customerErrorMobile()));
    }

    @Test
    @Tags({@Tag("customer"), @Tag("regression"), @Tag("ui")})
    @DisplayName("'Agree to calls' checkbox must be set if the phone is not empty")
    void checkCheckBoxShouldBeCheckedIfPhoneSet() {
        new BRAXLogin().doLogin(testData.stores[0]).clickCreateNewCustomer()
                .fillInAllMandatoryFields(testData.SEARCH_CUSTOMER)
                .setPhone(testData.SEARCH_CUSTOMER.getPhone())
                .selectEmailCheckBox()
                .selectLegalInfoCheckBox()
                .clickRegisterCustomer()
                .getNotificationTextElement()
                .shouldHave(text(testData.APP_TEXT.customerErrorSelectCalls()));
    }

    @Test
    @Tags({@Tag("customer"), @Tag("regression"), @Tag("ui")})
    @DisplayName("check Customer Age must be more then 18")
    void checkAgeMustBeMoreThen18() {
        val newCustomer = new BRAXLogin().doLogin(testData.stores[0]).clickCreateNewCustomer();
        newCustomer.fillInAllMandatoryFields(testData.CUSTOMER_AGE_LESS_18)
                .setPostalCode("1");
        newCustomer
                .selectEmailCheckBox()
                .selectLegalInfoCheckBox()
                .clickRegisterCustomer()
                .getNotificationTextElement()
                .shouldHave(text(testData.APP_TEXT.customerErrorBirthdayEmpty()));

    }

}
