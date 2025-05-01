package web.pages;

import io.qameta.allure.Step;
import web.components.LoadingDialogWindow;
import web.components.Scanner;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class CurrentRequests {

    @Step("open requested order {orderId}")
    public CurrentRequests openOrder(String orderId) {
        new Scanner().openScanner().search(orderId);
        return this;
    }

    @Step("approve Article")
    public CurrentRequests approveArticle() {
        clickYes();
        clickSendPackage();
        return this;
    }

    @Step("decline Article")
    public CurrentRequests declineArticle() {
        clickNo();
        selectDefect();
        clickCloseRequestAfterDefect();
        new LoadingDialogWindow().waitLoading();
        return this;
    }

    // TODO change css -> ticket
    private void selectDefect() {
        $(byText("Defekt")).click();
    }


    @Step("click 'Yes' (approve one article)")
    public CurrentRequests clickYes() {
        $("[data-testid='request-item-answer-CONFIRMED']").click();
        return this;
    }

    @Step("click 'No' (decline one article)")
    public CurrentRequests clickNo() {
        $("[data-testid='request-item-answer-DECLINED']").click();
        return this;
    }


    @Step("click 'Send Package'")
    public CurrentRequests clickSendPackage() {
        $("[data-testid='request-confirm']").click();
        return this;
    }

    @Step("click 'Close Request'")
    public CurrentRequests clickCloseRequestAfterDefect() {
        $("[data-testid='request-declined']").click();
        return this;
    }


    public CurrentRequests clickCloseRequest() {
        $("[data-testid='request-submit']").click();
        return this;
    }

    @Step("approve Current Request: {orderId} with Article: {articleEAN}")
    public CurrentRequests approveCurrentRequest(String orderId, String articleEAN) {
        openOrder(orderId);
        approveArticle();
        new Scanner().openScannerOnRequestWindow().search(articleEAN);
        clickCloseRequest();
        return this;
    }


    @Step("decline Current Request: {orderId}")
    public CurrentRequests declineRequest(String orderId) {
        openOrder(orderId);
        declineArticle();
        return this;
    }

    //todo change css -> ticket
    @Step("open 'Closed' tab")
    public CurrentRequests openClosedTab() {
        $(byText("Abgeschlossen")).click();
        return this;
    }


}
