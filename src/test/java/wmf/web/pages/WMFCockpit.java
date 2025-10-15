package wmf.web.pages;

import io.qameta.allure.Step;
import se.web.pages.Cockpit;
import se.web.pages.CustomerSearch;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class WMFCockpit extends Cockpit {


    //TODO change css
    @Step("click Create new Customer")
    public WMFCreateNewCustomer clickCreateNewCustomerAndLoginWithPopUp(String login, String pass) {
        $(byText("Kunden anlegen")).click();
        return new WMFCreateNewCustomer(login, pass);
    }

    //TODO change css
    @Step("click Search Customer")
    public CustomerSearch clickCustomerSearch() {
        $(byText("Kunden suchen")).click();
        return new CustomerSearch();
    }
}
