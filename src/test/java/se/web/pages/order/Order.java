package se.web.pages.order;

import brax.web.pages.BRAXShoppingCart;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import se.web.components.ActiveProcessDeleteWindow;
import se.web.pages.ShoppingCart;

import static com.codeborne.selenide.Selenide.$;

public class Order {
    String id;

    public Order(String id) {
        this.id = id;
    }

    @Step("click 'Proceed editing' button in Parked Order")
    public ShoppingCart clickProceedEditing() {
        $("[data-testid='move-order-to-next-step']").click();
        return new ShoppingCart();
    }

    @Step("click 'Continue Order' button in Parked Order")
    public BRAXShoppingCart clickContinueOrder() {
        $("[data-testid='continue-order-button']").click();
        return new BRAXShoppingCart();
    }

    @Step("click to Delete order icon")
    public ActiveProcessDeleteWindow clickToDeleteIcon() {
        $("[data-testid='DeleteOutlineIcon']").click();
        return new ActiveProcessDeleteWindow();
    }

    public SelenideElement getMonogramElement() {
        return $("[data-testid='order-monogram-article']");
    }
}
