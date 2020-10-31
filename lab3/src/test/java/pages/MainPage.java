package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MainPage extends PageObject {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    private final String LOG_IN = "//*[@id='current_account']/a";
    private final String ACCOUNT = "//*[@id='current_account']/a";
    public final String DESTINATION_INPUT = "//*[@class='sb-destination-label-sr']/input";
    public final String DESTINATION_SUGGESTIONS = "//*[@data-component='search/destination/input']//span[@class='search_hl_name']";
    private final String REGISTER_BUTTON = "//*[@id='current_account_create']/a";
    private final String LOG_OUT = "//*[@id=\"profile-menu\"]/form/input[5]";
    private final String ACCOUNT_ICON = "//*[@id=\"current_account\"]/a";

    @FindBy(xpath = DESTINATION_INPUT)
    public WebElement destinationInput;

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

    public List<WebElement> getSuggestions() {
        return driver.findElements(By.xpath(DESTINATION_SUGGESTIONS));
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
