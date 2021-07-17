package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import pageobjects.AuthenticationPage;
import pageobjects.Blouse1Page;
import pageobjects.BlousesPage;
import pageobjects.DressesPage;
import pageobjects.IndexPage;
import pageobjects.MyAccountPage;
import pageobjects.WishlistPage;

public class WishlistTests extends BaseTest {
	
	@Severity(SeverityLevel.NORMAL)
	@Test (description = "add items to wishlist while not logged")
	@Description ("Attempt to add item to wishlist as guest")
	public void tc14_addToWishlist() {
		//go to dress category
		IndexPage ip = new IndexPage(driver);
		ip.goTodressesCategory();

		//add to wishlist dresses with the word 'summer' in name
		DressesPage dp = new DressesPage(driver);
		String actual = dp.addToWishlist(1);

		//error message should appear
		String expected  = "You must be logged in to manage your wishlist.";
		Assert.assertEquals(actual, expected);
	}
	

	@Severity(SeverityLevel.NORMAL)
	@Test (description = "add items to wishlist after login")
	@Description ("Successful adding item to wishlist")
	public void tc15_addToWishlist() {
		//go to authentication page
		DressesPage dp = new DressesPage(driver);
		dp.clickSigninLink();

		//sign in
		AuthenticationPage ap = new AuthenticationPage(driver);
		ap.registeredUserSignIn("098oiu@gmail.com", "pass123");
		MyAccountPage map = new MyAccountPage(driver);

		//go to dresses category
		map.goTodressesCategory();

		//add to wishlist dresses with the word 'summer' in name
		dp = new DressesPage(driver);
		String actual = dp.addToWishlist(1);

		//error message should appear
		String expected  = "Added to your wishlist.";
		System.out.println(actual);
		Assert.assertEquals(actual, expected);
	}

	@Severity(SeverityLevel.NORMAL)
	@Test (description = "View wishlist")
	@Description ("View specific wishlist")
	public void tc16_viewWishlist() {
		DressesPage dp = new DressesPage(driver);
		dp.clickMyAccountLink();

		//go to wishlist page
		MyAccountPage map = new MyAccountPage(driver);
		map.goToWishlistPage();
		WishlistPage wp = new WishlistPage(driver);

		//click on list
		wp.viewWishlist(1);
		wp = new WishlistPage(driver);

		//chech if list opened
		boolean actual = wp.isListOpened();
		boolean expected = true;
		Assert.assertEquals(actual, expected);
	}

	@Severity(SeverityLevel.NORMAL)
	@Test (description = "delete items from wishlist")
	@Description ("Delete items from wishlist")
	public void tc17_deleteItemFromWishlist() {
		//delete all items in list
		WishlistPage wp = new WishlistPage(driver);
		wp.deleteAllItems();

		//refresh page
		wp = new WishlistPage(driver);
		wp.refreshPage();

		//click again on the same list again
		wp = new WishlistPage(driver);
		wp.viewWishlist(1);

		//make sure everything deleted
		wp = new WishlistPage(driver);
		int actual = wp.numOfItemsInList();
		int expected = 0;
		Assert.assertEquals(actual, expected);
	}

	@Severity(SeverityLevel.NORMAL)
	@Test (description = "create new wishlist")
	@Description ("Create a new wishlist")
	public void tc18_createNewList() {
		//save the number of lists before
		WishlistPage wp = new WishlistPage(driver);
		int before = wp.numOfWishlists();

		//create one new list
		wp = new WishlistPage(driver);
		wp.createNewWishlist("My NewNew List");

		//check if it was added correctly
		wp = new WishlistPage(driver);
		int expected = before +1;
		int actual = wp.numOfWishlists();
		Assert.assertEquals(actual, expected);				
	}

	@Severity(SeverityLevel.NORMAL)
	@Test (description = "delete wishlist")
	@Description ("Delete existing wishlist")
	public void tc19_deleteWishlist( ) {
		//save the number of lists before
		WishlistPage wp = new WishlistPage(driver);
		int before = wp.numOfWishlists();

		//delete wishlist
		wp = new WishlistPage(driver);
		wp.deleteWishlist(1);

		//validation
		wp = new WishlistPage(driver);
		int actual = wp.numOfWishlists();
		int expected = before - 1;
		Assert.assertEquals(actual, expected);
	}
}
