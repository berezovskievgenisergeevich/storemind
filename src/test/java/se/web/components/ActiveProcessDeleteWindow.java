package se.web.components;

import io.qameta.allure.Step;
import se.web.pages.Cockpit;

import static com.codeborne.selenide.Selenide.$;

public class ActiveProcessDeleteWindow extends ActiveProcessWindow {


    @Step("click Delete Order button")
    public Cockpit clickDeleteOrder() {
        new LoadingDialogWindow().waitLoading();
        $("[data-testid='delete-order']").click();
        return new Cockpit();
    }
}
