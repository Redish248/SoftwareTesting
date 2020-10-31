package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
    private final String TRAVEL_BY_WORK = "//*[@id='frm']/div[5]/div[1]/div[3]/label/input";
    private final String SEARCH_BUTTON = "//*[@id='frm']/div[5]/div[2]/button";
    private final String FIRST_RESULT = "//*[@id='hotellist_inner']/div[1]";
    private final String FIRST_RESULT_IMAGE = "//*[@id='hotellist_inner']/div[1]/div/a/img";
    private final String FIRST_RESULT_TITLE = "//*[@id='hotellist_inner']/div[1]/div[2]/div[1]/div[1]/div[1]/h3/a";
    private final String FIRST_RESULT_CHOOSE_BUTTON = "//*[@id='hotellist_inner']/div[1]/div[2]/div[3]/div/div/div/div/div[2]/div[2]/div/div/div/a";
    private final String CITY_ERROR = "//*[@id=\"destination__error\"]/div";

    @FindBy(xpath = CITY_INPUT)
    WebElement cityInput;

    @FindBy(xpath = CALENDAR_1)
    WebElement calendarFirst;

    @FindBy(xpath = CALENDAR_2)
    WebElement calendarSecond;

    @FindBy(xpath = SELECT_ADULT)
    WebElement selectAdult;

    @FindBy(xpath = SELECT_CHILD)
    WebElement selectChild;

    @FindBy(xpath = SELECT_ROOM)
    WebElement selectRoom;

    @FindBy(xpath = TRAVEL_BY_WORK)
    WebElement travelByWorkCheckbox;

    @FindBy(xpath = SEARCH_BUTTON)
    WebElement searchButton;

    @FindBy(xpath = FIRST_RESULT)
    WebElement firstResult;

    @FindBy(xpath = FIRST_RESULT_IMAGE)
    WebElement firstResultImage;

    @FindBy(xpath = FIRST_RESULT_TITLE)
    WebElement firstResultTitle;

    @FindBy(xpath = FIRST_RESULT_CHOOSE_BUTTON)
    WebElement firstResultChooseButton;

    @FindBy(xpath = CITY_ERROR)
    WebElement cityError;

    public void enterCity(String city) {
        cityInput.sendKeys(city);
    }

    public void enterDateFirst() {
        calendarFirst.click();
        
    }

    public void enterDateSecond() {

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
        WebElement number = driver.findElement(By.xpath(SELECT_CHILD.concat("/option[" + amount + "]")));
        number.click();
    }

    public void setTravelByWork() {
        travelByWorkCheckbox.click();
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

    public boolean isCityErrorDisplayed() {
        return cityError.isDisplayed();
    }

}
