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

public class CartPage{
    protected WebDriver driver;

    private static final String REMOVED_PRODUCT_FROM_CART_LOCATOR = "//button[@id='remove-%s']";

    @FindBy(className = "shopping_cart_link")
    private WebElement shoppingCartLink;

    @FindBy(className = "shopping_cart_badge")
    private WebElement shoppingCartCounter;

    @FindBy(id = "continue-shopping")
    private WebElement backToShoppingLink;

    @FindBy(id = "checkout")
    private WebElement checkoutBtn;

    @FindBy(xpath = "//*[text()='Your Cart']")
    WebElement shoppingCartTitle;

    public CartPage (WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean removeItemFromTheCart(String productName){
        String xpathOfElementToBeRemoved = String.format(REMOVED_PRODUCT_FROM_CART_LOCATOR, productName);

        FluentWait fluentWait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofSeconds(3))
                .ignoreAll(Collections.singleton(NoSuchElementException.class));

        WebElement removeBtn = driver.findElement(By.xpath(xpathOfElementToBeRemoved));
        fluentWait.until(ExpectedConditions.elementToBeClickable(removeBtn));

        if (removeBtn.getText().equals("Remove")){
            removeBtn.click();
            return true;
        }else{
            return false;
        }
    }

    public int getItemsInTheCart(){
        if(shoppingCartCounter.getText().isEmpty()){
            return 0;
        } else{
            return Integer.parseInt(shoppingCartCounter.getText());
        }
    }

    public CheckoutStepOnePage continueToPageOneOfCheckout(){

        checkoutBtn.click();

        return new CheckoutStepOnePage(driver);
        }

    public boolean isCartPageDisplayed () {
        try {
            shoppingCartTitle.isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
        return true;
    }

    public boolean isProductAddedToTheCart(String productName) {
        String xpathOfElementToBeChecked = String.format(REMOVED_PRODUCT_FROM_CART_LOCATOR, productName);

        FluentWait fluentWait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofSeconds(3))
                .ignoreAll(Collections.singleton(NoSuchElementException.class));

        WebElement removeBtn = driver.findElement(By.xpath(xpathOfElementToBeChecked));
        fluentWait.until(ExpectedConditions.elementToBeClickable(removeBtn));

        try {
            removeBtn.isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
        return true;
    }
}
