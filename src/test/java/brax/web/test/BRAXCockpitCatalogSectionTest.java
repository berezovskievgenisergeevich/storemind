package brax.web.test;

import brax.web.pages.BRAXLogin;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import se.web.components.ArticleInfoWindow;
import se.web.pages.Catalog;
import se.web.pages.Login;

import static com.codeborne.selenide.Condition.interactable;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class BRAXCockpitCatalogSectionTest extends BraxTest {
    @Test
    @Tags({@Tag("cockpit"), @Tag("catalog"), @Tag("regression"), @Tag("ui")})
    @DisplayName("check Title Name in Catalog section")
    void checkCatalogSectionTitle() {
        new BRAXLogin().doLogin(testData.stores[0]);
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
    @DisplayName("check search article by Article No on Cockpit page")
    void searchArticleByNoInCockpit() {
        new Login().doLogin(testData.stores[0])
                .searchArticle(testData.SEARCH_ARTICLE_FULL_NO);

        new Catalog().getAllArticles().first().shouldBe(interactable).click();
        new ArticleInfoWindow().focusToArticleInfoWindow()
                .shouldHave(text(testData.SEARCH_ARTICLE_FULL_NO));
    }

    @Test
    @Tags({@Tag("cockpit"), @Tag("catalog"), @Tag("regression"), @Tag("ui")})
    @DisplayName("check search article by Article No (1 part of No) on Cockpit page")
    void searchArticleByFirstPartsOfNoInCockpit() {
        new Login().doLogin(testData.stores[0])
                .searchArticle(testData.SEARCH_ARTICLE_FIRST_PARTS_OF_NO);

        new Catalog().getAllArticles().first().shouldBe(interactable).click();
        new ArticleInfoWindow().focusToArticleInfoWindow()
                .shouldHave(text(testData.SEARCH_ARTICLE_FIRST_PARTS_OF_NO));
    }

    @Test
    @Tags({@Tag("cockpit"), @Tag("catalog"), @Tag("regression"), @Tag("ui")})
    @DisplayName("check open Women catalog and check selected filter")
    void openWomenCatalogAndCheckFilter() {
        new BRAXLogin().doLogin(testData.stores[0])
                .openWomenCatalog().selectedWomenFilterElement
                .shouldHave(text(testData.APP_TEXT.catalogFilterWomen()));
    }

    @Test
    @Tags({@Tag("cockpit"), @Tag("catalog"), @Tag("regression"), @Tag("ui")})
    @DisplayName("check open Men catalog and check selected filter")
    void openMenCatalogAndCheckFilter() {
        new BRAXLogin().doLogin(testData.stores[0])
                .openMenCatalog().selectedMenFilterElement
                .shouldHave(text(testData.APP_TEXT.catalogFilterMen()));
    }

    @Test
    @Tags({@Tag("cockpit"), @Tag("catalog"), @Tag("regression"), @Tag("ui")})
    @DisplayName("check search by Scanner")
    void searchByScanner() {
        new BRAXLogin().doLogin(testData.stores[0])
                .searchArticleByScanner(testData.SEARCH_ARTICLE_EAN)
                .focusToArticleInfoWindow().shouldHave(text(testData.SEARCH_ARTICLE_EAN));
    }

    @Test
    @Tags({@Tag("cockpit"), @Tag("catalog"), @Tag("regression"), @Tag("ui")})
    @DisplayName("check search by Scanner - invalid EAN")
    void searchByScannerInvalidEan() {
        new BRAXLogin().doLogin(testData.stores[0])
                .openScanner().search(testData.NOT_EXISTING_EAN);
        $("[data-testid='notification-text']")
                .shouldHave(text(testData.APP_TEXT.errorScannerArticleNotFound()));
    }


}
