package wmf.web.test;

import io.qameta.allure.Story;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import se.web.components.LoadingDialogWindow;
import se.web.pages.Login;
import wmf.helpers.AppointmentCountExtractor;
import wmf.web.components.appointment.WMFOnlineAppointment;
import wmf.web.pages.WMFCockpit;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Story("[UI] Appointment")
public class WMFAppointmentTest extends WMFTest {

    @Test
    @Tags({@Tag("appointment"), @Tag("regression"), @Tag("ui")})
    @DisplayName("check create new appointment")
    void checkCreateNewAppointment() {
        new Login().doLogin(testData.stores[0]);
        val wmfCockpit = new WMFCockpit();
        wmfCockpit.clickCreateNewAppointment()
                .fillInAppointment(testData.RANDOM_APPOINTMENT)
                .clickCrateAppointment();
        wmfCockpit.openAppointmentsToday();
        $("body").shouldHave(text(testData.RANDOM_APPOINTMENT.getName()))
                .shouldHave(text(testData.RANDOM_APPOINTMENT.getLastName()))
                .shouldHave(text(testData.RANDOM_APPOINTMENT.getEmail()))
                .shouldHave(text(testData.RANDOM_APPOINTMENT.getPhone()));
    }

    @Test
    @Tags({@Tag("appointment"), @Tag("regression"), @Tag("ui")})
    @DisplayName("Check the change in the appointment counter for today and all")
    void checkCountAppointmentsChange() {
        new Login().doLogin(testData.stores[0]);
        val wmfCockpit = new WMFCockpit();
        String appointmentsCount = wmfCockpit.getAppointmentsCountToday();
        int appointmentsCountAll = AppointmentCountExtractor.getAllAppointments(appointmentsCount);
        int appointmentsCountToday = AppointmentCountExtractor.getAppointmentsToday(appointmentsCount);
        wmfCockpit.clickCreateNewAppointment()
                .fillInAppointment(testData.RANDOM_APPOINTMENT)
                .clickCrateAppointment();
        appointmentsCount = wmfCockpit.getAppointmentsCountToday();
        new LoadingDialogWindow().waitLoading();
        int appointmentsCountAllExpected = AppointmentCountExtractor.getAllAppointments(appointmentsCount);
        int appointmentsCountTodayExpected = AppointmentCountExtractor.getAppointmentsToday(appointmentsCount);
        assertTrue(appointmentsCountAll + 1 == appointmentsCountAllExpected);
        assertTrue(appointmentsCountToday + 1 == appointmentsCountTodayExpected);
    }

    @Test
    @Tags({@Tag("appointment"), @Tag("regression"), @Tag("ui")})
    @DisplayName("create an Appointment without data")
    void checkCreateEmptyAppointment() {
        new Login().doLogin(testData.stores[0]);
        new WMFCockpit().clickCreateNewAppointment()
                .clickCrateAppointment();
        assertTrue($$(".Mui-error").size() > 10);
    }

    @Test
    @Tags({@Tag("appointment"), @Tag("regression"), @Tag("ui")})
    @DisplayName("check Create Appointments List Increase After new Appointment Created")
    void checkCreatedAppointmentListIncreaseAfterAppointmentCreated() {
        new Login().doLogin(testData.stores[0]);
        val wmfCockpit = new WMFCockpit();
        val newAppointmentWindow = wmfCockpit.clickCreateNewAppointment();
        int appointmentCount = newAppointmentWindow.getCreatedAppointmentCount();
        newAppointmentWindow.fillInAppointment(testData.RANDOM_APPOINTMENT)
                .clickCrateAppointment();
        int appointmentCountExpected = wmfCockpit.clickCreateNewAppointment().getCreatedAppointmentCount();
        assertTrue(appointmentCount + 1 == appointmentCountExpected, "appointment count should be:" + appointmentCountExpected);
    }

    @Test
    @Tags({@Tag("appointment"), @Tag("regression"), @Tag("ui")})
    @DisplayName("check create new appointment in App and after that Confirm it")
    void checkCreateAndConfirmAppointment() {
        new Login().doLogin(testData.stores[0]);
        val wmfCockpit = new WMFCockpit();
        wmfCockpit.clickCreateNewAppointment()
                .fillInAppointment(testData.RANDOM_APPOINTMENT)
                .clickCrateAppointment();
        wmfCockpit.openAppointmentsToday()
                .openAppointment(testData.RANDOM_APPOINTMENT)
                .clickCancel()
                .clickOK()
                .openCancelledAappointmentsTab()
                .openAppointment(testData.RANDOM_APPOINTMENT);
        $("[role='dialog']").shouldHave(text("Abgelehnt"));

    }

    @Test
    @Tags({@Tag("appointment"), @Tag("regression"), @Tag("ui")})
    @DisplayName("check create new appointment in App and after that Confirm it")
    void check() {
        new Login().doLogin(testData.stores[0]);
        new WMFOnlineAppointment()
                .setName()
                .setLastName()
                .setEmail()
                .setPhone()
                .setNote()
                .selectStore();

        sleep(10000);
     //   open("/");
       // sleep(2000);
    }


}
