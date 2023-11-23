package golftests.ui.uitests.auth;

import golf.ui.pages.HomePage;
import golf.ui.pages.LoginPage;
import golftests.dataprovider.XlsDataProvider;
import golftests.ui.uibase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import static golf.utilities.Constant.*;

//@Listeners(TestListener.class)
public class Login extends BaseTest {


    @Test
    public void loginAsAdmin() {
        HomePage homePage = new HomePage(driver, wait);
        LoginPage loginPage = new LoginPage(driver, wait);
        homePage.openPage(BASE_URL_UI);
        homePage.clickProfileButton();
        loginPage.login(ADMIN_LOGIN, ADMIN_PASSWORD);
        Assert.assertTrue(homePage.isAdminHomePageLoaded());
    }

    @Test
    public void loginAsUser() {
        HomePage homePage = new HomePage(driver, wait);
        LoginPage loginPage = new LoginPage(driver, wait);
        homePage.openPage(BASE_URL_UI);
        homePage.clickProfileButton();
        loginPage.login(CLIENT_LOGIN, CLIENT_PASSWORD);
        Assert.assertTrue(homePage.isUserHomePageLoaded());
    }

    @Test(dataProvider = "InvalidLoginCredentials", dataProviderClass = XlsDataProvider.class)
    public void loginAsUserFalse(String login, String password, String expectedMessage) {
        HomePage homePage = new HomePage(driver, wait);
        LoginPage loginPage = new LoginPage(driver, wait);
        homePage.openPage(BASE_URL_UI);
        homePage.clickProfileButton();
        loginPage.login(login, password);
        loginPage.assertExactTextAppearedOnPage(expectedMessage);
    }
}
