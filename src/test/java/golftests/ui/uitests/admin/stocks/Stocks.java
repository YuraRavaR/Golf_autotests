package golftests.ui.uitests.admin.stocks;

import golf.ui.pages.HomePage;
import golf.ui.pages.LoginPage;
import golf.ui.pages.StocksAdminPage;
import golftests.ui.uibase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static golf.utilities.Constant.*;

public class Stocks extends BaseTest {

    private HomePage homePage;
    private LoginPage loginPage;
    private StocksAdminPage stocksAdminPage;

    @BeforeMethod
    public void setup() {
        homePage = new HomePage(driver, wait);
        loginPage = new LoginPage(driver, wait);
        homePage.openPage(BASE_URL_UI);
        homePage.clickProfileButton();
        loginPage.login(ADMIN_LOGIN, ADMIN_PASSWORD);
        Assert.assertTrue(homePage.isAdminHomePageLoaded());
        homePage.clickStockBtn();
        stocksAdminPage = new StocksAdminPage(driver, wait);
        stocksAdminPage.waitStockPageLoad();
    }

    @Test
    public void checkFilterByPrice() {
        Assert.assertTrue(stocksAdminPage.setFilterPrice());
    }

    @Test
    public void checkFilterByOneCategory() {
        stocksAdminPage.selectOneCategoryInDropdown("Protein Bar");
        Assert.assertTrue(stocksAdminPage.areAllCategoriesMatching("Protein Bar"));
    }

    @Test
    public void checkFilterByTwoCategories() {
        List<String> selectedCategoriesList = new ArrayList<>(Arrays.asList("Protein Bar", "Protein Whey"));
        stocksAdminPage.selectCategoriesInDropdown(selectedCategoriesList);
        Assert.assertTrue(stocksAdminPage.areAllCategoriesMatching(selectedCategoriesList));
    }

    @Test
    public void checkSortByPriceHighToLow() {
        stocksAdminPage.clickPriceSortBtn();
        Assert.assertTrue(stocksAdminPage.isProductPriceSortedHighToLow());
    }

    @Test
    public void checkSortByPriceLowToHigh() {
        stocksAdminPage.clickPriceSortBtn();
        stocksAdminPage.clickPriceSortBtn();
        Assert.assertTrue(stocksAdminPage.isProductPriceSortedLowToHigh());
    }

    @Test
    public void checkSortByQuantityHighToLow() {
        stocksAdminPage.clickQuantitySortBtn();
        Assert.assertTrue(stocksAdminPage.isProductQuantitySortedHighToLow());
    }

    @Test
    public void checkSortByQuantityLowToHigh() {
        stocksAdminPage.clickQuantitySortBtn();
        stocksAdminPage.clickQuantitySortBtn();
        Assert.assertTrue(stocksAdminPage.isProductQuantitySortedLowToHigh());
    }

    @Test
    public void checkSortAndFilterByPriceAndCategory() {
        Assert.assertTrue(stocksAdminPage.setFilterPrice());
        stocksAdminPage.selectOneCategoryInDropdown("Protein Bar");
        Assert.assertTrue(stocksAdminPage.areAllCategoriesMatching("Protein Bar"));
        stocksAdminPage.clickPriceSortBtn();
        Assert.assertTrue(stocksAdminPage.isProductPriceSortedHighToLow());
    }
}
