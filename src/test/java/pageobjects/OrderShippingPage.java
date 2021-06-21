package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Step;

public class OrderShippingPage extends MenuBarPage {

	@FindBy (css = "#uniform-cgv")
	private WebElement termOfServiceCheckbox;
	@FindBy (css = "[name='processCarrier']")
	private WebElement proceedToPayment;
	@FindBy (css = ".fancybox-error")
	private WebElement alertMsg;
	@FindBy (css = ".fancybox-close")
	private WebElement closeAlertIcon;

	//constructor
	public OrderShippingPage(WebDriver driver) {
		super(driver);
	}

	@Step("Click terms of service checkbox")
	public void checkTerms() {
		click(termOfServiceCheckbox);
	}

	@Step("Click 'proceed to payment' button")
	public void clickProceedToPaymentBtn() {
		click(proceedToPayment);
	}

	@Step("Get alert text")
	public String getAlertMsgText() {
		waitToBeSeen(alertMsg);
		return getText(alertMsg);
	}

	@Step("Close alert")
	public void clickCloseAlert() {
		click(closeAlertIcon);
	}
}
