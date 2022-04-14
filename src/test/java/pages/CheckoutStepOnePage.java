package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutStepOnePage {
      protected WebDriver driver;

      @FindBy(id = "first-name")
      private WebElement userFirstNameInput;

      @FindBy(id = "last-name")
      private WebElement userLastNameInput;

      @FindBy(id = "postal-code")
      private WebElement userPostalCodeInput;

      @FindBy(id = "continue")
      private WebElement continueWithCheckoutBtn;

      @FindBy(id = "cancel")
      private WebElement cancelBtn;

      @FindBy(xpath = "//button[@class='error-button']")
      private WebElement xButtonOfCheckoutErrorMessages;

      @FindBy(xpath = "//*[text()='Checkout: Your Information']")
      WebElement stepOneOfCheckoutTitle;

      public CheckoutStepOnePage(WebDriver driver) {
            this.driver = driver;
            PageFactory.initElements(driver, this);
      }

      public CheckoutStepTwoPage fillInDataForCheckout(String firstName, String lastName, String postalCode){
            userFirstNameInput.click();
            userFirstNameInput.sendKeys(firstName);

            userLastNameInput.click();
            userLastNameInput.sendKeys(lastName);

            userPostalCodeInput.click();
            userPostalCodeInput.sendKeys(postalCode);

            continueWithCheckoutBtn.click();

           return new CheckoutStepTwoPage(driver);
      }
      public boolean stepOneOfCheckoutIsDisplayed() {
            try{
                  stepOneOfCheckoutTitle.isDisplayed();
            } catch (org.openqa.selenium.NoSuchElementException e){
                  return false;
            }
            return true;
      }

}
