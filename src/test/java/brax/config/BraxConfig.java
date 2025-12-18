package brax.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:brax/brax.properties"

})
public interface BraxConfig extends Config {
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
}
