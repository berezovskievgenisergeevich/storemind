package wmf.web.components.appointment;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.interactions.Actions;
import wmf.config.WMFConfig;
import wmf.helpers.AppointmentsProvider;
import wmf.web.data.model.AppointmentModel;
import wmf.web.data.model.AppointmentType;

import java.time.Duration;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selenide.*;

/**
 * wmf online - checks the robot script, so the class is not relevant
 */
public class WMFOnlineAppointment {
    WMFConfig projectConfig = ConfigFactory.create(WMFConfig.class, System.getProperties());
    public AppointmentModel appointment = AppointmentsProvider.getRandomAppointment();
    private SelenideElement searchStoreInput = $("[id='appointment-branch-search-city-8ae8c4693828245ae0697147b7d6d541']");
    private SelenideElement appointmentDayElem = $("[name='appointment[day]']");

    public WMFOnlineAppointment() {
        open(projectConfig.onlineAppointmentUrl());
    }

    @Step("add Name")
    public WMFOnlineAppointment setName() {
        $("[name='appointment[firstname]']").val(appointment.getName());
        return this;
    }

    @Step("add Last Name")
    public WMFOnlineAppointment setLastName() {
        $("[name='appointment[lastname]']").val(appointment.getName());
        return this;
    }

    @Step("add Email")
    public WMFOnlineAppointment setEmail() {
        $("[name='appointment[email]']").val(appointment.getEmail());
        return this;
    }

    @Step("add Phone")
    public WMFOnlineAppointment setPhone() {
        $("[name='appointment[telephone]']").val(appointment.getEmail());
        return this;
    }

    @Step("add Note")
    public WMFOnlineAppointment setNote() {
        $("[name='appointment[note]']").val(appointment.getEmail());
        return this;
    }


    @Step("Search and Select store")
    public WMFOnlineAppointment selectStore() {
        $("[id='appointment-branch-search-8ae8c4693828245ae0697147b7d6d541']").click();
        searchStoreInput.val("07545 Gera, Germany");
        selectGooglePlace();
        sleep(1000);
        $("[id='appointment-branch-search-button-8ae8c4693828245ae0697147b7d6d541']").shouldBe(enabled, Duration.ofSeconds(2)).click();
        $("[id='appointment-branch-search-result-inner-container-8ae8c4693828245ae0697147b7d6d541'] div").click();
        $("[name='appointment[day]']").selectOption(1);
        executeJavaScript("arguments[0].removeAttribute('disabled');", $("[name='appointment[time]']"));
        $("[name='appointment[time]']").shouldBe(enabled, Duration.ofSeconds(2)).selectOption(1);
        selectAppointment(appointment.getAppointmentType());
        $("[name='appointment[agreement]']").click();
        $("[id='appointment-button-8ae8c4693828245ae0697147b7d6d541']").click();

        return this;
    }

    @Step("Select store in Google Place")
    private WMFOnlineAppointment selectGooglePlace() {
        new Actions(WebDriverRunner.getWebDriver()).moveToElement(searchStoreInput)
                .moveByOffset(0, 50) // смещение вниз на 50 пикселей
                .click()
                .perform();
        return this;
    }


    @Step("select appointment type: {type}")
    private WMFOnlineAppointment selectAppointment(AppointmentType type) {
        if (type == AppointmentType.PHONE)
            $("[id='appointment-type-allowappointmentphone-8ae8c4693828245ae0697147b7d6d541']").click();
        else if (type == AppointmentType.STORE) {
            $("[id='appointment-type-allowappointmentonsite-8ae8c4693828245ae0697147b7d6d541']").click();
        } else if (type == AppointmentType.PERSONAL) {
            $("[id='appointment-type-allowappointmentcoffeemachineconsultation-8ae8c4693828245ae0697147b7d6d541']").click();
        } else {
            $("[id='appointment-type-allowappointmentavantgardekitchenmachine-8ae8c4693828245ae0697147b7d6d541']").click();
        }
        return this;
    }
}
