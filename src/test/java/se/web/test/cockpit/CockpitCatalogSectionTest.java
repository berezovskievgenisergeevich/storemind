package se.web.test.cockpit;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Story;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import se.web.components.ArticleInfoWindow;
import se.web.pages.Catalog;
import se.web.pages.Login;
import se.web.test.SeTest;

import static com.codeborne.selenide.Condition.interactable;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

@Story("[UI] Cockpit - Catalog section")
public class CockpitCatalogSectionTest extends SeTest {
    @Test
    @Tags({@Tag("cockpit"), @Tag("catalog"), @Tag("regression"), @Tag("ui")})
    @DisplayName("check Title Name in Catalog section")
    void checkCatalogSectionTitle() {
        new Login().doLogin(testData.stores[0]);
        $("body").shouldHave(text(testData.APP_TEXT.cockpitCatalogSectionName()));
    }

    @Test
    @Tags({@Tag("cockpit"), @Tag("catalog"), @Tag("regression"), @Tag("ui")})
    @DisplayName("check search article by Name on Cockpit page")
    void searchArticleByNameInCockpit() {
        new Login().doLogin(testData.stores[0])
                .searchArticle(testData.SEARCH_ARTICLE_NAME);
        for (SelenideElement article : new Catalog().getAllArticles()) {
            article.shouldHave(text(testData.SEARCH_ARTICLE_NAME));
        }

    }

    @Test
    @Tags({@Tag("cockpit"), @Tag("catalog"), @Tag("regression"), @Tag("ui")})
    @DisplayName("check search article by Article No (3 part of No) on Cockpit page")
    void searchArticleByNoInCockpit() {
        new Login().doLogin(testData.stores[0])
                .searchArticle(testData.SEARCH_ARTICLE_FULL_NO);

        new Catalog().getAllArticles().first().shouldBe(interactable).click();
        new ArticleInfoWindow().focusToArticleInfoWindow()
                .shouldHave(text(testData.SEARCH_ARTICLE_FULL_NO));
    }

    @Test
    @Tags({@Tag("cockpit"), @Tag("catalog"), @Tag("regression"), @Tag("ui")})
    @DisplayName("check search article by Article No (2 part of No) on Cockpit page")
    void searchArticleByTwoPartsOfNoInCockpit() {
        new Login().doLogin(testData.stores[0])
                .searchArticle(testData.SEARCH_ARTICLE_TWO_PARTS_OF_NO);

        new Catalog().getAllArticles().first().shouldBe(interactable).click();
        new ArticleInfoWindow().focusToArticleInfoWindow()
                .shouldHave(text(testData.SEARCH_ARTICLE_TWO_PARTS_OF_NO));
    }

    @Test
    @Tags({@Tag("cockpit"), @Tag("catalog"), @Tag("regression"), @Tag("ui")})
    @DisplayName("check search article by Article No (1 part of No) on Cockpit page")
    void searchArticleByOnePartOfNoInCockpit() {
        new Login().doLogin(testData.stores[0])
                .searchArticle(testData.SEARCH_ARTICLE_ONE_PART_OF_NO);

        val first = new Catalog().getAllArticles().getFirst();
        first.click();
        new ArticleInfoWindow().focusToArticleInfoWindow()
                .shouldHave(text(testData.SEARCH_ARTICLE_ONE_PART_OF_NO));
    }

}
