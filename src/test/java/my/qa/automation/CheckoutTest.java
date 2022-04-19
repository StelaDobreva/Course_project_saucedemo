package my.qa.automation;

import base.TestUtil;
import com.opencsv.exceptions.CsvException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.*;
import utils.csvHelper;

import java.io.IOException;

public class CheckoutTest extends TestUtil {

    @DataProvider(name = "correctCheckoutUserData")
    public static Object[][] readUsersFromCsvFile() throws IOException, CsvException {
        return csvHelper.readCsvFile("src/test/resources/correctCheckoutUserData.csv");
    }

    @Test(dataProvider = "correctCheckoutUserData")
    public void addItemToTheCart(String userName, String password, String productName1, String productName2, String productName3, String firstName, String lastName, String postalCode) {

        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.login(userName, password);
        productsPage.addItemToTheCart(productName1);
        productsPage.addItemToTheCart(productName2);
        productsPage.addItemToTheCart(productName3);

            SoftAssert softAssert = new SoftAssert();
            softAssert.assertEquals(productsPage.getItemsInTheCart(), 3, "Because we have three items added in the cart");

        productsPage.removeItemFromTheCart(productName2);

            softAssert.assertEquals(productsPage.getItemsInTheCart(), 2, "Because we have two items added in the cart");

        CartPage cartPage = productsPage.goToTheCart();

            softAssert.assertTrue(cartPage.isCartPageDisplayed());

        cartPage.removeItemFromTheCart(productName3);

            softAssert.assertEquals(cartPage.getItemsInTheCart(), 1, "Because we have only one item in the cart");

        CheckoutStepOnePage checkoutStepOnePage = cartPage.continueToPageOneOfCheckout();

            softAssert.assertTrue(checkoutStepOnePage.stepOneOfCheckoutIsDisplayed());

        CheckoutStepTwoPage checkoutStepTwoPage = checkoutStepOnePage.fillInDataForCheckout(firstName, lastName, postalCode);

            softAssert.assertTrue(checkoutStepTwoPage.stepTwoOfCheckoutIsDisplayed());

        CheckoutCompletePage checkoutCompletePage = checkoutStepTwoPage.goToFinishCheckout();

            softAssert.assertTrue(checkoutCompletePage.checkoutCompleteMessageIsDisplayed());
    }
}
