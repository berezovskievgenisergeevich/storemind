package se.web.pages;

import io.qameta.allure.Step;
import se.web.components.ArticleInfoWindow;
import se.web.components.LoadingDialogWindow;
import se.web.components.Scanner;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class Cockpit {


    @Step("Open Menu and click Change Store button")
    public Login logOut() {
        $("[data-testid='MenuIcon']").click();
        $("[role='dialog']").$("[data-testid='change-store']").click();
        sleep(500);
        return new Login();
    }

    @Step("Search customer {customer}")
    public CustomerInfo searchCustomer(String customer) {
        $("[data-testid='customer-search-input']").val(customer).pressEnter();
        new LoadingDialogWindow().waitLoading();
        return new CustomerInfo();
    }


    @Step("Search Customer By Scanner. Customer Id is: {id}")
    public CustomerInfo searchCustomerByScanner(String id) {
        $("[data-testid='customer-scanner']").click();
        new Scanner().search(id);
        return new CustomerInfo();
    }


    @Step("click Create new Customer")
    public CreateNewCustomer clickCreateNewCustomer() {
        $("[data-testid='customer-registration']").click();
        new LoadingDialogWindow().waitLoading();
        return new CreateNewCustomer();
    }


    @Step("click advanced customer search")
    public CustomerSearch clickAdvancedCustomerSearch() {
        $("[data-testid='customer-search']").click();
        return new CustomerSearch();
    }


    @Step("search article, value: {searchString}")
    public Catalog searchArticle(String searchString) {
        $("[data-testid='article-search-input']").val(searchString).pressEnter();
        return new Catalog();
    }

    @Step("open scanner")
    public Scanner openScanner() {
        $("[data-testid='scanner-icon']").click();
        return new Scanner();
    }

    @Step("search article by Scanner and Ean, value: {searchString}")
    public ArticleInfoWindow searchArticleByScanner(String searchString) {
        openScanner().search(searchString);
        $("[data-testid='article-modal']").shouldHave(text(searchString));
        return new ArticleInfoWindow();
    }

    @Step("Add article to Shopping Cart By EAN {ean}")
    public Cockpit addArticleToShoppingCartByEAN(String ean) {
        searchArticleByScanner(ean)
                .clickAddArticleToShoppingCart();
        return this;
    }

    @Step("Open Processes page on 'In Creation' tab")
    public Processes openInCreationTab() {
        $("[data-testid='in-creation-orders-box']").click();
        return new Processes();
    }

    @Step("Open Processes page on 'Waiting for Delivery' tab")
    public Processes openWaitingForDeliveryTab() {
        $("[data-testid='delivery-orders-box']").click();
        return new Processes();
    }

    @Step("Open Processes page on 'Waiting for Customer' tab")
    public Processes openWaitingForCustomerTab() {
        $("[data-testid='waiting-customer-orders-box']").click();
        return new Processes();
    }

    @Step("Open Processes page on 'Finished' tab")
    public Processes openFinishedTab() {
        $("[data-testid='finished-orders-box']").click();
        return new Processes();
    }


    @Step("open 'Current Requests' and refresh data")
    public CurrentRequests openCurrentRequestsAndRefreshData() {
        $("[data-testid='requests-box']").click();
        $("[data-testid='RefreshIcon']").click();
        new LoadingDialogWindow().waitLoading();
        return new CurrentRequests();
    }
}
