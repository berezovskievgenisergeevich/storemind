package brax.web.data;

import brax.config.BraxConfig;
import brax.config.BraxLocalizationConfig;
import brax.helpers.BRAXCustomerProvider;
import brax.helpers.BraxStoreProvider;
import com.github.javafaker.Faker;
import org.aeonbits.owner.ConfigFactory;
import se.web.components.Language;
import se.web.data.model.Customer;
import se.web.data.model.Store;

import java.util.Locale;

public class BraxTestData {
    BraxConfig projectConfig = ConfigFactory.create(BraxConfig.class, System.getProperties());
    public Store[] stores = BraxStoreProvider.stores;
    public final BraxLocalizationConfig APP_TEXT = ConfigFactory.create(BraxLocalizationConfig.class, System.getProperties());

    private final Faker faker = new Faker(new Locale("en-US"));
    public final String CUSTOMER_ID = projectConfig.getCustomerId();
    public final String RANDOM_EMAIL = faker.internet().emailAddress();
    public final String RANDOM_PASS = faker.internet().password();
    public final Language LANGUAGE_TO_SELECT = Language.Français;
    public final Customer SEARCH_CUSTOMER = BRAXCustomerProvider.getSearchCustomer();
    public final Customer CUSTOMER_AGE_LESS_18 = BRAXCustomerProvider.getCustomerWithAgeLess18();
    public final Customer SEARCH_NOT_EXISTING_CUSTOMER = BRAXCustomerProvider.getNotExistingCustomer();
}
