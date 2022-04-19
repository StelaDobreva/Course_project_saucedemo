package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutStepTwoPage {
    protected WebDriver driver;

    @FindBy(id = "finish")
    private WebElement finishCheckoutBtn;

    @FindBy(xpath = "//*[text()='Checkout: Overview']")
    private WebElement stepTwoOfCheckoutTitle;

    public CheckoutStepTwoPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public CheckoutCompletePage goToFinishCheckout(){
        finishCheckoutBtn.click();
        return new CheckoutCompletePage(driver);
    }

    public boolean stepTwoOfCheckoutIsDisplayed() {
        try{
            stepTwoOfCheckoutTitle.isDisplayed();
        } catch (NoSuchElementException e){
            return false;
        }
        return true;
    }
}
