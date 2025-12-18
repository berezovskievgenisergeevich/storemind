package brax.helpers;

import com.github.javafaker.Faker;
import lombok.val;
import se.helpers.CustomerProvider;
import se.helpers.RandomGenerator;
import se.web.data.model.Customer;
import se.web.data.model.Salutation;

import java.util.Locale;

public class BRAXCustomerProvider {
    private static Faker faker = new Faker(new Locale("DE"));
    private static String PHONE_MASK = "+49151#######";

    public static Customer getSearchCustomer() {
        val customer = new Customer(Salutation.He, "KK00856634", "search", "search", "12345", "ort", "str", "1", "search@mail.com");
        customer.setBirthday(RandomGenerator.getRandomBirthdayMoreThen18());
        customer.setPhone(faker.numerify(PHONE_MASK));
        return customer;
    }

    public static Customer getCustomerWithAgeLess18() {
        return new CustomerProvider().createNewRandomCustomerAgeLess18();
    }

    public static Customer getEditCustomer() {
        var customer = new Customer(Salutation.He, "KK00856641", "EditName", "EditLastName", "12345", "ort", "str", "1", "edit@mail.com");
        customer.setBirthday(RandomGenerator.getRandomBirthdayMoreThen18());
        customer.setPhone(faker.numerify(PHONE_MASK));
        return customer;
    }

    public static Customer getNotExistingCustomer() {
        return new Customer(Salutation.He, "3151020_000_000", "not_existing_customer", "not_existing_customer", "1234", "stad", "str", "1", "not_existing_customer@mail.com");

    }

}
