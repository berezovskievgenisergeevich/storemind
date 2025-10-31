package se.web.data;

import com.github.javafaker.Faker;
import se.config.LocalizationConfig;
import se.config.SeConfig;
import se.config.TestsConfig;
import se.helpers.CustomerProvider;
import se.helpers.RandomGenerator;
import se.helpers.StoreProvider;
import org.aeonbits.owner.ConfigFactory;
import se.web.components.Language;
import se.web.data.model.Customer;
import se.web.data.model.Store;

import java.util.List;
import java.util.Locale;

public class TestData {
    private final TestsConfig config = ConfigFactory.create(TestsConfig.class, System.getProperties());
    private final SeConfig projectConfig = ConfigFactory.create(SeConfig.class, System.getProperties());

    public final LocalizationConfig APP_TEXT = ConfigFactory.create(LocalizationConfig.class, System.getProperties());
    private final Faker faker = new Faker(new Locale("en-US"));
    public final String CUSTOMER_ID = projectConfig.getCustomerId();
    public final String RANDOM_EMAIL = faker.internet().emailAddress();
    public final String RANDOM_PASS = faker.internet().password();
    public Store[] stores = StoreProvider.stores;
    public final Language LANGUAGE_TO_SELECT = Language.English;
    public final Customer SEARCH_CUSTOMER = CustomerProvider.searchCustomer;
    public final Customer SEARCH_NOT_EXISTING_CUSTOMER = CustomerProvider.notExistingCustomer;
    public final String SEARCH_MULTIPLE_CUSTOMERS = "Eugene";
    public final String SEARCH_ARTICLE_NAME = "Polo";
    public final String SEARCH_ARTICLE_FULL_NO = "01.474980.0001";
    public final String SEARCH_ARTICLE_TWO_PARTS_OF_NO = "01.474980";
    public final String SEARCH_ARTICLE_ONE_PART_OF_NO = "474980";
    public final String SEARCH_ARTICLE_EAN = "4048869132797";
    public final String ARTICLE_WITH_MONOGRAM_EAN = "4048872002513";
    public final List<String> forbiddenMonogramOptions = List.of("SS", "HH", "HJ", "KZ", "NS", "SA");
    public final String MONOGRAM = RandomGenerator.getRandomMonogram(faker, forbiddenMonogramOptions);


}
