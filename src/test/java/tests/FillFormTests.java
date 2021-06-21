package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import pageobjects.AddAddressPage;
import pageobjects.AddressesPage;
import pageobjects.AuthenticationPage;
import pageobjects.ContactUsPage;
import pageobjects.IndexPage;
import pageobjects.MyAccountPage;
import pageobjects.SearchResultPage;

public class FillFormTests extends BaseTest {

	@Severity(SeverityLevel.NORMAL)
	@Test (description = "add new address")
	@Description ("Add new address")
	public void tc08_addAddress(){
		//sign in
		IndexPage ip = new IndexPage(driver);
		ip.clickSigninLink();
		AuthenticationPage ap = new AuthenticationPage(driver);
		ap.registeredUserSignIn("098oiu@gmail.com", "pass123");

		//go to "My adresses" section
		MyAccountPage map = new MyAccountPage(driver);
		map.clickMyAdresses();

		//click "add new address"
		AddressesPage adp = new AddressesPage(driver);
		int before = adp.numberOFaddresses();
		adp.clickAddNewAddress();

		//fill the info
		AddAddressPage add = new AddAddressPage(driver);
		add.fillInfo("Lili", "Cowl", "My ", "Herzl", "City2", "Florida", "12347", "0987754321", "098765733243", "My", "N333");

		//make sure the address is added
		adp = new AddressesPage(driver);
		int actual = adp.numberOFaddresses();
		int expected = before +1;
		Assert.assertEquals(actual, expected);
	}

	@Severity(SeverityLevel.MINOR)
	@Test (description = "delete address")
	@Description ("Deleting address")
	public void tc09_deleteAddress() {
		//open addresses page
		AddressesPage adp = new AddressesPage(driver);
		int before = adp.numberOFaddresses();

		//decide what address to delete (first, second, etc)
		adp = new AddressesPage(driver);
		adp.deleteAddress(2);

		//make sure the address is deleted
		adp = new AddressesPage(driver);
		int actual = adp.numberOFaddresses();
		int expected = before-1;
		Assert.assertEquals(actual, expected);

		//go to index page 
		adp.goToMainPage();
	}

	@Severity(SeverityLevel.MINOR)
	@Test (dataProvider="getData3", description = "subscribe to newsletter")
	@Description ("Subscrive to newsletter with different emails, both valid and not")
	public void tc10_subscribeToNews(String email) {
		//enter an email
		IndexPage ip = new IndexPage(driver);
		ip.subscribeToNews(email);
		ip = new IndexPage(driver);

		//check if the alert message is one of:
		String alertText = ip.getNewsletterAlertText();
		boolean expected  = true;
		boolean actual;

		if (alertText.equalsIgnoreCase("Newsletter : You have successfully subscribed to this newsletter.") || 
				alertText.equalsIgnoreCase("Newsletter : Invalid email address.") ||
				alertText.equalsIgnoreCase("Newsletter : This email address is already registered.")) {
			actual = true;
		} else {
			actual = false;
		}
		Assert.assertEquals(actual, expected);
	}

	@Severity(SeverityLevel.NORMAL)
	@Test (dataProvider="getData4", description = "search for products")
	@Description ("Search for specific products")
	public void tc11_useSearch(String product) {
		IndexPage ip = new IndexPage(driver);
		//search for a specific item
		ip.search(product);
		SearchResultPage srp = new SearchResultPage(driver);

		//all the results must include the searched word
		boolean actual = srp.isSearchCorrect(product);
		boolean expected = true;
		Assert.assertEquals(actual, expected);
	}

	@Severity(SeverityLevel.NORMAL)
	@Test (description = "fill contact us when signed in")
	@Description ("Fill the contact us form as a signed in user")
	public void tc12_fillForm() {
		//click contact us link
		IndexPage ip = new IndexPage(driver);
		ip.clickContactUsLink();

		//fill form and send
		ContactUsPage cp = new ContactUsPage(driver);
		cp.fillContactUsForm("Customer service", "123456@gmail.com", "When my order will be shipped");

		//if message is sent alert will be shown
		cp = new ContactUsPage(driver);
		String expected = "Your message has been successfully sent to our team.";
		String actual = cp.msgSentAlert();
		Assert.assertEquals(actual, expected);
	}

	@Severity(SeverityLevel.NORMAL)
	@Test (description = "fill contact us form after sign out")
	@Description ("Fill the contact us form as a guest")
	public void tc13_fillForm() {
		//logout
		ContactUsPage cp = new ContactUsPage(driver);
		cp.clickSignoutLink();

		//fill form and send
		cp = new ContactUsPage(driver);
		cp.fillContactUsForm("Webmaster", "123456@gmail.com", "Hello, checkboxes are not loading");

		//if message is sent alert will be shown
		String expected = "Your message has been successfully sent to our team.";
		String actual = cp.msgSentAlert();
		Assert.assertEquals(actual, expected);
	}


	@DataProvider
	public Object[][] getData3(){
		Object[][] myData = {
				{"m515k@gmail.com"},
				{"l23nj23"},
				{"dsa@gmail.com"},
				{"12098ee7@yahoo.com"},
				{"rtt@gmail.com"},
				{"bnbn5656@gmail.com"}
		};
		return myData;
	}


	@DataProvider
	public Object[][] getData4(){
		Object[][] myData = {
				{"dress"},
				{"shirt"},
				{"blouse"},
				{"summer"},
				{"t-shirt"}
		};
		return myData;
	}
}
