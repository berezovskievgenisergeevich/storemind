package wmf.web.test;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import se.web.pages.Login;
import wmf.helpers.RobotHelper;
import wmf.web.pages.WMFCockpit;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

@Story("[UI] Avantgarde")
public class WMFAvantgardeTest extends WMFTest {
    @Test
    @Tags({@Tag("Avantgarde"), @Tag("regression"), @Tag("ui")})
    @DisplayName("Check Avantgarde random link")
    void checkAvantgardeRandomLink() {
        new Login().doLogin(testData.stores[0]);
        SelenideElement avantgarde = new WMFCockpit().getRandomAvantgardeElem();
        String expectedText = getLastWord(avantgarde);
        avantgarde.click();
        RobotHelper.switchToNewTab();
        $("body").shouldHave(text(expectedText));
    }

    private String getLastWord(SelenideElement text) {
        return text.text().trim().replaceAll(".*\\s", "");
    }
}
