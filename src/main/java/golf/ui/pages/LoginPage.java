package golf.ui.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends AbstractPage {


    @FindBy(css = "input[placeholder='Email']")
    WebElement emailInput;
    @FindBy(css = "input[placeholder='Password']")
    WebElement passwordInput;
    @FindBy(css = "input[placeholder='First Name']")
    WebElement firstNameInput;
    @FindBy(css = "input[placeholder='Last Name']")
    WebElement lastNameInput;
    @FindBy(xpath = "//button[text()='Log in']")
    WebElement loginBtn;

    @FindBy(xpath = "//button[text()='Sign up']")
    WebElement signUpButton;


    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }


    public void login(String email, String password) {
        inputTextByElement(email, emailInput);
        inputTextByElement(password, passwordInput);
        loginBtn.click();
    }

    public void clickSignUpBtn() {
        super.clickToElement(signUpButton);
    }

    public void signUp(String firstName, String lastName, String email, String password) {
        inputTextByElement(firstName, firstNameInput);
        inputTextByElement(lastName, lastNameInput);
        inputTextByElement(email, emailInput);
        inputTextByElement(password, passwordInput);
        super.clickToElement(emailInput);
        emailInput.sendKeys(Keys.ENTER);
    }
}
