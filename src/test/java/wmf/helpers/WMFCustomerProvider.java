package wmf.helpers;

import se.web.data.model.Salutation;

public class WMFCustomerProvider {
    public static final se.web.data.model.Customer notExistingCustomer = new se.web.data.model.Customer(Salutation.He, "3151020", "automation", "not_existing_customer", "1234", "stad", "str", "1", "not_existing_customer@mail.com");
    public static final se.web.data.model.Customer editCustomer = new se.web.data.model.Customer(Salutation.He, "2030050925", "User", "Test", "10115", "some info", "adress and home 1a", "", "berezovskievgenisergeevich@gmail.com");
    public static final se.web.data.model.Customer searchCustomer = new se.web.data.model.Customer(Salutation.He, "2030050925", "Eugene", "BER", "88448", "Attenweiler_2", "Attenweiler Straße 12", "", "eugene.berazouski@innomos.com");
    public static final se.web.data.model.Customer multipleSearchCustomer = new se.web.data.model.Customer(Salutation.He, "2030050925", "test", "test", "88448", "Attenweiler_2", "Attenweiler Straße 12", "", "eugene.berazouski@innomos.com");

}
