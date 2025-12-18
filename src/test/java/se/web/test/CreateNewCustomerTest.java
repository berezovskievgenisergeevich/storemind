package se.web.test;

import io.qameta.allure.Story;
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
public class CreateNewCustomerTest extends SeTest {

    @Test
    @Tags({@Tag("customer"), @Tag("regression"), @Tag("ui")})
    @DisplayName("Create new customer with all information")
    void checkCustomerCreationWithAllField() {
        Customer customer = new CustomerProvider().createNewRandomCustomer();
        String customerId = new Login().doLogin(testData.stores[0]).clickCreateNewCustomer()
                .selectSalutation(customer)
                .setName(customer.getName())
                .setLastName(customer.getLastName())
                .setBirthday(customer.getBirthday())
                .setPostalCode(customer.getZip())
                .setCity(customer.getCity())
                .setStreet(customer.getStreet())
                .setStreetNumber(customer.getHomeNr())
                .setAdditionalAddress(customer.getAdditionalAddress())
                .setPhone(customer.getPhone())
                .setEmail(customer.getEmail())
                .signForm()
                .selectEmailCheckBox()
                .selectLegalInfoCheckBox()
                .approveEmailVerificationAndClickCreateNewCustomer()
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
        new Login().doLogin(testData.stores[0]).clickCreateNewCustomer()
                .fillInAllMandatoryFields(testData.SEARCH_CUSTOMER)
                .approveEmailVerificationAndClickCreateNewCustomer()
                .getNotificationTextElement()
                .shouldHave(text(testData.APP_TEXT.customerAlreadyExistsError()));
    }

    @Test
    @Tags({@Tag("customer"), @Tag("regression"), @Tag("ui")})
    @DisplayName("open Create new customer page and fill in all mandatory fields except: Name, City, Email -> check exception message")
    void checkNameCityEmailEmptyMessage() {
        new Login().doLogin(testData.stores[0]).clickCreateNewCustomer()
                .fillInAllMandatoryFieldsExpectNameCityEmail(testData.SEARCH_CUSTOMER).clickRegisterCustomer()
                .getVerificationErrorWindow()
                .shouldNotHave(text(testData.APP_TEXT.customerErrorSalutation()))
                .shouldHave(text(testData.APP_TEXT.customerErrorName()))
                .shouldNotHave(text(testData.APP_TEXT.customerErrorLastName()))
                .shouldNotHave(text(testData.APP_TEXT.customerErrorLegalInfo()))
                .shouldNotHave(text(testData.APP_TEXT.customerErrorStreet()))
                .shouldHave(text(testData.APP_TEXT.customerErrorCity()))
                .shouldNotHave(text(testData.APP_TEXT.customerErrorZIP()))
                .shouldNotHave(text(testData.APP_TEXT.customerErrorHomeNr()))
                .shouldNotHave(text(testData.APP_TEXT.customerErrorSignature()))
                .shouldHave(text(testData.APP_TEXT.customerErrorEmail()));
    }

    @Test
    @Tags({@Tag("customer"), @Tag("regression"), @Tag("ui")})
    @DisplayName("open Create new customer page and check ClearFields button -> message fields can't be empty")
    void checkClearFieldsButton() {
        new Login().doLogin(testData.stores[0]).clickCreateNewCustomer()
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
}
