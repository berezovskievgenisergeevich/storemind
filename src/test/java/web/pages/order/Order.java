package web.pages.order;

import io.qameta.allure.Step;
import web.pages.ShoppingCart;

import static com.codeborne.selenide.Selenide.$;

public class Order {
    String id;

    public Order(String id) {
        this.id = id;
    }

    @Step("click 'Proceed editing' button in Parked Order")
    public ShoppingCart clickProceedEditing(){
        $("[data-testid='move-order-to-next-step']").click();
        return new ShoppingCart();
    }
}
