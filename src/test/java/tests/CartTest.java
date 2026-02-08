package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;

import static org.testng.Assert.*;
import static user.UserFactory.withAdminPermission;

@Epic("Тестирование интернет площадки")
@Feature("Корзина")
@Owner("Бугоркова Ирина")
public class CartTest extends BaseTest {
    final String goodName = "Sauce Labs Onesie";

    @Story("Добавление товара в корзину")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Пользователь может добавить товар в корзину и увидеть его в списке")
    public void checkGoodsAdded() {
        System.out.println("CartTest.incorrect!!!! in thread: " + Thread.currentThread().threadId());
        loginPage.open();
        loginPage.login(withAdminPermission());

        assertEquals(productsPage.checkTitleName(), "Products");

        productsPage.addGoodsToCart(goodName);
        productsPage.switchToCart();

        assertEquals(cartPage.checkTitleName(), "Your Cart");
        assertEquals(cartPage.getProductsNames().size(), 1);
        assertFalse(cartPage.getProductsNames().isEmpty());
        assertTrue(cartPage.getProductsNames().contains(goodName));
    }
}
