package wmf.web.test;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Story;
import lombok.val;
import org.junit.jupiter.api.*;
import se.web.components.ArticleInfoWindow;
import se.web.pages.Catalog;
import se.web.pages.Login;
import wmf.web.pages.WMFCockpit;

import static com.codeborne.selenide.Condition.interactable;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

@Story("[UI] Catalog")
public class WMFCatalogTest extends WMFTest {
    @Test
    @Tags({@Tag("cockpit"), @Tag("catalog"), @Tag("regression"), @Tag("ui")})
    @DisplayName("check Title Name in Catalog section on Cockpit")
    void checkCatalogSectionTitle() {
        new Login().doLogin(testData.stores[0]);
        $("body").shouldHave(text(testData.APP_TEXT.cockpitCatalogSectionName()));
    }

    @Test
    @Tags({@Tag("catalog"), @Tag("regression"), @Tag("ui")})
    @DisplayName("check Title Name in Catalog")
    void checkCatalogTitle() {
        new Login().doLogin(testData.stores[0]);
        new WMFCockpit().openCatalog();
        $("body").shouldHave(text(testData.APP_TEXT.cockpitCatalogSectionName()));
    }

    @Test
    @Tags({@Tag("catalog"), @Tag("regression"), @Tag("ui")})
    @DisplayName("check Title Name in Catalog when change Catalog section")
    void checkCatalogTitleChange() {
        new Login().doLogin(testData.stores[0]);
        SelenideElement productCategory = new WMFCockpit().openCatalog()
                .getRandomProductCategoryElem();
        val expectedText = productCategory.text();
        productCategory.click();
        $("h2").shouldHave(text(expectedText));
    }

    @Test
    @Tags({@Tag("catalog"), @Tag("regression"), @Tag("ui")})
    @DisplayName("check search article by Name")
    void searchArticleByName() {
        new Login().doLogin(testData.stores[0]);
        new WMFCockpit().openCatalog().search(testData.SEARCH_ARTICLE_NAME);
        for (SelenideElement article : new Catalog().getAllArticles()) {
            article.shouldHave(text(testData.SEARCH_ARTICLE_NAME));
        }
    }

    @Test
    @Tags({@Tag("catalog"), @Tag("regression"), @Tag("ui")})
    @DisplayName("check search article by invalid Name")
    void searchArticleByInvalidName() {
        new Login().doLogin(testData.stores[0]);
        new WMFCockpit().openCatalog().search(testData.SEARCH_ARTICLE_INVALID_NAME);
        $("[id='infinite-scroll-area']").shouldHave(text(testData.APP_TEXT.catalogNoArticlesFoundText()));
    }

    @Test
    @Tags({@Tag("catalog"), @Tag("regression"), @Tag("ui")})
    @DisplayName("check search article by Article No")
    void searchArticleByNo() {
        new Login().doLogin(testData.stores[0]);
        new WMFCockpit().openCatalog().search(testData.SEARCH_ARTICLE_FULL_NO);
        new Catalog().getAllArticles().first().shouldBe(interactable).click();
        new ArticleInfoWindow().focusToArticleInfoWindow()
                .shouldHave(text(testData.SEARCH_ARTICLE_FULL_NO));
    }

    @Test
    @Tags({@Tag("catalog"), @Tag("regression"), @Tag("ui")})
    @DisplayName("check search article by Scanner")
    void searchArticleByScanner() {
        new Login().doLogin(testData.stores[0]);
        new WMFCockpit().openCatalog().searchArticleByScanner(testData.SEARCH_ARTICLE_EAN)
                .focusToArticleInfoWindow()
                .shouldHave(text(testData.SEARCH_ARTICLE_EAN));
    }

    @Test
    @Tags({@Tag("catalog"), @Tag("regression"), @Tag("ui")})
    @DisplayName("check search article by Scanner invalid EAN")
    void searchArticleByScannerInvalidEan() {
        new Login().doLogin(testData.stores[0]);
        new WMFCockpit().openCatalog().openScanner().search(testData.SEARCH_ARTICLE_INVALID_EAN);
        $("[role='dialog']").shouldHave(text(testData.APP_TEXT.articleNotFoundText()));

    }


}
