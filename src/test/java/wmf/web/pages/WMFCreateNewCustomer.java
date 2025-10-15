package wmf.web.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.val;
import wmf.web.components.WMFLoginPopUp;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class WMFCreateNewCustomer {

    SelenideElement inputName = $("[id='firstName']");
    SelenideElement inputLastName = $("[id='lastName']");
    SelenideElement inputEmail = $("[id='email']");
    SelenideElement inputPass = $("[id='newPassword']");
    SelenideElement createCustomerButton = $("[id='createAccountBtn']");

    WMFCreateNewCustomer(String login, String pass) {
        new WMFLoginPopUp().doLogin(login, pass);
        switchToNewTab();
        acceptCookie();
        Selenide.webdriver().driver().getWebDriver().switchTo().frame($("[id='ucr-iframe']"));
    }

    @Step("get page title element")
    public SelenideElement getPageTitle() {
        return $("[class='typo-title-medium account-title']");
    }

    @Step("fill in name: {name}")
    public WMFCreateNewCustomer setName(String name) {
        sleep(1000);
        inputName.val(name);
        return this;
    }

    @Step("fill in lastname: {lastName}")
    public WMFCreateNewCustomer setLastName(String lastName) {
        sleep(1000);
        inputLastName.val(lastName);
        return this;
    }

    @Step("fill in email: {email}")
    public WMFCreateNewCustomer setEmail(String email) {
        sleep(1000);
        inputEmail.val(email);
        return this;
    }

    @Step("fill in pass: {pass}")
    public WMFCreateNewCustomer setPass(String pass) {
        sleep(1000);
        inputPass.val(pass);
        return this;
    }

    @Step("click Create new Customer")
    public WMFCreateNewCustomer createNewCustomerClick() {
        sleep(1000);
        createCustomerButton.click();
        return this;
    }

    @Step("click Human Confirmation dialog")
    public WMFCreateNewCustomer clickHumanConfirmation() {
        sleep(1000);
        createCustomerButton.click();
        val elemLocation = createCustomerButton.getLocation();
        sleep(1000);
        Robot robot = null;
        try {
            smoothMouseMovement(createCustomerButton.getLocation());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    private static void smoothMouseMovement(org.openqa.selenium.Point point) throws Exception {
        Robot robot = new Robot();
        Point start = MouseInfo.getPointerInfo().getLocation();
        int startX = start.x;
        int startY = start.y;

        int endX = point.x + 200;
        int endY = point.y - 350;

        int steps = 100;
        int delay = 5;

        for (int i = 1; i <= steps; i++) {
            int x = startX + (endX - startX) * i / steps;
            int y = startY + (endY - startY) * i / steps;
            robot.mouseMove(x, y);
            TimeUnit.MILLISECONDS.sleep(delay);
        }
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }


    private static void switchToNewTab() {
        var driver = Selenide.webdriver().driver().getWebDriver();
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size() - 1));
    }


    @Step("accept Cookie")
    private WMFCreateNewCustomer acceptCookie() {
        $("[id='onetrust-accept-btn-handler']").click();
        return this;
    }
}
