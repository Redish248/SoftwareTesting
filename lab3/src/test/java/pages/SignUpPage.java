package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignUpPage extends PageObject  {
    public SignUpPage(WebDriver driver) {
        super(driver);
    }

    private final String EMAIL_INPUT = "//*[@id='login_name_register']";
    private final String GET_STARTED = "//*[@id='root']/div/div[2]/div[1]/div/div/div/div/div/div/form/button";
    private final String EMAIL_ERROR = "//*[@id='login_name_register-description']";
    private final String PASSWORD_INPUT_1 = "//*[@id='password']";
    private final String PASSWORD_INPUT_2 = "//*[@id='confirmed_password']";
    private final String CREATE_ACCOUNT = "//*[@id='root']/div/div[2]/div[1]/div/div/div/div/div/div/form/button";
    private final String ERROR_UNDER_PWD_1 = "//*[@id='password-description']";
    private final String ERROR_UNDER_PWD_2 = "//*[@id='confirmed_password-description']";
    private final String LOGO = "//div[@class='bui-header__logo']";

    @FindBy(xpath = EMAIL_INPUT)
    WebElement emailInput;

    @FindBy(xpath = GET_STARTED)
    WebElement getStartedButton;

    @FindBy(xpath = EMAIL_ERROR)
    WebElement emailErrorMessage;

    @FindBy(xpath = PASSWORD_INPUT_1)
    WebElement passwordInputFirst;

    @FindBy(xpath = PASSWORD_INPUT_2)
    WebElement passwordInputSecond;

    @FindBy(xpath = LOGO)
    WebElement logo;

    @FindBy(xpath = CREATE_ACCOUNT)
    WebElement createAccountButton;

    @FindBy(xpath = ERROR_UNDER_PWD_1)
    WebElement errorUnderPwd1;

    @FindBy(xpath = ERROR_UNDER_PWD_2)
    WebElement errorUnderPwd2;

    public void enterEmail(String email) {
        emailInput.sendKeys(email);
    }

    public void clickGetStarted() {
        getStartedButton.click();
    }

    public void enterPasswordFirst(String password) {
        passwordInputFirst.sendKeys(password);
    }

    public void enterPasswordSecond(String password) {
        passwordInputSecond.sendKeys(password);
    }

    public void clickToCreateAccount() {
        createAccountButton.click();
    }

    public void createAccount(String email, String password) {
        enterEmail(email);
        clickGetStarted();
        if (isPasswordInputDisplayed()) {
            enterPasswordFirst(password);
            enterPasswordSecond(password);
            clickToCreateAccount();
        }
    }

    public void createAccount(String email, String password, String repeatedPassword) {
        enterEmail(email);
        clickGetStarted();
        if (isPasswordInputDisplayed()) {
            enterPasswordFirst(password);
            enterPasswordSecond(repeatedPassword);
            clickToCreateAccount();
        }
    }

    public boolean isEmailInputDisplayed() {
        return emailInput.isDisplayed();
    }

    public boolean isEmailErrorDisplayed() {
        return emailErrorMessage.isDisplayed();
    }

    public boolean isPasswordInputDisplayed() {
        return passwordInputFirst.isDisplayed() && passwordInputSecond.isDisplayed();
    }

    public boolean isPasswordFirstErrorDisplayed() {
        return errorUnderPwd1.isDisplayed();
    }

    public boolean isPasswordSecondErrorDisplayed() {
        return errorUnderPwd2.isDisplayed();
    }

}
