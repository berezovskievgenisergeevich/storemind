package web.components;

import io.qameta.allure.Step;
import web.pages.ShoppingCart;

import static com.codeborne.selenide.Selenide.$;

public class BackToShoppingCartWindow {

    @Step
    public ShoppingCart backToShoppingCart() {
        $("[data-testid='restart-checkout-cancel']").click();
        return new ShoppingCart();
    }

    @Step
    public ShoppingCart backToShoppingCartAndChangeDeliveryType() {
        new LoadingDialogWindow().waitLoading();
        $("[data-testid='restart-checkout-accept']").click();
        return new ShoppingCart();
    }
}
