package pageobjects;

import org.openqa.selenium.support.ui.Select;

import io.qameta.allure.Step;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class RegistrationPage extends MenuBarPage {
	@FindBy (css = "#id_gender1")
	private WebElement mrField;
	@FindBy (css = "#id_gender2")
	private WebElement mrsField;
	@FindBy (css = "#customer_firstname")
	private WebElement firstnameField;
	@FindBy (css = "#customer_lastname")
	private WebElement lastnameField;
	@FindBy (css = "#passwd")
	private WebElement passwordField;
	@FindBy (css = "#email")
	private WebElement emailField;
	@FindBy (css = "#days")
	private WebElement days;
	@FindBy (css = "#months")
	private WebElement months;
	@FindBy (css = "#years")
	private WebElement year;
	@FindBy (css = "#uniform-newsletter")
	private WebElement newsletterCheckbox;
	@FindBy (css = "#uniform-optin")
	private WebElement specOfferCheckbox;
	@FindBy (css = ".account_creation #firstname")
	private WebElement addNamefield;
	@FindBy (css = ".account_creation #lastname")
	private WebElement addLastnamefield;
	@FindBy (css = "#address1")
	private WebElement streetField;
	@FindBy (css = "#city")
	private WebElement cityField;
	@FindBy (css = "#id_state")
	private WebElement state;
	@FindBy (css = "#postcode")
	private WebElement postalcodeField;
	@FindBy (css = "#id_country")
	private WebElement country;
	@FindBy (css = "#phone_mobile")
	private WebElement mobileField;
	@FindBy (css = "#alias")
	private WebElement aliasField;
	@FindBy (css = "#submitAccount > span")
	private WebElement registerBtn;

	//constructor
	public RegistrationPage(WebDriver driver) {
		super(driver);
	} 

	@Step("Type in first name: {firstnameText}, last name: {lastnameText}, email: {emailText}, password: {passwordText},"
			+ "day: {dayValue}, month: {monthValue}, year: {yearValue}, street: {streetText}, city: {cityText},"
			+ "state: {stateText}, postal code: {postalcodeText}, mobile: {mobileText} ")
	public void fillRegistrationInfo(String firstnameText, String lastnameText, String emailText, 
			String passwordText, String dayValue, String monthValue, String yearValue, String streetText, 
			String cityText, String stateText, String postalcodeText, String mobileText) {

		//fill gender, name and password
		waitToBeSeen(firstnameField);
		click(mrsField);
		fillText(firstnameField, firstnameText);
		fillText(lastnameField, lastnameText);
		//check if email field is filled automatically
		if (emailField.getAttribute("value").isEmpty()) {
			fillText(emailField, emailText);
		} 
		fillText(passwordField, passwordText);
		//fill date of birth
		Select d = new Select(days);
		d.selectByValue(dayValue);
		sleep(1000);
		Select m = new Select(months);
		m.selectByValue(monthValue);
		Select y = new Select(year);
		y.selectByValue(yearValue);
		//click checkboxes
		click(newsletterCheckbox);
		click(specOfferCheckbox);
		//fill the address info
		if (!addNamefield.getAttribute("value").equals(firstnameText)) {
			fillText(addNamefield, firstnameText);
		}
		if (!addLastnamefield.getAttribute("value").equals(lastnameText)) {
			fillText(addLastnamefield, lastnameText);
		}
		fillText(streetField, streetText);
		fillText(cityField, cityText);
		Select st = new Select(state);
		st.selectByVisibleText(stateText);
		fillText(postalcodeField, postalcodeText);
		Select co = new Select(country);
		if (!co.getFirstSelectedOption().getText().equalsIgnoreCase("United States")) {
			co.selectByVisibleText("United States");
		}
		fillText(mobileField, mobileText);

		//click register
		//click(registerBtn);

	}
}
