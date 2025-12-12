package brax.web.test.order;

import brax.web.pages.BRAXCurrentRequests;
import brax.web.pages.BRAXOrderConfirmation;
import brax.web.pages.BRAXProcesses;
import brax.web.pages.BRAXShoppingCart;
import brax.web.test.BraxTest;
import io.qameta.allure.Story;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import se.web.data.model.Store;
import se.web.pages.Cockpit;
import se.web.pages.CurrentRequests;
import se.web.pages.Login;
import se.web.pages.Processes;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

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
        val cockpit = new Cockpit();

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
