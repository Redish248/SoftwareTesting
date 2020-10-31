package tests;

import config.WebDriverConfiguration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import pages.MainPage;
import pages.SearchPage;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchPageTest {
    private static WebDriver webDriver;
    private static MainPage mainPage;
    private static SearchPage searchPage;

    @BeforeAll
    public static void setUp() {
        webDriver = WebDriverConfiguration.getWebDriver(WebDriverConfiguration.Browser.CHROME);
        mainPage = new MainPage(webDriver);
        searchPage = new SearchPage(webDriver);
        webDriver.navigate().to("https://www.booking.com/searchresults.ru.html");
    }

    @Test
    public void testEmptyCity() {
        searchPage.enterCity("");
        searchPage.clickSearch();
        assertTrue(searchPage.isCityErrorDisplayed());
    }

    @Test
    public void testCorrectCity() {
        searchPage.enterCity("Saint Petersburg");
        searchPage.clickSearch();
        assertTrue(searchPage.checkFirstResultCity());
    }

    @Test
    public void testStrangeInput() {
        searchPage.enterCity("а");
        searchPage.clickSearch();
        assertTrue(searchPage.getFirstResultCity().contains("Адлер"));
    }

    @Test
    public void testRealFirstData() {
        searchPage.enterCity("Saint Petersburg");
        searchPage.enterDateFirst(4);
        assertTrue(searchPage.calendarSecond.getText().contains("5 ноября"));
    }

    @Test
    public void testRealSecondData() {
        searchPage.enterCity("Saint Petersburg");
        searchPage.enterDateSecond();
        assertTrue(searchPage.calendarFirst.getText().contains("4 ноября"));
    }

    @Test
    public void testFirstDataBeforeSecond() {
        searchPage.enterCity("Saint Petersburg");
        searchPage.enterDateFirst(4);
        searchPage.enterDateSecond();
        assertTrue(searchPage.calendarFirst.getText().contains("4 ноября"));
    }

    @Test
    public void testFirstDataAfterSecond() {
        searchPage.enterCity("Saint Petersburg");
        searchPage.enterDateFirst(11);
        searchPage.enterDateSecond();
        assertTrue(searchPage.calendarFirst.getText().contains("10 ноября"));
    }

    @Test
    public void testFirstDataInPast() {
        searchPage.enterCity("Saint Petersburg");
        searchPage.clickDateInPastFirst();
        assertTrue(searchPage.calendarFirst.getText().contains("Дата заезда"));
    }

    @Test
    public void testSecondDataInPast() {
        searchPage.enterCity("Saint Petersburg");
        searchPage.clickDateInPastSecond();
        assertTrue(searchPage.calendarSecond.getText().contains("Дата отъезда"));
    }

    @Test
    public void testNightAmount() {
        searchPage.enterCity("Saint Petersburg");
        searchPage.setUpDate();
        searchPage.clickSearch();
        assertTrue(searchPage.getDaysAndPeopleText().contains("8 ночей"));
    }

    @Test
    public void testAdultAmount() {
        searchPage.enterCity("Saint Petersburg");
        searchPage.setUpDate();
        searchPage.selectAdultAmount(4);
        searchPage.clickSearch();
        assertTrue(searchPage.getDaysAndPeopleText().contains("4 взрослых"));
    }

    @Test
    public void testChildAmount() {
        searchPage.enterCity("Saint Petersburg");
        searchPage.setUpDate();
        searchPage.selectChildAmount(3);
        searchPage.clickSearch();
        assertTrue(searchPage.getDaysAndPeopleChildText().contains("2 ребенка"));
    }

    @Test
    public void testEmptyChildAmount() {
        searchPage.enterCity("Сочи");
        searchPage.setUpDate();
        searchPage.selectChildAmount(1);
        searchPage.clickSearch();
        assertFalse(searchPage.getDaysAndPeopleText().contains("ребенок"));
    }

    @Test
    public void testEmptyChildren() {
        searchPage.enterCity("Сочи");
        searchPage.setUpDate();
        searchPage.selectChildAmount(1);
        searchPage.clickSearch();
        assertThrows(NoSuchElementException.class, () -> searchPage.isChildFirstDisplayed());
    }

    @Test
    public void testTwoChildren() {
        searchPage.enterCity("Сочи");
        searchPage.setUpDate();
        searchPage.selectChildAmount(3);
        searchPage.clickSearch();
        //FIXME: переделать на размер size()
        assertAll(
                () -> assertTrue(searchPage.isChildFirstDisplayed()),
                () -> assertTrue(searchPage.isChildSecondDisplayed()),
                () -> assertThrows(NoSuchElementException.class, () -> searchPage.isChildThirdDisplayed())
        );
    }

    @AfterAll
    public static void tearDown(){
        webDriver.quit();
    }
}
