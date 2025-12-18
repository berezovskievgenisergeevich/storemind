package wmf.helpers;

import com.github.javafaker.Faker;
import se.helpers.CustomerProvider;
import se.helpers.RandomGenerator;
import wmf.web.data.model.AppointmentModel;
import wmf.web.data.model.AppointmentType;

import java.time.LocalDateTime;
import java.util.Locale;

public class AppointmentsProvider {
    static Faker faker = new Faker(new Locale("en-US"));

    public static AppointmentModel getRandomAppointment() {
        return new AppointmentModel(new CustomerProvider().getRandomSalutation(),
                faker.name().firstName(),
                faker.name().lastName(),
                faker.internet().emailAddress(),
                faker.numerify("+4915########"),
                faker.lorem().sentence(5),
                getRandomAppointmentType(),
                LocalDateTime.now());

    }

    public static AppointmentType getRandomAppointmentType() {
        switch (new RandomGenerator().getRandomNumber(0, AppointmentType.values().length)) {
            case 0:
                return AppointmentType.STORE;
            case 1:
                return AppointmentType.PHONE;
            case 2:
                return AppointmentType.PERSONAL;
            default:
                return AppointmentType.AVANTGARDE;
        }
    }
}
