package golf.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class StocksAdminPage extends AbstractPage {
    @FindBy(xpath = "//label[text()='Category']/following-sibling::div")
    WebElement categoryDropDownList;

    @FindBy(xpath = "//label[text()='Price']/following-sibling::div")
    WebElement priceDropDown;

    @FindBy(css = ".MuiBackdrop-root")
    WebElement backDrop;
    String categoryCheckbox = "//li[@data-value='%s']";

    @FindBy(xpath = "//div[text()='All Stocks']")
    WebElement allStocksBtn;

    @FindBy(id = ":ra:")
    WebElement minPriceFilterCategoryInput;

    @FindBy(id = ":rb:")
    WebElement maxPriceFilterCategoryInput;

    List<WebElement> priceList = driver.findElements(By.cssSelector("td:nth-child(5)"));

    List<WebElement> categoryList = driver.findElements(By.cssSelector("td:nth-child(4)"));


    public StocksAdminPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void waitStockPageLoad() {
        super.waitPageLoaded(allStocksBtn);
    }

    public void setFilterPrice(String minPrice, String maxPrice) {
        super.clickToElement(priceDropDown);
        super.inputTextByElement(minPrice, minPriceFilterCategoryInput);
        super.inputTextByElement(maxPrice, maxPriceFilterCategoryInput);
    }

    public void selectOneCategoryInDropdown(String categoryName) {
        clickToElement(categoryDropDownList);
        String categoryXpath = String.format(categoryCheckbox, categoryName);
        WebElement categoryElement = driver.findElement(By.xpath(categoryXpath));
        clickToElement(categoryElement);
        clickToElement(backDrop);
    }

    public boolean areAllCategoriesMatching (String expectedCategoryName) {
        List<WebElement> actualCategoryList = driver.findElements(By.cssSelector("td:nth-child(4)"));
        for (WebElement actualCategory : actualCategoryList) {
            if (!actualCategory.getText().equals(expectedCategoryName)) {
                return false;
            }
        }
        return true;
    }

}
