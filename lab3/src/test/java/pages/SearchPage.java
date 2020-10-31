package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.NoSuchElementException;

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
    private final String FIRST_RESULT_NAME = "//*[@id='hotellist_inner']/div[1]/div[2]/div[1]/div[1]/div[2]/a";
    private final String CITY_ERROR = "//*[@id='destination__error']/div";
    private final String AUTOCOMPLETE = "//*[@id='frm']/div[2]/div/div[1]/ul[2]/li[1]";
    private final String FOURTH_OF_NOVEMBER = "//*[@id='frm']/div[3]/div/div[2]/div/div/div[3]/div[2]/table/tbody/tr[2]/td[3]";
    private final String ELEVENTH_OF_NOVEMBER = "//*[@id='frm']/div[3]/div/div[2]/div/div/div[3]/div[2]/table/tbody/tr[3]/td[2]";
    private final String FIFTH_OF_OCTOBER = "//*[@id='frm']/div[3]/div/div[2]/div/div/div[3]/div[2]/table/tbody/tr[2]/td[4]";
    private final String DEC_7 = "//*[@id='frm']/div[3]/div/div[2]/div/div/div[3]/div[2]/table/tbody/tr[2]/td[1]";
    private final String NOV_10 = "//*[@id='frm']/div[3]/div/div[2]/div/div/div[3]/div[1]/table/tbody/tr[3]/td[2]";
    private final String PAST_DATE = "//*[@id='frm']/div[3]/div/div[2]/div/div/div[3]/div[1]/table/tbody/tr[3]/td[4]";
    private final String DAYS_AND_PEOPLE_TEXT = "//*[@id='hotellist_inner']/div[1]/div[2]/div[3]/div/div/div/div/div[2]/div[1]/div[1]/div";
    private final String DAYS_AND_PEOPLE_CHILD_TEXT = "//*[@id='hotellist_inner']/div[1]/div[2]/div[3]/div/div[2]/div/div[1]/div";
    private final String CHILD_1 = "//*[@id='frm']/div[4]/div/div/div/div[2]/div[2]/div[1]/div[1]/select";
    private final String CHILD_2 = "//*[@id='frm']/div[4]/div/div/div/div[2]/div[2]/div[2]/div[2]/select";
    private final String CHILD_3 = "//*[@id='frm']/div[4]/div/div/div/div[2]/div[2]/div[2]/div[3]/select";

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

    @FindBy(xpath = AUTOCOMPLETE)
    WebElement firstItemInAutocomplete;

    @FindBy(xpath = FIRST_RESULT_NAME)
    WebElement firstResultCity;

    @FindBy(xpath = FOURTH_OF_NOVEMBER)
    WebElement fourthOfNovember;

    @FindBy(xpath = ELEVENTH_OF_NOVEMBER)
    WebElement eleventhOfNovember;

    @FindBy(xpath = FIFTH_OF_OCTOBER)
    WebElement fifthOfOctober;

    @FindBy(xpath = PAST_DATE)
    WebElement pastDate;

    @FindBy(xpath = DAYS_AND_PEOPLE_TEXT)
    WebElement daysAndPeople;

    @FindBy(xpath = DAYS_AND_PEOPLE_CHILD_TEXT)
    WebElement daysAndPeopleChild;

    @FindBy(xpath = CHILD_1)
    WebElement firstChild;

    @FindBy(xpath = CHILD_2)
    WebElement secondChild;

    @FindBy(xpath = CHILD_3)
    WebElement thirdChild;

    public void enterCity(String city) {
        cityInput.click();
        cityInput.sendKeys(city);
    }

    public void enterDateFirst(int date) {
        calendarFirst.click();
        switch (date) {
            case 4:
                fourthOfNovember.click();
                break;
            case 11:
                eleventhOfNovember.click();
                break;
        }
    }

    public void enterDateSecond() {
        calendarSecond.click();
        fifthOfOctober.click();
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

    public void clickToAutocomplete() {
        firstItemInAutocomplete.click();
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

    public boolean checkFirstResultCity() {
        return firstResultCity.getText().contains(cityInput.getText());
    }

    public String getFirstResultCity() {
        return firstResultCity.getText();
    }

    public void clickDateInPastFirst() {
        calendarFirst.click();
        pastDate.click();
    }

    public void clickDateInPastSecond() {
        calendarSecond.click();
        pastDate.click();
    }

    public boolean isCityErrorDisplayed() {
        return cityError.isDisplayed();
    }

    public String getDaysAndPeopleText() {
        return daysAndPeople.getText();
    }

    public String getDaysAndPeopleChildText() {
        return daysAndPeopleChild.getText();
    }

    public void setUpDate() {
        calendarFirst.click();
        driver.findElement(By.xpath(DEC_7)).click();
        calendarSecond.click();
        driver.findElement(By.xpath(NOV_10)).click();
    }

    public boolean isChildFirstDisplayed() {
        return firstChild.isDisplayed();
    }

    public boolean isChildSecondDisplayed() {
        return secondChild.isDisplayed();
    }

    public boolean isChildThirdDisplayed() {
        return thirdChild.isDisplayed();
    }

}
