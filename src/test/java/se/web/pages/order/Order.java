package se.web.pages.order;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import se.web.pages.ShoppingCart;

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

    public SelenideElement getMonogramElement() {
        return $("[data-testid='order-monogram-article']");
    }
}
