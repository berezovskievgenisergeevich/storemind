package wmf.web.components.appointment;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class DayTimeSelectionInAppointment {

    //TODO change CSS
    @Step("click Day selection")
    public DayTimeSelectionInAppointment clickToDaySelection() {
        $(byText("Tag auswählen *")).click();
        return this;
    }

    //TODO change CSS
    @Step("click Time selection")
    public DayTimeSelectionInAppointment clickToTimeSelection() {
        $(byText("Zeit auswählen *")).click();
        return this;
    }

    @Step("click Ok in Day Selection")
    public NewAppointmentWindow clickOk() {
        $(byText("Bestätigen")).click();
        return new NewAppointmentWindow();
    }
}
