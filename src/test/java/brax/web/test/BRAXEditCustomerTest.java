package brax.web.test;

import brax.web.pages.BRAXCustomerSearch;
import brax.web.pages.BRAXLogin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import se.helpers.CustomerProvider;
import se.web.data.model.Customer;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class BRAXEditCustomerTest extends BraxTest {
    @Test
    @Tags({@Tag("customer"), @Tag("edit_customer"), @Tag("regression"), @Tag("ui")})
    @DisplayName("Edit existing customer data")
    void checkCustomerEdit() {
        Customer customer = new CustomerProvider().createNewRandomCustomer();
        new BRAXLogin().doLogin(testData.stores[0])
                .clickAdvancedCustomerSearch();
        new BRAXCustomerSearch().search(testData.EDIT_CUSTOMER.getId())
                .clickEdit()
                .setName(customer.getName())
                .setLastName(customer.getLastName())
                .setStreetNumber(customer.getHomeNr())
                .setStreet(customer.getStreet())
                .clickRegisterCustomer();
        $("body")
                .shouldHave(text(customer.getName()))
                .shouldHave(text(customer.getLastName()))
                .shouldHave(text(customer.getStreet()))
                .shouldHave(text(customer.getHomeNr()));
    }
}
