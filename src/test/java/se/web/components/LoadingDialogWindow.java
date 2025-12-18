package se.web.components;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class LoadingDialogWindow {

    public void waitLoading() {
        $(".css-8feepa").shouldNot(Condition.exist)
                .shouldNot(Condition.interactable)
                .shouldBe(Condition.hidden);
        sleep(500);
    }
}
