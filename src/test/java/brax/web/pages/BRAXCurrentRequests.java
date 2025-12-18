package brax.web.pages;

import io.qameta.allure.Step;
import lombok.val;
import se.web.components.LoadingDialogWindow;
import se.web.components.Scanner;
import se.web.pages.CurrentRequests;

public class BRAXCurrentRequests extends CurrentRequests {
    @Step("approve Current Request: {orderId} with Article: {articleEAN}")
    public CurrentRequests approveCurrentRequest(String orderId, String articleEAN) {
        openOrder(orderId);
        approveArticle();
        new Scanner().openArticleScannerOnRequestWindow().search(articleEAN);
        clickCloseRequest();
        return this;
    }

    @Step("approve Current Request: {orderId} with Article: {articleEAN}")
    public CurrentRequests approveCurrentRequestAndAddTrackingUrl(String orderId, String articleEAN, String trackingUrl) {
        val scanner = new Scanner();
        openOrder(orderId);
        approveArticle();
        scanner.openArticleScannerOnRequestWindow().search(articleEAN);
        scanner.openTrackingUrlScannerOnRequestWindow().search(trackingUrl);
        clickCloseRequest();
        return this;
    }

    @Step("approve Current Request: {orderId} with Article: {articleEAN}")
    public CurrentRequests scannArticleEanAndAddTrackingUrl(String orderId, String articleEAN, String trackingUrl) {
        val scanner = new Scanner();
        openOrder(orderId);
        approveArticle();
        scanner.openArticleScannerOnRequestWindow().search(articleEAN);
        scanner.openTrackingUrlScannerOnRequestWindow().search(trackingUrl);
        return this;
    }

    @Step("decline Current Request: {orderId}")
    public CurrentRequests declineRequest(String orderId) {
        openOrder(orderId);
        declineArticle();
        return this;
    }

    @Step("decline Article")
    public CurrentRequests declineArticle() {
        clickNo();
        clickCloseRequestAfterDefect();
        new LoadingDialogWindow().waitLoading();
        return this;
    }


}
