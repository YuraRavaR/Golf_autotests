package golftests.ui.uitests.auth;

import golf.ui.pages.HomePage;
import golf.ui.pages.LoginPage;
import golf.ui.pages.ProfilePage;
import golf.utilities.EmailReceiver;
import golftests.ui.uibase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static golf.utilities.Constant.*;

public class RecoveryPassword extends BaseTest {
    private HomePage homePage;
    private LoginPage loginPage;

    private boolean runAfterMethod = true;


    @BeforeMethod
    public void setup() {
        homePage = new HomePage(driver, wait);
        loginPage = new LoginPage(driver, wait);
        homePage.openPage(BASE_URL_UI);
        homePage.clickProfileButton();
        loginPage.clickForgotPasswordBtn();
    }

    @Test
    public void positiveRecoverAndChangePassword() {
        loginPage.inputEmail(CLIENT_LOGIN);
        loginPage.clickChangePasswordBtn();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String html = EmailReceiver.getLastMailHtmlValue(CLIENT_LOGIN, CLIENT_MAIL_PASSWORD_831);
        String temporaryPassword = EmailReceiver.getTemporaryPasswordValue(html);
        loginPage.inputTempPassword(temporaryPassword);
        loginPage.clickConfirmPasswordBtn();
        loginPage.inputNewPassword(CLIENT_NEW_PASSWORD, CLIENT_NEW_PASSWORD);
        loginPage.clickSavePasswordBtn();

        homePage.openPage(BASE_URL_UI);
        homePage.clickProfileButton();
        loginPage.login(CLIENT_LOGIN, CLIENT_NEW_PASSWORD);
        Assert.assertTrue(homePage.isUserHomePageLoaded());
    }

    @Test
    public void positiveLoginWithRecoveryPassword() {
        loginPage.inputEmail(CLIENT_LOGIN);
        loginPage.clickChangePasswordBtn();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String html = EmailReceiver.getLastMailHtmlValue(CLIENT_LOGIN, CLIENT_MAIL_PASSWORD_831);
        String temporaryPassword = EmailReceiver.getTemporaryPasswordValue(html);
        loginPage.clickRememberThePasswordBtn();
        loginPage.login(CLIENT_LOGIN, temporaryPassword);
        Assert.assertTrue(homePage.isUserHomePageLoaded());

    }

    @Test
    public void negativeLoginWithOldPasswordAfterRecovery() {
        loginPage.inputEmail(CLIENT_LOGIN);
        loginPage.clickChangePasswordBtn();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String html = EmailReceiver.getLastMailHtmlValue(CLIENT_LOGIN, CLIENT_MAIL_PASSWORD_831);
        String temporaryPassword = EmailReceiver.getTemporaryPasswordValue(html);
        loginPage.inputTempPassword(temporaryPassword);
        loginPage.clickConfirmPasswordBtn();
        loginPage.inputNewPassword(CLIENT_NEW_PASSWORD, CLIENT_NEW_PASSWORD);
        loginPage.clickSavePasswordBtn();

        homePage.openPage(BASE_URL_UI);
        homePage.clickProfileButton();
        loginPage.login(CLIENT_LOGIN, CLIENT_PASSWORD);
        loginPage.assertExactTextAppearedOnPage("Invalid email or password");
        homePage.openPage(BASE_URL_UI);
        homePage.clickProfileButton();
        loginPage.login(CLIENT_LOGIN, CLIENT_NEW_PASSWORD);
    }

    @Test
    public void negativeRecoverPasswordNoValidNewPassword() {
        loginPage.inputEmail(CLIENT_LOGIN);
        loginPage.clickChangePasswordBtn();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String html = EmailReceiver.getLastMailHtmlValue(CLIENT_LOGIN, CLIENT_MAIL_PASSWORD_831);
        String temporaryPassword = EmailReceiver.getTemporaryPasswordValue(html);
        loginPage.inputTempPassword(temporaryPassword);
        loginPage.clickConfirmPasswordBtn();
        String invalidNewPassword = "1234test";
        loginPage.inputNewPassword(invalidNewPassword, invalidNewPassword);
        loginPage.assertExactTextAppearedOnPage("Please make sure your password adheres to the specified rules.");
        loginPage.clickRememberThePasswordBtn();
        loginPage.login(CLIENT_LOGIN, temporaryPassword);

    }

    @Test
    public void negativeInvalidTempPassword() {
        loginPage.inputEmail(CLIENT_LOGIN);
        loginPage.clickChangePasswordBtn();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String html = EmailReceiver.getLastMailHtmlValue(CLIENT_LOGIN, CLIENT_MAIL_PASSWORD_831);
        String temporaryPassword = EmailReceiver.getTemporaryPasswordValue(html);
        String invalidTemporaryPassword = "1234QWERTY<>";
        loginPage.inputTempPassword(invalidTemporaryPassword);
        loginPage.clickConfirmPasswordBtn();
        loginPage.assertExactTextAppearedOnPage("Please enter a valid temporary password");
        loginPage.clickRememberThePasswordBtn();
        loginPage.login(CLIENT_LOGIN, temporaryPassword);
        Assert.assertTrue(homePage.isUserHomePageLoaded());
    }

    @AfterMethod
    public void returnPreviousPassword() {
        if (runAfterMethod) {
            homePage.clickProfileButton();
            loginPage.clickMyProfileBtn();
            ProfilePage profilePage = new ProfilePage(driver, wait);
            profilePage.clickChangePasswordBtn();
            profilePage.changePassword(CLIENT_NEW_PASSWORD, CLIENT_PASSWORD, CLIENT_PASSWORD);
            profilePage.clickSavePasswordBtn();
            homePage.clickProfileButton();
            loginPage.login(CLIENT_LOGIN, CLIENT_PASSWORD);
            Assert.assertTrue(homePage.isUserHomePageLoaded());
        }
    }


}
