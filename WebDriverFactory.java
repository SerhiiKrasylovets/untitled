import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverFactory {
    private static WebDriver driver;

    private WebDriverFactory() {
    }

    public static WebDriver getWebDRiverInstance() {
        if (driver == null) {
            driver = new FirefoxDriver();
        }
        return driver;
    }
}