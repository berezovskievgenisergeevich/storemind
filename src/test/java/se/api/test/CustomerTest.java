package se.api.test;

import se.api.model.customer.CreateNewCustomerErrorResponseModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Story;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
@Story("[API] Create-Update Customer Appwrite")
public class CustomerTest extends BaseApiLoginTest {


    @Test
    @Tags({@Tag("customer"), @Tag("se/api")})
    @DisplayName("check create-customer api")
    void checkCreateNewCustomer() throws JsonProcessingException {
        doLoginWithCookie()
                .body(apiData.bodyCustomer)
                .post("/v1/functions/develop-1.0/executions")
                .then()
                .log().body()
                .statusCode(201);
    }

    @Test
    @Tags({@Tag("customer"), @Tag("se/api")})
    @DisplayName("check create-customer api - Existing customer error")
    void checkCreateNewCustomerExistingCustomerError() throws JsonProcessingException {
        String response = doLoginWithCookie()
                .body(apiData.bodyExistingCustomer)
                .post("/v1/functions/develop-1.0/executions")
                .then()
                .log().body()
                .extract().path("responseBody");

        ObjectMapper om = new ObjectMapper();
        val error = om.readValue(response, CreateNewCustomerErrorResponseModel.class).getError();
        assertThat(error.getMessage().equals("Kunde ist bereits angelegt (90000871)!"));
        assertThat(error.getKey().equals("already_exists"));
        assertThat(error.getCode() == 400);

    }

    @Test
    @Tags({@Tag("customer"), @Tag("se/api")})
    @DisplayName("check update-customer api")
    void checkUpdateCustomer() throws JsonProcessingException {
        doLoginWithCookie()
                .body(apiData.bodyUpdateCustomer)
                .post("/v1/functions/develop-1.0/executions")
                .then()
                .log().body()
                .statusCode(201);
    }
}
