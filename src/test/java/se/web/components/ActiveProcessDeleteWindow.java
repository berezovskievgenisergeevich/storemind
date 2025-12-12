package se.web.components;

import io.qameta.allure.Step;
import se.web.pages.Cockpit;

import static com.codeborne.selenide.Selenide.$;

import static com.codeborne.selenide.Selectors.byText;

public class ActiveProcessDeleteWindow extends ActiveProcessWindow {

    //TODO chenge css
    @Step("click Delete Order button")
    public Cockpit clickDeleteOrder() {
        new LoadingDialogWindow().waitLoading();
        $(byText("Löschen")).click();
        return new Cockpit();
    }
}
