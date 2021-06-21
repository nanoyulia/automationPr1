package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Step;

public class MyAccountPage extends MenuBarPage {

	@FindBy (css = ".logout")
	private WebElement logoutBtn;
	@FindBy (css = "[title='Addresses']")
	private WebElement myAddressesBtn;
	@FindBy (css = "[title='Information']")
	private WebElement myPersonalInfoBtn;
	@FindBy (css = ".lnk_wishlist>a")
	private WebElement wishlistLink;

	//constructor
	public MyAccountPage(WebDriver driver) {
		super(driver);
	}

	@Step("Click 'logout' button")
	public void clickLogout() {
		click(logoutBtn);
	}

	@Step("Click 'my addresses' button")
	public void clickMyAdresses() {
		click(myAddressesBtn);
	}

	@Step("Click 'my personal information' button")
	public void goToMyInfo() {
		click(myPersonalInfoBtn);
	}

	@Step("Click 'my wishlists' button")
	public void goToWishlistPage() {
		click(wishlistLink);
	}
}
