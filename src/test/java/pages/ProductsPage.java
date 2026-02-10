package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends BasePage {
    private static final String ADD_TO_CART_PATTERN =
            "//*[text()='%s']//ancestor::div[@class='inventory_item']" +
                    "//child::button[text()='Add to cart']";
    private final By title = By.cssSelector(DATA_TEST_PATTERN.formatted("title"));
    private final By cartCounter = By.cssSelector(DATA_TEST_PATTERN.formatted("shopping-cart-badge"));
    private final By cartLink = By.cssSelector(DATA_TEST_PATTERN.formatted("shopping-cart-link"));

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Проверить, что заголовок страницы Products отображается")
    public boolean isTitleIsDisplayed() {

        return driver.findElement(title).isDisplayed();
    }

    @Step("Добавить товар в корзину по названию: {goodName}")
    public void addGoodsToCart(String goodName) {
        By addToCart = By.xpath(ADD_TO_CART_PATTERN.formatted(goodName));
        driver.findElement(addToCart).click();
    }

    @Step("Добавить товар в корзину по индексу: {goodIndex}")
    public void addGoodsToCart(int goodIndex) {
        driver.findElements(By.xpath("//*[text()= 'Add to cart']")).get(goodIndex).click();
    }

    @Step("Получить значение счётчика корзины")
    public String chekCounterValue() {
        return driver.findElement(cartCounter).getText();
    }

    @Step("Получить цвет счётчика корзины")
    public String chekCounterColor() {

        return driver.findElement(cartCounter).getCssValue("background-color");
    }

    @Step("Перейти в корзину")
    public void switchToCart() {
        driver.findElement(cartLink).click();
    }
}
