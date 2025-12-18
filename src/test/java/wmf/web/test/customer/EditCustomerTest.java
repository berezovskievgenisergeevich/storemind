package wmf.web.test.customer;

import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import se.helpers.CustomerProvider;
import se.web.data.model.Customer;
import se.web.pages.CreateNewCustomer;
import se.web.pages.Login;
import wmf.helpers.WMFCustomerProvider;
import wmf.web.pages.WMFCockpit;
import wmf.web.test.WMFTest;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

@Story("[UI] Edit Customer")
public class EditCustomerTest extends WMFTest {
    //TODO old css - should be changed by VLAD delete DISABLED
    @Test
    @Disabled
    @Tags({@Tag("customer"), @Tag("edit_customer"), @Tag("regression"), @Tag("ui")})
    @DisplayName("Edit existing customer data")
    void checkCustomerEdit() {
        Customer customer = new CustomerProvider().createNewRandomCustomer();
        new Login().doLogin(testData.stores[0]);
        new WMFCockpit().clickCustomerSearch()
                .searchCustomerByEmail(WMFCustomerProvider.editCustomer)
                .clickEdit()
                .setStreet(customer.getStreet() + " " + customer.getHomeNr())
                .setCity(customer.getCity())
                .selectLegalInfoCheckBox()
                .clickRegisterCustomer();
        $("[type='button']").parent().parent()
                .shouldHave(text(customer.getStreet()))
                .shouldHave(text(customer.getHomeNr()))
                .shouldHave(text(customer.getCity()));
    }

    @Test
    @Tags({@Tag("customer"), @Tag("edit_customer"), @Tag("regression"), @Tag("ui")})
    @DisplayName("Check that Name, Last Name and Email field disabled")
    void checkCustomerFieldsDisabled() {
        new Login().doLogin(testData.stores[0]);
        new WMFCockpit().clickCustomerSearch()
                .searchCustomerByEmail(WMFCustomerProvider.editCustomer)
                .clickEdit();
        $("[name='firstName']").shouldBe(disabled);
        $("[name='lastName']").shouldBe(disabled);
        $("[name='email']").shouldBe(disabled);
    }


    @Test
    @Tags({@Tag("customer"), @Tag("edit_customer"), @Tag("regression"), @Tag("ui")})
    @DisplayName("Check text about changing customer Address info")
    void checkTextOnEditCustomer() {
        new Login().doLogin(testData.stores[0]);
        new WMFCockpit().clickCustomerSearch()
                .searchCustomerByEmail(WMFCustomerProvider.editCustomer)
                .clickEdit();
        $("[class='MuiBox-root css-1sgc73c']").shouldHave(text(testData.APP_TEXT.customerEditAddressText()));

    }

    //TODO old css - should be changed by VLAD delete DISABLED
    @Test
    @Disabled
    @Tags({@Tag("customer"), @Tag("edit_customer"), @Tag("regression"), @Tag("ui")})
    @DisplayName("Check fields Can't be Empty Exception")
    void checkFieldsCanNotBeEmptyException() {
        new Login().doLogin(testData.stores[0]);
        new WMFCockpit().clickCustomerSearch()
                .searchCustomerByEmail(WMFCustomerProvider.editCustomer)
                .clickEdit()
                .selectLegalInfoCheckBox()
                .cleanUpField($("[name='street']"))
                .cleanUpField($("[name='postalCode']"))
                .cleanUpField($("[name='city']"));
        //   .clickRegisterCustomer();
        $(byText("Speichern")).click();
        new CreateNewCustomer().getVerificationErrorWindow()
                .shouldHave(text(testData.APP_TEXT.customerErrorLegalInfo()))
                .shouldHave(text(testData.APP_TEXT.customerErrorStreet()))
                .shouldHave(text(testData.APP_TEXT.customerErrorCity()))
                .shouldHave(text(testData.APP_TEXT.customerErrorZIP()));


    }
}
