package web.components;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import helpers.RandomGenerator;
import io.qameta.allure.Step;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.driver;

public class ArticleInfoWindow {

    SelenideElement addMonogramButton = $("[data-testid='article-add-monogram-button']");
    ElementsCollection monogramLetters = $$("[data-element='input']");
    List<String> forbiddenMonogramOptions = List.of("SS", "HH", "HJ", "KZ", "NS", "SA");

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
        $("[type='button']").click();
        return this;
    }

    @Step("fill in monogram")
    public ArticleInfoWindow fillInMonogram(String letters) {
        sleep(900);
        var element = $("img.css-boil6");
        Actions actions = new Actions(WebDriverRunner.getWebDriver());
        actions.moveToElement(element).perform(); // Наводим курсор

        try {
            Robot robot = new Robot();
            robot.mouseWheel(1);
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
        $x("(//input[@data-element='input'])[1]").setValue("A");
        $x("(//input[@data-element='input'])[2]").setValue("B");
        $x("(//input[@data-element='input'])[3]").setValue("C");

        //  $x("(//input[@data-element='input'])[1]").scrollTo();
        sleep(900);
      /*  executeJavaScript("arguments[0].setAttribute('value', 'A')", $x("(//input[@data-element='input'])[1]").getWrappedElement());
        sleep(900);
        executeJavaScript("arguments[0].setAttribute('value', 'B')", $x("(//input[@data-element='input'])[2]"));
        sleep(900);
        executeJavaScript("arguments[0].setAttribute('value', 'C')", $x("(//input[@data-element='input'])[3]"));*/


    /*    for (int i = 0; i < letters.length(); i++) {
            //   monogramLetters.get(i).parent().parent().click();
            // monogramLetters.get(i).setValue(letters.charAt(i) + "");//.val(letters.charAt(i) + "");
            executeJavaScript("arguments[0].setAttribute('value', '" + letters.charAt(i) + "')", monogramLetters.get(i).getWrappedElement());
        }*/
        return this;
    }

    @Step("fill in monogram with forbidden options")
    public ArticleInfoWindow fillInMonogramWithForbiddenOptions() {
        fillInMonogram(new RandomGenerator().getRandomString(forbiddenMonogramOptions));
        return this;
    }

    // todo change css -> MONOGRAM
    public SelenideElement getMonogramRestrictionWindow() {
        return $$("[role='dialog']").get(1);
    }

    public ArticleInfoWindow addMonogramToArticle(String monogram) {
        clickAddMonogram();
        fillInMonogram(monogram);
        clickAddMonogramToArticle();
        return this;
    }

    public ArticleInfoWindow addMonogramWithForbiddenOptions() {
        clickAddMonogram();
        sleep(500);
        fillInMonogramWithForbiddenOptions();
        //  clickAddMonogramToArticle();
        return this;
    }


}
