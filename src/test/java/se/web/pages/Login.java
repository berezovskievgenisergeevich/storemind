package se.web.pages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import se.web.components.Language;
import se.web.components.LanguageSelection;
import se.web.components.LoadingDialogWindow;
import se.web.data.model.Store;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class Login {

    public SelenideElement loginButton = $("[data-testid='login-button']");
    public final LanguageSelection languageSelection = new LanguageSelection();

    public Login() {
        step("Open page: " + Configuration.baseUrl,
                () -> open("/"));
    }

    @Step("Enter email: {email}")
    public Login setEmail(String email) {
        $("[name='email']").val(email);
        return this;
    }

    @Step("Enter pass: {pass}")
    public Login setPass(String pass) {
        $("[name='password']").val(pass);
        return this;
    }

    @Step("Click Enter")
    public Login clickLogin() {
        loginButton.click();
        return this;
    }

    @Step("Click Merchant login")
    public Cockpit clickMerchantLogin() {
        $("[data-testid='merchant-login-button']").click();
        return new Cockpit();
    }

    @Step("Enter Customer Id: {customerId}")
    public Login enterCustomerId(String customerId) {
        new LoadingDialogWindow().waitLoading();
        $("[name='merchant']").val(customerId);
        return this;
    }

    @Step("login to store")
    public Cockpit doLogin(Store store) {
        setEmail(store.getEmail());
        setPass(store.getPass());
        clickLogin();
        new LoadingDialogWindow().waitLoading();
        enterCustomerId(store.getCustomerId());
        clickMerchantLogin();
        $("body").shouldHave(text(store.getName()));
        return new Cockpit();
    }

    @Step("click to Language -> open language selection window")
    public Login openLanguageSelectionWindow() {
        languageSelection.getLanguage().click();
        return this;
    }

    @Step("Select language: ${language}")
    public Login selectLanguage(Language language) {
        languageSelection.setLanguage(language);
        return this;
    }


}
