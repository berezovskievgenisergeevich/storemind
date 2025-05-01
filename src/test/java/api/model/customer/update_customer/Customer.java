package api.model.customer.update_customer;
import lombok.Data;
import lombok.NonNull;

@Data
@NonNull
public class Customer {
    public String birthday;
    public String customerId;
    public String language;
    public Address address;

    public Customer() {
    }

    public Customer(web.data.model.Customer customer) {
        this.customerId = customer.getId();
        this.address = new Address(customer);
    }
}
