package tests;

import config.WebDriverConfiguration;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.LogInPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.MainPage;
import pages.SignUpPage;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MainPageTest {
    private static WebDriver webDriver;
    private static MainPage mainPage;
    private static LogInPage logInPage;
    private static SignUpPage signUpPage;

    @BeforeAll
    public static void setUp() {
        webDriver = WebDriverConfiguration.getWebDriver(WebDriverConfiguration.Browser.CHROME);
        mainPage = new MainPage(webDriver);
        logInPage = new LogInPage(webDriver);
        signUpPage = new SignUpPage(webDriver);
    }

    @Test
    @Order(1)
    public void testDestinationEmptyCityNotEmptySuggestions() {
        mainPage.focusCity();
        assertNotEquals(0, mainPage.getSuggestionsNames().size());
    }

    @Test
    @Order(2)
    public void testDestinationRealCityCheckValue() {
        mainPage.enterCity("Казань");
        assertEquals("Казань", mainPage.destinationInput.getAttribute("value"));

    }

    @Test
    @Order(3)
    public void testDestinationRealCityNotEmptySuggestions() {
        mainPage.enterCity("Казань");
        new WebDriverWait(webDriver, 5)
                .until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath(mainPage.DEST_SUGGESTIONS_NAMES), 0));
        assertNotEquals(0, mainPage.getSuggestionsNames().size());
    }

    @Test
    @Order(4)
    public void testDestinationRealCityFirstSuggestion() {
        mainPage.enterCity("Казань");
        new WebDriverWait(webDriver, 5)
                .until(ExpectedConditions.attributeContains(mainPage.cityUlBlocks.get(0),
                        "class", "-visible"));
        WebElement firstSuggestion = mainPage.getSuggestionsNames().get(0);
        assertEquals("Казань", firstSuggestion.getText());
    }

    @Test
    @Order(5)
    public void testDestinationRealCityWithMistake() {
        mainPage.enterCity("Казаньб");
        new WebDriverWait(webDriver, 5)
                .until(ExpectedConditions.attributeContains(mainPage.cityUlBlocks.get(0),
                        "class", "-visible"));
        WebElement firstSuggestion = mainPage.getSuggestionsNames().get(0);
        assertEquals("Казань", firstSuggestion.getText());

    }

    @Test
    @Order(6)
    public void testDestinationFakeCity() {
        mainPage.enterCity("Йцукен");
        new WebDriverWait(webDriver, 5)
                .until(ExpectedConditions.attributeToBe(mainPage.cityUlBlocks.get(1),
                        "class", mainPage.CITY_LOADING_CLASS));
        assertFalse(mainPage.cityUlBlocks.get(0).getAttribute("class").contains("-visible"));
    }

    @Test
    @Order(7)
    @Disabled("В целом работает, но оооооочень долго")
    public void testDestinationLongNameCity() {
        mainPage.enterCity("НуОченьДлинноеНазваниеГородаДляТестаВводаНаБукинге".repeat(276));
        new WebDriverWait(webDriver, 5)
                .until(ExpectedConditions.attributeToBe(mainPage.cityUlBlocks.get(1),
                        "class", mainPage.CITY_LOADING_CLASS));
        assertFalse(mainPage.cityUlBlocks.get(0).getAttribute("class").contains("-visible"));
    }

    @Test
    @Order(8)
    public void testGuestsDisplayContainerAfterClick() {
        if (mainPage.isGuestContainerClosed()) mainPage.guestsLabel.click();
        assertEquals("display: block;", mainPage.guestsContainer.getAttribute("style"));
    }

    @Test
    @Order(9)
    public void testGuestsAdultMinusButton() {
        if (mainPage.isGuestContainerClosed()) mainPage.guestsLabel.click();
        String expValue = String.valueOf(Integer.parseInt(mainPage.getGuestNumber(mainPage.getGuestInput(0)).getText())-1);
        mainPage.getGuestButton(mainPage.getGuestInput(0), false).click();
        assertEquals(expValue, mainPage.getGuestNumber(mainPage.getGuestInput(0)).getText());
    }

    @Test
    @Order(10)
    public void testGuestsAdultMinusButtonDisabled() {
        if (mainPage.isGuestContainerClosed()) mainPage.guestsLabel.click();
        while (!"1".equals(mainPage.getGuestNumber(mainPage.getGuestInput(0)).getText())) {
            mainPage.getGuestButton(mainPage.getGuestInput(0), false).click();
        }
        assertTrue(mainPage.getGuestButton(mainPage.getGuestInput(0), false).getAttribute("class").contains("disabled"));
    }

    @Test
    @Order(11)
    public void testGuestsAdultPlusButton() {
        if (mainPage.isGuestContainerClosed()) mainPage.guestsLabel.click();
        String expValue = String.valueOf(Integer.parseInt(mainPage.getGuestNumber(mainPage.getGuestInput(0)).getText())+1);
        mainPage.getGuestButton(mainPage.getGuestInput(0), true).click();
        assertEquals(expValue, mainPage.getGuestNumber(mainPage.getGuestInput(0)).getText());
    }

    @Test
    @Order(12)
    public void testGuestsAdultPlusButtonDisabled() {
        if (mainPage.isGuestContainerClosed()) mainPage.guestsLabel.click();
        while (!"30".equals(mainPage.getGuestNumber(mainPage.getGuestInput(0)).getText())) {
            mainPage.getGuestButton(mainPage.getGuestInput(0), true).click();
        }
        assertTrue(mainPage.getGuestButton(mainPage.getGuestInput(0), true).getAttribute("class").contains("disabled"));
    }

    @Test
    @Order(13)
    public void testGuestsChildPlusButton() {
        if (mainPage.isGuestContainerClosed()) mainPage.guestsLabel.click();
        String expValue = String.valueOf(Integer.parseInt(mainPage.getGuestNumber(mainPage.getGuestInput(1)).getText())+1);
        mainPage.getGuestButton(mainPage.getGuestInput(1), true).click();
        assertEquals(expValue, mainPage.getGuestNumber(mainPage.getGuestInput(1)).getText());
    }

    @Test
    @Order(14)
    public void testGuestsChildPlusButtonDisabled() {
        if (mainPage.isGuestContainerClosed()) mainPage.guestsLabel.click();
        while (!"10".equals(mainPage.getGuestNumber(mainPage.getGuestInput(1)).getText())) {
            mainPage.getGuestButton(mainPage.getGuestInput(1), true).click();
        }
        assertTrue(mainPage.getGuestButton(mainPage.getGuestInput(1), true).getAttribute("class").contains("disabled"));
    }

    @Test
    @Order(15)
    public void testGuestsChildMinusButton() {
        if (mainPage.isGuestContainerClosed()) mainPage.guestsLabel.click();
        String expValue = String.valueOf(Integer.parseInt(mainPage.getGuestNumber(mainPage.getGuestInput(1)).getText())-1);
        mainPage.getGuestButton(mainPage.getGuestInput(1), false).click();
        assertEquals(expValue, mainPage.getGuestNumber(mainPage.getGuestInput(1)).getText());
    }

    @Test
    @Order(16)
    public void testGuestsChildMinusButtonDisabled() {
        if (mainPage.isGuestContainerClosed()) mainPage.guestsLabel.click();
        while (!"0".equals(mainPage.getGuestNumber(mainPage.getGuestInput(1)).getText())) {
            mainPage.getGuestButton(mainPage.getGuestInput(1), false).click();
        }
        assertTrue(mainPage.getGuestButton(mainPage.getGuestInput(1), false).getAttribute("class").contains("disabled"));
    }

    @Test
    @Order(17)
    public void testGuestsRoomPlusButton() {
        if (mainPage.isGuestContainerClosed()) mainPage.guestsLabel.click();
        String expValue = String.valueOf(Integer.parseInt(mainPage.getGuestNumber(mainPage.getGuestInput(2)).getText())+1);
        mainPage.getGuestButton(mainPage.getGuestInput(2), true).click();
        assertEquals(expValue, mainPage.getGuestNumber(mainPage.getGuestInput(2)).getText());
    }

    @Test
    @Order(18)
    public void testGuestsRoomMinusButton() {
        if (mainPage.isGuestContainerClosed()) mainPage.guestsLabel.click();
        String expValue = String.valueOf(Integer.parseInt(mainPage.getGuestNumber(mainPage.getGuestInput(2)).getText())-1);
        mainPage.getGuestButton(mainPage.getGuestInput(2), false).click();
        assertEquals(expValue, mainPage.getGuestNumber(mainPage.getGuestInput(2)).getText());
    }

    @Test
    @Order(19)
    public void testGuestsRoomMinusButtonDisabled() {
        if (mainPage.isGuestContainerClosed()) mainPage.guestsLabel.click();
        while (!"1".equals(mainPage.getGuestNumber(mainPage.getGuestInput(2)).getText())) {
            mainPage.getGuestButton(mainPage.getGuestInput(2), false).click();
        }
        assertTrue(mainPage.getGuestButton(mainPage.getGuestInput(2), false).getAttribute("class").contains("disabled"));
    }

    @Test
    @Order(20)
    public void testGuestsRoomPlusButtonDisabled() {
        if (mainPage.isGuestContainerClosed()) mainPage.guestsLabel.click();
        while (!"30".equals(mainPage.getGuestNumber(mainPage.getGuestInput(2)).getText())) {
            mainPage.getGuestButton(mainPage.getGuestInput(2), true).click();
        }
        assertTrue(mainPage.getGuestButton(mainPage.getGuestInput(2), true).getAttribute("class").contains("disabled"));
    }

    @Test
    @Order(21)
    public void testChildrenAgeInputsAmount() {
        if (mainPage.isGuestContainerClosed()) mainPage.guestsLabel.click();
        int expResult = Integer.parseInt(mainPage.getGuestNumber(mainPage.getGuestInput(1)).getText())+3;
        mainPage.getGuestButton(mainPage.getGuestInput(1), true).click();
        mainPage.getGuestButton(mainPage.getGuestInput(1), true).click();
        mainPage.getGuestButton(mainPage.getGuestInput(1), true).click();
        assertEquals(expResult, mainPage.getChildrenAgeInput().size());
    }

    @Test
    @Order(22)
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
    @Disabled
    public void testGoToLogInPage() {
        mainPage.clickToLogIn();
        assertTrue(logInPage.isEmailInputDisplayed());
    }

    @Test
    @Disabled
    public void testGoToSignInPage() {
        mainPage.goToRegisterPage();
        assertTrue(signUpPage.isEmailInputDisplayed());
    }

    @Test
    @Disabled
    public void testLogOut() {
        mainPage.clickToLogIn();
        logInPage.signIn("yecine5178@aieen.com", "12345678");
        mainPage.signOutClick();
        assertTrue(mainPage.isMainSearchDisplayed());
    }

    @AfterAll
    public static void tearDown(){
        webDriver.quit();
    }
}
