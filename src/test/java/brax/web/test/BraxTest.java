package brax.web.test;

import brax.config.BraxConfig;
import brax.web.data.BraxTestData;
import com.codeborne.selenide.Configuration;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import se.web.test.BaseTest;

@Tag("BRAX")
@Tag("all")
public class BraxTest extends BaseTest {
    BraxTestData testData = new BraxTestData();
    static BraxConfig projectConfig;

    @BeforeAll
    static void setUpProject() {
        projectConfig = ConfigFactory.create(BraxConfig.class, System.getProperties());
        Configuration.baseUrl = projectConfig.baseUrl();

    }
}
