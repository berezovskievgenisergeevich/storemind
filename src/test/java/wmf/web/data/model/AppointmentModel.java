package wmf.web.data.model;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import se.web.data.model.Salutation;

import java.time.LocalDateTime;

@Data
@NonNull

@RequiredArgsConstructor
public class AppointmentModel {
    final Salutation salutation;
    final String name, lastName, email, phone, note;
    final AppointmentType appointmentType;
    final LocalDateTime birthday;

}
