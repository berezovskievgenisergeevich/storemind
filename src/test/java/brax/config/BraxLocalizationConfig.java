package brax.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:${project}/${language}.properties",
        "classpath:brax/de.properties"
})
public interface BraxLocalizationConfig extends Config {
    @Config.Key("login.login_button")
    String loginButton();

    @Config.Key("login.merchant_id_text")
    String loginPageMerchantIdText();

    @Config.Key("login.login_merchant_button")
    String loginMerchantButton();

    @Config.Key("cockpit.title")
    String cockpitTitle();

    @Config.Key("cockpit.customer_section_name")
    String cockpitCustomerSectionName();

    @Config.Key("cockpit.customer_search_bar")
    String cockpitCustomerSearchBar();

    @Config.Key("cockpit.customer_scanner")
    String cockpitCustomerScanner();

    @Config.Key("cockpit.create_new_customer")
    String cockpitCreateNewCustomer();

    @Config.Key("cockpit.advanced_customer_search")
    String cockpitAdvancedCustomerSearch();

    @Config.Key("create_new_customer_title")
    String createNewCustomerTitle();

    @Config.Key("advanced_customer_search_title")
    String advancedCustomerSearchTitle();

    @Config.Key("customer_search.no_result_in_table")
    String customerSearchNoResult();

    @Config.Key("scanner.customer_not_found")
    String scannerCustomerNotFound();

    @Config.Key("customer.title")
    String customerTitle();

    @Config.Key("cockpit.catalog_section_name")
    String cockpitCatalogSectionName();

    @Config.Key("cockpit.scanner")
    String cockpitScanner();

    @Config.Key("order.home.type_name")
    String homeOrderTypeName();

    @Config.Key("order.store.type_name")
    String storeOrderTypeName();

    @Config.Key("customer.error.salutation")
    String customerErrorSalutation();

    @Config.Key("customer.error.name")
    String customerErrorName();

    @Config.Key("customer.error.last_name")
    String customerErrorLastName();

    @Config.Key("customer.error.legal_info")
    String customerErrorLegalInfo();

    @Config.Key("customer.error.street")
    String customerErrorStreet();

    @Config.Key("customer.error.city")
    String customerErrorCity();

    @Config.Key("customer.error.zip")
    String customerErrorZIP();

    @Config.Key("customer.error.home_nr")
    String customerErrorHomeNr();

    @Config.Key("customer.error.signature")
    String customerErrorSignature();

    @Config.Key("customer.error.email")
    String customerErrorEmail();

    @Config.Key("customer.error.customer_already_exists")
    String customerAlreadyExistsError();

    @Config.Key("order.create_new_customer.customer_exists")
    String orderCreationCustomerAlreadyExists();

    @Config.Key("monogram.restrictions.text")
    String monogramRestrictionsText();

    @Config.Key("monogram.store_order")
    String monogramCantBeDeliveredToStoreText();

    @Config.Key("customer.error.birthday_empty")
    String customerErrorBirthdayEmpty();

    @Config.Key("customer.newsletter_message")
    String customerNusletterMessage();

    @Config.Key("customer.error.phone")
    String customerErrorPhone();

    @Config.Key("customer.error.mobile")
    String customerErrorMobile();

    @Config.Key("customer.error.select_calls")
    String customerErrorSelectCalls();

    @Config.Key("catalog.filter_women")
    String catalogFilterWomen();

    @Config.Key("catalog.filter_men")
    String catalogFilterMen();

    @Config.Key("error.scanner.article_not_found")
    String errorScannerArticleNotFound();
}
