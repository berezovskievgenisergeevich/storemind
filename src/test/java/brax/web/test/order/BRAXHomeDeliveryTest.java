package brax.web.test.order;

import brax.web.pages.BRAXOrderConfirmation;
import brax.web.pages.BRAXShoppingCart;
import brax.web.test.BraxTest;
import io.qameta.allure.Story;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import se.web.components.OrderItem;
import se.web.pages.Cockpit;
import se.web.pages.Login;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

@Story("[UI] Home delivery order")
public class BRAXHomeDeliveryTest extends BraxTest {

    @Test
    @Tags({@Tag("order"), @Tag("home_order"), @Tag("regression"), @Tag("ui")})
    @DisplayName("create new Home Delivery order")
    void createNewHomeDeliveryOrderExistingCustomer() {
        String orderId = loginAddArticleSelectCustomer()
                .createHomeOrder();
        new Cockpit().openFinishedTab();
        $("body").shouldHave(text(orderId));
    }

    @Test
    @Tags({@Tag("order"), @Tag("home_order"), @Tag("regression"), @Tag("ui")})
    @DisplayName("create new Home Delivery Parked order with Existing customer and 1 article")
    void createNewHomeDeliveryParkedOrder() {
        String orderId = loginAddArticleSelectCustomer()
                .createParkedOrder();

        new Cockpit().openInCreationTab();
        $("body").shouldHave(text(orderId));
    }

    @Test
    @Tags({@Tag("order"), @Tag("home_order"), @Tag("regression"), @Tag("ui")})
    @DisplayName("create new Home Delivery Parked order used 'Parked Order' button")
    void createNewHomeDeliveryParkedOrderParkedButton() {
        String orderId = loginAddArticleSelectCustomer()
                .createParkedOrderParkedButton();

        new Cockpit().openInCreationTab();
        $("body").shouldHave(text(orderId));
    }

    @Test
    @Tags({@Tag("order"), @Tag("home_order"), @Tag("store_order"), @Tag("regression"), @Tag("ui")})
    @DisplayName("create new Home Delivery Parked order and change delivery type to Store Parked")
    void createNewHomeOrderAndChangeTypeToStoreParked() throws Exception {
        val cockpit = new Cockpit();
        String orderId = loginAddArticleSelectCustomer()
                .createParkedOrder();

        OrderItem homeParkedOrder = cockpit.openInCreationTab()
                .getOrder(orderId);

        homeParkedOrder.getOrderType().shouldHave(text(testData.APP_TEXT.homeOrderTypeName()));

        homeParkedOrder.openOrder().
                clickContinueOrder().clickNext();

        new BRAXOrderConfirmation()
                .changeOrderTypeToStore(testData.SEARCH_CUSTOMER)
                .createParkedOrder();

        cockpit.openInCreationTab();
        homeParkedOrder.getOrderType().shouldHave(text(testData.APP_TEXT.storeOrderTypeName()));

    }

    @Test
    @Tags({@Tag("order"), @Tag("home_order"), @Tag("regression"), @Tag("ui")})
    @DisplayName("delete Home Delivery order in Checkout")
    void deleteNewHomeDeliveryOrderInCheckout() {
        loginAddArticleSelectCustomer()
                .deleteOrder();
        $("body").shouldHave(text(testData.APP_TEXT.cockpitTitle()));
    }

    @Test
    @Tags({@Tag("order"), @Tag("home_order"), @Tag("regression"), @Tag("ui")})
    @DisplayName("delete new Home Delivery Parked order in Checkout")
    void deleteNewHomeDeliveryParkedOrderInCheckout() throws Exception {
        Cockpit cockpit = new Cockpit();
        BRAXOrderConfirmation orderConfirmation = new BRAXOrderConfirmation();
        String orderId = loginAddArticleSelectCustomer()
                .createParkedOrder();

        cockpit.openInCreationTab()
                .getOrder(orderId)
                .openOrder().
                clickContinueOrder().clickNext();

        orderConfirmation.deleteOrder();
        orderConfirmation.closeOrderDeletedDialogWindow();
        cockpit.openInCreationTab();
        $("body").shouldNotHave(text(orderId));
    }

    @Test
    @Tags({@Tag("order"), @Tag("home_order"), @Tag("regression"), @Tag("ui")})
    @DisplayName("create new Home Delivery Parked order in Shopping cart")
    void createNewHomeDeliveryParkedOrderInShoppingCart() {
        loginAddArticleSelectCustomer()
                .clickBackToShoppingCart()
                .backToShoppingCart();
        String orderId = new BRAXOrderConfirmation().
                createParkedOrder();
        new Cockpit().openInCreationTab();
        $("body").shouldHave(text(orderId));
    }

    @Test
    @Tags({@Tag("order"), @Tag("home_order"), @Tag("regression"), @Tag("ui")})
    @DisplayName("delete new Home Delivery order in Shopping cart")
    void deleteNewHomeDeliveryParkedOrderInShoppingCart() {
        loginAddArticleSelectCustomer()
                .clickBackToShoppingCart()
                .backToShoppingCart();
        new BRAXOrderConfirmation().deleteOrder();
        $("body").shouldHave(text(testData.APP_TEXT.cockpitTitle()));
    }

    BRAXOrderConfirmation loginAddArticleSelectCustomer() {
        new Login().doLogin(testData.stores[0])
                .addArticleToShoppingCartByEAN(testData.HOME_ARTICLE_EAN);
        return new BRAXShoppingCart().clickToShoppingCart()
                .selectHomeDeliveryAndExistingCustomer(testData.SEARCH_CUSTOMER);
    }


}
