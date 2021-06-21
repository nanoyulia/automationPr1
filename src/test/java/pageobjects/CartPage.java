package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Step;

public class CartPage extends MenuBarPage {

	@FindBy (css = ".alert.alert-warning")
	private WebElement cartAlert;
	@FindBy (css = ".standard-checkout")
	private WebElement proceedToCheckoutBtn;

	//constructor
	public CartPage(WebDriver driver) {
		super(driver);
	}

	@Step("Get alert text")
	public String cartAlertText() {
		return getText(cartAlert);
	}

	@Step("Click checkout button")
	public void clickOnCheckoutBtn() {
		click(proceedToCheckoutBtn);
	}
}
