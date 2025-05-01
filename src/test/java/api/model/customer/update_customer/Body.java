package api.model.customer.update_customer;
import lombok.Data;
import lombok.NonNull;

@Data
@NonNull
public class Body {
    public String id;
    public Customer customer;
}
