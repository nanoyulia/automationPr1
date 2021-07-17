package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.qameta.allure.Step;

public class BlousesPage extends MenuBarPage {

	@FindBy (css = ".product_img_link > img")
	private WebElement productImage;
	@FindBy (css = ".button.ajax_add_to_cart_button")
	private WebElement addToCartBtn;
	@FindBy (css = ".button.lnk_view.btn.btn-default")
	private WebElement moreBtn;
	@FindBy (css = ".icon-th-list")
	private WebElement listView;
	@FindBy (css = ".icon-th-large")
	private WebElement largeIconView;

	//constructor
	public BlousesPage(WebDriver driver) {
		super(driver);
	}

	@Step("Click 'more' button")
	public void clickOnMore() {
		if (listView.isSelected()) {
			click(moreBtn);
		} else {
			scrollTo(productImage);
			mouseHoverTo(productImage);
			waitToBeSeen(moreBtn);
			click(moreBtn);
		}
	}
}
