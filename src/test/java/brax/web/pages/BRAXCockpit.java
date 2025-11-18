package brax.web.pages;

import io.qameta.allure.Step;
import se.web.components.LoadingDialogWindow;
import se.web.pages.Cockpit;

import static com.codeborne.selenide.Selenide.$;

public class BRAXCockpit extends Cockpit {

    @Step("click Create new Customer")
    public BRAXCreateNewCustomer clickCreateNewCustomer() {
        $("[data-testid='customer-registration']").click();
        new LoadingDialogWindow().waitLoading();
        return new BRAXCreateNewCustomer();
    }

    @Step("open Catalog for Women")
    public BRAXCatalog openWomenCatalog() {
        $("[data-testid='open-catalog-with-filter-Damen']").click();
        return new BRAXCatalog();
    }

    @Step("open Catalog for Men")
    public BRAXCatalog openMenCatalog() {
        $("[data-testid='open-catalog-with-filter-Herren']").click();
        return new BRAXCatalog();
    }
}
