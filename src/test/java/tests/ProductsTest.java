package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static user.UserFactory.withAdminPermission;


@Epic("Тестирование интернет площадки")
@Feature("Каталог товаров")
@Owner("Бугоркова Ирина")
public class ProductsTest extends BaseTest {
    List<String> goodsList =
            List.of("Test.allTheThings() T-Shirt (Red)",
                    "Sauce Labs Onesie",
                    "Sauce Labs Fleece Jacket");

    @Story("Добавление нескольких товаров из каталога")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Пользователь может добавить несколько товаров и видит корректный счётчик корзины")
    public void checkGoodsAdded() {
        System.out.println("ProductsTEst.correct!!!! in thread: " + Thread.currentThread().threadId());
        loginPage.open();
        loginPage.login(withAdminPermission());

        assertTrue(productsPage.isTitleIsDisplayed());
        assertEquals(productsPage.checkTitleName(), "Products");

        for (String goods : goodsList) {
            productsPage.addGoodsToCart(goods);
        }

        productsPage.addGoodsToCart(2);
        assertEquals(productsPage.chekCounterValue(), "4");
        assertEquals(productsPage.chekCounterColor(), "rgba(226, 35, 26, 1)");
    }
}