package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MainPage extends PageObject {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    private final String LOG_IN = "//*[@id='current_account']/a";
    private final String ACCOUNT = "//*[@id='current_account']/a";
    public final String DEST_INPUT = "//*[@class='sb-destination-label-sr']/input";
    public final String DEST_SUGGESTIONS_NAMES = "//*[@data-component='search/destination/input']//span[@class='search_hl_name']";
    public final String DEST_SUGGESTIONS_AND_LOADING = "//*[@data-component='search/destination/input']//ul";
    private final String REGISTER_BUTTON = "//*[@id='current_account_create']/a";
    private final String LOG_OUT = "//*[@id=\"profile-menu\"]/form/input[5]";
    private final String ACCOUNT_ICON = "//*[@id=\"current_account\"]/a";
    private final String GUESTS_LABEL = "//div[@class = 'xp__input-group xp__guests']/label";
    private final String GUESTS_CONTAINER = "//div[@class = 'xp__input-group xp__guests']/div[contains(@class, 'inputs')]";

    public final String CITY_LOADING_CLASS = "c-autocomplete__list sb-autocomplete__list sb-autocomplete__list--loading";

    @FindBy(xpath = DEST_INPUT)
    public WebElement destinationInput;

    @FindBy(xpath = DEST_SUGGESTIONS_AND_LOADING)
    public List<WebElement> cityUlBlocks;

    @FindBy(xpath = GUESTS_LABEL)
    public WebElement guestsLabel;

    @FindBy(xpath = GUESTS_CONTAINER)
    public WebElement guestsContainer;

    @FindBy(xpath = LOG_IN)
    WebElement logInButton;

    @FindBy(xpath = ACCOUNT)
    WebElement accountLink;

    @FindBy(xpath = REGISTER_BUTTON)
    WebElement registerButton;

    @FindBy(xpath = LOG_OUT)
    WebElement logoutButton;

    @FindBy(xpath = ACCOUNT_ICON)
    WebElement accountIcon;

    public List<WebElement> getSuggestionsNames() {
        return driver.findElements(By.xpath(DEST_SUGGESTIONS_NAMES));
    }

    public WebElement getGuestInput(int inputIndex) {
        return guestsContainer.findElements(By.xpath(".//div[@class='bui-stepper__wrapper sb-group__stepper-a11y']"))
                .get(inputIndex);
    }

    public WebElement getGuestButton(WebElement guest, boolean isPlus) {
        return guest.findElements(By.xpath("./button"))
                .get(isPlus ? 1 : 0);
    }

    public WebElement getGuestNumber(WebElement guest) {
        return guest.findElements(By.xpath("./span")).get(0);
    }

    public boolean isGuestContainerClosed() {
        return !this.guestsContainer.getAttribute("style").equals("display: block;");
    }

    public List<WebElement> getChildrenAgeInput() {
        return guestsContainer.findElements(By.xpath(".//div[@class='sb-group__children__field clearfix']/select"));
    }

    public void goToSuggestion(WebElement suggestion) {
        suggestion.click();
    }

    public void focusCity() {
        this.destinationInput.click();
    }

    public void enterCity(String city) {
        this.destinationInput.clear();
        this.destinationInput.sendKeys(city);
    }

    public void clickToLogIn() {
        logInButton.click();
    }

    public void goToRegisterPage() {
        registerButton.click();
    }

    public void signOutClick() {
        accountIcon.click();
        logoutButton.click();
    }

    public boolean isAccountDisplayed() {
        return accountLink.isDisplayed();
    }

    public boolean isMainSearchDisplayed() {
        return destinationInput.isDisplayed();
    }
}
