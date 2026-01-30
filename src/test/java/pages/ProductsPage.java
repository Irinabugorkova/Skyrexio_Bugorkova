package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends BasePage {
//    private static final String TITLE_PATTERN = "//span[text() = '%s']";
    private static final String ADD_TO_CART_PATTERN =
        "//*[text()='%s']//ancestor::div[@class='inventory_item']" +
            "//child::button[text()='Add to cart']";
    private final By title = By.cssSelector("[data-test= 'title']");

    private final By cartCounter = By.cssSelector(DATA_TEST_PATTERN.formatted("shopping-cart-badge"));

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isTitleIsDisplayed() {
      //  By title = By.xpath(title).formatted("Products"));
        return driver.findElement(title).isDisplayed();
    }

    public String getTitle() {
        return driver.findElement(title).getText();
    }

    public void addGoodsToCart(String goodName) {
        By addToCart = By.xpath(ADD_TO_CART_PATTERN.formatted(goodName));
         driver.findElement(addToCart).click();
    }
    public String chekCounterValue() {
       return driver.findElement(cartCounter).getText();
    }
    public String chekCounterColor() {
        return driver.findElement(cartCounter).getCssValue("background-color");
    }
}

