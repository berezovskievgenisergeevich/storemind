package wmf.web.test;

import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import se.web.pages.Login;
import wmf.helpers.WMFCustomerProvider;
import wmf.web.pages.WMFCockpit;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Story("[UI] Cockpit - Customer section")
public class CustomerSectionTest extends WMFTest {
    @Test
    @Tags({@Tag("cockpit"), @Tag("customer"), @Tag("regression"), @Tag("ui")})
    @DisplayName("check search Customer by Id")
    void searchCustomerById() {
        new Login().doLogin(testData.stores[0]);
        new WMFCockpit().clickCustomerSearch()
                .searchCustomerByNo(WMFCustomerProvider.editCustomer);
        $("[data-testid='page-title']").shouldHave(text(testData.APP_TEXT.customerTitle()));
        $("body").shouldHave(text(testData.SEARCH_CUSTOMER.getId()));

    }

    @Test
    @Tags({@Tag("cockpit"), @Tag("customer"), @Tag("regression"), @Tag("ui")})
    @DisplayName("check search for not existing Customer Id")
    void searchNotExistingCustomerById() {
        new Login().doLogin(testData.stores[0]);
        new WMFCockpit().clickCustomerSearch()
                .searchCustomerByNo(testData.SEARCH_NOT_EXISTING_CUSTOMER);
        $("body").shouldHave(text(testData.APP_TEXT.advancedCustomerSearchTitle()));
        $("table").shouldHave(text(testData.APP_TEXT.customerSearchNoResult()));

    }

    @Test
    @Tags({@Tag("cockpit"), @Tag("customer"), @Tag("regression"), @Tag("ui")})
    @DisplayName("check search Customer by Email")
    void searchCustomerByEmail() {
        new Login().doLogin(testData.stores[0]);
        new WMFCockpit().clickCustomerSearch()
                .searchCustomerByEmail(testData.SEARCH_CUSTOMER);
        $("[data-testid='page-title']").shouldHave(text(testData.APP_TEXT.customerTitle()));
        $("body").shouldHave(text(testData.SEARCH_CUSTOMER.getEmail()));
    }

    @Test
    @Tags({@Tag("cockpit"), @Tag("customer"), @Tag("regression"), @Tag("ui")})
    @DisplayName("check search for not existing Customer Email")
    void searchNotExistingCustomerByEmail() {
        new Login().doLogin(testData.stores[0]);
        new WMFCockpit().clickCustomerSearch()
                .searchCustomerByEmail(testData.SEARCH_NOT_EXISTING_CUSTOMER);
        $("body").shouldHave(text(testData.APP_TEXT.advancedCustomerSearchTitle()));
        $("table").shouldHave(text(testData.APP_TEXT.customerSearchNoResult()));

    }

    @Test
    @Tags({@Tag("cockpit"), @Tag("customer"), @Tag("regression"), @Tag("ui")})
    @DisplayName("check search with multiple results")
    void searchWithMultipleResults() {
        new Login().doLogin(testData.stores[0]);
        new WMFCockpit().clickCustomerSearch()
                .enterName(testData.SEARCH_MULTIPLE_CUSTOMERS.getName())
                .enterLastName(testData.SEARCH_MULTIPLE_CUSTOMERS.getLastName())
                .pressEnter();
        $("body").shouldHave(text(testData.APP_TEXT.advancedCustomerSearchTitle()));
        $("body").shouldHave(text(testData.SEARCH_MULTIPLE_CUSTOMERS.getName()));
        $$("[data-testid='customer-row']").forEach(it -> it.shouldHave(text(testData.SEARCH_MULTIPLE_CUSTOMERS.getLastName())));
    }

    @Test
    @Tags({@Tag("cockpit"), @Tag("customer"), @Tag("regression"), @Tag("ui")})
    @DisplayName("check search customer by Scanner")
    void searchCustomerByScanner() {
        new Login().doLogin(testData.stores[0]);
        new WMFCockpit().clickCustomerSearch().searchCustomerByScanner(testData.SEARCH_CUSTOMER.getId());
        $("body").shouldHave(text(testData.APP_TEXT.advancedCustomerSearchTitle()));
        $("[data-testid='page-title']").shouldHave(text(testData.APP_TEXT.customerTitle()));
        $("body").shouldHave(text(testData.SEARCH_CUSTOMER.getId()));
    }

    @Test
    @Tags({@Tag("cockpit"), @Tag("customer"), @Tag("regression"), @Tag("ui")})
    @DisplayName("check search NotExisting customer by Scanner")
    void searchNotExistingCustomerByScanner() {
        new Login().doLogin(testData.stores[0]);
        new WMFCockpit().clickCustomerSearch().searchCustomerByScanner(testData.SEARCH_NOT_EXISTING_CUSTOMER.getId());
        $("[role='dialog']").shouldHave(text(testData.APP_TEXT.scannerCustomerNotFound()));
    }

    @Test
    @Tags({@Tag("cockpit"), @Tag("customer"), @Tag("regression"), @Tag("ui")})
    @DisplayName("check Create new customer button")
    void checkCreateNewCustomerButton() {
        new Login().doLogin(testData.stores[0]);
        new WMFCockpit()
                .clickCreateNewCustomerAndLoginWithPopUp(projectConfig.createCustomerLogin(), projectConfig.createCustomerPass())
                .getPageTitle().shouldHave(text(testData.APP_TEXT.createNewCustomerTitle()));

    }

    @Test
    @Tags({@Tag("cockpit"), @Tag("customer"), @Tag("regression"), @Tag("ui")})
    @DisplayName("check Advanced customer search button")
    void checkAdvancedCustomerSearch() {
        new Login().doLogin(testData.stores[0]);
        new WMFCockpit().clickCustomerSearch();
        $("body").shouldHave(text(testData.APP_TEXT.advancedCustomerSearchTitle()));
    }
}
