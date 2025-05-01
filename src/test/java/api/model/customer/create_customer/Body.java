package api.model.customer.create_customer;

import lombok.Data;
import lombok.NonNull;

@Data
@NonNull
public class Body {
    public String initialStoreId;
    public String birthday;
    public String language;
    public String digitalInfoUrl;
    public Address address;

}
