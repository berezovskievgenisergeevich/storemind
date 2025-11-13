package brax.web.pages;

import io.qameta.allure.Step;
import se.web.components.LoadingDialogWindow;
import se.web.data.model.Store;
import se.web.pages.Cockpit;
import se.web.pages.Login;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class BRAXLogin extends Login {
    @Step("login to store")
    public BRAXCockpit doLogin(Store store) {
        setEmail(store.getEmail());
        setPass(store.getPass());
        clickLogin();
        new LoadingDialogWindow().waitLoading();
        enterCustomerId(store.getCustomerId());
        clickMerchantLogin();
        $("body").shouldHave(text(store.getName()));
        return new BRAXCockpit();
    }
}
