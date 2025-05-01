package web.test.order;

import io.qameta.allure.Story;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import web.components.OrderItem;
import web.data.model.Store;
import web.pages.*;
import web.test.BaseTest;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

@Story("[UI] Store delivery order")
public class StoreDeliveryTest extends BaseTest {

    @Test
    @Tags({@Tag("order"), @Tag("store_order"), @Tag("regression"), @Tag("ui")})
    @DisplayName("create new S2S order with Existing customer and 1 article")
    void createNewS2SOrderExistingCustomer() {
        String orderId = loginAddArticleSelectCustomer()
                .createS2SOrder();

        new Cockpit().openWaitingForDeliveryTab();
        $("body").shouldHave(text(orderId));
    }

    @Test
    @Tags({@Tag("order"), @Tag("store_order"), @Tag("regression"), @Tag("ui")})
    @DisplayName("create new S2S Parked order with Existing customer and 1 article")
    void createNewS2SParkedOrder() {
        String orderId = loginAddArticleSelectCustomer()
                .createParkedOrder();

        new Cockpit().openInCreationTab();
        $("body").shouldHave(text(orderId));
    }

    @Test
    @Tags({@Tag("order"), @Tag("store_order"), @Tag("regression"), @Tag("ui")})
    @DisplayName("S2S full positive flow: create order -> approve request in other store-> approve order")
    void createS2SOrderAndApproveRequest() {
        val process = new Processes();
        val cockpit = new Cockpit();

        String orderId = loginAddArticleSelectCustomer()
                .createS2SOrder();

        Store requestStore = process.getRequestStore(orderId);
        reLoginAndOpenCurrentRequest(requestStore);

        new CurrentRequests()
                .approveCurrentRequest(orderId, testData.SEARCH_ARTICLE_EAN)
                .openClosedTab();
        $("body").shouldHave(text(orderId));

        reLoginAndOpenWFD(testData.stores[0])
                .clickToConfirmPackage(orderId);

        process.clickToConfirmPackageHandover(orderId)
                .openFinishedTab();
        $("body").shouldHave(text(orderId));

    }


    @Test
    @Tags({@Tag("order"), @Tag("home_order"), @Tag("store_order"), @Tag("regression"), @Tag("ui")})
    @DisplayName("create new Store Delivery Parked order and change delivery type to Home Parked")
    void createNewStoreOrderAndChangeTypeToHomeParked() throws Exception {
        val cockpit = new Cockpit();
        String orderId = loginAddArticleSelectCustomer()
                .createParkedOrder();

        OrderItem storeParkedOrder = cockpit.openInCreationTab()
                .getOrder(orderId);

        storeParkedOrder.getOrderType().shouldHave(text(testData.APP_TEXT.storeOrderTypeName()));

        storeParkedOrder.openOrder().
                clickProceedEditing().clickNext();

        new OrderConfirmation().changeOrderTypeToHome(testData.SEARCH_CUSTOMER)
                .createParkedOrder();

        cockpit.openInCreationTab();
        storeParkedOrder.getOrderType().shouldHave(text(testData.APP_TEXT.homeOrderTypeName()));

    }

    @Test
    @Tags({@Tag("order"), @Tag("home_order"), @Tag("store_order"), @Tag("regression"), @Tag("ui")})
    @DisplayName("create new Store Delivery Parked order and change delivery type to Home(order created)")
    void createNewStoreOrderAndChangeTypeToHome() throws Exception {
        val cockpit = new Cockpit();
        String orderId = loginAddArticleSelectCustomer()
                .createParkedOrder();

        OrderItem storeParkedOrder = cockpit.openInCreationTab()
                .getOrder(orderId);

        storeParkedOrder.getOrderType().shouldHave(text(testData.APP_TEXT.storeOrderTypeName()));

        storeParkedOrder.openOrder().
                clickProceedEditing().clickNext();

        new OrderConfirmation().changeOrderTypeToHome(testData.SEARCH_CUSTOMER)
                .createPrepaymentOrder();

        cockpit.openFinishedTab();
        storeParkedOrder.getOrderType().shouldHave(text(testData.APP_TEXT.homeOrderTypeName()));

    }

    @Test
    @Tags({@Tag("order"), @Tag("home_order"), @Tag("regression"), @Tag("ui")})
    @DisplayName("create new Store Delivery order with Duplicate customer and 1 article")
    void createNewHomeDeliveryOrderWithDuplicateCustomer() {
        new Login().doLogin(testData.stores[0])
                .addArticleToShoppingCartByEAN(testData.SEARCH_ARTICLE_EAN);
        CreateNewCustomer customer = new ShoppingCart()
                .selectDelivery()
                .selectStoreDelivery()
                .selectNewCustomerAndFillInInfo(testData.SEARCH_CUSTOMER)
                .approveEmailVerificationAndClickCreateNewCustomer();

        customer.getNotificationTextElement().shouldHave(text(testData.APP_TEXT.orderCreationCustomerAlreadyExists()));

        String orderId = customer.continueWithExistingCustomer()
                .createS2SOrder();

        new Cockpit().openWaitingForDeliveryTab();
        $("body").shouldHave(text(orderId));
    }


    @Test
    @Tags({@Tag("order"), @Tag("store_order"), @Tag("regression"), @Tag("ui")})
    @DisplayName("S2S full positive flow: create order -> decline request in first store-> approve request in second store -> approve order")
    void createS2SOrderDelcineAndApproveRequest() {
        val process = new Processes();
        val cockpit = new Cockpit();

        String orderId = loginAddArticleSelectCustomer()
                .createS2SOrder();

        Store requestStore = process.getRequestStore(orderId);
        reLoginAndOpenCurrentRequest(requestStore);

        new CurrentRequests()
                .declineRequest(orderId)
                .openClosedTab();
        $("body").shouldHave(text(orderId));

        reLoginAndOpenWFD(testData.stores[0]);
        requestStore = process.getRequestStore(orderId);

        reLoginAndOpenCurrentRequest(requestStore);
        new CurrentRequests()
                .approveCurrentRequest(orderId, testData.SEARCH_ARTICLE_EAN)
                .openClosedTab();
        $("body").shouldHave(text(orderId));

        reLoginAndOpenWFD(testData.stores[0])
                .clickToConfirmPackage(orderId);

        process.clickToConfirmPackageHandover(orderId)
                .openFinishedTab();
        $("body").shouldHave(text(orderId));

    }

    @Test
    @Tags({@Tag("order"), @Tag("store_order"), @Tag("regression"), @Tag("ui")})
    @DisplayName("delete Store Delivery order in Checkout")
    void deleteNewStoreDeliveryOrderInCheckout() {
        loginAddArticleSelectCustomer()
                .deleteOrder();
        $("body").shouldHave(text(testData.APP_TEXT.cockpitTitle()));
    }

    @Test
    @Tags({@Tag("order"), @Tag("store_order"), @Tag("regression"), @Tag("ui")})
    @DisplayName("delete new Store Delivery Parked order in Checkout")
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
    @Tags({@Tag("order"), @Tag("store_order"), @Tag("regression"), @Tag("ui")})
    @DisplayName("create new Store Delivery Parked order in Shopping cart")
    void createNewStoreDeliveryParkedOrderInShoppingCart() {
        loginAddArticleSelectCustomer()
                .clickBackToShoppingCart()
                .backToShoppingCart();
        String orderId = new OrderConfirmation().
                createParkedOrder();
        new Cockpit().openInCreationTab();
        $("body").shouldHave(text(orderId));
    }

    @Test
    @Tags({@Tag("order"), @Tag("store_order"), @Tag("regression"), @Tag("ui")})
    @DisplayName("delete new Store Delivery order in Shopping cart")
    void deleteNewStoreDeliveryParkedOrderInShoppingCart() {
        loginAddArticleSelectCustomer()
                .clickBackToShoppingCart()
                .backToShoppingCart();
        new OrderConfirmation().deleteOrder();
        $("body").shouldHave(text(testData.APP_TEXT.cockpitTitle()));
    }


    OrderConfirmation loginAddArticleSelectCustomer() {
        new Login().doLogin(testData.stores[0])
                .addArticleToShoppingCartByEAN(testData.SEARCH_ARTICLE_EAN);
        return new ShoppingCart().clickToShoppingCart()
                .selectStoreDeliveryAndExistingCustomer(testData.SEARCH_CUSTOMER);
    }

    Processes reLoginAndOpenWFD(Store store) {
        return new Cockpit().logOut()
                .doLogin(store)
                .openWaitingForDeliveryTab();
    }

    void reLoginAndOpenCurrentRequest(Store store) {
        new Cockpit().logOut()
                .doLogin(store)
                .openCurrentRequestsAndRefreshData();
    }
}
