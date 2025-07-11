package se.api.model.customer.create_customer;

import lombok.Data;
import lombok.NonNull;

@Data
@NonNull
public class CreateCustomerApiModel {
    public final String api = "create-customer";
    public Body body;
}
