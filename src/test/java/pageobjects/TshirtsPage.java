package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import io.qameta.allure.Step;

public class TshirtsPage extends MenuBarPage {
	@FindBy (css = ".product_img_link > img")
	private WebElement tshirtPic;
	@FindBy (css = ".quick-view > span")
	private WebElement quickViewLink;
	@FindBy (css = ".fancybox-iframe")
	private WebElement frameElement;
	@FindBy (css = ".icon-plus")
	private WebElement inFramePlusIcon;
	@FindBy (css = "#group_1")
	private WebElement inFrameSelectSize;
	@FindBy (css = "#color_14")
	private WebElement inFrameblueColorBtn;
	@FindBy (css = "#add_to_cart > button")
	private WebElement inFrameAddToCartBtn ;
	@FindBy (css = ".cross")
	private WebElement closeWindowIcon;

	//constructor
	public TshirtsPage(WebDriver driver) {
		super(driver);
	}
	
	@Step("Define size:{sizeText} and quantity: {quantity}")
	public void addShirt(String sizeText, int quantity) {
		mouseHoverTo(tshirtPic);
		click(quickViewLink);
		switchToFrame(frameElement);
		for (int i = 0; i < (quantity -1); i++) {
			waitToBeClickable(inFramePlusIcon);
			click(inFramePlusIcon);
		}
		Select size = new Select(inFrameSelectSize);
		size.selectByVisibleText(sizeText.toUpperCase());
		click(inFrameblueColorBtn);
		click(inFrameAddToCartBtn);
		switchBackFromFrame();
		click(closeWindowIcon);
		
	}
}
