package web.components;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.interactable;
import static com.codeborne.selenide.Selenide.$;

public class Scanner {
    SelenideElement scannerInput = $("[data-testid='scanner-input']");

    @Step("input ID: {id} to input and press Enter")
    public void search(String id) {
        scannerInput.val(id).pressEnter();
    }

    @Step("open scanner")
    public Scanner openScanner() {
        $("[data-testid='scanner-icon']").shouldBe(exist).shouldBe(interactable).click();
        return this;
    }

    @Step("open scanner on current Request")
    public Scanner openScannerOnRequestWindow() {
        $(".MuiBox-root .css-hoe9xz [data-testid='scanner-icon']").shouldBe(exist).shouldBe(interactable).click();
        return this;
    }

}
