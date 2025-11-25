package brax.web.pages;

import io.qameta.allure.Step;
import se.web.components.LoadingDialogWindow;
import se.web.data.model.Customer;
import se.web.pages.CustomerSearch;
import se.web.pages.OrderConfirmation;
import se.web.pages.ShoppingCart;

import static com.codeborne.selenide.Selenide.$;

public class BRAXShoppingCart extends ShoppingCart {

    @Step("click 'Next' button")
    public BRAXShoppingCart clickNext() {
        $("[data-testid='process-cart-button']").click();
        return this;
    }

    @Step("click to 'Existing Customer'")
    public BRAXCustomerSearch selectExistingCustomer() {
        new LoadingDialogWindow().waitLoading();
        $("[data-testid='customer-type-EXIST-button']").click();
        return new BRAXCustomerSearch();
    }

    @Step("Select Home delivery type and Existing customer")
    public BRAXOrderConfirmation selectHomeDeliveryAndExistingCustomer(Customer customer) {
        clickNext();
        selectHomeDelivery();
        selectExistingCustomer();
        return new BRAXCustomerSearch().searchCustomerAndOpenOrderConfirmationPage(customer);
    }

    public BRAXShoppingCart clickToShoppingCart() {
        $("[data-testid='bucket']").click();
        return this;
    }

    @Step("Select Store delivery type and Existing customer")
    public BRAXOrderConfirmation selectStoreDeliveryAndExistingCustomer(Customer customer) {
        clickNext();
        selectStoreDelivery();
        selectExistingCustomer();
        return new BRAXCustomerSearch().searchCustomerAndOpenOrderConfirmationPage(customer);
    }
}
