package wmf.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:wmf/wmf.properties"

})
public interface WMFConfig extends Config {
    @Key("app.url")
    String baseUrl();

    @Key("app.email")
    String getEmail();

    @Key("app.pass")
    String getPass();

    @Key("app.customerId")
    String getCustomerId();

    @Key("api.project.name")
    String apiProjectName();

    @Key("api.email")
    String apiEmail();

    @Key("api.pass")
    String apiPass();

    @Key("create.customer.login")
    String createCustomerLogin();

    @Key("create.customer.pass")
    String createCustomerPass();

    @Key("online.appointment.url")
    String onlineAppointmentUrl();

}
