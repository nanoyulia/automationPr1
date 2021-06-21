package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Step;

public class OrderPaymentPage extends MenuBarPage {

	@FindBy (css = ".bankwire")
	private WebElement bankwireBtn;
	@FindBy (css = ".cheque")
	private WebElement chequeBtn;

	//constructor
	public OrderPaymentPage(WebDriver driver) {
		super(driver);
	}

	@Step("Click bankwire button")
	public void clickBankwireBtn() {
		click(bankwireBtn);
	}

	@Step("Click cheque button")
	public void clickChequeBtn() {
		click(chequeBtn);
	}
}
