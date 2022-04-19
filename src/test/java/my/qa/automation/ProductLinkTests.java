package my.qa.automation;

import base.TestUtil;
import com.opencsv.exceptions.CsvException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;
import pages.ProductInnerPage;
import pages.ProductsPage;
import utils.csvHelper;

import java.io.IOException;

public class ProductLinkTests extends TestUtil {

    @DataProvider(name = "csvProductLinkData")
    public static Object[][] readUsersFromCsvFile() throws IOException, CsvException {
        return csvHelper.readCsvFile ("src/test/resources/productLinkData.csv");
    }

    @Test(dataProvider = "csvProductLinkData")
    public void addItemToTheCart(String userName, String password, String productNumber, String productName) {

        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.login(userName, password);

        ProductInnerPage productInnerPage = productsPage.goToProductInnerPage(productNumber);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(productInnerPage.isBackToProductsBtnDisplayed());

        softAssert.assertTrue(productInnerPage.isProductInnerPageVisible(productName));

        softAssert.assertAll();
    }
}


