package wmf.web.pages;

import io.qameta.allure.Step;
import se.web.pages.OrderConfirmation;

import static com.codeborne.selenide.Selenide.$;

public class WMFOrderConfirmation extends OrderConfirmation {

    @Step("click Parked Order button")
    public WMFOrderConfirmation clickParkedButton() {
        $("[data-testid='park-order-button']").click();
        return this;
    }

    @Step("add discount")
    public WMFOrderConfirmation addDiscount(String discount) {
        $("[name='firstName']").val(discount).pressEnter();
        return this;
    }
}
