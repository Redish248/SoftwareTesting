package tests;

import config.WebDriverConfiguration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HotelPage;
import pages.SearchPage;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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
        String mainWindow = webDriver.getWindowHandle();
        Set<String> handles = webDriver.getWindowHandles();
        for (String handle : handles) {
            if (!handle.equals(mainWindow)) {
                webDriver.switchTo().window(handle);
                break;
            }
        }
    }

    @Test
    @Order(1)
    public void testNavigationToInfo() {
        hotelPage.navigateToSomewhere(1);
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 10);
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='hprt-form']")));
        assertTrue(webDriver.findElement(By.xpath("//*[@id='hprt-form']")).isDisplayed());
    }

    @Test
    @Order(2)
    public void testNavigationToComfortInfo() {
        hotelPage.navigateToSomewhere(2);
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 10);
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='HotelFacilities']")));
        assertTrue(webDriver.findElement(By.xpath("//*[@id='hp-facilities-sliding']/div[1]/div[2]/div")).isDisplayed());
    }

    @Test
    @Order(3)
    public void testNavigationToConditions() {
        hotelPage.navigateToSomewhere(3);
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 10);
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='hotelPoliciesInc']")));
        assertTrue(webDriver.findElement(By.xpath("//*[@id='hotelPoliciesInc']")).isDisplayed());
    }

    @Test
    @Order(4)
    public void testNavigationToReviews() {
        hotelPage.navigateToSomewhere(4);
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 10);
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='review_list_score_container']")));
        assertTrue(webDriver.findElement(By.xpath("//*[@id='review_list_score_container']")).isDisplayed());
    }

    @Test
    @Order(5)
    public void testBooking() {
        String hotelTitle = hotelPage.getHotelTitle().replaceAll("\n", " ").replaceAll("<","").replaceAll(">","");
        String arrivalDate = hotelPage.getArrivalDate(); //вторник, 24 ноября 2020
        String departureDate = hotelPage.getDepartureDate();
        hotelPage.bookHotel();
        //WebDriverWait webDriverWait = new WebDriverWait(webDriver, 10);
        //webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='retain-leaving-users__modal']/div/div/button")));
       // webDriver.findElement(By.xpath("//*[@id='retain-leaving-users__modal']/div/div/button")).click();
        String newDate_1 = webDriver.findElement(By.xpath("//*[@id='fullwidth']/div[2]/div[1]/div[2]/div/div[1]/div[1]/time/span[1]")).getText(); //вт, 24 нояб. 2020
        String newDate_2 = webDriver.findElement(By.xpath("//*[@id='fullwidth']/div[2]/div[1]/div[2]/div/div[1]/div[2]/time/span[1]")).getText();
        assertAll(
                () -> assertTrue(hotelTitle.contains(webDriver.findElement(By.xpath("//*[@id='bookOverviewTop']/div[1]/div[2]/div[1]/h1")).getText())),
                () -> assertTrue(arrivalDate.substring(arrivalDate.lastIndexOf(",") + 2).startsWith(newDate_1.substring(newDate_1.indexOf(",") + 2, newDate_1.indexOf(".")))),
                () -> assertTrue(departureDate.substring(departureDate.lastIndexOf(",") + 2).startsWith(newDate_2.substring(newDate_2.indexOf(",") + 2, newDate_2.indexOf("."))))
        );
    }

    @AfterEach
    public void tearDown(){
        webDriver.quit();
    }
}
