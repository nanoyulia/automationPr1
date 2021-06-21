package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Step;

public class OrderConfirmationPage extends MenuBarPage {

	@FindBy (css = ".cheque-indent .dark")
	private WebElement confirmationMsg;
	@FindBy (css = ".alert.alert-success")
	private WebElement confirmationAlert;

	//constructor
	public OrderConfirmationPage(WebDriver driver) {
		super(driver);
	}

	@Step("Get confirmation message text")
	public String getConfirmationMsg() {
		return getText(confirmationMsg);
	}

	@Step("Get confirmation alert text")
	public String getConfirmationAlertText() {
		return getText(confirmationAlert);
	}
}
