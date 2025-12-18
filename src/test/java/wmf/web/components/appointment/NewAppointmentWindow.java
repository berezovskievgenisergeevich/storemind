package wmf.web.components.appointment;

import io.qameta.allure.Step;
import se.web.components.LoadingDialogWindow;
import se.web.data.model.Salutation;
import wmf.web.data.model.AppointmentModel;
import wmf.web.data.model.AppointmentType;
import wmf.web.pages.WMFCockpit;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class NewAppointmentWindow {
    @Step("Select Salutation: She")
    public NewAppointmentWindow selectShe() {
        $("[data-testid='salutation-MRS']").click();
        return this;
    }


    @Step("Select Salutation: He")
    public NewAppointmentWindow selectHe() {
        $("[data-testid='salutation-MR']").click();
        return this;
    }


    @Step("Enter name: {name}")
    public NewAppointmentWindow enterName(String name) {
        $("[name='firstName']").val(name);
        return this;
    }

    @Step("Enter Last name: {lastName}")
    public NewAppointmentWindow enterLastName(String lastName) {
        $("[name='lastName']").val(lastName);
        return this;
    }

    @Step("Enter Email: {email}")
    public NewAppointmentWindow enterEmail(String email) {
        $("[name='email']").val(email);
        return this;
    }

    @Step("Enter Phone: {phone}")
    public NewAppointmentWindow enterPhone(String phone) {
        $("[name='phone']").val(phone);
        return this;
    }

    @Step("Enter note: {note}")
    public NewAppointmentWindow enterNote(String note) {
        $("[data-testid='note-textarea']").val(note);
        return this;
    }

    @Step("Select Appointment Type")
    public NewAppointmentWindow clickAppointmentType() {
        $("[data-testid='select-appointment-type']").click();
        return this;
    }

    @Step("select appointment: Store")
    public NewAppointmentWindow selectStoreAppointment() {
        $("[data-value='STORE']").click();
        return this;
    }

    @Step("select appointment: Phone")
    public NewAppointmentWindow selectPhoneAppointment() {
        $("[data-value='PHONE']").click();
        return this;
    }

    @Step("select appointment: Personal")
    public NewAppointmentWindow selectPersonalAppointment() {
        $("[data-value='PERSONAL']").click();
        return this;
    }

    @Step("select appointment: Avantgarde")
    public NewAppointmentWindow selectAvanrgardeAppointment() {
        $("[data-value='AVANTGARDE']").click();
        return this;
    }


    @Step("Select Day - Today")
    public NewAppointmentWindow selectDayToday() {
        return new DayTimeSelectionInAppointment().clickToDaySelection().clickOk();
    }

    @Step("Select Time - Now")
    public NewAppointmentWindow selectTimeToday() {
        return new DayTimeSelectionInAppointment().clickToTimeSelection().clickOk();
    }

    @Step("click Create Appointment")
    public WMFCockpit clickCrateAppointment() {
        $("[data-testid='submit-appointment-button']").click();
        new LoadingDialogWindow().waitLoading();
        return new WMFCockpit();
    }

    public NewAppointmentWindow fillInAppointment(AppointmentModel appointment) {
        new LoadingDialogWindow().waitLoading();
        return selectSalutation(appointment.getSalutation())
                .enterName(appointment.getName())
                .enterLastName(appointment.getLastName())
                .enterEmail(appointment.getEmail())
                .enterPhone(appointment.getPhone())
                .clickAppointmentType()
                .selectAppointment(appointment.getAppointmentType())
                .enterNote(appointment.getNote())
                .selectDayToday()
                .selectTimeToday();

    }

    private NewAppointmentWindow selectSalutation(Salutation salutation) {
        return (Salutation.He == salutation) ? selectHe() : selectShe();
    }

    @Step("select appointment type: {type}")
    private NewAppointmentWindow selectAppointment(AppointmentType type) {
        $("[data-value='" + type + "']").click();
        return this;
    }

    public int getCreatedAppointmentCount() {
        new LoadingDialogWindow().waitLoading();
        $("[data-testid='registered-appointment-row']").shouldBe(visible).shouldBe(interactable).should(exist);
        return $$("[data-testid='registered-appointment-row']").size();
    }


}
