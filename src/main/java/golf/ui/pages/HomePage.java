package golf.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends AbstractPage {


    @FindBy(xpath = "//button[@class='custom-btn-top-bar']")
    WebElement profileButton;
    @FindBy(css = "input[placeholder='Email']")
    WebElement emailInput;
    @FindBy(css = "input[placeholder='Password']")
    WebElement passwordInput;
    @FindBy(xpath = "//button[text()='Log in']")
    WebElement loginBtn;

    @FindBy(xpath = "//*[contains(text(), 'Admin panel')]")
    WebElement adminPanelElement;
    @FindBy(xpath = "//div[contains(text(), 'HomePage')]")
    WebElement clientPanelElement;



    public HomePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void clickProfileButton() {
        super.clickToElement(profileButton);
    }

    public boolean isAdminHomePageLoaded() {
        return super.waitPageLoaded(adminPanelElement);
    }

    public boolean isUserHomePageLoaded() {
        return super.waitPageLoaded(clientPanelElement);
    }
}
