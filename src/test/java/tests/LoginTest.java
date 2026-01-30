package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


    public class LoginTest extends BaseTest {
        @Test(priority = 1, description = "Тест проверяет правильный логин и пароль")
        public void correctLogin() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        boolean isTitleDispl = productsPage.isTitleIsDisplayed();
        assertTrue(productsPage.isTitleIsDisplayed(), "Заголовок не виден");
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

        @Test(priority = 2, dataProvider = "incorrectLoginDta")
        public void incorrectLogin(String user, String password, String errorMsg) {
        loginPage.open();
        loginPage.login(user, password);

        assertTrue(loginPage.isErrorDisplayed(), "Нет сообщения об ошибке");
        assertEquals(loginPage.getErrorText(), errorMsg,
                "Не верный текст сообщения об ошибке ");
    }
}
