package golf.ui.pages;

import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends AbstractPage {


    @FindBy(xpath = "//button[@class='custom-btn-top-bar']")
    WebElement profileButton;

    @FindBy(xpath = "//*[contains(text(), 'Admin panel')]")
    WebElement adminPanelElement;
    @FindBy(xpath = "//div[contains(text(), 'HomePage')]")
    WebElement clientPanelElement;

    @FindBy(xpath = "//a[@href='/stock']")
    WebElement stockBtn;



    public HomePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void clickProfileButton() {
        Allure.step("Click profile button");
        super.clickToElement(profileButton);
    }

    public boolean isAdminHomePageLoaded() {
        Allure.step("Checking if admin home page is loaded");
        return super.waitPageLoaded(adminPanelElement);
    }

    public boolean isUserHomePageLoaded() {
        Allure.step("Checking if client home page is loaded");
        return super.waitPageLoaded(clientPanelElement);
    }

    public void clickStockBtn(){
        Allure.step("Click Stock button");
        super.clickToElement(stockBtn);
    }
}
