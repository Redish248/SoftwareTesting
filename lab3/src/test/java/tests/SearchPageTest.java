package tests;

import config.WebDriverConfiguration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.MainPage;
import pages.SearchPage;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SearchPageTest {
    private WebDriver webDriver;
    private MainPage mainPage;
    private SearchPage searchPage;

    @BeforeEach
    public void setUp() {
        webDriver = WebDriverConfiguration.getWebDriver(WebDriverConfiguration.Browser.CHROME);
        mainPage = new MainPage(webDriver);
        searchPage = new SearchPage(webDriver);
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='onetrust-accept-btn-handler']")));
        webDriver.findElement(By.xpath("//*[@id='onetrust-accept-btn-handler']")).click();
        webDriver.navigate().to("https://www.booking.com/searchresults.ru.html");
    }

    @Test
    @Order(1)
    public void testEmptyCity() {
        searchPage.enterCity("");
        searchPage.clickSearch();
        assertTrue(searchPage.isCityErrorDisplayed());
    }

    @Test
    @Order(2)
    public void testCorrectCity() {
        searchPage.enterCity("Saint Petersburg");
        searchPage.clickSearch();
        assertTrue(searchPage.checkFirstResultCity());
    }

    @Test
    @Order(3)
    public void testStrangeInput() {
        searchPage.enterCity("а");
        searchPage.clickSearch();
        assertTrue(searchPage.getFirstResultCity().contains("Адлер"));
    }

    @Test
    @Order(4)
    public void testRealFirstData() {
        searchPage.enterCity("Saint Petersburg");
        searchPage.setArrivalDate("2020-12-09");
        assertTrue(searchPage.calendarSecond.getText().contains("10 декабря"));
    }

    @Test
    @Order(5)
    public void testRealSecondData() {
        searchPage.enterCity("Saint Petersburg");
        searchPage.setDepartureDate("2020-12-10");
        assertTrue(searchPage.calendarFirst.getText().contains("9 декабря"));
    }

    @Test
    @Order(6)
    public void testFirstDataBeforeSecond() {
        searchPage.enterCity("Saint Petersburg");
        searchPage.setArrivalDate("2020-12-09");
        searchPage.setDepartureDate("2020-12-10");
        assertTrue(searchPage.calendarFirst.getText().contains("9 декабря"));
    }

    @Test
    @Order(7)
    public void testFirstDataAfterSecond() {
        searchPage.enterCity("Saint Petersburg");
        searchPage.setArrivalDate("2020-12-15");
        searchPage.setDepartureDate("2020-12-10");
        assertTrue(searchPage.calendarSecond.getText().contains("16 декабря"));
    }

    @Test
    @Order(8)
    public void testFirstDataInPast() {
        searchPage.enterCity("Saint Petersburg");
        searchPage.clickDateInPastFirst();
        assertTrue(searchPage.calendarFirst.getText().contains("Дата заезда"));
    }

    @Test
    @Order(9)
    public void testSecondDataInPast() {
        searchPage.enterCity("Saint Petersburg");
        searchPage.clickDateInPastSecond();
        assertTrue(searchPage.calendarSecond.getText().contains("Дата отъезда"));
    }

    @Test
    @Order(10)
    public void testNightAmount() {
        searchPage.enterCity("Адлер");
        searchPage.setUpDate();
        searchPage.clickSearch();
        assertTrue(searchPage.getDaysAndPeopleText().contains("6 ночей"));
    }

    @Test
    @Order(11)
    public void testAdultAmount() {
        searchPage.enterCity("Адлер");
        searchPage.setUpDate();
        searchPage.selectAdultAmount(4);
        searchPage.clickSearch();
        assertTrue(searchPage.getDaysAndPeopleText().contains("4 взрослых"));
    }

    @Test
    @Order(12)
    public void testChildAmount() {
        searchPage.enterCity("Saint Petersburg");
        searchPage.setUpDate();
        searchPage.selectChildAmount(3);
        searchPage.clickSearch();
        assertTrue(searchPage.getDaysAndPeopleText().contains("2 ребенка"));
    }

    @Test
    @Order(13)
    public void testEmptyChildAmount() {
        searchPage.enterCity("Сочи");
        searchPage.setUpDate();
        searchPage.selectChildAmount(1);
        searchPage.clickSearch();
        assertFalse(searchPage.getDaysAndPeopleText().contains("ребенок"));
    }

    @Test
    @Order(14)
    public void testEmptyChildren() {
        searchPage.enterCity("Сочи");
        searchPage.setUpDate();
        searchPage.selectChildAmount(1);
        searchPage.clickSearch();
        assertEquals(0, searchPage.getChildAgeSize());
    }

    @Test
    @Order(15)
    public void testTwoChildren() {
        searchPage.enterCity("Сочи");
        searchPage.setUpDate();
        searchPage.selectChildAmount(3);
        searchPage.clickSearch();
        assertEquals(2, searchPage.getChildAgeSize());
    }

    @AfterEach
    public void tearDown(){
        webDriver.quit();
    }
}
