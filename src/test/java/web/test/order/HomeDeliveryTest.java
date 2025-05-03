package web.test.order;

import io.qameta.allure.Story;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import web.components.OrderItem;
import web.pages.*;
import web.test.BaseTest;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

@Story("[UI] Home delivery order")
public class HomeDeliveryTest extends BaseTest {
    @Test
    @Tags({@Tag("order"), @Tag("home_order"), @Tag("regression"), @Tag("ui")})
    @DisplayName("create new Home Delivery order with Existing customer and 1 article and Prepayment(Vorkasse)")
    void createNewHomeDeliveryOrderExistingCustomer() {
        String orderId = loginAddArticleSelectCustomer()
                .createPrepaymentOrder();

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
                clickProceedEditing().clickNext();

        new OrderConfirmation()
                .changeOrderTypeToStore(testData.SEARCH_CUSTOMER)
                .createParkedOrder();

        cockpit.openInCreationTab();
        homeParkedOrder.getOrderType().shouldHave(text(testData.APP_TEXT.storeOrderTypeName()));

    }

    @Test
    @Tags({@Tag("order"), @Tag("home_order"), @Tag("store_order"), @Tag("regression"), @Tag("ui")})
    @DisplayName("create new Home Delivery Parked order and change delivery type to Store(order created)")
    void createNewHomeOrderAndChangeTypeToStore() throws Exception {
        val cockpit = new Cockpit();
        String orderId = loginAddArticleSelectCustomer()
                .createParkedOrder();

        OrderItem homeParkedOrder = cockpit.openInCreationTab()
                .getOrder(orderId);

        homeParkedOrder.getOrderType().shouldHave(text(testData.APP_TEXT.homeOrderTypeName()));

        homeParkedOrder.openOrder().
                clickProceedEditing().clickNext();

        new OrderConfirmation()
                .changeOrderTypeToStore(testData.SEARCH_CUSTOMER)
                .createS2SOrder();

        cockpit.openWaitingForDeliveryTab();
        homeParkedOrder.getOrderType().shouldHave(text(testData.APP_TEXT.storeOrderTypeName()));

    }

    @Test
    @Tags({@Tag("order"), @Tag("home_order"), @Tag("regression"), @Tag("ui")})
    @DisplayName("create new Home Delivery order with Duplicate customer and 1 article and Prepayment(Vorkasse)")
    void createNewHomeDeliveryOrderWithDuplicateCustomer() {
        new Login().doLogin(testData.stores[0])
                .addArticleToShoppingCartByEAN(testData.SEARCH_ARTICLE_EAN);
        CreateNewCustomer customer = new ShoppingCart()
                .selectDelivery()
                .selectHomeDelivery()
                .selectNewCustomerAndFillInInfo(testData.SEARCH_CUSTOMER)
                .approveEmailVerificationAndClickCreateNewCustomer();

        customer.getNotificationTextElement().shouldHave(text(testData.APP_TEXT.orderCreationCustomerAlreadyExists()));

        String orderId = customer.continueWithExistingCustomer()
                .createPrepaymentOrder();

        new Cockpit().openFinishedTab();
        $("body").shouldHave(text(orderId));
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
        OrderConfirmation orderConfirmation = new OrderConfirmation();
        String orderId = loginAddArticleSelectCustomer()
                .createParkedOrder();

        cockpit.openInCreationTab()
                .getOrder(orderId)
                .openOrder().
                clickProceedEditing().clickNext();

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
        String orderId = new OrderConfirmation().
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
        new OrderConfirmation().deleteOrder();
        $("body").shouldHave(text(testData.APP_TEXT.cockpitTitle()));
    }

    @Test
    @Tags({@Tag("order"), @Tag("home_order"), @Tag("monogram"), @Tag("regression"), @Tag("ui")})
    @DisplayName("check monogram forbidden letters combination")
    void checkMonogramRestrictionText() {
        new Login().doLogin(testData.stores[0])
                .searchArticleByScanner(testData.ARTICLE_WITH_MONOGRAM_EAN)
                .addMonogramWithForbiddenOptions()
                .getMonogramRestrictionWindow()
                .shouldHave(text(testData.APP_TEXT.monogramRestrictionsText()));
    }

    @Test
    @Tags({@Tag("order"), @Tag("home_order"), @Tag("monogram"), @Tag("regression"), @Tag("ui")})
    @DisplayName("check monogram Letters added to Article Info Window")
    void checkMonogramTextAddedToArticleInfoWindow() {
        new Login().doLogin(testData.stores[0])
                .searchArticleByScanner(testData.ARTICLE_WITH_MONOGRAM_EAN)
                .addMonogramToArticle(testData.MONOGRAM)
                .editMonogramButton.shouldHave(text(testData.MONOGRAM));
    }

    @Test
    @Tags({@Tag("order"), @Tag("home_order"), @Tag("monogram"), @Tag("regression"), @Tag("ui")})
    @DisplayName("check Create Order With Monogram")
    void checkCreateOrderWithMonogram() throws Exception {
        new Login().doLogin(testData.stores[0])
                .searchArticleByScanner(testData.ARTICLE_WITH_MONOGRAM_EAN)
                .addMonogramToArticle(testData.MONOGRAM)
                .clickAddArticleToShoppingCart();
        String orderId = new ShoppingCart().clickToShoppingCart()
                .selectHomeDeliveryAndExistingCustomer(testData.SEARCH_CUSTOMER)
                .createPrepaymentOrder();

        new Cockpit().openFinishedTab().getOrder(orderId)
                .openOrder().getMonogramElement()
                .shouldHave(text(testData.MONOGRAM));

    }


    OrderConfirmation loginAddArticleSelectCustomer() {
        new Login().doLogin(testData.stores[0])
                .addArticleToShoppingCartByEAN(testData.SEARCH_ARTICLE_EAN);
        return new ShoppingCart().clickToShoppingCart()
                .selectHomeDeliveryAndExistingCustomer(testData.SEARCH_CUSTOMER);
    }


}
