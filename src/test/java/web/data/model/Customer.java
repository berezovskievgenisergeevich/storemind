package web.data.model;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@NonNull

@RequiredArgsConstructor
public class Customer {
    final Salutation salutation;
    final String id, name, lastName, zip, city, street, homeNr, email;
    LocalDate birthday;
    String additionalAddress, phone, country;
}
