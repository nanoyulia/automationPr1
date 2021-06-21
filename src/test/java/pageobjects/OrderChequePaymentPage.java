package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Step;

public class OrderChequePaymentPage extends MenuBarPage {
	
	@FindBy (css = ".cart_navigation.clearfix > button")
	private WebElement confirmBtn;

	//constructor
	public OrderChequePaymentPage(WebDriver driver) {
		super(driver);
	}
	
	@Step("Click 'confirm' button")
	public void clickConfirmOrderBtn() {
		click(confirmBtn);
	}
}
