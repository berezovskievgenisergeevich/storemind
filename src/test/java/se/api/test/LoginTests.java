package se.api.test;

import se.api.model.LoginRequestModel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static se.api.specs.LoginSpec.loginRequestSpec;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@Story("[API] Login Appwrite")
public class LoginTests extends BaseApiLoginTest {


    @Test
    @Tags({@Tag("login"), @Tag("se/api")})
    @DisplayName("check 'v1/account' function with cookies from 'v1/account/sessions/email'")
    void executeAccountFunctionWithCookiesTest() {
        Map<String, String> cookies = given(loginRequestSpec)
                .body(apiData.bodyData)
                .post("/v1/account/sessions/email")
                .then()
                .statusCode(201)
                .log().body()
                .extract().cookies();

        given(loginRequestSpec)
                .body(apiData.bodyData)
                .cookies(cookies)
                .get("/v1/account")
                .then()
                .statusCode(200)
                .log().body();
    }

    @Test
    @Tags({@Tag("login"), @Tag("se/api")})
    @DisplayName("check 'v1/account' function without cookies from 'v1/account/sessions/email'")
    void executeAccountFunctionWithoutCookiesTest() {
        String messageResponse = given(loginRequestSpec)
                .body(apiData.bodyData)
                .get("/v1/account")
                .then()
                .statusCode(401)
                .log().body()
                .extract().path("message");

        assertThat(messageResponse.equals("User (role: guests) missing scope (account)"));

    }

    @Test
    @Tags({@Tag("login"), @Tag("se/api")})
    @DisplayName("check 'v1/account/sessions/email' function")
    void successEmailFunctionTest() {
        given(loginRequestSpec)
                .body(apiData.bodyData)
                .post("/v1/account/sessions/email")
                .then()
                .statusCode(201)
                .log().body();
    }

    @Test
    @Tags({@Tag("login"), @Tag("se/api")})
    @DisplayName("check 'v1/account/sessions/email' function with wrong Email and Pass")
    void checkEmailFunctionWithWrongEmailAndPassTest() {
        LoginRequestModel body = new LoginRequestModel(apiData.RANDOM_EMAIL, apiData.RANDOM_PASS);
        String responseMessage = given(loginRequestSpec)
                .body(body)
                .post("/v1/account/sessions/email")
                .then()
                .statusCode(401)
                .log().body()
                .extract().path("message");
        assertThat(responseMessage.equals("Invalid credentials. Please check the email and password."));
    }

}
