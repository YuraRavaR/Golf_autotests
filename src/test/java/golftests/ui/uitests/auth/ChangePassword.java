package golftests.ui.uitests.auth;

import golf.ui.pages.HomePage;
import golf.ui.pages.LoginPage;
import golf.ui.pages.ProfilePage;
import golf.utilities.EmailReceiver;
import golftests.dataprovider.XlsDataProvider;
import golftests.ui.uibase.BaseTest;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static golf.utilities.Constant.*;

public class ChangePassword extends BaseTest {
    private String temporaryPassword;
    private boolean runAfterMethod;

    private HomePage homePage;
    private LoginPage loginPage;
    private ProfilePage profilePage;

    @BeforeMethod
    public void setup() {
        homePage = new HomePage(driver, wait);
        loginPage = new LoginPage(driver, wait);
        homePage.openPage(BASE_URL_UI);
        homePage.clickProfileButton();
        loginPage.login(CLIENT_LOGIN, CLIENT_PASSWORD);
        homePage.isUserHomePageLoaded();
        homePage.clickProfileButton();
        loginPage.clickMyProfileBtn();
        profilePage = new ProfilePage(driver, wait);
        profilePage.clickChangePasswordBtn();
    }

    @Test
    public void userChangePassword() {
        profilePage.changePassword(CLIENT_PASSWORD, CLIENT_NEW_PASSWORD, CLIENT_NEW_PASSWORD);
        profilePage.clickSavePasswordBtn();
        String html = EmailReceiver.getLastMailHtmlValue(CLIENT_LOGIN, CLIENT_MAIL_PASSWORD_831);
        temporaryPassword = EmailReceiver.getTemporaryPasswordValue(html);
        runAfterMethod = true;
        homePage.clickProfileButton();
        loginPage.login(CLIENT_LOGIN, CLIENT_NEW_PASSWORD);
        Assert.assertTrue(homePage.isUserHomePageLoaded());
    }
    @Test
    public void userChangePasswordAndLoginWithOldPasswordNegative() {
        profilePage.changePassword(CLIENT_PASSWORD, CLIENT_NEW_PASSWORD, CLIENT_NEW_PASSWORD);
        profilePage.clickSavePasswordBtn();
        String html = EmailReceiver.getLastMailHtmlValue(CLIENT_LOGIN, CLIENT_MAIL_PASSWORD_831);
        temporaryPassword = EmailReceiver.getTemporaryPasswordValue(html);
        runAfterMethod = true;
        homePage.clickProfileButton();
        loginPage.login(CLIENT_LOGIN, CLIENT_PASSWORD);
        loginPage.assertExactTextAppearedOnPage("Invalid email or password");
    }

    @Test(dataProvider = "InvalidChangePasswordData", dataProviderClass = XlsDataProvider.class)
    public void userChangePasswordNegative(String currentPassword, String newPassword,
                                           String confirmNewPassword, String expectedMessage) {
        runAfterMethod = false;
        profilePage.changePassword(currentPassword, newPassword, confirmNewPassword);
        profilePage.assertExactTextAppearedOnPage(expectedMessage);

    }

    @AfterMethod
    public void returnPreviousPassword() {
        if (runAfterMethod) {
            String requestBody = String.format("{\"email\":\"%s\",\"tempPassword\":\"%s\",\"newPassword\":\"%s\"}",
                    CLIENT_LOGIN, temporaryPassword, CLIENT_PASSWORD);

            RestAssured.given()
                    .baseUri(BASE_URI)
                    .basePath(PASSWORD_RECOVERY_BASE_PATH)
                    .contentType(ContentType.JSON).given()
                    .body(requestBody)
                    .filter(new AllureRestAssured())
                    .when()
                    .post()
                    .then()
                    .statusCode(200);
        }
    }

}
