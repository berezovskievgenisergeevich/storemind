package se.web.test;

import com.codeborne.selenide.Configuration;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import se.config.SeConfig;
import se.web.data.TestData;

@Tag("SE")
@Tag("all")
public class SeTest extends BaseTest {
    public TestData testData = new TestData();
    static SeConfig projectConfig;

    @BeforeAll
    static void setUpProject() {
        projectConfig = ConfigFactory.create(SeConfig.class, System.getProperties());
        Configuration.baseUrl = projectConfig.baseUrl();

    }
}
