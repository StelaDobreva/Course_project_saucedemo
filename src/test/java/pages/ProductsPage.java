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

public class ProductsPage {

        protected WebDriver driver;
        private static final String ADD_TO_CART_LOCATOR = "//button[@id='add-to-cart-%s']";
        private static final String REMOVE_FROM_CART_LOCATOR = "//button[@id='remove-%s']";
        private static final String PRODUCT_LINK_LOCATOR = "//a[@id='item_%s_title_link']";

        @FindBy(className = "shopping_cart_link")
        private WebElement shoppingCartLink;

        @FindBy(className = "shopping_cart_badge")
        private WebElement shoppingCartCounter;

        @FindBy(id = "react-burger-menu-btn")
        WebElement burgerMenuBtn;

    public ProductsPage(WebDriver driver) {
            this.driver = driver;
            PageFactory.initElements(driver, this);
        }

        public void addItemToTheCart (String productName){
            String xpathOfElementToBeAdded = String.format(ADD_TO_CART_LOCATOR, productName);
            WebElement addToCartBtn = driver.findElement(By.xpath(xpathOfElementToBeAdded));
            addToCartBtn.click();
        }

        public boolean removeItemFromTheCart(String productName){
            String xpathOfElementToBeRemoved = String.format(REMOVE_FROM_CART_LOCATOR, productName);

            FluentWait fluentWait = new FluentWait(driver)
                    .withTimeout(Duration.ofSeconds(5))
                    .pollingEvery(Duration.ofSeconds(3))
                    .ignoreAll(Collections.singleton(NoSuchElementException.class));

            WebElement removeBtn = driver.findElement(By.xpath(xpathOfElementToBeRemoved));
            fluentWait.until(ExpectedConditions.elementToBeClickable(removeBtn));

            if (removeBtn.getText().equals("Remove")) {
                removeBtn.click();
                return true;
            } else {
                return false;
            }
        }

        public int getItemsInTheCart () {
            if (shoppingCartCounter.getText().isEmpty()) {
                return 0;
            } else {
                return Integer.parseInt(shoppingCartCounter.getText());
            }
        }

        public CartPage goToTheCart () {

            shoppingCartLink.click();

            return new CartPage(driver);
        }

        public boolean isBurgerMenuDisplayed(){
            try{
                burgerMenuBtn.isDisplayed();
            } catch (org.openqa.selenium.NoSuchElementException e){
                return false;
            }
            return true;
        }

    public ProductInnerPage goToProductInnerPage (String productNumber) {
        String xpathOfElementToBeClicked = String.format(PRODUCT_LINK_LOCATOR, productNumber);
        WebElement productLink = driver.findElement(By.xpath(xpathOfElementToBeClicked));
        productLink.click();

        return new ProductInnerPage(driver);
    }
    }


