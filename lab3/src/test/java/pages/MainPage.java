package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends PageObject {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    private final String DESTINATION = "//*[@class='sb-destination-label-sr']/input";

    @FindBy(xpath = DESTINATION)
    public WebElement destinationInput;

    public void enterCity(String city) {
        this.destinationInput.clear();
        this.destinationInput.sendKeys(city);
    }
}
