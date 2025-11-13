package brax.web.pages;

import io.qameta.allure.Step;
import se.web.pages.CustomerSearch;

import static com.codeborne.selenide.Selenide.$;

public class BRAXCustomerSearch extends CustomerSearch {

    @Step("Search by: {search}")
    public BRAXCustomerInfo search(String search) {
        $("[data-testid='search-term-input']").val(search).pressEnter();
        return new BRAXCustomerInfo();
    }


}
