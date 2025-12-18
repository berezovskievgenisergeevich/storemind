package brax.web.pages;

import com.codeborne.selenide.SelenideElement;
import se.web.pages.Catalog;

import static com.codeborne.selenide.Selenide.$;

public class BRAXCatalog extends Catalog {

    public SelenideElement selectedWomenFilterElement = $("[data-testid='selected-category-209']");
    public SelenideElement selectedMenFilterElement = $("[data-testid='selected-category-206']");
}
