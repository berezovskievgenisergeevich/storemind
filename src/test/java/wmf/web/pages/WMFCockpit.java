package wmf.web.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import se.helpers.RandomGenerator;
import se.web.pages.Cockpit;
import se.web.pages.CustomerSearch;
import wmf.web.components.appointment.NewAppointmentWindow;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class WMFCockpit extends Cockpit {

    SelenideElement appointmentsTodayElement = $("[data-testid='confirmed-appointments-link']");

    @Step("click Create new Customer")
    public WMFCreateNewCustomer clickCreateNewCustomerAndLoginWithPopUp(String login, String pass) {
        $("[data-testid='customer-registration']").click();
        return new WMFCreateNewCustomer(login, pass);
    }

    @Step("click Search Customer")
    public CustomerSearch clickCustomerSearch() {
        $("[data-testid='customer-search']").click();
        return new CustomerSearch();
    }

    @Step("open Catalog")
    public WMFCatalog openCatalog() {
        $("[data-testid='open-catalog']").click();
        return new WMFCatalog();
    }

    //TODO change css
    @Step("get Avantgarde links elements")
    public ElementsCollection getAvantgardeElements() {
        return $$("[class='MuiBox-root css-rbtf7a'] ul li");
    }

    @Step("click Create new Appointment")
    public NewAppointmentWindow clickCreateNewAppointment() {
        $("[data-testid='create-appointment-button']").click();
        return new NewAppointmentWindow();
    }

    @Step("get random Avantgarde")
    public SelenideElement getRandomAvantgardeElem() {
        return getAvantgardeElements().get(new RandomGenerator().getRandomNumber(0, getAvantgardeElements().size()));
    }

    @Step("get Appointments Today")
    public String getAppointmentsCountToday() {
        return appointmentsTodayElement.$("div").text();
    }

    @Step("open Appointments Today")
    public WMFAppointment openAppointmentsToday() {
        appointmentsTodayElement.click();
        return new WMFAppointment();
    }

}
