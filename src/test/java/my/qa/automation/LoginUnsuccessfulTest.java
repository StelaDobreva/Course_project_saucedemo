package my.qa.automation;

import base.TestUtil;
import com.opencsv.exceptions.CsvException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.csvHelper;

import java.io.IOException;

public class LoginUnsuccessfulTest extends TestUtil {

    @DataProvider(name = "csvUsersDataWrong")
    public static Object[][] readUsersFromCsvFile() throws IOException, CsvException {
        return csvHelper.readCsvFile("src/test/resources/loginDataWrong.csv");
    }

    @Test(dataProvider = "csvUsersDataWrong")
    public void unsuccessfulLoginTest(String userName, String password) {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(userName, password);

        Assert.assertTrue(loginPage.isLoginErrorMessageShown());
    }
}
