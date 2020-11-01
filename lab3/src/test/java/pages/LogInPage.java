package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogInPage extends PageObject {
    public LogInPage(WebDriver driver) {
        super(driver);
    }

    private final String EMAIL = "//*[@id='root']/div/div[2]/div[1]/div/div/div/div/div/div/form/div[1]/div/div/div/input";
    private final String NEXT = "//*[@id='root']/div/div[2]/div[1]/div/div/div/div/div/div/form/div[3]/button";
    private final String PASSWORD = "//*[@id='password']";
    private final String SIGN_IN = "//*[@id='root']/div/div[2]/div[1]/div/div/div/div/div/div/form/button";
    private final String EMAIL_ERROR = "//*[@id='username-description']";
    private final String INCORRECT_PASS = "//*[@id='password-description']";
    private final String SHOW_PWD = "//*[@id='root']/div/div[2]/div[1]/div/div/div/div/div/div/form/div/div/div/div/div/div/div[2]/svg";

    @FindBy(xpath = EMAIL)
    WebElement emailInput;

    @FindBy(xpath = NEXT)
    WebElement nextButton;

    @FindBy(xpath = PASSWORD)
    WebElement passwordInput;

    @FindBy(xpath = SIGN_IN)
    WebElement signIn;

    @FindBy(xpath = EMAIL_ERROR)
    WebElement error;

    @FindBy(xpath = INCORRECT_PASS)
    WebElement errorPwd;

    @FindBy(xpath = SHOW_PWD)
    WebElement showPassword;

    public void goNext() {
        nextButton.click();
    }

    public void clickSignIn() {
        signIn.click();
    }

    public void inputEmail(String email) {
        emailInput.sendKeys(email);
    }

    public void inputPassword(String pass) {
        passwordInput.sendKeys(pass);
    }

    public void signIn(String email, String pass) {
        inputEmail(email);
        goNext();
        if (isPassInputDisplayed()) {
            inputPassword(pass);
            clickSignIn();
        }
    }

    public void clickShowPassword() {
        showPassword.click();
    }

    public boolean isEmailInputDisplayed() {
        return emailInput.isDisplayed();
    }

    public boolean isPassInputDisplayed() {
        return passwordInput.isDisplayed();
    }

    public boolean isErrorDisplayed() {
        return error.isDisplayed();
    }

    public boolean isErrorForIncorrectPasswordDisplayed() { return errorPwd.isDisplayed(); }
}
