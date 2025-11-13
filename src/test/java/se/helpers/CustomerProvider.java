package se.helpers;

import se.api.model.customer.create_customer.Address;
import se.api.model.customer.create_customer.Body;
import se.api.model.customer.create_customer.CreateCustomerApiModel;
import se.api.model.customer.update_customer.Customer;
import se.api.model.customer.update_customer.UpdateCustomerApiModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.javafaker.Faker;
import lombok.val;
import se.web.data.model.Salutation;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.util.Locale;

public class CustomerProvider {
    private final String ZIP_AT = "1234";
    private final String PHONE_MASK = "+49151#######";
    Faker faker = new Faker(new Locale("DE"));

    public static final se.web.data.model.Customer searchCustomer = new se.web.data.model.Customer(Salutation.He, "90000772", "automation", "ui_test", "1234", "stad", "str", "1", "automation_ui_test@mail.com");
    public static final se.web.data.model.Customer notExistingCustomer = new se.web.data.model.Customer(Salutation.He, "3151020", "automation", "not_existing_customer", "1234", "stad", "str", "1", "not_existing_customer@mail.com");
    public static final se.web.data.model.Customer editCustomer = new se.web.data.model.Customer(Salutation.He, "90000871", "edit", "edit", "1234", "cit", "str", "1", "edit@mail.com");

    public se.web.data.model.Customer createNewRandomCustomer() {
        val name = faker.name().firstName();
        val email = name + LocalDateTime.now().toString().replace(":", "-") + "@mail.com";
        se.web.data.model.Customer customer = new se.web.data.model.Customer(getRandomSalutation(), "",
                name, faker.name().lastName(), ZIP_AT,
                faker.address().city(),
                faker.address().streetName(),
                faker.address().streetAddressNumber(),
                email);
        customer.setBirthday(RandomGenerator.getRandomBirthdayMoreThen18());
        customer.setAdditionalAddress(faker.address().secondaryAddress());
        customer.setPhone(faker.numerify(PHONE_MASK));
        return customer;
    }

    public se.web.data.model.Customer createNewRandomCustomerAgeLess18() {
        val newRandomCustomer = createNewRandomCustomer();
        newRandomCustomer.setBirthday(RandomGenerator.getRandomBirthdayLessThen18());
        return newRandomCustomer;
    }


    public Salutation getRandomSalutation() {
        return (new RandomGenerator().getRandomNumber(0, 2) == 0) ? Salutation.He : Salutation.She;
    }

    public String getRandomCustomerApiModel() {
        val newRandomCustomer = createNewRandomCustomer();
        return getCustomerApiModel(newRandomCustomer);
    }

    public String getCustomerApiModel(se.web.data.model.Customer customer) {
        val createCustomerApiModel = new CreateCustomerApiModel();
        createCustomerApiModel.body = new Body();
        createCustomerApiModel.body.setAddress(new Address(customer));

        try {
            return new ObjectMapper().writeValueAsString(createCustomerApiModel);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String getUpdateCustomerApiModel(se.web.data.model.Customer customer) {
        val updateCustomerApiModel = new UpdateCustomerApiModel();
        updateCustomerApiModel.body = new se.api.model.customer.update_customer.Body();
        updateCustomerApiModel.body.id = customer.getId();
        updateCustomerApiModel.body.setCustomer(new Customer(customer));

        try {
            return new ObjectMapper().writeValueAsString(updateCustomerApiModel);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


}

