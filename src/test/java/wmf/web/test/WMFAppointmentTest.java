package wmf.web.test;

import io.qameta.allure.Story;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import se.web.pages.Login;
import wmf.helpers.AppointmentCountExtractor;
import wmf.web.pages.WMFCockpit;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Story("[UI] Appointment")
public class WMFAppointmentTest extends WMFTest {

    @Test
    @Tags({@Tag("appointment"), @Tag("regression"), @Tag("ui")})
    @DisplayName("check create new appointment")
    void checkCreateNewAppointment() {
        new Login().doLogin(testData.stores[0]);

        new WMFCockpit().clickCreateNewAppointment()
                .selectHe()
                .enterName("Test")
                .enterLastName("LastTest")
                .enterEmail("email@mail.com")
                .enterPhone("09874651321")
                .clickAppointmentType()
                .selectStoreAppointment()
                .enterNote("Some Note info message")
                .selectDayToday()
                .selectTimeToday()
                .clickCrateAppointment();

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
                .selectHe()
                .enterName("Test")
                .enterLastName("LastTest")
                .enterEmail("email@mail.com")
                .enterPhone("09874651321")
                .clickAppointmentType()
                .selectStoreAppointment()
                .enterNote("Some Note info message")
                .selectDayToday()
                .selectTimeToday()
                .clickCrateAppointment();
        appointmentsCount = wmfCockpit.getAppointmentsCountToday();
        int appointmentsCountAllExpected = AppointmentCountExtractor.getAllAppointments(appointmentsCount);
        int appointmentsCountTodayExpected = AppointmentCountExtractor.getAppointmentsToday(appointmentsCount);
        assertThat(appointmentsCountAll + 1 == appointmentsCountAllExpected);
        assertThat(appointmentsCountToday + 1 == appointmentsCountTodayExpected);
    }


}
