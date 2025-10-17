package wmf.web.data;

import com.github.javafaker.Faker;
import org.aeonbits.owner.ConfigFactory;
import se.web.components.Language;
import se.web.data.model.Customer;
import se.web.data.model.Store;
import wmf.config.WMFConfig;
import wmf.config.WMFLocalizationConfig;
import wmf.helpers.WMFCustomerProvider;
import wmf.helpers.WmfStoreProvider;

import java.util.Locale;

public class WMFTestData {
    WMFConfig projectConfig = ConfigFactory.create(WMFConfig.class, System.getProperties());
    public Store[] stores = WmfStoreProvider.stores;
    public final WMFLocalizationConfig APP_TEXT = ConfigFactory.create(WMFLocalizationConfig.class, System.getProperties());

    private final Faker faker = new Faker(new Locale("en-US"));
    public final String CUSTOMER_ID = projectConfig.getCustomerId();
    public final String RANDOM_EMAIL = faker.internet().emailAddress();
    public final String RANDOM_PASS = faker.internet().password() + "!Qw1";
    public final String CREATE_CUSTOMER_PASS = "QWEasd123!";
    public final Language LANGUAGE_TO_SELECT = Language.Deutsch;
    public final Customer SEARCH_CUSTOMER = WMFCustomerProvider.searchCustomer;
    public final Customer SEARCH_NOT_EXISTING_CUSTOMER = WMFCustomerProvider.notExistingCustomer;
    public final Customer SEARCH_MULTIPLE_CUSTOMERS = WMFCustomerProvider.multipleSearchCustomer;
    public final String SEARCH_ARTICLE_NAME = "KAISER";
    public final String SEARCH_ARTICLE_FULL_NO = "3201112501";
    public final String SEARCH_ARTICLE_EAN = "4000530691651";
    public final String SEARCH_ARTICLE_INVALID_EAN = "40005306916510";
    public final String SEARCH_ARTICLE_INVALID_NAME = "invalid_article_name";
}
