package api.test;

import api.data.ApiTestData;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static api.specs.LoginSpec.loginRequestSpec;
import static io.restassured.RestAssured.given;

public class BaseApiLoginTest {

    ApiTestData apiData = new ApiTestData();

    RequestSpecification doLoginWithCookie() {
        Map<String, String> cookies = given(loginRequestSpec)
                .body(apiData.bodyData)
                .post("/v1/account/sessions/email")
                .then()
                .statusCode(201)
                .log().body()
                .extract().cookies();

        return given(loginRequestSpec)
                .body(apiData.bodyData)
                .cookies(cookies);

    }
}
