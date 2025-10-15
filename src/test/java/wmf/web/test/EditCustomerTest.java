package wmf.web.test;

import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import se.helpers.CustomerProvider;
import se.web.data.model.Customer;
import se.web.pages.Login;
import wmf.helpers.WMFCustomerProvider;
import wmf.web.pages.WMFCockpit;

@Story("[UI] Edit Customer")
public class EditCustomerTest extends WMFTest {
    @Test
    @Tags({@Tag("customer"), @Tag("edit_customer"), @Tag("regression"), @Tag("ui")})
    @DisplayName("Edit existing customer data")
    void checkCustomerEdit() {
        Customer customer = new CustomerProvider().createNewRandomCustomer();
        new Login().doLogin(testData.stores[0]);
        new WMFCockpit().clickCustomerSearch()
                .searchCustomerByEmail(WMFCustomerProvider.editCustomer);
    }
}
