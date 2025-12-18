package se.web.pages;

import com.codeborne.selenide.SelenideElement;
import se.helpers.StoreProvider;
import io.qameta.allure.Step;
import lombok.val;
import se.web.components.LoadingDialogWindow;
import se.web.components.OrderItem;
import se.web.components.Scanner;
import se.web.data.model.Store;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class Processes {

    protected final int WAIT_UNTIL_PROCESS_UPDATED = 7;


    SelenideElement requestIcon = $("[data-testid='REQUESTED-icon']");

    SelenideElement confirmPackageButton = $("[data-testid='move-order-to-next-step']");

    @Step("close order window")
    public void closeOrderWindow() {
        $("[data-testid='order-modal-close']").click();
    }

    @Step("check is order request created")
    public boolean isOrderRequested() {
        return requestIcon.exists();
    }

    @Step("wait order request")
    public Processes waitForOrderRequest(String orderId) {
        val scanner = new Scanner();
        val loadingDialogWindow = new LoadingDialogWindow();
        for (int i = 0; i < WAIT_UNTIL_PROCESS_UPDATED; i++) {
            waitUntilOrderChangeState(scanner, loadingDialogWindow, orderId);
            if (isOrderRequested())
                return this;

            closeOrderWindow();
        }
        return this;
    }

    @Step("open list requested sores")
    public Processes openRequestsList() {
        requestIcon.click();
        return this;
    }

    public String getRequestStoreName() {
        return $("[data-testid='order-log-store-name']").text();
    }

    @Step("wait until S2S request created, open list with Requested Store and provide S2S request Store name")
    public Store getRequestStore(String orderId) {
        String requestStoreName = waitForOrderRequest(orderId)
                .openRequestsList()
                .getRequestStoreName();
        closeOrderWindow();
        return StoreProvider.getStoreByName(requestStoreName);
    }


    @Step("check is order request approved And 'Confirm package' button is active")
    public boolean isOrderApproved() {
        return confirmPackageButton.isEnabled();
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

    @Step("click to 'Confirm package' button")
    public Processes clickToConfirmPackage(String orderId) {
        waitForOrderRequestApproved(orderId);
        confirmPackageButton.click();
        return this;
    }

    /**
     * The service method is designed to explicitly wait for the process information to be updated.
     * The backend updates the data once a minute, it has been waiting some time and check the relevance of the Order
     *
     * @param scanner
     * @param loadingDialogWindow
     * @param orderId
     */
    protected void waitUntilOrderChangeState(Scanner scanner, LoadingDialogWindow loadingDialogWindow, String orderId) {
        sleep(9000);
        scanner.openScanner().search(orderId);
        loadingDialogWindow.waitLoading();
        sleep(1000);
    }

    @Step("click to 'Confirm Package handover' button on WFC tab")
    public Processes clickToConfirmPackageHandover(String orderId) {
        new Scanner().openScanner().search(orderId);
        $("[data-testid='pickup-with-purchase']").click();
        return this;
    }

    @Step("get order by Id")
    public OrderItem getOrder(String id) throws Exception {
        return new OrderItem(id);
    }

    @Step("Open 'Finished' tab")
    public Processes openFinishedTab() {
        $("[data-testid='orders-tab-archive']").click();
        return this;
    }

    @Step("Open 'In creation' tab")
    public Processes openInCreationTab() {
        $("[data-testid='orders-tab-active']").click();
        return this;
    }

    @Step("Open 'Waiting For Customer' tab")
    public Processes openWaitingForCustomerTab() {
        $("[data-testid='orders-tab-customer']").click();
        return this;
    }

    @Step("Open 'Waiting For Delivery' tab")
    public Processes openWaitingForDeliveryTab() {
        $("[data-testid='orders-tab-delivery']").click();
        return this;
    }

}
