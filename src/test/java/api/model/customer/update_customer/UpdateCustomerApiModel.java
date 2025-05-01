package api.model.customer.update_customer;
import lombok.Data;
import lombok.NonNull;

@Data
@NonNull
public class UpdateCustomerApiModel {
    public final String api = "update-customer";
    public Body body;
}
