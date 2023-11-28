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

    @FindBy(xpath = "//button[text()='Save']")
    WebElement savePasswordBtn;

    @FindBy(xpath = "//button[text()='Log in with the temporary password']")
    WebElement loginWithTempPasswordBtn;

    @FindBy(xpath = "//button[text()='Sign up']")
    WebElement signUpButton;
    @FindBy(xpath = "//button[text()='Change password']")
    WebElement changePasswordBtn;

    @FindBy(xpath = "//button[text()='Confirm']")
    WebElement confirmTempPasswordBtn;

    @FindBy(xpath = "//input[@placeholder='New password']")
    WebElement newPasswordInput;

    @FindBy(xpath = "//input[@placeholder='Confirm new password']")
    WebElement confirmNewPasswordInput;

    @FindBy(xpath = "//input[@id='outlined-adornment-password']")
    WebElement temporaryPasswordInput;

    @FindBy(xpath = "//button[text()='I remember the password']")
    WebElement rememberPasswordBtn;


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

    public void clickRememberThePasswordBtn() {
        Allure.step("Click to I remember the password button");
        super.clickToElement(rememberPasswordBtn);
    }

    public void inputEmail(String value) {
        Allure.step("Input email: " + value);
        inputTextByElement(value, emailInput);
    }

    public void inputTempPassword(String value) {
        Allure.step("Input temporary password: " + value);
        inputTextByElement(value, temporaryPasswordInput);
    }

    public void clickConfirmPasswordBtn() {
        Allure.step("Click confirm password button");
        super.clickToElement(confirmTempPasswordBtn);
    }

    public void clickSavePasswordBtn() {
        Allure.step("Click save password button");
        super.clickToElement(savePasswordBtn);
    }

    public void inputNewPassword(String newPassword, String confirmNewPassword) {
        Allure.step("Input new password: " + newPassword + " and confirm new password: " + confirmNewPassword);
        inputTextByElement(newPassword, newPasswordInput);
        inputTextByElement(confirmNewPassword, confirmNewPasswordInput);
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
