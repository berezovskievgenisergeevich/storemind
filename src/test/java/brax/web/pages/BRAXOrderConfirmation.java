package brax.web.pages;

import io.qameta.allure.Step;
import se.web.components.LoadingDialogWindow;
import se.web.data.model.Customer;
import se.web.pages.OrderConfirmation;

import static com.codeborne.selenide.Selenide.$;

public class BRAXOrderConfirmation extends OrderConfirmation {
    @Step("create new Home delivery order")
    public String createHomeOrder() {
        clickCreateNewOrder();
        return getOrderId();
    }

    @Step("click to Create new order button")
    public BRAXOrderConfirmation clickCreateNewOrder() {
        $("[data-testid='checkout-confirm-button']").hover().click();
        new LoadingDialogWindow().waitLoading();
        return this;
    }

    @Step("create Parked order in Order Confirmation page")
    public String createParkedOrderParkedButton() {
        $("[data-testid='checkout-park-button']").click();
        return getOrderId();
    }

    @Step("change order type to Store")
    public BRAXOrderConfirmation changeOrderTypeToStore(Customer customer) {
        clickBackToShoppingCart()
                .backToShoppingCartAndChangeDeliveryType();
        return new BRAXShoppingCart().selectStoreDeliveryAndExistingCustomer(customer);
    }

    @Step("change order type to Store")
    public BRAXOrderConfirmation changeOrderTypeToHome(Customer customer) {
        clickBackToShoppingCart()
                .backToShoppingCartAndChangeDeliveryType();
        return new BRAXShoppingCart().selectHomeDeliveryAndExistingCustomer(customer);
    }

    @Step("create new Store delivery order")
    public String createS2SOrder() {
        clickCreateNewOrder();
        return getOrderId();
    }

}
