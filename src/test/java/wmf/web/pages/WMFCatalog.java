package wmf.web.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import se.helpers.RandomGenerator;
import se.web.pages.Catalog;

import static com.codeborne.selenide.Selenide.$$;

public class WMFCatalog extends Catalog {

    //TODO change css
    @Step("get Product Categories elements")
    public ElementsCollection getProductCategoriesElements() {
        return $$("[class='MuiBox-root css-1bt8ldp'] div");
    }

    @Step("get random Product Category")
    public SelenideElement getRandomProductCategoryElem() {
        return getProductCategoriesElements().get(new RandomGenerator().getRandomNumber(0, getProductCategoriesElements().size()));
    }
}
