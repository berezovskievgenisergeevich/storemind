package web.pages;

import io.qameta.allure.Step;
import web.components.LoadingDialogWindow;
import web.data.model.Customer;

import static com.codeborne.selenide.Selenide.$;

public class CustomerSearch {

    @Step("search customer by No, value: {customer}")
    public CreateNewCustomer searchCustomerByNo(Customer customer) {
        $("[name='email']").val(customer.getId()).pressEnter();
        new LoadingDialogWindow().waitLoading();
        return new CreateNewCustomer();
    }

    @Step("search customer and open Order Confirmation page")
    public OrderConfirmation searchCustomerAndOpenOrderConfirmationPage(Customer customer) {
        return searchCustomerByNo(customer).clickNextInCreateNewOrder();
    }

}
