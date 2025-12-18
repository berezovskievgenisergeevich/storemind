package se.web.test;

import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import se.helpers.CustomerProvider;
import se.web.data.model.Customer;
import se.web.pages.Login;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

@Story("[UI] Edit Customer")
public class EditCustomerTest extends SeTest {

    @Test
    @Tags({@Tag("customer"), @Tag("edit_customer"), @Tag("regression"), @Tag("ui")})
    @DisplayName("Edit existing customer data")
    void checkCustomerEdit() {
        Customer customer = new CustomerProvider().createNewRandomCustomer();
        new Login().doLogin(testData.stores[0])
                .searchCustomer(CustomerProvider.editCustomer.getEmail())
                .clickEdit()
                .setName(customer.getName())
                .setLastName(customer.getLastName())
                .setStreetNumber(customer.getHomeNr())
                .setStreet(customer.getStreet())
                .signForm()
                .clickRegisterCustomer();
        $("[type='button']").parent().parent()
                .shouldHave(text(customer.getName()))
                .shouldHave(text(customer.getLastName()))
                .shouldHave(text(customer.getStreet()))
                .shouldHave(text(customer.getHomeNr()));
    }


}
