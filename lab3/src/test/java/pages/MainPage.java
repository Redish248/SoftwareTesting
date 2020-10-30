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

    public final String DESTINATION_INPUT = "//*[@class='sb-destination-label-sr']/input";
    public final String DESTINATION_SUGGESTIONS = "//*[@data-component='search/destination/input']//span[@class='search_hl_name']";

    @FindBy(xpath = DESTINATION_INPUT)
    public WebElement destinationInput;

    public List<WebElement> getSuggestions() {
        return driver.findElements(By.xpath(DESTINATION_SUGGESTIONS));
    }

    public void focusCity() {
        this.destinationInput.click();
    }

    public void enterCity(String city) {
        this.destinationInput.clear();
        this.destinationInput.sendKeys(city);
    }
}
