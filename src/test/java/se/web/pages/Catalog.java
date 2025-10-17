package se.web.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import se.web.components.ArticleInfoWindow;
import se.web.components.LoadingDialogWindow;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class Catalog {
    SelenideElement articlesResultArea = $("[id='infinite-scroll-area']").shouldBe(Condition.exist);

    public ElementsCollection getAllArticles() {
        new LoadingDialogWindow().waitLoading();
        articlesResultArea.$("[data-testid='article-card']").shouldBe(Condition.visible);
        for (SelenideElement article : $$("[data-testid='article-card']")) {
            article.shouldBe(Condition.interactable).shouldBe(Condition.visible).shouldBe(Condition.enabled);
        }
        return $$("[data-testid='article-card']");
    }

    @Step("click to Search icon")
    public Catalog clickSearch() {
        $("[data-testid='SearchIcon']").click();
        return this;
    }

    @Step("open Search input and enter value: {name}")
    public Catalog search(String name) {
        clickSearch();
        $("input").val(name).pressEnter();
        new LoadingDialogWindow().waitLoading();
        return this;
    }

    public ArticleInfoWindow searchArticleByScanner(String searchString) {
        return new Cockpit().searchArticleByScanner(searchString);
    }

}

