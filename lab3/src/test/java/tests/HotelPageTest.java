package tests;

import config.WebDriverConfiguration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HotelPage;
import pages.SearchPage;

import java.util.concurrent.TimeUnit;

public class HotelPageTest {

    private WebDriver webDriver;
    private SearchPage searchPage;
    private HotelPage hotelPage;

    @BeforeEach
    public void setUp() {
        webDriver = WebDriverConfiguration.getWebDriver(WebDriverConfiguration.Browser.CHROME);
        searchPage = new SearchPage(webDriver);
        hotelPage = new HotelPage(webDriver);
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='onetrust-accept-btn-handler']")));
        webDriver.findElement(By.xpath("//*[@id='onetrust-accept-btn-handler']")).click();
        webDriver.navigate().to("https://www.booking.com/searchresults.ru.html");
        searchPage.goToSomeHotel();
    }

    @AfterEach
    public void tearDown(){
        webDriver.quit();
    }
}
