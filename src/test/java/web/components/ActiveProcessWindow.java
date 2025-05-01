package web.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import web.pages.Cockpit;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.interactable;
import static com.codeborne.selenide.Selenide.$;

public class ActiveProcessWindow {


    @Step("click Delete Order button")
    public Cockpit clickDeleteOrder() {
        new LoadingDialogWindow().waitLoading();
        focusToWindow().$("[data-testid='active-process-modal-cancel']").click();
        return new Cockpit();
    }

    @Step("click Parked Order button")
    public Cockpit clickParkedOrder() {
        focusToWindow().$("[data-testid='active-process-modal-accept']").should(exist).click();
        return new Cockpit();
    }

    @Step("get Window Title")
    public String getTitle() {
        return focusToWindow().$("[data-testid='notification-title']").text();
    }

    @Step("get Window description")
    public String getDescription() {
        return focusToWindow().$("[data-testid='notification-text']").text();
    }

    SelenideElement focusToWindow() {
        new LoadingDialogWindow().waitLoading();
        return $("[data-testid='active-process-modal']").should(interactable);
    }
}
