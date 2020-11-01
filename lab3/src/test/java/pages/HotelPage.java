package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class HotelPage extends PageObject{
    public HotelPage(WebDriver driver) {
        super(driver);
    }

    public final String HOTEL_TITLE = "//*[@id='hp_hotel_name']";
    public final String CITY_INPUT = "//*[@id='ss']";
    public final String CALENDAR_1 = "//*[@id='frm']/div[3]/div/div[1]/div[1]/div/div/div/div[2]";
    public final String CALENDAR_2 = "//*[@id='frm']/div[3]/div/div[1]/div[2]/div/div/div/div[2]";
    public final String ADULT = "//*[@id='group_adults']";
    public final String CHILD = "//*[@id='group_children']";
    public final String ROOM = "//*[@id='no_rooms']";
    public final String CHILD_AGE = "//*[@id='frm']/div[4]/div/div/div/div[2]/div[2]";
    private final String BOOK_BUTTON = "//*[@id='hp_book_now_button']";

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
}
