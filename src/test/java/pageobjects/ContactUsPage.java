package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import io.qameta.allure.Step;

public class ContactUsPage extends MenuBarPage {

	@FindBy (css = "#id_contact")
	private WebElement subjectHeading;
	@FindBy (css = "#email")
	private WebElement emailField;
	@FindBy (css = "[name='id_order']")
	private WebElement orderReference;
	@FindBy (css = ".unvisible.product_select.form-control")
	private WebElement productRef;
	@FindBy (css = "#message")
	private WebElement mesgField ;
	@FindBy (css = "#submitMessage ")
	private WebElement sendBtn;
	@FindBy (css = ".alert.alert-success")
	private WebElement msgSentSuccesssfully;

	//constructor
	public ContactUsPage(WebDriver driver) {
		super(driver);
	}

	@Step("Type in heading: {headingText}, email: {emailText} and message: {msgText}")
	public void fillContactUsForm(String headingText, String emailText, String msgText) {
		Select subject = new Select(subjectHeading);
		subject.selectByVisibleText(headingText);

		String s = emailField.getAttribute("value");
		if (s.isEmpty()) {
			fillText(emailField, emailText);
		} 
		if (orderReference.getTagName().equalsIgnoreCase("input")) {
			fillText(orderReference, "----");
		} else  {
			Select s1 = new Select(orderReference);
			s1.selectByIndex(1);
			if (productRef.isEnabled()) {
				Select p = new Select(productRef);
				p.selectByIndex(1);
			} 
		}
		fillText(mesgField, msgText);
		click(sendBtn);
	}

	@Step("Get alert message")
	public String msgSentAlert() {
		waitToBeSeen(msgSentSuccesssfully);
		return getText(msgSentSuccesssfully);
	}
}
