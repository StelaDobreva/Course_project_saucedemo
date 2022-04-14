package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.Collections;
import java.util.NoSuchElementException;

public class ProductInnerPage {

    protected WebDriver driver;
    private static final String ADD_TO_CART_LOCATOR = "//button[@id='add-to-cart-%s']";
    private static final String REMOVE_FROM_CART_LOCATOR = "//button[@id='remove-%s']";


    @FindBy(id = "back-to-products")
    WebElement backToProductsBtn;

    public ProductInnerPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isBackToProductsBtnDisplayed() {
        try {
            backToProductsBtn.isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
        return true;
    }

    public boolean isProductInnerPageVisible(String productName) {
        String xpathOfElementToBeChecked = String.format(ADD_TO_CART_LOCATOR, productName);

        FluentWait fluentWait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofSeconds(3))
                .ignoreAll(Collections.singleton(NoSuchElementException.class));

        WebElement addOrRemoveBtn = driver.findElement(By.xpath(xpathOfElementToBeChecked));
        fluentWait.until(ExpectedConditions.elementToBeClickable(addOrRemoveBtn));

        try {
            addOrRemoveBtn.isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
        return true;
    }
}

