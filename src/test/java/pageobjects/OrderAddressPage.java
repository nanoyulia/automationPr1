package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Step;

public class OrderAddressPage extends MenuBarPage{

	@FindBy (css = "[name='processAddress']")
	private WebElement proceedToShippingBtn;

	//constructor
	public OrderAddressPage(WebDriver driver) {
		super(driver);
	}

	@Step("Click 'proceed to shipping' button")
	public void clickProceedToShipping() {
		click(proceedToShippingBtn);
	}
}
