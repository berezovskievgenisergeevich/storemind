package se.web.pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import se.web.components.ActiveProcessWindow;
import se.web.components.BackToShoppingCartWindow;
import se.web.components.LoadingDialogWindow;
import se.web.data.model.Customer;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class OrderConfirmation {


    @Step("check Check-Box with legal information")
    public OrderConfirmation checkLegalInfoCheckBox() {
        $("[data-testid='terms-and-conditions-checkbox']").shouldBe(Condition.interactable).click();
        return this;
    }


    @Step("click to Create new order button")
    public OrderConfirmation clickCreateNewOrder() {
        $("[data-testid='create-order-button']").hover().click();
        new LoadingDialogWindow().waitLoading();
        return this;
    }


    @Step("select Prepayment")
    public OrderConfirmation selectPrepayment() {
        $("[data-testid='prepayment-button']").shouldBe(Condition.interactable).click();
        return this;
    }

    @Step("get created Order Id and close 'Order Created Successfully' window")
    public String getOrderId() {
        new LoadingDialogWindow().waitLoading();
        String orderId = $("[data-testid='success-checkout']").should(exist).getAttribute("data-id");
        $("[data-testid='finish-success-checkout']")
                .should(exist).shouldBe(interactable).shouldBe(enabled).click();
        return orderId;
    }

    @Step("create new Home delivery order with Prepayment(Vorkasse)")
    public String createPrepaymentOrder() {
        checkLegalInfoCheckBox();
        clickCreateNewOrder();
        selectPrepayment();
        return getOrderId();
    }

    @Step("create new Home delivery order with Prepayment(Vorkasse)")
    public String createS2SOrder() {
        checkLegalInfoCheckBox();
        clickCreateNewOrder();
        return getOrderId();
    }

    @Step("create Parked order in Order Confirmation page")
    public String createParkedOrder() {
        clickBackButton().
                clickParkedOrder();
        return getOrderId();
    }

    @Step("delete Order")
    public Cockpit deleteOrder() {
        return clickBackButton().
                clickDeleteOrder();
    }

    @Step("click Back button")
    public ActiveProcessWindow clickBackButton() {
        new LoadingDialogWindow().waitLoading();
        $("[data-testid='go-back']").click();
        return new ActiveProcessWindow();
    }

    //TODO change css -> ticket
    @Step("click to 'Back to Catalog'")
    public BackToShoppingCartWindow clickBackToShoppingCart() {
        new LoadingDialogWindow().waitLoading();
        $(byText("Bestellung ändern")).click();
        return new BackToShoppingCartWindow();
    }

    @Step("change order type to Store")
    public OrderConfirmation changeOrderTypeToStore(Customer customer) {
        return clickBackToShoppingCart()
                .backToShoppingCartAndChangeDeliveryType()
                .selectStoreDeliveryAndExistingCustomer(customer);
    }

    @Step("change order type to Home")
    public OrderConfirmation changeOrderTypeToHome(Customer customer) {
        return clickBackToShoppingCart()
                .backToShoppingCartAndChangeDeliveryType()
                .selectHomeDeliveryAndExistingCustomer(customer);
    }

    @Step("close 'Order has been deleted successfully' window")
    public Cockpit closeOrderDeletedDialogWindow() {
        new LoadingDialogWindow().waitLoading();
        $("[role='dialog'] [type='button']").click();
        return new Cockpit();
    }

}
