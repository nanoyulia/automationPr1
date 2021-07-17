package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import pageobjects.Blouse1Page;
import pageobjects.BlousesPage;
import pageobjects.DressesPage;
import pageobjects.IndexPage;
import pageobjects.TshirtsPage;

public class AddToCartTests extends BaseTest {

	@Severity(SeverityLevel.NORMAL)
	@Test (description = "adding all the dresses to cart at dresses page")
	@Description ("Adding all the items to cart at Dresses page")
	public void tc26_addDresses() {
		//go to dresses category
		IndexPage ip = new IndexPage(driver);
		ip.goTodressesCategory();
		DressesPage dp = new DressesPage(driver);
		//save the number of items at the cart
		int before = dp.getCartQuantity();

		//number of items on page
		dp = new DressesPage(driver);
		int d = dp.numOfDressesTotal();

		//adding all the dresses
		dp = new DressesPage(driver);
		dp.addAllDressesToCart();

		//check how many items added
		dp = new DressesPage(driver);
		int actual = dp.getCartQuantity();

		int expected = before + d;

		Assert.assertEquals(expected, actual);
	}

	@Severity(SeverityLevel.NORMAL)
	@Test (description = "adding blouse to cart at product page")
	@Description ("Adding one iten to cart at Blouses page")
	public void tc27_addBlouse() {
		//go to blouse category
		DressesPage dp = new DressesPage(driver);
		dp.goToBlouseCategory();

		//save the number of items at the cart
		BlousesPage bp = new BlousesPage(driver);
		int before = bp.getCartQuantity();

		//click om 'more' button
		bp = new BlousesPage(driver);
		bp.clickOnMore();

		//change quantity, size and add to cart
		Blouse1Page blp = new Blouse1Page(driver);
		blp.chooseBlouseDetails("2", "l");

		//check how many items were added
		blp = new Blouse1Page(driver);
		int actual = blp.getCartQuantity();
		int expected = before +2;
		Assert.assertEquals(actual, expected);
	}

	@Severity(SeverityLevel.NORMAL)
	@Test (description = "adding t-shirt")
	@Description ("Adding one item and changing the size and quantity")
	public void tc28_addShirt() {
		//go to t-chirt category
		Blouse1Page blp = new Blouse1Page(driver);
		blp.goToTshirtCategory();

		//save the number of items at the cart
		TshirtsPage tp = new TshirtsPage(driver);
		int before = tp.getCartQuantity();

		//add items
		tp = new TshirtsPage(driver);
		tp.addShirt("l", 3);
		tp = new TshirtsPage(driver);

		//check how many items were added
		int actual = tp.getCartQuantity();
		int expected = before + 3;
		Assert.assertEquals(actual, expected);
	}

	@Severity(SeverityLevel.NORMAL)
	@Test (description = "remove all items without going to the cart page")
	@Description ("Delete all the items by clicking floating cart menu")
	public void tc29_deleteItems() {
		//hover over a cart icon and delete all items
		TshirtsPage tp = new TshirtsPage(driver);
		tp = new TshirtsPage(driver);
		tp.removeAllItems();
		tp = new TshirtsPage(driver);

		//check that cart is empty
		int actual = tp.getCartQuantity();
		int expected = 0;
		Assert.assertEquals(actual, expected);
	}

}
