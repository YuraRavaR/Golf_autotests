package golftests.ui.uitests.auth;

import golf.ui.pages.HomePage;
import golf.ui.pages.LoginPage;
import golf.utilities.EmailReceiver;
import golftests.ui.uibase.BaseTest;
import org.testng.annotations.Test;

import static golf.utilities.Constant.BASE_URL_UI;

public class RecoveryPassword extends BaseTest {

    @Test
    public void recoveryPassword() {
        HomePage homePage = new HomePage(driver, wait);
        LoginPage loginPage = new LoginPage(driver, wait);
        homePage.openPage(BASE_URL_UI);
    }

    String userName = "testacount831@gmail.com";
    String password = "aoub whvr fdxp enif";
    String html = EmailReceiver.getLastMailHtmlValue(userName, password);
    String temporaryPassword = EmailReceiver.getTemporaryPasswordValue(html);

}
