package se.web.test;

import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import se.web.data.model.Store;
import se.web.pages.Login;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

@Story("[UI] Login")
public class LoginTest extends SeTest {
    @Test
    @Tags({@Tag("login"), @Tag("regression"), @Tag("ui")})
    @DisplayName("check First step authentification with valid Email and valid Password")
    void successLoginWithEmailAndPassTest() {
        new Login()
                .setEmail(testData.stores[0].getEmail())
                .setPass(testData.stores[0].getPass())
                .clickLogin();
        $("[name='merchant']").shouldBe(visible);
    }

    @Test
    @Tags({@Tag("login"), @Tag("regression"), @Tag("ui"), @Tag("smoke")})
    @DisplayName("Successful login with valid Email, valid Password and valid Customer Id")
    void successLoginWithEmailPassAndCustomerIdTest() {
        new Login()
                .setEmail(testData.stores[0].getEmail())
                .setPass(testData.stores[0].getPass())
                .clickLogin()
                .enterCustomerId(testData.CUSTOMER_ID)
                .clickMerchantLogin();
        $("body").shouldHave(text(testData.APP_TEXT.cockpitTitle()));
    }

    @Test
    @Tags({@Tag("login"), @Tag("regression"), @Tag("ui")})
    @DisplayName("Login to App with wrong Email and Pass")
    void loginToStoreWithWrongEmailAndPassTest() {
        new Login()
                .setEmail(testData.RANDOM_EMAIL)
                .setPass(testData.RANDOM_PASS)
                .clickLogin();
        $("[id=':r0:-helper-text']").shouldBe(visible);
    }

    @Test
    @Tags({@Tag("login"), @Tag("regression"), @Tag("ui")})
    @DisplayName("The Enter button is not active if the email has not been entered")
    void checkIfEnterButtonDisabledWhenEmailIsEmpty() {
        new Login()
                .setPass(testData.RANDOM_PASS)
                .loginButton.shouldBe(disabled);
    }

    @Test
    @Tags({@Tag("login"), @Tag("regression"), @Tag("ui")})
    @DisplayName("Login to first store, open menu and click Change Store button and after that login to second store")
    void checkLoginToDifferentStores() {
        Store firstStore = testData.stores[0];
        Store secondStore = testData.stores[1];
        new Login()
                .doLogin(firstStore)
                .logOut()
                .doLogin(secondStore);
    }

    @Test
    @Disabled
    @Tags({@Tag("login"), @Tag("regression"), @Tag("ui")})
    @DisplayName("Login to all stores")
    void checkLoginToAllStores() {
        Login login = new Login();
        for (Store store : testData.stores) {
            login.doLogin(store).logOut();
        }
    }

    @Test
    @Tags({@Tag("login"), @Tag("language"), @Tag("ui")})
    @DisplayName("Change language")
    void checkChangeLanguage() {
        new Login()
                .openLanguageSelectionWindow()
                .selectLanguage(testData.LANGUAGE_TO_SELECT)
                .languageSelection
                .getLanguage().shouldHave(text(testData.LANGUAGE_TO_SELECT.toString()));
    }
}
