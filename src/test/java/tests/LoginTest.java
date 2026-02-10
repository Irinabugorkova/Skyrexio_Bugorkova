package tests;

import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static user.UserFactory.withAdminPermission;

@Epic("Тестирование интернет площадки")
@Feature("Авторизация пользователя")
@Owner("Бугоркова Ирина")
public class LoginTest extends BaseTest {

    @Story("Успешная авторизация с валидными данными")
    @Severity(SeverityLevel.BLOCKER)
    @TmsLink("Skyrexio_Bugorkova")
    @Test(priority = 1, description = "Тест проверяет правильный логин и пароль")
    public void correctLogin() {
        System.out.println("LoginTest.correct!!!! in thread: " + Thread.currentThread().threadId());
        loginPage.open();
        loginPage.login(withAdminPermission());

        boolean isTitleDispl = productsPage.isTitleIsDisplayed();
        assertTrue(productsPage.isTitleIsDisplayed(), "Заголовок не виден");
        assertEquals(productsPage.checkTitleName(), "Products",
                "Не верный заголовок");
    }

    @DataProvider(name = "incorrectLoginDta")
    public Object[][] loginData() {
        return new Object[][]{
                {"locked_out_user", "secret_sauce", "Epic sadface: Sorry, this user has been locked out."},
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"STANDARD_USER", "secret_sauce", "Epic sadface: Username and password do not match any user in this service"},
                {"standard_user", "", "Epic sadface: Password is required"},
        };
    }

    @Story("Ошибка авторизации при некорректных данных")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 2, dataProvider = "incorrectLoginDta",
            description = "Тест проверяет неправильный ллогин и пароль")
    public void incorrectLogin(String user, String password, String errorMsg) {
        System.out.println("LoginTest.incorrect!!!! in thread: " + Thread.currentThread().threadId());
        loginPage.open();
        loginPage.negativeLogin(user, password);

        assertTrue(loginPage.isErrorDisplayed(), "Нет сообщения об ошибке");
        assertEquals(loginPage.getErrorText(), errorMsg,
                "Не верный текст сообщения об ошибке ");
    }
}
