package wmf.web.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import se.helpers.RandomGenerator;
import se.web.components.Scanner;
import se.web.pages.Catalog;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class WMFCatalog extends Catalog {

    @Step("get Product Categories elements")
    public ElementsCollection getProductCategoriesElements() {
        return $$("[data-testid^='category-']");
    }

    @Step("get random Product Category")
    public SelenideElement getRandomProductCategoryElem() {
        return getProductCategoriesElements().get(new RandomGenerator().getRandomNumber(0, getProductCategoriesElements().size()));
    }

    @Step("open scanner")
    public Scanner openScanner() {
        $("[data-testid='scanner-icon']").click();
        return new Scanner();
    }
}
