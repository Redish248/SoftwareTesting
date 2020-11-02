package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class MainPage extends PageObject {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    private final String LOG_IN = "//*[@id='current_account']/a";
    private final String ACCOUNT = "//*[@class='bui-avatar-block']";
    public final String DEST_INPUT = "//*[@class='sb-destination-label-sr']/input";
    public final String DEST_SUGGESTIONS_NAMES = "//*[@data-component='search/destination/input']//span[@class='search_hl_name']";
    public final String DEST_SUGGESTIONS_AND_LOADING = "//*[@data-component='search/destination/input']//ul";
    private final String REGISTER_BUTTON = "//*[@id='current_account_create']/a";
    private final String LOG_OUT = "//*[@id='profile-menu']/form/input[5]";
    private final String ACCOUNT_ICON = "//*[@id='current_account']/a";
    private final String GUESTS_LABEL = "//div[@class = 'xp__input-group xp__guests']/label";
    private final String GUESTS_CONTAINER = "//div[@class = 'xp__input-group xp__guests']/div[contains(@class, 'inputs')]";
    private final String CALENDAR = "//div[@class='bui-calendar']";
    private final String DATES_INPUT = "//div[@class='xp__dates xp__group']";
    public final String SELECTED_DATES = ".//td[contains(@class,'bui-calendar__date--selected')]";
    public final String DATES_ERRORS = ".//div[@data-dates-errors]//div";
    public final String DEST_ERRORS = ".//div[@id='destination__error']";
    public final String SEARCH_ERRORS = "//div[contains(@class,'sb-searchbox__error')]";
    public final String SUBMIT_BUTTON = "//button[@class='sb-searchbox__button ']";
    public final String NAVIGATION = "//ul[@class='bui-tab__nav']//span[@class='bui-tab__text']";
    public final String LANGUAGE_BUTTON = "//button[@data-modal-id='language-selection']";
    public final String LANGUAGE = "//div[@id='language-selection']//li/a[@data-lang='%s']";
    public final String POSTCARD = "//div[@class='promotion-postcard__large'][1]";
    public final String POSTCARDS_CITY = ".//h3/a";
    public final String EMPTY_CELL = "//td[contains(@class,'bui-calendar__date--empty')][1]";

    public final String ACCEPT_COOKIE = ".//button[@id='onetrust-accept-btn-handler']";

    public final String CITY_LOADING_CLASS = "c-autocomplete__list sb-autocomplete__list sb-autocomplete__list--loading";
    public final String SELECTED_DATES_CLASS = "bui-calendar__date--selected";

    @FindBy(xpath = DEST_INPUT)
    public WebElement destinationInput;

    @FindBy(xpath = DATES_INPUT)
    public WebElement datesInput;

    @FindBy(xpath = POSTCARD)
    public WebElement postcard;

    @FindBy(xpath = SUBMIT_BUTTON)
    public WebElement submitButton;

    @FindBy(xpath = LANGUAGE_BUTTON)
    public WebElement languageButton;

    @FindBy(xpath = DEST_SUGGESTIONS_AND_LOADING)
    public List<WebElement> cityUlBlocks;

    @FindBy(xpath = NAVIGATION)
    public List<WebElement> navigationTabs;

    @FindBy(xpath = GUESTS_LABEL)
    public WebElement guestsLabel;

    @FindBy(xpath = GUESTS_CONTAINER)
    public WebElement guestsContainer;

    @FindBy(xpath = CALENDAR)
    public WebElement calendar;

    @FindBy(xpath = LOG_IN)
    WebElement logInButton;

    @FindBy(xpath = ACCOUNT)
    WebElement accountLink;

    @FindBy(xpath = REGISTER_BUTTON)
    WebElement registerButton;

    @FindBy(xpath = LOG_OUT)
    WebElement logoutButton;

    @FindBy(xpath = ACCOUNT_ICON)
    WebElement  accountIcon;

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

    public WebElement getCalendarEmptyCell() {
        return driver.findElement(By.xpath(EMPTY_CELL));
    }

    public boolean isGuestContainerClosed() {
        return !this.guestsContainer.getAttribute("style").equals("display: block;");
    }

    public List<WebElement> getChildrenAgeInput() {
        return guestsContainer.findElements(By.xpath(".//div[@class='sb-group__children__field clearfix']/select"));
    }

    public WebElement getGuestLabel(int index) {
        return switch (index) {
            case 0 -> guestsLabel.findElement(By.xpath(".//span[@data-adults-count]"));
            case 1 -> guestsLabel.findElement(By.xpath(".//span[@data-children-count]"));
            default -> guestsLabel.findElement(By.xpath(".//span[@data-room-count]"));
        };
    }

    public WebElement getTodayElement() {
        return calendar.findElement(By.xpath(".//td[contains(@class, 'today')]"));
    }

    public WebElement getElementByDate(String date) {
        return calendar.findElement(By.xpath(".//td[@data-date='" +
                date + "']"));
    }

    public LocalDate getTodayDate(WebElement todayElement) {
        String strDate = todayElement.getAttribute("data-date");
        return LocalDate.parse(strDate);
    }

    public String getPostcardCityName() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(POSTCARD)));
        return postcard.findElement(By.xpath(POSTCARDS_CITY)).getText();
    }

    public String getNewDate(LocalDate date, int days) {
        return date.plusDays(days).toString();
    }

    public boolean isCalendarClosed() {
        return !this.calendar.getAttribute("style").equals("display: block;");
    }

    public List<WebElement> getSelectedDates() {
        return calendar.findElements(By.xpath(SELECTED_DATES));
    }

    public boolean isCookiesBannerOpen() {
        return !driver.findElement(By.xpath("//div[@id='onetrust-banner-sdk']"))
                .getAttribute("style").contains("visibility: hidden");
    }

    public void changeLanguage(String language) {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(LANGUAGE_BUTTON)));
        driver.findElement(By.xpath(LANGUAGE_BUTTON)).click();
        driver.findElement(By.xpath(String.format(LANGUAGE, language))).click();
    }

    public void acceptCookies() {
        driver.findElement(By.xpath(ACCEPT_COOKIE)).click();
    }

    public void focusCity() {
        this.destinationInput.click();
    }

    public void enterCity(String city) {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(DEST_INPUT)));
        this.driver.findElement(By.xpath("//span[@class='sb-searchbox__title-text']")).click();
        this.destinationInput.clear();
        this.destinationInput.sendKeys(city);
    }

    public void clearCity() {
        this.destinationInput.clear();
        this.driver.findElement(By.xpath("//header")).click();
    }

    public boolean isDestErrorVisible() {
        return driver.findElement(By.xpath(DEST_ERRORS)).getAttribute("class").contains("visible");
    }

    public boolean isSearchErrorVisible() {
        return driver.findElement(By.xpath(SEARCH_ERRORS)).getAttribute("class").contains("visible");
    }

    public boolean idDatesErrorVisible() {
        return driver.findElements(By.xpath(DATES_ERRORS)).size() != 0;
    }

    public void clickToLogIn() {
        logInButton.click();
    }

    public void goToRegisterPage() {
        registerButton.click();
    }

    public void signOutClick() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(ACCOUNT_ICON)));
        accountIcon.click();
        logoutButton.click();
    }

    public boolean isAccountDisplayed() {
        return accountLink.isDisplayed();
    }

    public boolean isLoginDisplayed() {
        return logInButton.isDisplayed();
    }

    public boolean isMainSearchDisplayed() {
        return destinationInput.isDisplayed();
    }

    public void setCheckInCheckOut(int days) {
        if (isCalendarClosed()) datesInput.click();
        WebElement today = getTodayElement();
        today.click();
        WebElement checkOut = getElementByDate(getNewDate(getTodayDate(today), days));
        checkOut.click();
    }

    public List<Integer> getGuestsAmount() {
        guestsLabel.click();
        int adult = Integer.parseInt(getGuestNumber(getGuestInput(0)).getText());
        int child = Integer.parseInt(getGuestNumber(getGuestInput(1)).getText());
        int room = Integer.parseInt(getGuestNumber(getGuestInput(2)).getText());
        return List.of(adult, child, room);
    }

    public List<String> getGuestsDates() {
        return getSelectedDates().stream()
                .map(date -> date.getAttribute("data-date"))
                .collect(Collectors.toList());
    }
}
