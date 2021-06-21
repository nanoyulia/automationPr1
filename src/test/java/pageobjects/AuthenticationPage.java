package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Step;

public class AuthenticationPage extends MenuBarPage {

	@FindBy (css = "#email")
	private WebElement emailField;
	@FindBy (css = "#passwd")
	private WebElement passwordField;
	@FindBy (css = "#SubmitLogin > span")
	private WebElement submitBtn;
	@FindBy (css = ".lost_password.form-group > a")
	private WebElement forgotPswdLink;
	@FindBy (css = "#center_column > div.alert.alert-danger  li")
	private WebElement wrongEmailMsg;
	@FindBy (css = "#email_create")
	private WebElement createUserEmail;
	@FindBy (css = "#SubmitCreate > span")
	private WebElement createAccSubmit;
	@FindBy (css = "#create_account_error  li")
	private WebElement createAccError;

	//constructor
	public AuthenticationPage(WebDriver driver) {
		super(driver);
	}

	@Step("Sign in with email: {emailText} and password: {passwdText}")
	public void registeredUserSignIn(String emailText, String passwdText) {
		waitToBeSeen(emailField);
		fillText(emailField, emailText);
		fillText(passwordField, passwdText);
		click(submitBtn);
		sleep(1000);
	}

	@Step("Get error message")
	public String failedAuthenticationMsg() {
		return getText(wrongEmailMsg);
	}
	
	@Step("Fill email field: {mailField}")
	public void createAccount(String mailField) {
		fillText(createUserEmail, mailField);
		click(createAccSubmit);
		sleep(2000);
	}

	@Step("Get error message")
	public String createAccErrorMsg() {
		waitToBeSeen(createAccError);
		return getText(createAccError);
	}

	@Step("Click 'forgot password' link")
	public void clickForgotPswdLink() {
		click(forgotPswdLink);
		sleep(1000);
	}
}
