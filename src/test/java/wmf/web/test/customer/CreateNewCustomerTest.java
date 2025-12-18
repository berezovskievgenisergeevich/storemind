package wmf.web.test.customer;

import org.junit.jupiter.api.*;
import se.helpers.CustomerProvider;
import se.web.data.model.Customer;
import se.web.pages.Login;
import wmf.web.pages.WMFCockpit;
import wmf.web.test.WMFTest;

public class CreateNewCustomerTest extends WMFTest {
    @Test
    @Disabled("The site uses protection against bots and robots, the captcha cannot be passed")
    @Tags({@Tag("customer"), @Tag("regression"), @Tag("ui")})
    @DisplayName("Create new customer with all information")
    void checkCustomerCreationWithAllField() {
        Customer customer = new CustomerProvider().createNewRandomCustomer();

        new Login().doLogin(testData.stores[0]);

        new WMFCockpit()
                .clickCreateNewCustomerAndLoginWithPopUp(projectConfig.createCustomerLogin(), projectConfig.createCustomerPass())
                .setName(customer.getName())
                .setLastName(customer.getLastName())
                .setEmail(customer.getEmail())
                .setPass(testData.CREATE_CUSTOMER_PASS).
                clickHumanConfirmation();

    }
}
