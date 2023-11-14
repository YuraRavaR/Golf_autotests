package golftests.ui.uitests.auth;

import golf.ui.pages.HomePage;
import golf.ui.pages.LoginPage;
import golftests.dataprovider.XlsDataProvider;
import golftests.ui.uibase.BaseTest;
import io.qameta.allure.Allure;
import org.testng.Assert;
import org.testng.annotations.Test;

import static golf.utilities.Constant.*;

public class Registration extends BaseTest {

    @Test
    public void registerAsUser() {
        Allure.step("registerAsUser");
        HomePage homePage = new HomePage(driver, wait);
        LoginPage loginPage = new LoginPage(driver, wait);
        homePage.openPage(BASE_URL_UI);
        homePage.clickProfileButton();
        loginPage.login(ADMIN_LOGIN, ADMIN_PASSWORD);
        Assert.assertTrue(homePage.isAdminHomePageLoaded());Allure.step("finish");

    }

    @Test(dataProvider = "InvalidRegisterCredentials", dataProviderClass = XlsDataProvider.class)
    public void registerAsUserFalse(String firsName, String lastName, String login, String password,
                                    String expectedMessage) {
        HomePage homePage = new HomePage(driver, wait);
        LoginPage loginPage = new LoginPage(driver, wait);
        homePage.openPage(BASE_URL_UI);
        homePage.clickProfileButton();
        loginPage.clickSignUpBtn();
        loginPage.signUp(firsName,lastName,login,password);
        loginPage.assertExactTextAppearedOnPage(expectedMessage);
    }
}
