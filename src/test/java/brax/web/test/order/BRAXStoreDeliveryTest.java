package brax.web.test.order;

import brax.web.pages.BRAXCurrentRequests;
import brax.web.pages.BRAXOrderConfirmation;
import brax.web.pages.BRAXProcesses;
import brax.web.pages.BRAXShoppingCart;
import brax.web.test.BraxTest;
import io.qameta.allure.Story;
import lombok.val;
import org.junit.jupiter.api.*;
import se.web.components.OrderItem;
import se.web.data.model.Store;
import se.web.pages.*;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

@Story("[UI] Store delivery order")
public class BRAXStoreDeliveryTest extends BraxTest {

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
    @DisplayName("create new S2S Parked order used 'Parked Order' button")
    void createNewS2SDeliveryParkedOrderParkedButton() {
        String orderId = loginAddArticleSelectCustomer()
                .createParkedOrderParkedButton();

        new Cockpit().openInCreationTab();
        $("body").shouldHave(text(orderId));
    }

    @Test
    @Tags({@Tag("order"), @Tag("store_order"), @Tag("regression"), @Tag("ui")})
    @DisplayName("S2S full positive flow: create order -> approve request in other store-> approve order")
    void createS2SOrderAndApproveRequest() {
        val process = new BRAXProcesses();

        String orderId = loginAddArticleSelectCustomer()
                .createS2SOrder();

        Store requestStore = process.getRequestStore(orderId);
        reLoginAndOpenCurrentRequest(requestStore);

        new BRAXCurrentRequests()
                .approveCurrentRequest(orderId, testData.SEARCH_ARTICLE_PART)
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
    @DisplayName("create new Store Delivery Parked order and change delivery type to Home Parked")
    void createNewStoreOrderAndChangeTypeToHomeParked() throws Exception {
        val cockpit = new Cockpit();
        String orderId = loginAddArticleSelectCustomer()
                .createParkedOrder();

        OrderItem storeParkedOrder = cockpit.openInCreationTab()
                .getOrder(orderId);

        storeParkedOrder.getOrderType().shouldHave(text(testData.APP_TEXT.storeOrderTypeName()));

        storeParkedOrder.openOrder().
                clickContinueOrder().clickNext();

        new BRAXOrderConfirmation().changeOrderTypeToHome(testData.SEARCH_CUSTOMER)
                .createParkedOrder();

        cockpit.openInCreationTab();
        storeParkedOrder.getOrderType().shouldHave(text(testData.APP_TEXT.homeOrderTypeName()));

    }

    @Test
    @Disabled("issue with creating Home order - need correct Article")
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
                clickContinueOrder().clickNext();

        new BRAXOrderConfirmation().changeOrderTypeToHome(testData.SEARCH_CUSTOMER)
                .createHomeOrder();

        cockpit.openFinishedTab();
        $("body").shouldHave(text(orderId));
        storeParkedOrder.getOrderType().shouldHave(text(testData.APP_TEXT.homeOrderTypeName()));
    }

    @Test
    @Tags({@Tag("order"), @Tag("store_order"), @Tag("regression"), @Tag("ui")})
    @DisplayName("S2S full positive flow: create order -> decline request in first store-> approve request in second store -> approve order")
    void createS2SOrderDelcineAndApproveRequest() {
        val process = new BRAXProcesses();
        val cockpit = new Cockpit();

        String orderId = loginAddArticleSelectCustomer()
                .createS2SOrder();

        Store requestStore = process.getRequestStore(orderId);
        reLoginAndOpenCurrentRequest(requestStore);

        new BRAXCurrentRequests()
                .declineRequest(orderId)
                .openClosedTab();
        $("body").shouldHave(text(orderId));

        reLoginAndOpenWFD(testData.stores[0]);
        requestStore = process.getRequestStore(orderId);

        reLoginAndOpenCurrentRequest(requestStore);
        new BRAXCurrentRequests()
                .approveCurrentRequest(orderId, testData.SEARCH_ARTICLE_PART)
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
        BRAXOrderConfirmation orderConfirmation = new BRAXOrderConfirmation();
        String orderId = loginAddArticleSelectCustomer()
                .createParkedOrder();

        cockpit.openInCreationTab()
                .getOrder(orderId)
                .openOrder().
                clickContinueOrder()
                .clickNext();

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
        String orderId = new BRAXOrderConfirmation().
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
        new BRAXOrderConfirmation().deleteOrder();
        $("body").shouldHave(text(testData.APP_TEXT.cockpitTitle()));
    }

    @Test
    @Tags({@Tag("order"), @Tag("store_order"), @Tag("regression"), @Tag("ui")})
    @DisplayName("S2S positive flow: create order -> approve request -> add Tracking Url -> Tracking Url button appears")
    void createS2SOrderWithTrackingUrl() {
        val process = new BRAXProcesses();

        String orderId = loginAddArticleSelectCustomer()
                .createS2SOrder();

        Store requestStore = process.getRequestStore(orderId);
        reLoginAndOpenCurrentRequest(requestStore);

        new BRAXCurrentRequests()
                .approveCurrentRequestAndAddTrackingUrl(orderId, testData.SEARCH_ARTICLE_PART, testData.TRACKING_URL)
                .openClosedTab();
        $("body").shouldHave(text(orderId));

        reLoginAndOpenWFD(testData.stores[0])
                .waitForOrderRequestApproved(orderId);
        process.trackingButton.should(exist);

    }

    @Test
    @Tags({@Tag("order"), @Tag("store_order"), @Tag("regression"), @Tag("ui")})
    @DisplayName("S2S invalid flow: create order -> approve request -> add invalid Tracking Url -> check exception")
    void createS2SOrderWithInvalidTrackingUrl() {
        val process = new BRAXProcesses();

        String orderId = loginAddArticleSelectCustomer()
                .createS2SOrder();

        Store requestStore = process.getRequestStore(orderId);
        reLoginAndOpenCurrentRequest(requestStore);

        new BRAXCurrentRequests()
                .scannArticleEanAndAddTrackingUrl(orderId, testData.SEARCH_ARTICLE_PART, testData.INVALID_TRACKING_URL);
        $("[data-testid='notification-text']").shouldHave(text(testData.APP_TEXT.errorScannerTrackingUrl()));
    }

    BRAXOrderConfirmation loginAddArticleSelectCustomer() {
        new Login().doLogin(testData.stores[0])
                .addArticleToShoppingCartByEAN(testData.SEARCH_ARTICLE_EAN);
        return new BRAXShoppingCart().clickToShoppingCart()
                .selectStoreDeliveryAndExistingCustomer(testData.SEARCH_CUSTOMER);
    }

    BRAXProcesses reLoginAndOpenWFD(Store store) {
        new Cockpit().logOut()
                .doLogin(store)
                .openWaitingForDeliveryTab();
        return new BRAXProcesses();
    }

    void reLoginAndOpenCurrentRequest(Store store) {
        new Cockpit().logOut();
        new Login().doLogin(store)
                .openCurrentRequestsAndRefreshData();
    }
}
