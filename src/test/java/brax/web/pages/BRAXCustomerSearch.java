package brax.web.pages;

import io.qameta.allure.Step;
import se.web.data.model.Customer;
import se.web.pages.CustomerSearch;
import se.web.pages.OrderConfirmation;

import static com.codeborne.selenide.Selenide.$;

public class BRAXCustomerSearch extends CustomerSearch {

    @Step("Search by: {search}")
    public BRAXCustomerInfo search(String search) {
        $("[data-testid='search-term-input']").val(search).pressEnter();
        return new BRAXCustomerInfo();
    }

    @Step("search customer and open Order Confirmation page")
    public BRAXOrderConfirmation searchCustomerAndOpenOrderConfirmationPage(Customer customer) {
        search(customer.getId());
        return new BRAXCreateNewCustomer().clickNextInCreateNewOrder();
    }


}
