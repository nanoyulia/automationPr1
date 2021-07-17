package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import io.qameta.allure.Step;

public class Blouse1Page extends MenuBarPage {

	@FindBy (css = "#group_1")
	private WebElement selectSize ;
	@FindBy (css = "#color_8")
	private WebElement whitecolorIcon ;
	@FindBy (css = "#quantity_wanted")
	private WebElement quantityField ;
	@FindBy (css = "#add_to_cart  span")
	private WebElement addBlouseTocart;
	@FindBy (css = ".cross")
	private WebElement closeIcon;
	@FindBy (css = "button .icon-twitter")
	private WebElement twitterBtn ;
	@FindBy (css = "button .icon-facebook")
	private WebElement facebookBtn;
	@FindBy (css = "button .icon-google-plus")
	private WebElement googleBtn;
	@FindBy (css = "button .icon-pinterest")
	private WebElement pinterestBtn;
	@FindBy (css = "#send_friend_button")
	private WebElement sendToFriendBtn;
	@FindBy (css = "#friend_name")
	private WebElement friendNameField;
	@FindBy (css = "#friend_email")
	private WebElement friendEmailField;
	@FindBy (css = "#sendEmail")
	private WebElement sendBtn;
	@FindBy (css = ".fancybox-inner> #send_friend_form")
	private WebElement sendToFriendForm;
	@FindBy (css = ".fancybox-inner > p:nth-child(2)")
	private WebElement messageSentAlert;
	@FindBy (css = ".fancybox-close")
	private WebElement closeAlert;

	//constructor
	public Blouse1Page(WebDriver driver) {
		super(driver);
	}

	@Step("Edit product details such as quantity: {quantityText} and size: {sizeText}")
	public void chooseBlouseDetails(String quantityText, String sizeText) {
		fillText(quantityField, quantityText);
		Select size = new Select(selectSize);
		size.selectByVisibleText(sizeText.toUpperCase());
		click(whitecolorIcon);
		click(addBlouseTocart);
		click(closeIcon);
	}

	@Step("Click twitter button")
	public void clickTwitterBtn() {
		click(twitterBtn);
	}

	@Step("Click Facebook btn")
	public void clickFacebookBtn() {
		click(facebookBtn);
	}

	@Step("Click Google button")
	public void clickGoogleBtn() {
		click(googleBtn);
	}

	@Step("Click Pinterest button")
	public void clickPinterestBtn() {
		click(pinterestBtn);
	}

	@Step("Click send email button")
	public void clickSendEmail() {
		click(sendToFriendBtn);

	}

	@Step("Type name: {name} and email: {email}")
	public void sendTofriendFillForm(String name, String email) {
		fillText(friendNameField, name);
		fillText(friendEmailField, email);
		click(sendBtn);
	}

	@Step("Get alert text")
	public String getMsgSentAlertText() {
		waitToBeSeen(messageSentAlert);
		return getText(messageSentAlert);
	}

	@Step("Click close button")
	public void clockCloseAlert() {
		click(closeAlert);
	}
}
