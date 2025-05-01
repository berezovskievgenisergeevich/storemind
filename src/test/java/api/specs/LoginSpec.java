package api.specs;

import config.TestsConfig;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;

import java.util.Map;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;

public class LoginSpec {
    static TestsConfig config = ConfigFactory.create(TestsConfig.class, System.getProperties());
    public static RequestSpecification loginRequestSpec = with()
            .filter(withCustomTemplates())
            .log().uri()
            .log().headers()
            .log().body()
            .contentType(JSON)
            .header("x-appwrite-project", config.apiProjectName())
            .baseUri(config.apiUrl());
}
