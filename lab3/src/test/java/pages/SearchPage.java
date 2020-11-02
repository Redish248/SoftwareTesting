package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
TODO:
-календарь
-фильтры
-проверка картинки
-сортировка
 */
public class SearchPage extends PageObject{
    public SearchPage(WebDriver driver) {
        super(driver);
    }

    private final String CITY_INPUT = "//*[@id='ss']";
    private final String CALENDAR_1 = "//*[@id='frm']/div[3]/div/div[1]/div[1]/div/div/div/div[2]";
    private final String CALENDAR_2 = "//*[@id='frm']/div[3]/div/div[1]/div[2]/div/div/div/div[2]";
    private final String SELECT_ADULT = "//*[@id='group_adults']";
    private final String SELECT_CHILD = "//*[@id='group_children']";
    private final String SELECT_ROOM = "//*[@id='no_rooms']";
    private final String SEARCH_BUTTON = "//*[@id='frm']/div[5]/div[2]/button";
    private final String FIRST_RESULT = "//*[@id='hotellist_inner']/div[1]";
    private final String FIRST_RESULT_IMAGE = "//*[@id='hotellist_inner']/div[1]/div/a/img";
    private final String FIRST_RESULT_TITLE = "//*[@id='hotellist_inner']/div[1]/div[2]/div[1]/div[1]/div[1]/h3/a";
    private final String FIRST_RESULT_CHOOSE_BUTTON = "//*[@id='hotellist_inner']/div[1]/div[2]/div[4]/div/div/div/div/div[2]/div[2]/div/div/div/a";
    private final String FIRST_RESULT_NAME = "//*[@id='hotellist_inner']/div[1]/div[2]/div[1]/div[1]/div[2]/a";
    private final String CITY_ERROR = "//*[@id='destination__error']/div";
    private final String CHILD_AGE = "//*[@name='age']";
    private final String MAP = "//*[@id='b_google_map_thumbnail']";
    private final String MAP_IMAGE = "//*[@id='b_map_tiles']/div/div/div[1]/div[3]";
    private final String CURRENCY_CHOOSER = "//*[@id='user_form']/ul/li[1]/a";
    private final String SELECT_PAYMENT = "//*[@id='filter_price']/div[3]/a[1]";
    private final String STAR = "//*[@id='filter_class']/div[2]";
    private final String RATING = "//*[@id='filter_review']/div[2]";
    private final String DISTANCE = "//*[@id='filter_distance']/div[2]";
    private final String BOOKING_RULES = "//*[@id='filter_fc']/div[2]";
    private final String SELECTED_DATES = "//td[contains(@class,'bui-calendar__date--selected')]";
    private final String LOGO = "//div[@class='bui-header__logo']";

    @FindBy(xpath = CITY_INPUT)
    WebElement cityInput;

    @FindBy(xpath = CALENDAR_1)
    public WebElement calendarFirst;

    @FindBy(xpath = CALENDAR_2)
    public WebElement calendarSecond;

    @FindBy(xpath = SELECT_ADULT)
    WebElement selectAdult;

    @FindBy(xpath = SELECT_CHILD)
    WebElement selectChild;

    @FindBy(xpath = SELECT_ROOM)
    WebElement selectRoom;

    @FindBy(xpath = SEARCH_BUTTON)
    WebElement searchButton;

    @FindBy(xpath = FIRST_RESULT)
    public WebElement firstResult;

    @FindBy(xpath = FIRST_RESULT_TITLE)
    WebElement firstResultTitle;

    @FindBy(xpath = FIRST_RESULT_CHOOSE_BUTTON)
    WebElement firstResultChooseButton;

    @FindBy(xpath = CITY_ERROR)
    WebElement cityError;

    @FindBy(xpath = FIRST_RESULT_NAME)
    WebElement firstResultCity;

    @FindBy(xpath = MAP)
    WebElement map;

    @FindBy(xpath = CURRENCY_CHOOSER)
    WebElement currencyChooser;

    @FindBy(xpath = SELECT_PAYMENT)
    WebElement selectPayment;

    @FindBy(xpath = STAR)
    WebElement selectStartRating;

    @FindBy(xpath = RATING)
    WebElement rating;

    @FindBy(xpath = DISTANCE)
    WebElement distanceCheckbox;

    @FindBy(xpath = BOOKING_RULES)
    WebElement bookingRules;

    @FindBy(xpath = LOGO)
    WebElement logo;

    public void enterCity(String city) {
        cityInput.click();
        cityInput.sendKeys(city);
    }

    public void setArrivalDate(String date) {
        cityInput.click();
        calendarFirst.click();
        calendarFirst.findElement(By.xpath("//*/td[@data-date='" + date + "']")).click();
    }

    public void setDepartureDate(String date) {
        Actions actions = new Actions(driver);
        actions.moveToElement(calendarSecond);
        actions.perform();
        calendarSecond.click();
        calendarSecond.findElement(By.xpath("//*/td[@data-date='" + date + "']")).click();
    }

    public void selectAdultAmount(int amount) {
        selectAdult.click();
        WebElement number = driver.findElement(By.xpath(SELECT_ADULT.concat("/option[" + amount + "]")));
        number.click();
    }

    public void selectChildAmount(int amount) {
        selectChild.click();
        WebElement number = driver.findElement(By.xpath(SELECT_CHILD.concat("/option[" + amount + "]")));
        number.click();
    }

    public void selectRoomAmount(int amount) {
        selectRoom.click();
        WebElement number = driver.findElement(By.xpath(SELECT_ROOM.concat("/option[" + amount + "]")));
        number.click();
    }

    public void clickSearch() {
        searchButton.click();
    }

    public void goToTitle() {
        firstResultTitle.click();
    }

    public void chooseHotel() {
        firstResultChooseButton.click();
    }

    public boolean checkFirstResultCity() {
        return firstResultCity.getText().contains(cityInput.getText());
    }

    public String getFirstResultCity() {
        return firstResultCity.getText();
    }

    public void clickDateInPastFirst() {
        setArrivalDate("2020-11-01");
    }

    public void clickDateInPastSecond() {
        setDepartureDate("2020-11-01");
    }

    public boolean isCityErrorDisplayed() {
        return cityError.isDisplayed();
    }

    public String getDaysAndPeopleText() {
        return firstResult.getText();
    }

    public void setUpDate() {
        setArrivalDate("2020-11-24");
        setDepartureDate("2020-11-30");
    }

    public int getChildAgeSize() {
        return driver.findElements(By.xpath(CHILD_AGE)).size();
    }

    public void showMap() {
        Actions actions = new Actions(driver);
        actions.moveToElement(map);
        actions.perform();
        map.click();
    }

    public boolean isMapImageWasShown() {
        return driver.findElement(By.xpath(MAP_IMAGE)).isDisplayed();
    }

    public void setUpSearch() {
        enterCity("Сочи");
        setUpDate();
        searchButton.click();
    }

    public void goToSomeHotel() {
        enterCity("Сочи");
        setUpDate();
        selectAdultAmount(3);
        selectChildAmount(3);
        searchButton.click();
        firstResultTitle.click();
    }

    public String getTitleText() {
        return firstResultTitle.getText();
    }

    public String getCityText() {
        return cityInput.getText();
    }

    public String getArrivalDateText() {
        return calendarFirst.getText();
    }

    public String getDepartureDateText() {
        return calendarSecond.getText();
    }

    public String getAdultAmountText() {
        return new Select(selectAdult).getFirstSelectedOption().getText();
    }

    public String getChildAmountText() {
        return new Select(selectChild).getFirstSelectedOption().getText();
    }

    public String getRoomAmountText() {
        return new Select(selectRoom).getFirstSelectedOption().getText();
    }

    public void selectCurrency(String currency_name) {
        currencyChooser.click();
        driver.findElement(By.xpath("//*[@class='currency_" + currency_name + "']")).click();
    }

    public void selectPaymentCheckbox() {
        Actions actions = new Actions(driver);
        actions.moveToElement(selectPayment);
        actions.perform();
        selectPayment.click();
    }

    public void selectStarRating(int amount) {
        Actions actions = new Actions(driver);
        actions.moveToElement(selectStartRating);
        actions.perform();
        driver.findElement(By.xpath(STAR + "/a[" + amount + "]")).click();
    }

    public void selectRating(int amount) {
        Actions actions = new Actions(driver);
        actions.moveToElement(rating);
        actions.perform();
        driver.findElement(By.xpath(RATING + "/a[" + amount + "]")).click();
    }

    public void selectDistance(int amount) {
        Actions actions = new Actions(driver);
        actions.moveToElement(distanceCheckbox);
        actions.perform();
        driver.findElement(By.xpath(DISTANCE + "/a[" + amount + "]")).click();
    }

    public void selectBookingRule(int amount) {
        Actions actions = new Actions(driver);
        actions.moveToElement(bookingRules);
        actions.perform();
        driver.findElement(By.xpath(BOOKING_RULES + "/a[" + amount + "]")).click();
    }

    public String getCity() {
        return driver.findElement(By.xpath(CITY_INPUT)).getAttribute("value");
    }

    public List<Integer> getGuestsAmount() {
        int adult = extractGuestAmount(selectAdult);
        int child = extractGuestAmount(selectChild);
        int room = extractGuestAmount(selectRoom);
        return List.of(adult, child, room);
    }

    public int extractGuestAmount(WebElement guest) {
        String text = guest.findElement(By.xpath("./option[@selected='selected']")).getText();
        String amountStr = Arrays.stream(text.split(" ")).findFirst().get();
        return amountStr.equals("Без") ? 0 : Integer.parseInt(amountStr);
    }

    public List<String> getGuestsDates() {
        return driver.findElements(By.xpath(SELECTED_DATES)).stream()
                .map(date -> date.getAttribute("data-date"))
                .collect(Collectors.toList());
    }

}
