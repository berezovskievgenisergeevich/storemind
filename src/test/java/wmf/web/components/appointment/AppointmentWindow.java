package wmf.web.components.appointment;

import io.qameta.allure.Step;
import wmf.web.data.model.AppointmentModel;
import wmf.web.pages.WMFAppointment;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class AppointmentWindow {
    private AppointmentModel appointment;

    public AppointmentWindow(AppointmentModel appointment) {
        this.appointment = appointment;
    }

    //TODO change css
    @Step("click Cancel appointment")
    public AppointmentWindow clickCancel() {
        $(byText("Stornieren")).click();
        return this;
    }

    // TODO change css
    @Step("click Ja in notification window")
    public WMFAppointment clickOK() {
        $("[data-testid='notification-title']").parent()
                .$(byText("Ja")).click();
        return new WMFAppointment();
    }
}
