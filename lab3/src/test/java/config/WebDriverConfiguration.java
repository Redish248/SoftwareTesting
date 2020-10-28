package config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverConfiguration {

    private static final String BOOKING_URL = "https://booking.com";

    public enum Browser {
        FIREFOX, CHROME
    }

    public static WebDriver getWebDriver(Browser browser) {
        WebDriver driver;
        switch (browser) {
            case CHROME: driver = getChromeDriver(); break;
            case FIREFOX:
            default: driver = getFirefoxDriver(); break;
        }
        driver.manage().window().maximize();
        driver.get(BOOKING_URL);
        return driver;
    }

    private static FirefoxDriver getFirefoxDriver() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        return new FirefoxDriver();
    }

    private static ChromeDriver getChromeDriver() {
        System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
        return new ChromeDriver();
    }
}
