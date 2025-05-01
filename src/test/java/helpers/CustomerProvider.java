package helpers;

import api.model.customer.create_customer.Address;
import api.model.customer.create_customer.Body;
import api.model.customer.create_customer.CreateCustomerApiModel;
import api.model.customer.update_customer.UpdateCustomerApiModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.javafaker.Faker;
import lombok.val;
import web.data.model.Customer;
import web.data.model.Salutation;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.util.Locale;

public class CustomerProvider {
    private final String ZIP_AT = "1234";
    Faker faker = new Faker(new Locale("en-US"));

    public static final Customer searchCustomer = new Customer(Salutation.He, "90000772", "automation", "ui_test", "1234", "stad", "str", "1", "automation_ui_test@mail.com");
    public static final Customer notExistingCustomer = new Customer(Salutation.He, "3151020", "automation", "not_existing_customer", "1234", "stad", "str", "1", "not_existing_customer@mail.com");
    public static final Customer editCustomer = new Customer(Salutation.He, "90000871", "edit", "edit", "1234", "cit", "str", "1", "edit@mail.com");

    public Customer createNewRandomCustomer() {
        val name = faker.name().firstName();
        val email = name + LocalDateTime.now().toString().replace(":", "-") + "@mail.com";
        Customer customer = new Customer(getRandomSalutation(), "",
                name, faker.name().lastName(), ZIP_AT,
                faker.address().city(),
                faker.address().streetName(),
                faker.address().streetAddressNumber(),
                email);
        customer.setBirthday(RandomGenerator.getRandomBirthdayMoreThen18());
        customer.setAdditionalAddress(faker.address().secondaryAddress());
        customer.setPhone(faker.phoneNumber().phoneNumber());
        return customer;
    }

    public Customer createNewRandomCustomerAgeLess18() {
        val newRandomCustomer = createNewRandomCustomer();
        newRandomCustomer.setBirthday(RandomGenerator.getRandomBirthdayLessThen18());
        return newRandomCustomer;
    }


    Salutation getRandomSalutation() {
        return (new RandomGenerator().getRandomNumber(0, 2) == 0) ? Salutation.He : Salutation.She;
    }

    public String getRandomCustomerApiModel() {
        val newRandomCustomer = createNewRandomCustomer();
        return getCustomerApiModel(newRandomCustomer);
    }

    public String getCustomerApiModel(Customer customer) {
        val createCustomerApiModel = new CreateCustomerApiModel();
        createCustomerApiModel.body = new Body();
        createCustomerApiModel.body.setAddress(new Address(customer));

        try {
            return new ObjectMapper().writeValueAsString(createCustomerApiModel);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String getUpdateCustomerApiModel(Customer customer) {
        val updateCustomerApiModel = new UpdateCustomerApiModel();
        updateCustomerApiModel.body = new api.model.customer.update_customer.Body();
        updateCustomerApiModel.body.id = customer.getId();
        updateCustomerApiModel.body.setCustomer(new api.model.customer.update_customer.Customer(customer));

        try {
            return new ObjectMapper().writeValueAsString(updateCustomerApiModel);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


}

