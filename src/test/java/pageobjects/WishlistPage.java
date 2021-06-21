package pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Step;


public class WishlistPage extends MenuBarPage {

	@FindBy (css = "#name")
	private WebElement newListField;
	@FindBy (css = "#submitWishlist")
	private WebElement saveListBtn;
	@FindBy (css = "[style='width:200px;'] > a")
	private List<WebElement> listOfWishlists ;
	@FindBy (css = ".icon-remove")
	private List<WebElement> deleteListIcon;
	@FindBy (css = ".wishlistLinkTop")
	private WebElement wishlistDetails;
	@FindBy (css = ".icon-remove-sign")
	private List<WebElement> deleteItemIcon;
	@FindBy (css = "#s_title")
	private List<WebElement> itemName ;

	//constructor
	public WishlistPage(WebDriver driver) {
		super(driver);
	}

	@Step("Type in wishlist name:{name}")
	public void createNewWishlist(String name) {
		fillText(newListField, name);
		click(saveListBtn);
	}

	@Step("Click on view wishlist number: {num}")
	public void viewWishlist(int num) {
		if (numOfWishlists() == 0) { //if list is null - there is nothing to click
			System.out.println("There is no lists to click");

		}else if (numOfWishlists() >= 1) { // if there is ine or more lists
			if (num <= listOfWishlists.size()) { 
				for (int i = 0; i < listOfWishlists.size(); i++) {
					sleep(500); //remove later
					click(listOfWishlists.get(num -1));
				}
			}
		}
	}

	@Step("Delete wishlist number: {a}")
	public void deleteWishlist(int a) {
		click(deleteListIcon.get(a -1 ));
		alertOK();
		waitForInvisibility(deleteListIcon.get(a -1 ));
	}

	@Step("Delete all items")
	public void deleteAllItems() {
		for (WebElement el : deleteItemIcon) {
			click(el);
			waitForInvisibility(el);
		}
	}


	//validation
	@Step("Get number of wishlists")
	public int numOfWishlists() {
		waitForListBeSeen(listOfWishlists);
		int n = listOfWishlists.size();
		return n;
	}

	@Step("Get number of items")
	public int numOfItemsInList() {
		sleep(1000); 
		int m = deleteItemIcon.size();
		return m;
	}

	@Step("Check if wishlist is opened")
	public boolean isListOpened() {
		waitToBeSeen(wishlistDetails);
		if (wishlistDetails.isDisplayed()) {
			return true;
		}
		return false;
	}
}
