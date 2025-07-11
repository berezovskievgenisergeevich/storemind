package se.web.components;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class VerificationErrorWindow {

    SelenideElement window = $("[data-testid='validation-error-modal']");

    @Step("click 'ok' in 'Email Verification' window")
    public VerificationErrorWindow clickOkInEmailVerificationWindow() {
        $("[role='dialog'] button").click();
        return this;
    }
}
