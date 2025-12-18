package se.web.pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class CustomerInfo {


    @Step("click Edit customer")
    public CreateNewCustomer clickEdit() {
        $("[type='button']").click();
        return new CreateNewCustomer();
    }
}
