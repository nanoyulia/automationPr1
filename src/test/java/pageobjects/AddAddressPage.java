package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import io.qameta.allure.Step;

public class AddAddressPage extends MenuBarPage {
	@FindBy (css = "#firstname")
	private WebElement firstnameField ;
	@FindBy (css = "#lastname")
	private WebElement lastnameField;
	@FindBy (css = "#address1")
	private WebElement addressField;
	@FindBy (css = "#company")
	private WebElement companyField;;
	@FindBy (css = "#city")
	private WebElement cityField;
	@FindBy (css = "#id_state")
	private WebElement stateSelect;
	@FindBy (css = "#postcode")
	private WebElement postcodeField;
	@FindBy (css = "#phone")
	private WebElement homephoneField;
	@FindBy (css = "#phone_mobile")
	private WebElement mobileField;
	@FindBy (css = "#other")
	private WebElement otherInfoField;
	@FindBy (css = "#alias")
	private WebElement addressTitle;
	@FindBy (css = "#submitAddress")
	private WebElement saveBtn;

	//constructor
	public AddAddressPage(WebDriver driver) {
		super(driver);
	}

	@Step("Fill the form: first name: {firstnameText}, last name: {lastnameText}, company: {companyText}, address: {addressText},"
			+ " city: {cityText}, state: {stateText}, postal code: {postalcodeText}, home phone: {homephoneText},"
			+ "mobile phone: {mobilephoneText}, other text: {otherText}, title: {titleText}")
	public void fillInfo(String firstnameText, String lastnameText, String companyText, String addressText, String cityText, 
			String stateText, String postalcodeText, String homephoneText, String mobilephoneText, String otherText, String titleText) {
		fillText(firstnameField, firstnameText);
		fillText(lastnameField, lastnameText);
		fillText(companyField, companyText);
		fillText(addressField, addressText);
		fillText(cityField, cityText);
		Select s = new Select(stateSelect);
		s.selectByVisibleText(stateText);
		fillText(postcodeField, postalcodeText);
		fillText(homephoneField, homephoneText);
		fillText(mobileField, mobilephoneText);
		fillText(otherInfoField, otherText);
		fillText(addressTitle, titleText);
		click(saveBtn);
	}
}
