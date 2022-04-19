package my.qa.automation;

import base.TestUtil;
import com.opencsv.exceptions.CsvException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductsPage;
import utils.csvHelper;

import java.io.IOException;

public class AddProductsToCartTests extends TestUtil {

    @DataProvider(name = "usersDataCorrect")
    public static Object[][] readUsersFromCsvFile() throws IOException, CsvException {
        return csvHelper.readCsvFile ("src/test/resources/userAndProductDataCorrect.csv");
    }

    @Test(dataProvider = "usersDataCorrect")
    public void addItemToTheCart(String userName, String password, String productName1, String productName2) {

        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.login(userName, password);

        productsPage.addItemToTheCart(productName1);
        productsPage.addItemToTheCart(productName2);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(productsPage.getItemsInTheCart(), 2, "Because we have two items added in the cart");

        CartPage cartPage = productsPage.goToTheCart();

        Assert.assertTrue(cartPage.isProductAddedToTheCart(productName1));
        Assert.assertTrue(cartPage.isProductAddedToTheCart(productName2));
    }
}


