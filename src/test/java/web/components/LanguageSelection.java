package web.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class LanguageSelection {

    public SelenideElement getLanguage() {
        return $("[data-testid='LanguageIcon']").parent();
    }

    public SelenideElement getLanguageSelectionWindow() {
        return $("[role='dialog']");
    }

    public String getTitle() {
        return $("[data-testid='choose-language-title']").text();
    }

    public void setLanguage(Language language) {
        getLanguageSelectionWindow().$(language.selector).click();
    }

}
