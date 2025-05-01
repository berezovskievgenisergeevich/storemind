package web.data;

import com.github.javafaker.Faker;
import config.LocalizationConfig;
import config.TestsConfig;
import helpers.CustomerProvider;
import helpers.StoreProvider;
import org.aeonbits.owner.ConfigFactory;
import web.components.Language;
import web.data.model.Customer;
import web.data.model.Store;

import java.util.Locale;

public class TestData {
    private final TestsConfig config = ConfigFactory.create(TestsConfig.class, System.getProperties());
    public final LocalizationConfig APP_TEXT = ConfigFactory.create(LocalizationConfig.class, System.getProperties());
    private final Faker faker = new Faker(new Locale("en-US"));
    public final String CUSTOMER_ID = config.getCustomerId();
    public final String RANDOM_EMAIL = faker.internet().emailAddress();
    public final String RANDOM_PASS = faker.internet().password();
    public Store[] stores = StoreProvider.stores;
    public final Language LANGUAGE_TO_SELECT = Language.English;
    public final Customer SEARCH_CUSTOMER = CustomerProvider.searchCustomer;
    public final Customer SEARCH_NOT_EXISTING_CUSTOMER = CustomerProvider.notExistingCustomer;
    public final String SEARCH_MULTIPLE_CUSTOMERS = "Eugene";
    public final String SEARCH_ARTICLE_NAME = "Polo";
    public final String SEARCH_ARTICLE_FULL_NO = "01.298950.0035";
    public final String SEARCH_ARTICLE_TWO_PARTS_OF_NO = "01.298950";
    public final String SEARCH_ARTICLE_ONE_PART_OF_NO = "298950";
    public final String SEARCH_ARTICLE_EAN = "4048872221235";
    public final String ARTICLE_WITH_MONOGRAM_EAN = "4048872080283";


}
