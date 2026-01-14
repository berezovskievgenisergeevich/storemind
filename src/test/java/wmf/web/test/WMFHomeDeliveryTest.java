package wmf.web.test;

import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import se.web.pages.Cockpit;
import se.web.pages.Login;
import se.web.pages.OrderConfirmation;
import se.web.pages.ShoppingCart;
import wmf.web.pages.WMFShoppingCart;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

@Story("[UI] Home delivery order")
public class WMFHomeDeliveryTest extends WMFTest {
    @Test
    @Tags({@Tag("order"), @Tag("home_order"), @Tag("regression"), @Tag("ui")})
    @DisplayName("create new Home Delivery order with Existing customer and 1 article")
    void createNewHomeDeliveryOrderExistingCustomer() {
        String orderId = loginAddArticleSelectCustomer()
                .checkLegalInfoCheckBox()
                .clickCreateNewOrder()
                .getOrderId();

        new Cockpit().openFinishedTab();
        $("body").shouldHave(text(orderId));
    }

    OrderConfirmation loginAddArticleSelectCustomer() {
        new Login().doLogin(testData.stores[0])
                .addArticleToShoppingCartByEAN(testData.SEARCH_ARTICLE_EAN);
        return new WMFShoppingCart().clickToShoppingCart()
                .selectHomeDeliveryAndExistingCustomer(testData.SEARCH_CUSTOMER);
    }

    OrderConfirmation loginAddArticleLowPriceSelectCustomer() {
        new Login().doLogin(testData.stores[0])
                .addArticleToShoppingCartByEAN(testData.ARTICLE_LOW_PRICE_EAN);
        return new WMFShoppingCart().clickToShoppingCart()
                .selectHomeDeliveryAndExistingCustomer(testData.SEARCH_CUSTOMER);
    }
}
