package golf.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractPage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    protected final String exactTextMath = "//*[text()='%s']";

    AbstractPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public void openPage(String pageURL) {
        driver.get(pageURL);
    }


    public void assertExactTextAppearedOnPage(String text) {
        textAssert(exactTextMath, text);
    }

    public void inputTextByElement(String text, WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.click();
        element.clear();
        wait.until(ExpectedConditions.textToBePresentInElementValue(element, ""));
        element.sendKeys(text);
        String enteredText = element.getAttribute("value");
        if (!enteredText.equals(text)) {
            throw new AssertionError("Entered text does not match the expected text.");
        }
    }

    public boolean waitPageLoaded(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (org.openqa.selenium.TimeoutException e) {
            return false;
        }
    }
    private void textAssert(String pattern, String text) {
        String locator = String.format(pattern, text);
        try {
            wait.until(
                    ExpectedConditions.and(
                            ExpectedConditions.presenceOfElementLocated(By.xpath(locator)),
                            ExpectedConditions.visibilityOfElementLocated(By.xpath(locator))
                    ));
            System.out.printf("Text %s is found", text);
        } catch (NoSuchElementException e) {
            throw new AssertionError(String.format("No element with text: %s is found on page", text));
        }
    }
    public void clickToElement(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.click();
    }

}
