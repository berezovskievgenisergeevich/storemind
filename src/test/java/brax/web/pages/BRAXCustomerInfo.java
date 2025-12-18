package brax.web.pages;

import io.qameta.allure.Step;
import se.web.pages.CustomerInfo;

import static com.codeborne.selenide.Selenide.$;

public class BRAXCustomerInfo extends CustomerInfo {

    @Step("click Edit customer")
    public BRAXCreateNewCustomer clickEdit() {
        $("[data-testid='customer-edit']").click();
        return new BRAXCreateNewCustomer();
    }
}
