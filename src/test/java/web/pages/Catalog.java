package web.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import web.components.LoadingDialogWindow;

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

}

