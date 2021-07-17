package pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.qameta.allure.Step;

public class DressesPage extends MenuBarPage {

	@FindBy (css = ".icon-eye-open")
	private List<WebElement> quickViewIcon;
	@FindBy (css = ".quick-view>span")
	private List<WebElement> quickViewLink;
	@FindBy (css = ".compare>a")
	private List<WebElement> addToCompareBtns;
	@FindBy (css = ".button.ajax_add_to_cart_button.btn.btn-default")
	private List<WebElement> addToCartBtns;
	@FindBy (css = ".bt_compare.bt_compare")
	private List<WebElement> compareBtns;
	@FindBy (css = ".total-compare-val")
	private WebElement totalItemsToCompare;
	@FindBy (css = ".fancybox-error")
	private WebElement textAlert;
	@FindBy (css = ".fancybox-item.fancybox-close")
	private WebElement closeMsg;
	@FindBy (css = ".wishlist >a")
	private List<WebElement> addToWishLinks ;
	@FindBy (css = ".product_img_link")
	private List<WebElement> dressesPics;
	@FindBy (css = ".product_list.grid.row .product-name")
	private List<WebElement> dressesNames;
	@FindBy (css = ".fancybox-iframe") 
	private WebElement frameElement ;
	@FindBy (css = "#add_to_cart>button")
	private WebElement addToCartInFrame ;
	@FindBy (css = ".continue.btn.btn-default.button.exclusive-medium")
	private WebElement continueBtnInFrame;
	@FindBy (css = ".layer_cart_product.col-xs-12.col-md-6 >h2")
	private WebElement addedProductConfirmation;
	@FindBy (css = ".cross")
	private WebElement closeAddedProductConfirmation;

	//constructor
	public DressesPage(WebDriver driver) {
		super(driver);
	}

	@Step("Add all items to cart")
	public void addAllDressesToCart() {
		for (int i = 0; i < dressesPics.size(); i++) {
			scrollTo(dressesPics.get(i));
			mouseHoverTo(dressesPics.get(i));
			click(quickViewLink.get(i));
			switchToFrame(frameElement);
			click(addToCartInFrame);
			click(continueBtnInFrame);
			switchBackFromFrame();
			waitToBeSeen(viewCartLink);
		}
	}

	@Step("Add item: {itemName} to wishlist")
	public String addToWishlist(int itemNumb) {
		waitForListBeSeen(dressesPics);
		click(driver.findElement(By.cssSelector("#list>a")));
		waitForListBeSeen(addToWishLinks);
		String s = null;

		scrollTo(driver.findElements(By.cssSelector(".product-container")).get(itemNumb-1));
		click(addToWishLinks.get(itemNumb-1));
		waitToBeSeen(textAlert);
		if (getAlertText().equalsIgnoreCase("You must be logged in to manage your wishlist.")) {
			s = getAlertText();
			click(closeMsg);
			return s;
		} else if(getAlertText().equalsIgnoreCase("Added to your wishlist.")) {
			s = getAlertText();
			click(closeMsg);
			return s;
		}
		return s;
	}




	@Step("Add item number:{n} to comparison list")
	public void addToCompare(int n) {
		waitForListBeSeen(dressesPics);
		click(driver.findElement(By.cssSelector("#list>a")));
		waitForListBeSeen(addToCompareBtns);
		
		scrollTo(addToCompareBtns.get(n-1));
		click(addToCompareBtns.get(n-1));
		wait.until(ExpectedConditions.attributeContains(addToCompareBtns.get(n-1), "class", "add_to_compare checked"));

	}	

	@Step("Get alert text")
	public String getAlertText() {
		waitToBeSeen(textAlert);
		String al = getText(textAlert);
		return al;
	}

	@Step("Add item number: {dressNumber} to cart")
	public void addDressToCart(int dressNumber) {
		mouseHoverTo(dressesNames.get(dressNumber-1));
		click(addToCartBtns.get(dressNumber-1));
		waitToBeSeen(addedProductConfirmation);
		if (productAddedText().equalsIgnoreCase("Product successfully added to your shopping cart")) {
			click(closeAddedProductConfirmation);
		}
	}

	@Step("Get confirmation text")
	public String productAddedText() {
		return getText(addedProductConfirmation);
	}


	//validation 
	@Step("Get the quantity of dresses")
	public int numOfDressesTotal() {
		int n = dressesPics.size();
		return n;
	}

	@Step("Get the quantity of items to compare")
	public int numOfItemsToCompare() {
		waitToBeSeen(totalItemsToCompare);
		int t = Integer.parseInt(totalItemsToCompare.getText());
		return t;
	}
}
