package pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Step;

public class AddressesPage extends MenuBarPage {

	@FindBy (css = ".clearfix.main-page-indent > a")
	private WebElement addNewAddressBtn;
	@FindBy (css = ".col-xs-12.col-sm-6.address")
	private List<WebElement> totalListOfAddresses ;
	@FindBy (css = "[title='Delete']")
	private List<WebElement> deleteBtn ;
	@FindBy (css = "#center_column li:nth-child(1) > a")
	private WebElement backToAccountBtn ;

	//constructor
	public AddressesPage(WebDriver driver) {
		super(driver);
	}

	@Step("Click 'add new address' button")
	public void clickAddNewAddress() {
		click(addNewAddressBtn);
	}

	@Step("Get the number of addresses")
	public int numberOFaddresses() {
		waitForListBeSeen(totalListOfAddresses);
		int n = totalListOfAddresses.size();
		return n;
	}

	@Step("Click 'delete address' button")
	public void deleteAddress(int count) {
		if (deleteBtn.size() > 1) {
			click(deleteBtn.get(count -1 ));
			alertOK();
		}
	}

	@Step("Click 'back to account' button")
	public void goBackToAccount() {
		click(backToAccountBtn);
	}
}
