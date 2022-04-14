package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutCompletePage {
    protected WebDriver driver;

    @FindBy(className = "complete-text")
    private WebElement checkoutCompleteMessage;


    public CheckoutCompletePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean checkoutCompleteMessageIsDisplayed() {
        try{
            checkoutCompleteMessage.isDisplayed();
        } catch (NoSuchElementException e){
            return false;
        }
        return true;
    }
}
