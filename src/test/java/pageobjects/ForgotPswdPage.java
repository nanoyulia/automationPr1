package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Step;

public class ForgotPswdPage extends MenuBarPage {
	@FindBy (css = "#email")
	private WebElement emailField;
	@FindBy (css = "#form_forgotpassword  span")
	private WebElement retrieveBtn;
	@FindBy (css = ".alert.alert-danger > ol > li")
	private WebElement errorMsg;
	@FindBy (css = ".alert.alert-success")
	private WebElement successConfirmationMsg ;
	@FindBy (css = "li > .btn.btn-default.button.button-small span")
	private WebElement backToLoginBtn;

	//constructor
	public ForgotPswdPage(WebDriver driver) {
		super(driver);
	}

	@Step("Type email: {emailText}")
	public void fillForgotPswd(String emailText) {
		fillText(emailField, emailText);
		click(retrieveBtn);
	}

	@Step("Get error text")
	public String errorrMsgText() {
		waitToBeSeen(errorMsg);
		return getText(errorMsg);
	}

	@Step("Get confirmation message text")
	public String successMsgText() {
		waitToBeSeen(successConfirmationMsg);
		return getText(successConfirmationMsg);
	}

	@Step("Click back to login page")
	public void clickBackToLogin() {
		click(backToLoginBtn);
	}
}
