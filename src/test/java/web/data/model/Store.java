package web.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@NonNull
@AllArgsConstructor
public class Store {
    String email, pass, customerId, name;
}
