package se.web.pages;

import io.qameta.allure.Step;
import se.web.components.LoadingDialogWindow;
import se.web.components.Scanner;
import se.web.data.model.Customer;

import static com.codeborne.selenide.Selenide.$;

public class CustomerSearch {

    @Step("search customer by No, value: {customer}")
    public CreateNewCustomer searchCustomerByNo(Customer customer) {
        $("[name='email']").val(customer.getId()).pressEnter();
        new LoadingDialogWindow().waitLoading();
        return new CreateNewCustomer();
    }

    @Step("search customer by Email, value: {customer}")
    public CustomerInfo searchCustomerByEmail(Customer customer) {
        $("[name='email']").val(customer.getEmail()).pressEnter();
        new LoadingDialogWindow().waitLoading();
        return new CustomerInfo();
    }

    @Step("search customer and open Order Confirmation page")
    public OrderConfirmation searchCustomerAndOpenOrderConfirmationPage(Customer customer) {
        return searchCustomerByNo(customer).clickNextInCreateNewOrder();
    }

    @Step("enter Name: {name}")
    public CustomerSearch enterName(String name) {
        $("[name='firstName']").val(name);
        return this;
    }

    @Step("enter last name: {lastName}")
    public CustomerSearch enterLastName(String lastName) {
        $("[name='lastName']").val(lastName);
        return this;
    }

    @Step("press Enter after data is added")
    public void pressEnter() {
        $("[name='email']").pressEnter();
        new LoadingDialogWindow().waitLoading();
    }

    // todo change css
    @Step("Search Customer By Scanner. Customer Id is: {id}")
    public CustomerInfo searchCustomerByScanner(String id) {
        //   $("[data-testid='customer-scanner']").click();
        new LoadingDialogWindow().waitLoading();
        $("[data-testid='scanner-icon']").click();
        new Scanner().search(id);
        return new CustomerInfo();
    }

}
