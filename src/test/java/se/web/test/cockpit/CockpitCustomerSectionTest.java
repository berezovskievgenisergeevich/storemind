package se.web.test.cockpit;

import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import se.web.pages.Login;
import se.web.test.BaseTest;
import se.web.test.SeTest;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Story("[UI] Cockpit - Customer section")
public class CockpitCustomerSectionTest extends SeTest {

    @Test
    @Tags({@Tag("cockpit"), @Tag("customer"), @Tag("regression"), @Tag("ui")})
    @DisplayName("check search Customer by Id")
    void searchCustomerById() {
        new Login().doLogin(testData.stores[0])
                .searchCustomer(testData.SEARCH_CUSTOMER.getId());
        $("[data-testid='customer-details-title']").shouldHave(text(testData.APP_TEXT.customerTitle()));
        $("body").shouldHave(text(testData.SEARCH_CUSTOMER.getId()));

    }

    @Test
    @Tags({@Tag("cockpit"), @Tag("customer"), @Tag("regression"), @Tag("ui")})
    @DisplayName("check search for not existing Customer Id")
    void searchNotExistingCustomerById() {
        new Login().doLogin(testData.stores[0])
                .searchCustomer(testData.SEARCH_NOT_EXISTING_CUSTOMER.getId());
        $("body").shouldHave(text(testData.APP_TEXT.advancedCustomerSearchTitle()));
        $("table").shouldHave(text(testData.APP_TEXT.customerSearchNoResult()));

    }

    @Test
    @Tags({@Tag("cockpit"), @Tag("customer"), @Tag("regression"), @Tag("ui")})
    @DisplayName("check search Customer by Email")
    void searchCustomerByEmail() {
        new Login().doLogin(testData.stores[0])
                .searchCustomer(testData.SEARCH_CUSTOMER.getEmail());
        $("[data-testid='customer-details-title']").shouldHave(text(testData.APP_TEXT.customerTitle()));
        $("body").shouldHave(text(testData.SEARCH_CUSTOMER.getEmail()));
    }

    @Test
    @Tags({@Tag("cockpit"), @Tag("customer"), @Tag("regression"), @Tag("ui")})
    @DisplayName("check search for not existing Customer Email")
    void searchNotExistingCustomerByEmail() {
        new Login().doLogin(testData.stores[0])
                .searchCustomer(testData.SEARCH_NOT_EXISTING_CUSTOMER.getEmail());
        $("body").shouldHave(text(testData.APP_TEXT.advancedCustomerSearchTitle()));
        $("table").shouldHave(text(testData.APP_TEXT.customerSearchNoResult()));

    }

    @Test
    @Tags({@Tag("cockpit"), @Tag("customer"), @Tag("regression"), @Tag("ui")})
    @DisplayName("check search Customer by Surname")
    void searchCustomerBySurname() {
        new Login().doLogin(testData.stores[0])
                .searchCustomer(testData.SEARCH_CUSTOMER.getLastName());
        $("[data-testid='customer-details-title']").shouldHave(text(testData.APP_TEXT.customerTitle()));
        $("body").shouldHave(text(testData.SEARCH_CUSTOMER.getLastName()));
    }

    @Test
    @Tags({@Tag("cockpit"), @Tag("customer"), @Tag("regression"), @Tag("ui")})
    @DisplayName("check search for not existing Customer Surname")
    void searchNotExistingCustomerBySurname() {
        new Login().doLogin(testData.stores[0])
                .searchCustomer(testData.SEARCH_NOT_EXISTING_CUSTOMER.getLastName());
        $("body").shouldHave(text(testData.APP_TEXT.advancedCustomerSearchTitle()));
        $("table").shouldHave(text(testData.APP_TEXT.customerSearchNoResult()));

    }

    @Test
    @Tags({@Tag("cockpit"), @Tag("customer"), @Tag("regression"), @Tag("ui")})
    @DisplayName("check search with multiple results")
    void searchWithMultipleResults() {
        new Login().doLogin(testData.stores[0])
                .searchCustomer(testData.SEARCH_MULTIPLE_CUSTOMERS);
        $("body").shouldHave(text(testData.APP_TEXT.advancedCustomerSearchTitle()));
        $("body").shouldHave(text(testData.SEARCH_MULTIPLE_CUSTOMERS));
        $$("table tbody tr").forEach(it -> it.shouldHave(text(testData.SEARCH_MULTIPLE_CUSTOMERS)));
    }

    @Test
    @Tags({@Tag("cockpit"), @Tag("customer"), @Tag("regression"), @Tag("ui")})
    @DisplayName("check search customer by Scanner")
    void searchCustomerByScanner() {
        new Login().doLogin(testData.stores[0])
                .searchCustomerByScanner(testData.SEARCH_CUSTOMER.getId());
        $("body").shouldHave(text(testData.APP_TEXT.advancedCustomerSearchTitle()));
        $("[data-testid='customer-details-title']").shouldHave(text(testData.APP_TEXT.customerTitle()));
        $("body").shouldHave(text(testData.SEARCH_CUSTOMER.getId()));
    }

    @Test
    @Tags({@Tag("cockpit"), @Tag("customer"), @Tag("regression"), @Tag("ui")})
    @DisplayName("check search NotExisting customer by Scanner")
    void searchNotExistingCustomerByScanner() {
        new Login().doLogin(testData.stores[0])
                .searchCustomerByScanner(testData.SEARCH_NOT_EXISTING_CUSTOMER.getId());
        $("[role='dialog']").shouldHave(text(testData.APP_TEXT.scannerCustomerNotFound()));
    }

    @Test
    @Tags({@Tag("cockpit"), @Tag("customer"), @Tag("regression"), @Tag("ui")})
    @DisplayName("check Create new customer button")
    void checkCreateNewCustomerButton() {
        new Login().doLogin(testData.stores[0])
                .clickCreateNewCustomer();
        $("[data-testid='create-customer-title']").shouldHave(text(testData.APP_TEXT.createNewCustomerTitle()));
    }

    @Test
    @Tags({@Tag("cockpit"), @Tag("customer"), @Tag("regression"), @Tag("ui")})
    @DisplayName("check Advanced customer search button")
    void checkAdvancedCustomerSearch() {
        new Login().doLogin(testData.stores[0])
                .clickAdvancedCustomerSearch();
        $("body").shouldHave(text(testData.APP_TEXT.advancedCustomerSearchTitle()));
    }


}
