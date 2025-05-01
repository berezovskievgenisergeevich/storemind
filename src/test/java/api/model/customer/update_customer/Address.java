package api.model.customer.update_customer;

import lombok.Data;
import lombok.NonNull;
import web.data.model.Customer;

@Data
@NonNull
public class Address {
    public String city;
    public String title;
    public String email;
    public String street;
    public String firstName;
    public String lastName;
    public String salutation;
    public String postalCode;
    public String streetNumber;
    public String addressLine1;
    public String country;
    public String phone;
    public String mobile;
    public String addressLine2;

    public Address(Customer customer) {
        setFirstName(customer.getName());
        setLastName(customer.getLastName());
        setCity(customer.getCity());
        setCountry(customer.getCountry());
        setAddressLine1(customer.getAdditionalAddress());
        setEmail(customer.getEmail());
        setSalutation("MR");
        setPostalCode(customer.getZip());
        setStreetNumber(customer.getHomeNr());
        setStreet(customer.getStreet());
    }

    public Address() {
    }
}
