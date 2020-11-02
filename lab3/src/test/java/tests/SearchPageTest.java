package tests;

import config.WebDriverConfiguration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HotelPage;
import pages.SearchPage;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SearchPageTest {
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
    }

    @Test
    @Order(1)
    public void testEmptyCity() {
        searchPage.enterCity("");
        searchPage.setUpDate();
        searchPage.clickSearch();
        assertTrue(searchPage.isCityErrorDisplayed());
    }

    @Test
    @Order(2)
    public void testCorrectCity() {
        searchPage.enterCity("Saint Petersburg");
        searchPage.setUpDate();
        searchPage.clickSearch();
        assertTrue(searchPage.checkFirstResultCity());
    }

    @Test
    @Order(3)
    public void testStrangeInput() {
        searchPage.setUpDate();
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

    @Test
    @Order(16)
    public void showMap() {searchPage.enterCity("Сочи");
        searchPage.setUpDate();
        searchPage.clickSearch();
        searchPage.showMap();
        assertTrue(searchPage.isMapImageWasShown());
    }

    @Test
    @Order(17)
    public void checkTitleInHotelByTitleClick() {
        searchPage.setUpSearch();
        String title = searchPage.getTitleText();
        searchPage.goToTitle();
        String mainWindow = webDriver.getWindowHandle();
        Set<String> handles = webDriver.getWindowHandles();
        for (String handle : handles) {
            if (!handle.equals(mainWindow)) {
                webDriver.switchTo().window(handle);
                break;
            }
        }
        assertTrue(title.contains(hotelPage.getHotelTitle()));
    }

    @Test
    @Order(18)
    public void checkTitleInHotelByButtonClick() {
        searchPage.setUpSearch();
        String title = searchPage.getTitleText();
        searchPage.chooseHotel();
        String mainWindow = webDriver.getWindowHandle();
        Set<String> handles = webDriver.getWindowHandles();
        for (String handle : handles) {
            if (!handle.equals(mainWindow)) {
                webDriver.switchTo().window(handle);
                break;
            }
        }
        assertTrue(title.contains(hotelPage.getHotelTitle()));
    }

    @Test
    @Order(19)
    @Disabled
    //FIXME select text
    public void checkSearchTextInHotel() {
        searchPage.setUpSearch();
        String city = searchPage.getCityText();
        String date1 = searchPage.getArrivalDateText();
        String date2 = searchPage.getDepartureDateText();
        String adultAmount = searchPage.getAdultAmountText();
        String childAmount = searchPage.getChildAmountText();
        String roomAmount = searchPage.getRoomAmountText();
        searchPage.chooseHotel();
        String mainWindow = webDriver.getWindowHandle();
        Set<String> handles = webDriver.getWindowHandles();
        for (String handle : handles) {
            if (!handle.equals(mainWindow)) {
                webDriver.switchTo().window(handle);
                break;
            }
        }

        assertAll(
                () -> assertEquals(city, hotelPage.getCity()),
                () -> assertEquals(date1, hotelPage.getArrivalDate()),
                () -> assertEquals(date2, hotelPage.getDepartureDate()),
                () -> assertEquals(adultAmount, hotelPage.getAdultAmount()),
                () -> assertEquals(childAmount, hotelPage.getChildAmount()),
                () -> assertEquals(roomAmount, hotelPage.getRoomAmount()),
                () -> assertEquals(2, hotelPage.getChildAgeSize())
        );
    }

    @Test
    @Order(20)
    public void checkCurrency() {
        searchPage.setUpSearch();
        searchPage.selectCurrency("EUR");
        assertTrue(searchPage.firstResult.getText().contains("€"));
    }

    @Test
    @Order(21)
    public void checkPaymentFilter() throws InterruptedException {
        searchPage.enterCity("Калининград");
        searchPage.setArrivalDate("2020-11-24");
        searchPage.setDepartureDate("2020-11-25");
        searchPage.clickSearch();
        searchPage.selectPaymentCheckbox();
        String payment = webDriver.findElement(By.xpath("//*[@id='hotellist_inner']/div[1]/div[2]/div[3]/div/div/div/div/div[2]/div[1]/div[2]/div/div")).getText();
        assertTrue(Integer.parseInt(payment.substring(payment.lastIndexOf("цена")+5,payment.length()-5).replaceAll(" ", "")) <= 4620);
    }

    @Test
    @Order(22)
    public void testStars() throws InterruptedException {
        searchPage.enterCity("Калининград");
        searchPage.setArrivalDate("2020-11-24");
        searchPage.setDepartureDate("2020-11-25");
        searchPage.clickSearch();
        searchPage.selectStarRating(3);
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='filter-selected-filters']/div[2]/a")));
        int starAmount = webDriver.findElements(By.xpath("//*[@id='hotellist_inner']/div[1]/div[2]/div[1]/div[1]/div[1]/span/span/span/span/span")).size();
        assertEquals(3, starAmount);
    }

    @Test
    @Order(23)
    public void checkRating() {
        searchPage.enterCity("Калининград");
        searchPage.setArrivalDate("2020-11-24");
        searchPage.setDepartureDate("2020-11-25");
        searchPage.clickSearch();
        searchPage.selectRating(2);
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='right']/div[3]/div")));
        double rating = Double.parseDouble(webDriver.findElement(By.xpath("//*[@id='hotellist_inner']/div[1]/div[2]/div[1]/div[2]/div/div/a/div/div[1]")).getText().replaceAll(",","."));
        assertTrue(rating >= 8);
    }

    @Test
    @Order(26)
    public void testDistance() {
        searchPage.enterCity("Калининград");
        searchPage.setArrivalDate("2020-11-24");
        searchPage.setDepartureDate("2020-11-25");
        searchPage.clickSearch();
        searchPage.selectDistance(2);
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='right']/div[3]/div")));
        String dist = webDriver.findElement(By.xpath("//*[@id='hotellist_inner']/div[1]/div[2]/div[1]/div[1]/div[2]/span[2]")).getText();
        double km = Double.parseDouble(dist.substring(0,dist.indexOf(" ")).replaceAll(",","."));
        if (dist.contains("м от центра")) {
            km *= 0.001;
        }
        assertTrue(km <= 3);
    }

    @Test
    @Order(27)
    public void testBookingCancelling() {
        searchPage.enterCity("Калининград");
        searchPage.setArrivalDate("2020-11-24");
        searchPage.setDepartureDate("2020-11-25");
        searchPage.clickSearch();
        searchPage.selectBookingRule(1);
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='right']/div[3]/div")));
        assertTrue(searchPage.firstResult.getText().contains("БЕСПЛАТНАЯ отмена бронирования"));
    }

    @Test
    @Order(28)
    public void testWithoutEarlyPay() {
        searchPage.enterCity("Калининград");
        searchPage.setArrivalDate("2020-11-24");
        searchPage.setDepartureDate("2020-11-25");
        searchPage.clickSearch();
        searchPage.selectBookingRule(3);
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='right']/div[3]/div")));
        assertTrue(searchPage.firstResult.getText().contains("Без предоплаты"));
    }

    @AfterEach
    public void tearDown(){
        webDriver.quit();
    }
}
