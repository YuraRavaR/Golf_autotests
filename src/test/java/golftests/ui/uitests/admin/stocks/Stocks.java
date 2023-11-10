package golftests.ui.uitests.admin.stocks;

import golf.ui.pages.HomePage;
import golf.ui.pages.LoginPage;
import golf.ui.pages.StocksAdminPage;
import golftests.ui.uibase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import static golf.utilities.Constant.*;

public class Stocks extends BaseTest {

    @Test
    public void checkFilterByPrice() {
        HomePage homePage = new HomePage(driver, wait);
        LoginPage loginPage = new LoginPage(driver, wait);
        homePage.openPage(BASE_URL_UI);
        homePage.clickProfileButton();
        loginPage.login(ADMIN_LOGIN, ADMIN_PASSWORD);
        Assert.assertTrue(homePage.isAdminHomePageLoaded());
        homePage.clickStockBtn();
        StocksAdminPage stocksAdminPage = new StocksAdminPage(driver,wait);
        stocksAdminPage.waitStockPageLoad();
    }
    @Test
    public void checkFilterByCategory() {
        HomePage homePage = new HomePage(driver, wait);
        LoginPage loginPage = new LoginPage(driver, wait);
        homePage.openPage(BASE_URL_UI);
        homePage.clickProfileButton();
        loginPage.login(ADMIN_LOGIN, ADMIN_PASSWORD);
        Assert.assertTrue(homePage.isAdminHomePageLoaded());
        homePage.clickStockBtn();
        StocksAdminPage stocksAdminPage = new StocksAdminPage(driver,wait);
        stocksAdminPage.waitStockPageLoad();
        stocksAdminPage.selectOneCategoryInDropdown("Protein Bar");
        Assert.assertTrue(stocksAdminPage.areAllCategoriesMatching("Protein Bar"));
    }
}
