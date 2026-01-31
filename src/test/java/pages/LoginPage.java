package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private final By LoginInput = By.id("user-name");
    private final By PasswordInput = By.cssSelector(DATA_TEST_PATTERN.formatted("password"));
    private final By LoginButton = By.cssSelector("[name='login-button']");
    private final By errorMsg = By.cssSelector(DATA_TEST_PATTERN.formatted("error"));

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(BASE_URL);
    }

    public void login(String user, String password) {
        fillLoginField(user);
        fillPasswordField(password);
        driver.findElement(LoginButton).click();
    }

    public void fillLoginField(String user) {
        driver.findElement(LoginInput).sendKeys(user);
    }

    public void fillPasswordField(String password) {
        driver.findElement(PasswordInput).sendKeys(password);
    }

    public boolean isErrorDisplayed() {
        return driver.findElement(errorMsg).isDisplayed();
    }

    public String getErrorText() {
        return driver.findElement(errorMsg).getText();
    }
}
