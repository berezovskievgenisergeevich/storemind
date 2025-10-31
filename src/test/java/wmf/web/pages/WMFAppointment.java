package wmf.web.pages;

import io.qameta.allure.Step;
import wmf.web.components.appointment.AppointmentWindow;
import wmf.web.data.model.AppointmentModel;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class WMFAppointment {

    @Step("open appointment")
    public AppointmentWindow openAppointment(AppointmentModel appointment) {
        $(byText(appointment.getEmail())).parent()
                .shouldHave(text(appointment.getName()))
                .shouldHave(text(appointment.getLastName()))
                .shouldHave(text(appointment.getPhone()))
                .click();
        return new AppointmentWindow(appointment);
    }

    // TODO change css
    @Step("open Tab 'Stornierte Termine'")
    public WMFAppointment openCancelledAappointmentsTab() {
        $(byText("Stornierte Termine")).click();
        return this;
    }


}
