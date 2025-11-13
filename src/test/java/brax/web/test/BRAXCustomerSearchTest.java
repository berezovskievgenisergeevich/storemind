package brax.web.test;

import brax.web.pages.BRAXCustomerSearch;
import brax.web.pages.BRAXLogin;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import se.web.components.LoadingDialogWindow;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

@Story("[UI] CustomerSearch")
public class BRAXCustomerSearchTest extends BraxTest {
    @Test
    @Tags({@Tag("cockpit"), @Tag("customer"), @Tag("regression"), @Tag("ui")})
    @DisplayName("check search Customer by Id")
    void searchCustomerById() {
        new BRAXLogin().doLogin(testData.stores[0])
                .clickAdvancedCustomerSearch();
        new BRAXCustomerSearch().search(testData.SEARCH_CUSTOMER.getId());
        $("[data-testid='customer-details-title']").shouldHave(text(testData.APP_TEXT.customerTitle()));
        $("body").shouldHave(text(testData.SEARCH_CUSTOMER.getId()));

    }

    @Test
    @Tags({@Tag("cockpit"), @Tag("customer"), @Tag("regression"), @Tag("ui")})
    @DisplayName("check search for not existing Customer Id")
    void searchNotExistingCustomerById() {
        new BRAXLogin().doLogin(testData.stores[0])
                .clickAdvancedCustomerSearch();
        new BRAXCustomerSearch().search(testData.SEARCH_NOT_EXISTING_CUSTOMER.getId());
        $("body").shouldHave(text(testData.APP_TEXT.advancedCustomerSearchTitle()));
        $("table").shouldHave(text(testData.APP_TEXT.customerSearchNoResult()));
    }

    @Test
    @Tags({@Tag("cockpit"), @Tag("customer"), @Tag("regression"), @Tag("ui")})
    @DisplayName("check search Customer by Name")
    void searchCustomerByName() {
        new BRAXLogin().doLogin(testData.stores[0])
                .clickAdvancedCustomerSearch();
        new BRAXCustomerSearch().search(testData.SEARCH_CUSTOMER.getName());
        $("[data-testid='customer-details-title']").shouldHave(text(testData.APP_TEXT.customerTitle()));
        $("body").shouldHave(text(testData.SEARCH_CUSTOMER.getName()));

    }

    @Test
    @Tags({@Tag("cockpit"), @Tag("customer"), @Tag("regression"), @Tag("ui")})
    @DisplayName("check search Customer by Email")
    void searchCustomerByEmail() {
        new BRAXLogin().doLogin(testData.stores[0])
                .clickAdvancedCustomerSearch();
        new BRAXCustomerSearch().search(testData.SEARCH_CUSTOMER.getEmail());
        new LoadingDialogWindow().waitLoading();
        $("body").shouldHave(text(testData.APP_TEXT.advancedCustomerSearchTitle()));
        $("table").shouldHave(text(testData.APP_TEXT.customerSearchNoResult()));
    }

    @Test
    @Tags({@Tag("cockpit"), @Tag("customer"), @Tag("regression"), @Tag("ui")})
    @DisplayName("check search by ZIP and Ort")
    void searchCustomerByZipAndOrt() {
        new BRAXLogin().doLogin(testData.stores[0])
                .clickAdvancedCustomerSearch();
        new BRAXCustomerSearch().search(testData.SEARCH_CUSTOMER.getZip() + " " + testData.SEARCH_CUSTOMER.getCity());
        new LoadingDialogWindow().waitLoading();
        $("body").shouldHave(text(testData.APP_TEXT.advancedCustomerSearchTitle()));
        $("table").shouldNotHave(text(testData.APP_TEXT.customerSearchNoResult()));
    }

    @Test
    @Tags({@Tag("cockpit"), @Tag("customer"), @Tag("regression"), @Tag("ui")})
    @DisplayName("check search customer by Scanner")
    void searchCustomerByScanner() {
        new BRAXLogin().doLogin(testData.stores[0])
                .clickAdvancedCustomerSearch()
                .searchCustomerByScanner(testData.SEARCH_CUSTOMER.getId());
        $("[data-testid='customer-details-title']").shouldHave(text(testData.APP_TEXT.customerTitle()));
        $("body").shouldHave(text(testData.SEARCH_CUSTOMER.getId()));
    }

    @Test
    @Tags({@Tag("cockpit"), @Tag("customer"), @Tag("regression"), @Tag("ui")})
    @DisplayName("check search NotExisting customer by Scanner")
    void searchNotExistingCustomerByScanner() {
        new BRAXLogin().doLogin(testData.stores[0])
                .clickAdvancedCustomerSearch()
                .searchCustomerByScanner(testData.SEARCH_NOT_EXISTING_CUSTOMER.getId());
        $("[role='dialog']").shouldHave(text(testData.APP_TEXT.scannerCustomerNotFound()));
    }
}
