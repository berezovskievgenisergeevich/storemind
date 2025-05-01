package helpers;

import com.codeborne.selenide.Selenide;
import config.TestsConfig;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Selenide.sessionId;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.openqa.selenium.logging.LogType.BROWSER;

public class Attach {
    @Attachment(value = "{attachName}", type = "image/png")
    public static byte[] screenshotAs(String attachName) {
        return ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Page source", type = "text/plain")
    public static byte[] pageSource() {
        return getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8);
    }

    @Attachment(value = "{attachName}", type = "text/plain")
    public static String attachAsText(String attachName, String message) {
        return message;
    }

    /**
     * Firefox does not support the function get logs from the console
     */
    public static void browserConsoleLogs(TestsConfig config) {
        if (!config.browser().equalsIgnoreCase("firefox"))
            attachAsText(
                    "Browser console logs",
                    String.join("\n", Selenide.getWebDriverLogs(BROWSER))
            );
    }

    @Attachment(value = "Video", type = "text/html", fileExtension = ".html")
    public static String addVideo(TestsConfig config) {
        if (config.isRemote())
            return "<html><body><video width='100%' height='100%' controls autoplay><source src='"
                    + getVideoUrl(config.getRemoteUrl())
                    + "' type='video/mp4'></video></body></html>";

        return "<html><body>Video not supported</body></html>";
    }

    /**
     * The video is recorded on the solenoid side,
     * so it will not be available when running locally
     */
    public static URL getVideoUrl(String url) {
        String videoUrl = url + "/video/" + sessionId() + ".mp4";
        try {
            return new URL(videoUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
