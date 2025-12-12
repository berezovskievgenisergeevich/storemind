package brax.web.pages;

import brax.helpers.BraxStoreProvider;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.val;
import se.web.components.LoadingDialogWindow;
import se.web.components.Scanner;
import se.web.data.model.Store;
import se.web.pages.Processes;

import static com.codeborne.selenide.Selenide.$;

public class BRAXProcesses extends Processes {

    SelenideElement confirmPackageButton = $("[data-testid='confirm-receival-button']");

    @Step("wait until S2S request created, open list with Requested Store and provide S2S request Store name")
    public Store getRequestStore(String orderId) {
        String requestStoreName = waitForOrderRequest(orderId)
                .openRequestsList()
                .getRequestStoreName();
        closeOrderWindow();
        return BraxStoreProvider.getStoreByName(requestStoreName);
    }

    @Step("click to 'Confirm package' button")
    public Processes clickToConfirmPackage(String orderId) {
        waitForOrderRequestApproved(orderId);
        confirmPackageButton.click();
        return this;
    }

    @Step("wait order request approved")
    public Processes waitForOrderRequestApproved(String orderId) {
        val loadingDialogWindow = new LoadingDialogWindow();
        val scanner = new Scanner();
        for (int i = 0; i < WAIT_UNTIL_PROCESS_UPDATED; i++) {
            waitUntilOrderChangeState(scanner, loadingDialogWindow, orderId);
            if (isOrderApproved())
                return this;
            closeOrderWindow();
        }
        return this;
    }

    @Step("check is order request approved And 'Confirm package' button is active")
    public boolean isOrderApproved() {
        return confirmPackageButton.exists();
    }

    @Step("click to 'Confirm Package handover' button on WFC tab")
    public Processes clickToConfirmPackageHandover(String orderId) {
        new Scanner().openScanner().search(orderId);
        $("[data-testid='delivered-order-button']").click();
        return this;
    }
}
