package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import user.User;

public class LoginPage extends BasePage {

    private final By LoginInput = By.id("user-name");
    private final By PasswordInput = By.cssSelector(DATA_TEST_PATTERN.formatted("password"));
    private final By LoginButton = By.cssSelector("[name='login-button']");
    private final By errorMsg = By.cssSelector(DATA_TEST_PATTERN.formatted("error"));

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открытие браузера")
    public void open() {
        driver.get(BASE_URL);
    }

    @Step("Логинимся под кредами пользователя")
    public void negativeLogin(String user, String password) {
        fillLoginField(user);
        fillPasswordField(password);
        driver.findElement(LoginButton).click();
    }

    public void login(User user) {
        fillLoginField(user.getEmail());
        fillPasswordField(user.getPassword());
        driver.findElement(LoginButton).click();
    }

    @Step("Вводим логин {user}")
    public void fillLoginField(String user) {
        driver.findElement(LoginInput).sendKeys(user);
    }

    @Step("Вводим пароль {password}")
    public void fillPasswordField(String password) {
        driver.findElement(PasswordInput).sendKeys(password);
    }

    @Step("Проверяет видимость сообщения об ошибке")
    public boolean isErrorDisplayed() {
        return driver.findElement(errorMsg).isDisplayed();
    }

    @Step("Проверяет текст сообщения об ошибке")
    public String getErrorText() {
        return driver.findElement(errorMsg).getText();
    }
}
