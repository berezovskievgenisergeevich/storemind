package se.web.components;

import com.codeborne.selenide.SelenideElement;
import se.helpers.RandomGenerator;
import io.qameta.allure.Step;
import se.web.data.TestData;

import java.util.List;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class ArticleInfoWindow {

    SelenideElement addMonogramButton = $("[data-testid='article-add-monogram-button']");
    public SelenideElement editMonogramButton = $("[data-testid='article-edit-monogram-button']");
    List<String> forbiddenMonogramOptions = new TestData().forbiddenMonogramOptions;

    @Step("click Add to Shopping Cart button")
    public void clickAddArticleToShoppingCart() {
        $("[data-testid='article-add-to-cart-button']").click();
    }

    public SelenideElement focusToArticleInfoWindow() {
        new LoadingDialogWindow().waitLoading();
        return $("[data-testid='article-modal']");
    }

    @Step("click to 'Add monogram' button")
    public ArticleInfoWindow clickAddMonogram() {
        new LoadingDialogWindow().waitLoading();
        addMonogramButton.click();
        return this;
    }


    //todo change css -> ticket
    @Step("click to 'Add monogram to Article' button")
    public ArticleInfoWindow clickAddMonogramToArticle() {
        $(byText("Monogramm hinzufügen")).click();
        return this;
    }

    @Step("fill in monogram: {letters}")
    public ArticleInfoWindow fillInMonogram(String letters) {
        int position = 0;
        for (int i = 0; i < letters.length(); i++) {
            position = i + 3;
            $x("(//input[@data-element='input'])[" + position + "]").setValue(letters.charAt(i) + "");
        }
        return this;
    }

    @Step("fill in monogram with forbidden options")
    public ArticleInfoWindow fillInMonogramWithForbiddenOptions() {
        fillInMonogram(new RandomGenerator().getRandomString(forbiddenMonogramOptions));
        return this;
    }

    // todo change css -> MONOGRAM
    public SelenideElement getMonogramRestrictionWindowDescription() {

        return $("[data-testid='notification-text']");
        //return $$("[role='dialog']").get(1);
    }

    @Step("add monogram to article {monogram}")
    public ArticleInfoWindow addMonogramToArticle(String monogram) {
        clickAddMonogram();
        fillInMonogram(monogram);
        clickAddMonogramToArticle();
        return this;
    }

    @Step("add monogram to article with Forbidden Options")
    public ArticleInfoWindow addMonogramWithForbiddenOptions() {
        clickAddMonogram();
        fillInMonogramWithForbiddenOptions();
        clickAddMonogramToArticle();
        return this;
    }


}
