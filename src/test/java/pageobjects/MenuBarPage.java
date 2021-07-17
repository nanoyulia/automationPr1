package pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.qameta.allure.Step;

abstract class MenuBarPage extends BasePage {

	@FindBy (css = ".login")
	private WebElement signInLink;
	@FindBy (css = ".logout")
	private WebElement signoutLink;
	@FindBy (css = ".account")
	private WebElement myAccLink;
	@FindBy (css = ".logo.img-responsive")
	private WebElement mainPageImg;
	@FindBy (css = "#block_top_menu > ul > li:nth-child(1) > a")
	private WebElement womenBtn;
	@FindBy  (css = "#block_top_menu > ul > li:nth-child(2) > a")
	private WebElement dressesBtn;
	@FindBy (css = "#block_top_menu > ul > li:nth-child(3) > a")
	private WebElement tshirtsBtn;
	@FindBy (css = ".submenu-container [title = 'Tops']")
	private WebElement topsLink;
	@FindBy (css = ".submenu-container [title = 'T-shirts']")
	private WebElement tshirtsLink ;
	@FindBy (css = ".submenu-container [title = 'Blouses']")
	private WebElement blousesLink;
	@FindBy (css = ".shopping_cart>a")
	private WebElement viewCartLink;
	@FindBy (css = ".cart-images")
	private List<WebElement> itemsInCart;
	@FindBy (css = ".ajax_cart_block_remove_link")
	private List<WebElement> removeProductsBtn;
	@FindBy (css = ".quantity")
	private List<WebElement> eachItemQuantity;
	@FindBy (css = ".ajax_cart_no_product")
	private WebElement emptyCart ;
	@FindBy (css = ".shopping_cart .ajax_cart_quantity")
	private WebElement cartQuantity;
	@FindBy (css = "#contact-link > a")
	private WebElement contactUsLink;
	@FindBy (css = "#newsletter-input")
	private WebElement newsletterEmailField;
	@FindBy (css = "[name='submitNewsletter']")
	private WebElement submitNewsletterBtn;
	@FindBy (css = "#search_query_top")
	private WebElement searchField;
	@FindBy (css = "[name='submit_search']")
	private WebElement submitSearch;

	//constructor
	public MenuBarPage(WebDriver driver) {
		super(driver);
	}

	@Step("Click 'sign in' link")
	public void clickSigninLink() {
		click(signInLink);
	}

	@Step("Click 'contact us' link")
	public void clickContactUsLink() {
		click(contactUsLink);
	}

	@Step("Click 'sign out' link")
	public void clickSignoutLink() {
		click(signoutLink);
	}

	@Step("Click 'my account' link")
	public void clickMyAccountLink() {
		click(myAccLink);
	}

	@Step("Click on cart link")
	public void clickOnCartLink() {
		click(viewCartLink);
	}

	@Step("Click 'blouses' link")
	public void goToBlouseCategory() {
		mouseHoverTo(womenBtn);
		waitToBeSeen(blousesLink);
		click(blousesLink);
	}

	@Step("Click 'dresses' link")
	public void goTodressesCategory() {
		click(dressesBtn);
	}

	@Step("Click 'tshirt' link")
	public void goToTshirtCategory() {
		click(tshirtsBtn);
	}

	@Step("Click main page logo")
	public void goToMainPage() {
		click(mainPageImg);
	}

	@Step("View cart and delete all items")
	public void removeAllItems() {
		mouseHoverTo(viewCartLink);
		for (WebElement el : removeProductsBtn) {
			click(el);
			waitForInvisibility(el);
		}
	}

	@Step("Type in email: {email} and submit")
	public void subscribeToNews(String email) {
		scrollTo(newsletterEmailField);
		fillText(newsletterEmailField, email);
		click(submitNewsletterBtn);
		if (!doesUrlInclude("automation")) {
			goToPreviousPage();
			wait.until(ExpectedConditions.urlContains("automation"));
		}
	}

	@Step("Typre in product description: {productName}")
	public void search(String productName) {
		fillText(searchField, productName);
		click(submitSearch);
	}


	//validation
	@Step("Get the quantity of items in the cart")
	public int getCartQuantity() {
		waitToBeSeen(viewCartLink);
		if (cartQuantity.isDisplayed()) {
			String s = getText(cartQuantity);
			int n = Integer.parseInt(s);
			return n;
		} else {
			return 0;
		}
	}
}
