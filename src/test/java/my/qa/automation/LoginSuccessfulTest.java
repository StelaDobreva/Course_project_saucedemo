package my.qa.automation;

import base.TestUtil;
import com.opencsv.exceptions.CsvException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;
import utils.csvHelper;
import java.io.IOException;

public class LoginSuccessfulTest extends TestUtil {

   @DataProvider(name = "csvLoginDataCorrect")
    public static Object[][] readUsersFromCsvFile() throws IOException, CsvException {
       return csvHelper.readCsvFile("src/test/resources/loginDataCorrect.csv");
    }

    @Test (dataProvider = "csvLoginDataCorrect")
    public void successfulLogin(String userName, String password) {

        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.login(userName, password);

        Assert.assertTrue(productsPage.isBurgerMenuDisplayed());
    }
}
