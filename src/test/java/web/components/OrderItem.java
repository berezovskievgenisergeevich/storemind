package web.components;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import web.pages.order.Order;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.interactable;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class OrderItem {

    private String id;

    public OrderItem(String id) {
        this.id = id;
    }

    public ElementsCollection getAllOrders() {
        $("[data-testid='order-row']").should(exist).shouldBe(interactable);
        return $$("[data-testid='order-row']");
    }

    public SelenideElement getOrder() throws Exception {
        for (SelenideElement order : getAllOrders()) {
            if (order.$("[data-testid='order-id']").text().equals(id))
                return order;
        }
        throw new Exception("order with id:" + id + " not found");
    }

    public SelenideElement getOrderType() throws Exception {
        return getOrder().$("[data-testid='order-type']");
    }

    public String getOrderStatus() throws Exception {
        return getOrder().$("[data-testid='order-status']").text();
    }

    public Order openOrder() throws Exception {
        getOrder().click();
        return new Order(id);
    }

}
