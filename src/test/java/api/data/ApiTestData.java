package api.data;

import api.model.customer.CreateNewCustomerRequestModel;
import api.model.LoginRequestModel;
import api.model.customer.UpdateCustomerRequestModel;
import api.model.customer.update_customer.UpdateCustomerApiModel;
import com.github.javafaker.Faker;
import config.TestsConfig;
import helpers.CustomerProvider;
import org.aeonbits.owner.ConfigFactory;

import java.util.Locale;

public class ApiTestData {
    private final TestsConfig config = ConfigFactory.create(TestsConfig.class, System.getProperties());
    private final Faker faker = new Faker(new Locale("en-US"));
    public final String RANDOM_EMAIL = faker.internet().emailAddress();
    public final String RANDOM_PASS = faker.internet().password();
    public final LoginRequestModel bodyData = new LoginRequestModel(config.apiEmail(), config.apiPass());
    public final CreateNewCustomerRequestModel bodyCustomer =
            new CreateNewCustomerRequestModel(new CustomerProvider().getRandomCustomerApiModel());
    public final CreateNewCustomerRequestModel bodyExistingCustomer =
            new CreateNewCustomerRequestModel(new CustomerProvider().getCustomerApiModel(CustomerProvider.editCustomer));

    public final UpdateCustomerRequestModel bodyUpdateCustomer =
            new UpdateCustomerRequestModel(new CustomerProvider().getUpdateCustomerApiModel(CustomerProvider.editCustomer));
}
