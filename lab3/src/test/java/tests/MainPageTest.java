package tests;

import config.WebDriverConfiguration;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.MainPage;

import java.time.LocalDate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MainPageTest {
    private static WebDriver webDriver;
    private static MainPage mainPage;
    private static final WebDriverConfiguration.Browser browser = WebDriverConfiguration.Browser.CHROME;

    @BeforeAll
    public static void setUp() {
        webDriver = WebDriverConfiguration.getWebDriver(browser);
        mainPage = new MainPage(webDriver);
    }

    @BeforeEach
    public void acceptCookies() {
        if (mainPage.isCookiesBannerOpen()) {
            mainPage.acceptCookies();
        }
    }

    @Test
    @Order(1)
    public void testIsMainPage() {
        assertTrue(webDriver.getCurrentUrl().contains("booking.com/index.ru"));
    }

    @Test
    @Order(2)
    public void testDestinationEmptyCityNotEmptySuggestions() {
        mainPage.focusCity();
        assertNotEquals(0, mainPage.getSuggestionsNames().size());
    }

    @Test
    @Order(3)
    public void testEmptyCity() {
        mainPage.clearCity();
        mainPage.submitButton.click();
        assertTrue(mainPage.isDestErrorVisible());
    }

    //todo: fix order
    //клик по прошлой дате
    //клик по пустому полю в календаре
    //клик по предложению для направления
    @Test
    @Order(4)
    public void testDestinationRealCityCheckValue() {
        mainPage.enterCity("Казань");
        assertEquals("Казань", mainPage.destinationInput.getAttribute("value"));

    }

    @Test
    @Order(5)
    public void testDestinationRealCityNotEmptySuggestions() {
        mainPage.enterCity("Казань");
        new WebDriverWait(webDriver, 5)
                .until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath(mainPage.DEST_SUGGESTIONS_NAMES), 0));
        assertNotEquals(0, mainPage.getSuggestionsNames().size());
    }

    @Test
    @Order(6)
    public void testDestinationRealCityFirstSuggestion() {
        mainPage.enterCity("Казань");
        new WebDriverWait(webDriver, 10)
                .until(ExpectedConditions.attributeContains(mainPage.cityUlBlocks.get(0),
                        "class", "-visible"));
        WebElement firstSuggestion = mainPage.getSuggestionsNames().get(0);
        assertEquals("Казань", firstSuggestion.getText());
    }

    @Test
    @Order(7)
    public void testDestinationRealCityWithMistake() {
        mainPage.enterCity("Казаньб");
        new WebDriverWait(webDriver, 10)
                .until(ExpectedConditions.attributeContains(mainPage.cityUlBlocks.get(0),
                        "class", "-visible"));
        WebElement firstSuggestion = mainPage.getSuggestionsNames().get(0);
        assertEquals("Казань", firstSuggestion.getText());

    }

    @Test
    @Order(8)
    public void testDestinationFakeCity() {
        mainPage.enterCity("Йцукен");
        new WebDriverWait(webDriver, 5)
                .until(ExpectedConditions.attributeToBe(mainPage.cityUlBlocks.get(1),
                        "class", mainPage.CITY_LOADING_CLASS));
        assertFalse(mainPage.cityUlBlocks.get(0).getAttribute("class").contains("-visible"));
    }

    @Test
    @Order(9)
    @Disabled("В целом работает, но ооооочень долго")
    public void testDestinationLongNameCity() {
        mainPage.enterCity("НуОченьДлинноеНазваниеГородаДляТестаВводаНаБукинге".repeat(276));
        new WebDriverWait(webDriver, 5)
                .until(ExpectedConditions.attributeToBe(mainPage.cityUlBlocks.get(1),
                        "class", mainPage.CITY_LOADING_CLASS));
        assertFalse(mainPage.cityUlBlocks.get(0).getAttribute("class").contains("-visible"));
    }

    @Test
    @Order(10)
    public void testGuestsDisplayContainerAfterClick() {
        if (mainPage.isGuestContainerClosed()) mainPage.guestsLabel.click();
        assertEquals("display: block;", mainPage.guestsContainer.getAttribute("style"));
    }

    @Test
    @Order(11)
    public void testGuestsAdultMinusButton() {
        if (mainPage.isGuestContainerClosed()) mainPage.guestsLabel.click();
        String expValue = String.valueOf(Integer.parseInt(mainPage.getGuestNumber(mainPage.getGuestInput(0)).getText())-1);
        mainPage.getGuestButton(mainPage.getGuestInput(0), false).click();
        assertEquals(expValue, mainPage.getGuestNumber(mainPage.getGuestInput(0)).getText());
    }

    @Test
    @Order(12)
    public void testGuestsAdultMinusButtonDisabled() {
        if (mainPage.isGuestContainerClosed()) mainPage.guestsLabel.click();
        while (!"1".equals(mainPage.getGuestNumber(mainPage.getGuestInput(0)).getText())) {
            mainPage.getGuestButton(mainPage.getGuestInput(0), false).click();
        }
        assertTrue(mainPage.getGuestButton(mainPage.getGuestInput(0), false).getAttribute("class").contains("disabled"));
        mainPage.getGuestButton(mainPage.getGuestInput(0), false).click();
        assertAll(
                () -> assertTrue(mainPage.getGuestButton(mainPage.getGuestInput(0), false).getAttribute("class").contains("disabled")),
                () -> assertEquals("1", mainPage.getGuestNumber(mainPage.getGuestInput(0)).getText())
        );
    }

    @Test
    @Order(13)
    public void testGuestsAdultPlusButton() {
        if (mainPage.isGuestContainerClosed()) mainPage.guestsLabel.click();
        String expValue = String.valueOf(Integer.parseInt(mainPage.getGuestNumber(mainPage.getGuestInput(0)).getText())+1);
        mainPage.getGuestButton(mainPage.getGuestInput(0), true).click();
        assertEquals(expValue, mainPage.getGuestNumber(mainPage.getGuestInput(0)).getText());
    }

    @Test
    @Order(14)
    public void testGuestsAdultPlusButtonDisabled() {
        if (mainPage.isGuestContainerClosed()) mainPage.guestsLabel.click();
        while (!"30".equals(mainPage.getGuestNumber(mainPage.getGuestInput(0)).getText())) {
            mainPage.getGuestButton(mainPage.getGuestInput(0), true).click();
        }
        mainPage.getGuestButton(mainPage.getGuestInput(0), true).click();
        assertAll(
                () -> assertTrue(mainPage.getGuestButton(mainPage.getGuestInput(0), true).getAttribute("class").contains("disabled")),
                () -> assertEquals("30", mainPage.getGuestNumber(mainPage.getGuestInput(0)).getText())
        );

    }

    @Test
    @Order(15)
    public void testGuestsChildPlusButton() {
        if (mainPage.isGuestContainerClosed()) mainPage.guestsLabel.click();
        String expValue = String.valueOf(Integer.parseInt(mainPage.getGuestNumber(mainPage.getGuestInput(1)).getText())+1);
        mainPage.getGuestButton(mainPage.getGuestInput(1), true).click();
        assertEquals(expValue, mainPage.getGuestNumber(mainPage.getGuestInput(1)).getText());
    }

    @Test
    @Order(16)
    public void testGuestsChildPlusButtonDisabled() {
        if (mainPage.isGuestContainerClosed()) mainPage.guestsLabel.click();
        while (!"10".equals(mainPage.getGuestNumber(mainPage.getGuestInput(1)).getText())) {
            mainPage.getGuestButton(mainPage.getGuestInput(1), true).click();
        }
        mainPage.getGuestButton(mainPage.getGuestInput(1), true).click();
        assertAll(
                () -> assertTrue(mainPage.getGuestButton(mainPage.getGuestInput(1), true).getAttribute("class").contains("disabled")),
                () -> assertEquals("10", mainPage.getGuestNumber(mainPage.getGuestInput(1)).getText())
        );
    }

    @Test
    @Order(17)
    public void testGuestsChildMinusButton() {
        if (mainPage.isGuestContainerClosed()) mainPage.guestsLabel.click();
        String expValue = String.valueOf(Integer.parseInt(mainPage.getGuestNumber(mainPage.getGuestInput(1)).getText())-1);
        mainPage.getGuestButton(mainPage.getGuestInput(1), false).click();
        assertEquals(expValue, mainPage.getGuestNumber(mainPage.getGuestInput(1)).getText());
    }

    @Test
    @Order(18)
    public void testGuestsChildMinusButtonDisabled() {
        if (mainPage.isGuestContainerClosed()) mainPage.guestsLabel.click();
        while (!"0".equals(mainPage.getGuestNumber(mainPage.getGuestInput(1)).getText())) {
            mainPage.getGuestButton(mainPage.getGuestInput(1), false).click();
        }
        mainPage.getGuestButton(mainPage.getGuestInput(1), false).click();
        assertAll(
                () -> assertTrue(mainPage.getGuestButton(mainPage.getGuestInput(1), false).getAttribute("class").contains("disabled")),
                () -> assertEquals("0", mainPage.getGuestNumber(mainPage.getGuestInput(1)).getText())
        );
    }

    @Test
    @Order(19)
    public void testGuestsRoomPlusButton() {
        if (mainPage.isGuestContainerClosed()) mainPage.guestsLabel.click();
        String expValue = String.valueOf(Integer.parseInt(mainPage.getGuestNumber(mainPage.getGuestInput(2)).getText())+1);
        mainPage.getGuestButton(mainPage.getGuestInput(2), true).click();
        assertEquals(expValue, mainPage.getGuestNumber(mainPage.getGuestInput(2)).getText());
    }

    @Test
    @Order(20)
    public void testGuestsRoomMinusButton() {
        if (mainPage.isGuestContainerClosed()) mainPage.guestsLabel.click();
        String expValue = String.valueOf(Integer.parseInt(mainPage.getGuestNumber(mainPage.getGuestInput(2)).getText())-1);
        mainPage.getGuestButton(mainPage.getGuestInput(2), false).click();
        assertEquals(expValue, mainPage.getGuestNumber(mainPage.getGuestInput(2)).getText());
    }

    @Test
    @Order(21)
    public void testGuestsRoomMinusButtonDisabled() {
        if (mainPage.isGuestContainerClosed()) mainPage.guestsLabel.click();
        while (!"1".equals(mainPage.getGuestNumber(mainPage.getGuestInput(2)).getText())) {
            mainPage.getGuestButton(mainPage.getGuestInput(2), false).click();
        }
        mainPage.getGuestButton(mainPage.getGuestInput(2), false).click();
        assertAll(
                () -> assertTrue(mainPage.getGuestButton(mainPage.getGuestInput(2), false).getAttribute("class").contains("disabled")),
                () -> assertEquals("1", mainPage.getGuestNumber(mainPage.getGuestInput(2)).getText())
        );
    }

    @Test
    @Order(22)
    public void testGuestsRoomPlusButtonDisabled() {
        if (mainPage.isGuestContainerClosed()) mainPage.guestsLabel.click();
        while (!"30".equals(mainPage.getGuestNumber(mainPage.getGuestInput(2)).getText())) {
            mainPage.getGuestButton(mainPage.getGuestInput(2), true).click();
        }
        mainPage.getGuestButton(mainPage.getGuestInput(2), true).click();
        assertAll(
                () -> assertTrue(mainPage.getGuestButton(mainPage.getGuestInput(2), true).getAttribute("class").contains("disabled")),
                () -> assertEquals("30", mainPage.getGuestNumber(mainPage.getGuestInput(2)).getText())
        );
    }

    @Test
    @Order(23)
    public void testChildrenAgeInputsAmount() {
        if (mainPage.isGuestContainerClosed()) mainPage.guestsLabel.click();
        int expResult = Integer.parseInt(mainPage.getGuestNumber(mainPage.getGuestInput(1)).getText())+3;
        mainPage.getGuestButton(mainPage.getGuestInput(1), true).click();
        mainPage.getGuestButton(mainPage.getGuestInput(1), true).click();
        mainPage.getGuestButton(mainPage.getGuestInput(1), true).click();
        assertEquals(expResult, mainPage.getChildrenAgeInput().size());
    }

    @Test
    @Order(24)
    public void testChildrenAgeInput() {
        if (mainPage.isGuestContainerClosed()) mainPage.guestsLabel.click();
        if ("0".equals(mainPage.getGuestNumber(mainPage.getGuestInput(1)).getText())) {
            mainPage.getGuestButton(mainPage.getGuestInput(1), true).click();
        }
        WebElement select = mainPage.getChildrenAgeInput().get(0);
        WebElement option = select.findElement(By.xpath("./option[@value='2']"));
        option.click();
        assertTrue(option.isSelected());
    }

    @Test
    @Order(25)
    public void testGuestLabelAdults() {
        if (mainPage.isGuestContainerClosed()) mainPage.guestsLabel.click();
        WebElement adultsLabel = mainPage.getGuestLabel(0);
        String amount = mainPage.getGuestNumber(mainPage.getGuestInput(0)).getText();
        assertTrue(adultsLabel.getText().contains(amount+" "));
    }

    @Test
    @Order(26)
    public void testGuestLabelChildren() {
        if (mainPage.isGuestContainerClosed()) mainPage.guestsLabel.click();
        WebElement childrenLabel = mainPage.getGuestLabel(1);
        String amount = mainPage.getGuestNumber(mainPage.getGuestInput(1)).getText();
        assertTrue(childrenLabel.getText().contains(Integer.parseInt(amount) > 0 ? amount+" " : "без"));
    }

    @Test
    @Order(27)
    public void testGuestLabelRooms() {
        if (mainPage.isGuestContainerClosed()) mainPage.guestsLabel.click();
        WebElement roomsLabel = mainPage.getGuestLabel(2);
        String amount = mainPage.getGuestNumber(mainPage.getGuestInput(2)).getText();
        assertTrue(roomsLabel.getText().contains(amount+" "));
    }

    @Test
    @Order(28)
    public void testCheckInToday() {
        if (mainPage.isCalendarClosed()) mainPage.datesInput.click();
        WebElement today = mainPage.getTodayElement();
        today.click();
        assertTrue(today.getAttribute("class").contains(mainPage.SELECTED_DATES_CLASS));
    }

    @Test
    @Order(29)
    public void testCheckInTodayCheckOutTomorrow() {
        if (mainPage.isCalendarClosed()) mainPage.datesInput.click();
        WebElement today = mainPage.getTodayElement();
        today.click();
        WebElement tomorrow = mainPage.getElementByDate(mainPage.getNewDate(mainPage.getTodayDate(today), 1));
        tomorrow.click();
        assertEquals(List.of(today, tomorrow), mainPage.getSelectedDates());
    }

    @Test
    @Order(30)
    public void testCheckInTomorrowCheckOutToday() {
        if (mainPage.isCalendarClosed()) mainPage.datesInput.click();
        WebElement today = mainPage.getTodayElement();
        WebElement tomorrow = mainPage.getElementByDate(mainPage.getNewDate(mainPage.getTodayDate(today), 1));
        tomorrow.click();
        today.click();
        assertEquals(List.of(today), mainPage.getSelectedDates());
    }

    @Test
    @Order(31)
    public void test31Nights() {
        if (mainPage.isCalendarClosed()) mainPage.datesInput.click();
        WebElement today = mainPage.getTodayElement();
        WebElement checkOutDate = mainPage.getElementByDate(mainPage.getNewDate(mainPage.getTodayDate(today), 31));
        today.click();
        checkOutDate.click();
        mainPage.submitButton.click();
        assertTrue(mainPage.idDatesErrorVisible());
        if (mainPage.isCalendarClosed()) mainPage.datesInput.click();
        checkOutDate.click();
    }

    @Test
    @Order(32)
    public void testYesterdayDisabled() {
        if (mainPage.isCalendarClosed()) mainPage.datesInput.click();
        LocalDate today = mainPage.getTodayDate(mainPage.getTodayElement());
        if (today.getDayOfMonth() != 1) {
            WebElement yesterday = mainPage.getElementByDate(mainPage.getNewDate(today, -1));
            assertTrue(yesterday.getAttribute("class").contains("disabled"));
        }
    }

    @Test
    @Order(33)
    public void testLanguageEnglishUs() {
        mainPage.changeLanguage("en-us");
        assertAll(
                () -> assertEquals("Stays", mainPage.navigationTabs.get(0).getText()),
                () -> assertEquals("Search", mainPage.submitButton.getText())
        );
    }

        @AfterAll
    public static void tearDown(){
        webDriver.quit();
    }
}
