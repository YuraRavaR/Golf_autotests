package golf.ui.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage extends AbstractPage {
    @FindBy(xpath = "//a[text()='Change password']")
    WebElement changePasswordBtn;

    @FindBy(css = "input[placeholder='Current password']")
    WebElement currentPasswordInput;
    @FindBy(css = "input[placeholder='New password']")
    WebElement newPasswordInput;
    @FindBy(css = "input[placeholder='Confirm new password']")
    WebElement confirmNewPasswordInput;
    @FindBy(xpath = "//button[text()='Save']")
    WebElement saveBtn;


    public ProfilePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void changePassword(String currentPassword, String newPassword,String confirmNewPassword) {
        inputTextByElement(currentPassword,currentPasswordInput);
        inputTextByElement(newPassword, newPasswordInput);
        inputTextByElement(confirmNewPassword, confirmNewPasswordInput);
        super.clickToElement(newPasswordInput);
    }

    public void clickChangePasswordBtn(){
        super.clickToElement(changePasswordBtn);
    }
    public void clickSavePasswordBtn(){
        super.clickToElement(saveBtn);
    }
}
