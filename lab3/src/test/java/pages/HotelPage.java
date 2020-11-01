package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HotelPage extends PageObject{
    public HotelPage(WebDriver driver) {
        super(driver);
    }

    private final String HOTEL_TITLE = "//*[@id='hp_hotel_name']";
    private final String CITY_INPUT = "//*[@id='ss']";
    private final String CALENDAR_1 = "//*[@id='frm']/div[3]/div/div[1]/div[1]/div/div/div/div[2]";
    private final String CALENDAR_2 = "//*[@id='frm']/div[3]/div/div[1]/div[2]/div/div/div/div[2]";
    private final String ADULT = "//*[@id='group_adults']";
    private final String CHILD = "//*[@id='group_children']";
    private final String ROOM = "//*[@id='no_rooms']";
    private final String CHILD_AGE = "//*[@id='frm']/div[4]/div/div/div/div[2]/div[2]";
    private final String BOOK_BUTTON = "//*[@id='hp_book_now_button']";
    private final String NAVIGATION = "//*[@id='right']/div[2]/ul/li";
    private final String CHOOSE_ROOM = "//*[@id='hprt-table']/tbody/tr[1]/td[5]/div/select";
    private final String I_BOOK_BUTTON = "//*[@id='hprt-form']/div[16]/div[2]/div[2]/div[6]/button";

    @FindBy(xpath = HOTEL_TITLE)
    WebElement hotelTitle;

    @FindBy(xpath = CITY_INPUT)
    WebElement cityInput;

    @FindBy(xpath = CALENDAR_1)
    WebElement calendarFirst;

    @FindBy(xpath = CALENDAR_2)
    WebElement calendarSecond;

    @FindBy(xpath = ADULT)
    WebElement adultInput;

    @FindBy(xpath = CHILD)
    WebElement childInput;

    @FindBy(xpath = ROOM)
    WebElement roomInput;

    @FindBy(xpath = BOOK_BUTTON)
    WebElement bookButton;

    @FindBy(xpath = CHOOSE_ROOM)
    WebElement chooseRoomButton;

    @FindBy(xpath = I_BOOK_BUTTON)
    WebElement iBookButton;

    public String getCity() {
        return cityInput.getText();
    }

    public String getHotelTitle() {
        return hotelTitle.getText();
    }

    public String getArrivalDate() {
        return calendarFirst.getText();
    }

    public String getDepartureDate() {
        return calendarSecond.getText();
    }

    public String getAdultAmount() {
        return new Select(adultInput).getFirstSelectedOption().getText();
    }

    public String getChildAmount() {
        return new Select(childInput).getFirstSelectedOption().getText();
    }

    public String getRoomAmount() {
        return new Select(roomInput).getFirstSelectedOption().getText();
    }

    public int getChildAgeSize() {
        return driver.findElements(By.xpath(CHILD_AGE)).size();
    }

    public void navigateToSomewhere(int number) {
        driver.findElement(By.xpath(NAVIGATION + "[" + number + "]/a")).click();
    }

    public void bookHotel() {
        bookButton.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(CHOOSE_ROOM)));
        chooseRoomButton.click();
        chooseRoomButton.findElement(By.xpath("./option[2]")).click();
        iBookButton.click();
    }
}
