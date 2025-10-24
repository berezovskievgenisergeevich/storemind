package wmf.web.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import se.helpers.RandomGenerator;
import se.web.pages.Cockpit;
import se.web.pages.CustomerSearch;
import wmf.web.components.appointment.NewAppointmentWindow;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class WMFCockpit extends Cockpit {


    //TODO change css
    @Step("click Create new Customer")
    public WMFCreateNewCustomer clickCreateNewCustomerAndLoginWithPopUp(String login, String pass) {
        $(byText("Kunden anlegen")).click();
        return new WMFCreateNewCustomer(login, pass);
    }

    //TODO change css
    @Step("click Search Customer")
    public CustomerSearch clickCustomerSearch() {
        $(byText("Kunden suchen")).click();
        return new CustomerSearch();
    }

    //TODO change css
    @Step("open Catalog")
    public WMFCatalog openCatalog() {
        $(byText("Katalog öffnen")).click();
        return new WMFCatalog();
    }

    //TODO change css
    @Step("get Avantgarde links elements")
    public ElementsCollection getAvantgardeElements() {
        return $$("[class='MuiBox-root css-rbtf7a'] ul li");
    }

    //TODO change css
    @Step("click Create new Appointment")
    public NewAppointmentWindow clickCreateNewAppointment() {
        $(byText("Termin erstellen")).click();
        return new NewAppointmentWindow();
    }

    @Step("get random Avantgarde")
    public SelenideElement getRandomAvantgardeElem() {
        return getAvantgardeElements().get(new RandomGenerator().getRandomNumber(0, getAvantgardeElements().size()));
    }
    //TODO change css
    @Step("get Appointments Today")
    public String getAppointmentsCountToday() {
        return $(byText("Termine heute")).parent().$("div").text();
    }
}
