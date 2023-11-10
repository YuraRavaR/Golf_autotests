package golf.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;

public class StocksAdminPage extends AbstractPage {
    @FindBy(xpath = "//label[text()='Category']/following-sibling::div")
    WebElement categoryDropDownList;

    @FindBy(xpath = "//label[text()='Price']/following-sibling::div")
    WebElement priceRangeSlider;

    @FindBy(css = ".MuiBackdrop-root")
    WebElement backDrop;
    String categoryCheckbox = "//li[@data-value='%s']";

    @FindBy(xpath = "//div[text()='All Stocks']")
    WebElement allStocksBtn;

    @FindBy(xpath = "(//span[@class='MuiSlider-valueLabel css-3besu'])[1]")
    WebElement minPriceRangeSlider;

    @FindBy(xpath = "(//span[@class='MuiSlider-valueLabel css-3besu'])[2]")
    WebElement maxPriceRangeSlider;


    public StocksAdminPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void waitStockPageLoad() {
        super.waitPageLoaded(allStocksBtn);
    }

    public boolean setFilterPrice() {
        super.clickToElement(priceRangeSlider);
        Actions actions = new Actions(driver);
//        Random random = new Random();
//        int MaxrandomOffsetInPriceSlider = random.nextInt(101) + 80;
//        int MinRandomOffsetInPriceSlider = random.nextInt(101) + 80;
        actions.dragAndDropBy(maxPriceRangeSlider, 150, 0).perform();
        actions.dragAndDropBy(minPriceRangeSlider, 50, 0).perform();
        clickToElement(backDrop);
        super.clickToElement(priceRangeSlider);
        double minPriceValue = getNumericPriceValue(":r4:");
        double maxPriceValue = getNumericPriceValue(":r5:");
        clickToElement(backDrop);
        List<WebElement> actualProductsPriceList = driver.findElements(By.cssSelector("td:nth-child(5)"));

        return areAllPricesInRange(minPriceValue, maxPriceValue, actualProductsPriceList);
    }

    private double getNumericPriceValue(String elementId) {
        WebElement minPriceFilterCategoryInput = driver.findElement(By.id(elementId));
        String minPrice = minPriceFilterCategoryInput.getAttribute("value");
        return Double.parseDouble(minPrice);
    }

    private boolean areAllPricesInRange(double minPriceValue, double maxPriceValue, List<WebElement> actualProductsPriceList) {
        for (WebElement actualPrice : actualProductsPriceList) {
            String productPriceValue = actualPrice.getText();
            String numericPriceValue = productPriceValue.replaceAll("[^0-9]", "");
            double productPrice = Double.parseDouble(numericPriceValue);
            if (productPrice < minPriceValue || productPrice > maxPriceValue) {
                return false;
            }
        }
        return true;
    }

    public void selectOneCategoryInDropdown(String categoryName) {
        clickToElement(categoryDropDownList);
        String categoryXpath = String.format(categoryCheckbox, categoryName);
        WebElement categoryElement = driver.findElement(By.xpath(categoryXpath));
        clickToElement(categoryElement);
        clickToElement(backDrop);
    }

    public void selectCategoriesInDropdown(List<String> categoryNames) {
        clickToElement(categoryDropDownList);
        for (String categoryName : categoryNames) {
            String categoryXpath = String.format(categoryCheckbox, categoryName);
            WebElement categoryElement = driver.findElement(By.xpath(categoryXpath));
            clickToElement(categoryElement);
        }
        clickToElement(backDrop);
    }

    public boolean areAllCategoriesMatching(String expectedCategoryName) {
        List<WebElement> actualCategoryList = driver.findElements(By.cssSelector("td:nth-child(4)"));
        for (WebElement actualCategory : actualCategoryList) {
            if (!actualCategory.getText().equals(expectedCategoryName)) {
                return false;
            }
        }
        return true;
    }

    public boolean areAllCategoriesMatching(List<String> expectedCategoryNames) {
        List<WebElement> actualProductsCategoryList = driver.findElements(By.cssSelector("td:nth-child(4)"));

        for (WebElement actualCategory : actualProductsCategoryList) {
            String actualCategoryName = actualCategory.getText();

            if (!expectedCategoryNames.contains(actualCategoryName)) {
                return false;
            }
        }
        return true;
    }
}
