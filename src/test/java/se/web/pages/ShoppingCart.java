package se.web.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import se.web.components.LoadingDialogWindow;
import se.web.data.model.Customer;

import static com.codeborne.selenide.Selenide.$;

public class ShoppingCart {

    SelenideElement selectStoreDeliveryButton = $("[data-testid='order-type-STORE_DELIVERY-button']");


    public ShoppingCart clickToShoppingCart() {
        $("[data-testid='bucket']").click();
        return this;
    }

    public int getShoppingCartArticleCount() {
        return Integer.parseInt($("[data-testid='bucket-amount']").text());
    }


    @Step("click 'Next' button")
    public ShoppingCart clickNext() {
        $("[data-testid='cart-process-button']").click();
        return this;
    }


    @Step("select 'Home Delivery'")
    public ShoppingCart selectHomeDelivery() {
        new LoadingDialogWindow().waitLoading();
        $("[data-testid='order-type-HOME_DELIVERY-button']").click();
        return this;
    }


    @Step("select 'Store Delivery'")
    public ShoppingCart selectStoreDelivery() {
        selectStoreDeliveryButton.click();
        return this;
    }

    public SelenideElement getStoreDeliveryChoiceElement() {
        return selectStoreDeliveryButton.parent();
    }

    @Step("click to 'Existing Customer'")
    public CustomerSearch selectExistingCustomer() {
        new LoadingDialogWindow().waitLoading();
        $("[data-testid='customer-type-EXIST-button']").click();
        return new CustomerSearch();
    }

    @Step("click to 'New Customer")
    public CreateNewCustomer selectNewCustomer() {
        $("[data-testid='customer-type-NEW-button']").click();
        new LoadingDialogWindow().waitLoading();
        return new CreateNewCustomer();
    }

    @Step("Select Home delivery type and Existing customer")
    public OrderConfirmation selectHomeDeliveryAndExistingCustomer(Customer customer) {
        clickNext();
        selectHomeDelivery();
        selectExistingCustomer();
        return new CustomerSearch().searchCustomerAndOpenOrderConfirmationPage(customer);
    }

    @Step("Select Store delivery type and Existing customer")
    public OrderConfirmation selectStoreDeliveryAndExistingCustomer(Customer customer) {
        clickNext();
        selectStoreDelivery();
        selectExistingCustomer();
        return new CustomerSearch().searchCustomerAndOpenOrderConfirmationPage(customer);
    }

    @Step("select order delivery")
    public ShoppingCart selectDelivery() {
        clickToShoppingCart();
        return clickNext();
    }

    public CreateNewCustomer selectNewCustomerAndFillInInfo(Customer customer) {
        return selectNewCustomer().
                fillInAllMandatoryFields(customer);
    }

    @Step("click 'back' in Shopping cart")
    public Cockpit clickBack() {
        $("[data-testid='go-back']").click();
        return new Cockpit();
    }


}
