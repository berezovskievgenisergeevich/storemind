package wmf.web.test;

import com.codeborne.selenide.Configuration;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import se.web.test.BaseTest;
import wmf.config.WMFConfig;
import wmf.web.data.WMFTestData;

@Tag("WMF")
@Tag("all")
public class WMFTest extends BaseTest {
    public WMFTestData testData = new WMFTestData();
    public static WMFConfig projectConfig;

    @BeforeAll
    static void setUpProject() {
        projectConfig = ConfigFactory.create(WMFConfig.class, System.getProperties());
        Configuration.baseUrl = projectConfig.baseUrl();
    }
}
