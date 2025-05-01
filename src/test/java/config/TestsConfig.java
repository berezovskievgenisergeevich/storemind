package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:${env}.properties",
        "classpath:local.properties"
})
public interface TestsConfig extends Config {
    @Key("browser")
    String browser();

    @Key("browser.size")
    String browserSize();

    @Key("app.url")
    String baseUrl();

    @Key("browser.version")
    String browserVersion();

    @Key("api.url")
    String apiUrl();

    @Key("remote")
    Boolean isRemote();

    @Key("remote.url")
    String getRemoteUrl();

    @Key("app.email")
    String getEmail();

    @Key("app.pass")
    String getPass();

    @Key("app.customerId")
    String getCustomerId();

    @Key("pageLoadTimeout")
    long pageLoadTimeout();

    @Key("api.project.name")
    String apiProjectName();

    @Key("api.email")
    String apiEmail();

    @Key("api.pass")
    String apiPass();
}
