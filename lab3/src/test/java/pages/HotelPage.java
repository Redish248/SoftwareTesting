package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
        return adultInput.getText();
    }

    public String getChildAmount() {
        return childInput.getText();
    }

    public String getRoomAmount() {
        return roomInput.getText();
    }
}
