package se.web.components;

import io.qameta.allure.Step;
import se.web.pages.ShoppingCart;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.interactable;
import static com.codeborne.selenide.Selenide.$;

public class BackToShoppingCartWindow {

    private final LoadingDialogWindow loadingDialogWindow = new LoadingDialogWindow();

    @Step
    public ShoppingCart backToShoppingCart() {
        loadingDialogWindow.waitLoading();
        $("[data-testid='restart-checkout-cancel']")
                .hover().should(exist)
                .shouldBe(interactable).click();
        return new ShoppingCart();
    }

    @Step
    public ShoppingCart backToShoppingCartAndChangeDeliveryType() {
        loadingDialogWindow.waitLoading();
        $("[data-testid='restart-checkout-accept']").hover().should(exist)
                .shouldBe(interactable).click();
        return new ShoppingCart();
    }
}
