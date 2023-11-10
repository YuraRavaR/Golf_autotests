package golftests.ui.uitests.auth;

import golf.ui.pages.HomePage;
import golf.ui.pages.LoginPage;
import golf.ui.pages.ProfilePage;
import golftests.dataprovider.XlsDataProvider;
import golftests.ui.uibase.BaseTest;
import org.testng.annotations.Test;

import static golf.utilities.Constant.*;

public class ChangePassword extends BaseTest {
    @Test
    public void userChangePassword() {
        HomePage homePage = new HomePage(driver, wait);
        LoginPage loginPage = new LoginPage(driver, wait);
        homePage.openPage(BASE_URL_UI);
        homePage.clickProfileButton();
        loginPage.login(CLIENT_LOGIN, CLIENT_PASSWORD);
    }

    @Test(dataProvider = "InvalidChangePasswordData", dataProviderClass = XlsDataProvider.class)
    public void userChangePasswordNegative(String currentPassword, String newPassword,
                                           String confirmNewPassword, String expectedMessage) {
        HomePage homePage = new HomePage(driver, wait);
        LoginPage loginPage = new LoginPage(driver, wait);
        homePage.openPage(BASE_URL_UI);
        homePage.clickProfileButton();
        loginPage.login(CLIENT_LOGIN, CLIENT_PASSWORD);
        homePage.isUserHomePageLoaded();
        homePage.clickProfileButton();
        loginPage.clickMyProfileBtn();
        ProfilePage profilePage = new ProfilePage(driver, wait);
        profilePage.clickChangePasswordBtn();
        profilePage.changePassword(currentPassword, newPassword, confirmNewPassword);
        profilePage.assertExactTextAppearedOnPage(expectedMessage);

    }

}
