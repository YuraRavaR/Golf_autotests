package golf.ui.pages;

import io.qameta.allure.Allure;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
    @FindBy(xpath = "//div[text()='My profile']")
    WebElement myProfileBtn;

    @FindBy(xpath = "//div[text()='Forgot password']")
    WebElement forgotPasswordBtn;

    @FindBy(xpath = "//button[text()='Sign up']")
    WebElement signUpButton;
    @FindBy(xpath = "//button[text()='Change password']")
    WebElement changePasswordBtn;



    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }


    public void login(String email, String password) {
        Allure.step("Login with email: " + email + " and password: " + password);
        inputTextByElement(email, emailInput);
        inputTextByElement(password, passwordInput);
        loginBtn.click();
    }

    public void clickSignUpBtn() {
        Allure.step("Click to sign up button");
        super.clickToElement(signUpButton);
    }

    public void clickMyProfileBtn() {
        Allure.step("Click to my Profile button");
        super.clickToElement(myProfileBtn);
    }

    public void clickForgotPasswordBtn() {
        Allure.step("Click to Forgot password button");
        super.clickToElement(forgotPasswordBtn);
    }

    public void clickChangePasswordBtn() {
        Allure.step("Click to Change password button");
        super.clickToElement(changePasswordBtn);
    }

    public void signUp(String firstName, String lastName, String email, String password) {
        Allure.step("Sign up with firstName: " + firstName + " ,lastname: " + lastName + " ,email: " + email +
                " ,password: " + password);
        inputTextByElement(firstName, firstNameInput);
        inputTextByElement(lastName, lastNameInput);
        inputTextByElement(email, emailInput);
        inputTextByElement(password, passwordInput);
        super.clickToElement(emailInput);
        emailInput.sendKeys(Keys.ENTER);
    }
}
