package brax.web.pages;

import io.qameta.allure.Step;
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



}
