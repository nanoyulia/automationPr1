package tests;

import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import pageobjects.Blouse1Page;
import pageobjects.BlousesPage;
import pageobjects.DressesPage;
import pageobjects.IndexPage;

public class ProductsTests extends BaseTest {

	@Severity(SeverityLevel.NORMAL)
	@Test (description = "share page via twitter")
	@Description ("Share product page via twitter")
	public void tc20_shareWithFriend( ) {
		//open main page and go to blouse category
		IndexPage ip = new IndexPage(driver);
		ip.goToBlouseCategory();

		//enter to the product page
		BlousesPage bp = new BlousesPage(driver);
		bp.clickOnMore();

		//save the current page
		Blouse1Page blp = new Blouse1Page(driver);
		String main = blp.mainWindow();

		//click on twitter button
		blp = new Blouse1Page(driver);
		blp.clickTwitterBtn();

		//new window is supposed to open
		blp = new Blouse1Page(driver);
		Set<String> list = blp.handleWindows();
		for (String win : list) {
			driver.switchTo().window(win);
		}

		//make sure that url of a new window includes "twitter"
		blp = new Blouse1Page(driver);
		boolean actual = blp.doesUrlInclude("twitter");
		boolean expected = true;

		//close the wondow
		blp = new Blouse1Page(driver);
		blp.closeWindow();

		//return to main window
		blp = new Blouse1Page(driver);
		blp.switchToWindow(main);

		Assert.assertEquals(actual, expected);
	}

	@Severity(SeverityLevel.NORMAL)
	@Test (description = "share page via facebook")
	@Description ("Share product page via facebook")
	public void tc21_shareWithFriend( ) {
		//save the current page
		Blouse1Page blp = new Blouse1Page(driver);
		String main2 = blp.mainWindow();

		//click on facebook button
		blp = new Blouse1Page(driver);
		blp.clickFacebookBtn();

		//new window is supposed to open
		blp = new Blouse1Page(driver);
		Set<String> list = blp.handleWindows();
		for (String win : list) {
			driver.switchTo().window(win);
		}

		//make sure that url of a new window includes "facebook"
		blp = new Blouse1Page(driver);
		boolean actual = blp.doesUrlInclude("facebook");
		boolean expected = true;

		//close the wondow
		blp = new Blouse1Page(driver);
		blp.closeWindow();

		//return to main window
		blp = new Blouse1Page(driver);
		blp.switchToWindow(main2);

		Assert.assertEquals(actual, expected);
	}

	@Severity(SeverityLevel.NORMAL)
	@Test (description = "share with friend via google plus")
	@Description ("Share product page via google plus")
	public void tc22_shareWithFriend( ) {
		//save the current page
		Blouse1Page blp = new Blouse1Page(driver);
		String main3 = blp.mainWindow();

		//click on google button
		blp = new Blouse1Page(driver);
		blp.clickGoogleBtn();

		//new window is supposed to open
		blp = new Blouse1Page(driver);
		Set<String> list = blp.handleWindows();
		for (String win : list) {
			driver.switchTo().window(win);
		}

		//make sure that url of a new window includes "google"
		blp = new Blouse1Page(driver);
		boolean actual = blp.doesUrlInclude("google");
		boolean expected = true;

		//close the wondow
		blp = new Blouse1Page(driver);
		blp.closeWindow();

		//return to main window
		blp = new Blouse1Page(driver);
		blp.switchToWindow(main3);

		Assert.assertEquals(actual, expected);
	}

	@Severity(SeverityLevel.NORMAL)
	@Test (description = "share via pinterest")
	@Description ("Share product page via pinterest")
	public void tc23_shareWithFriend( ) {
		//save the current page
		Blouse1Page blp = new Blouse1Page(driver);
		String main4 = blp.mainWindow();

		//click on google button
		blp = new Blouse1Page(driver);
		blp.clickPinterestBtn();

		//new window is supposed to open
		blp = new Blouse1Page(driver);
		Set<String> list = blp.handleWindows();
		for (String win : list) {
			driver.switchTo().window(win);
		}

		//make sure that url of a new window includes "pinterest"
		blp = new Blouse1Page(driver);
		boolean actual = blp.doesUrlInclude("pinterest");
		boolean expected = true;

		//close the wondow
		blp = new Blouse1Page(driver);
		blp.closeWindow();

		//return to main window
		blp = new Blouse1Page(driver);
		blp.switchToWindow(main4);

		Assert.assertEquals(actual, expected);
	}

	@Severity(SeverityLevel.NORMAL)
	@Test (description = "share with friend via email")
	@Description ("Share product page via email")
	public void tc24_sendEmail() {
		//click on send to friend envelope icon
		Blouse1Page blp = new Blouse1Page(driver);
		blp.clickSendEmail();

		//fill form with name and email
		blp = new Blouse1Page(driver);
		blp.sendTofriendFillForm("Lili", "2345@gmail.com");

		//alert should pop up
		blp = new Blouse1Page(driver);
		String actual = blp.getMsgSentAlertText();
		String expected = "Your e-mail has been sent successfully";

		//close the alert
		blp = new Blouse1Page(driver);
		blp.clockCloseAlert();

		Assert.assertEquals(actual, expected);
	}

	@Severity(SeverityLevel.NORMAL)
	@Test (description = "add product to comparison list")
	@Description ("Add item to comparison list")
	public void tc25_addToCompare( ) {
		Blouse1Page blp = new Blouse1Page(driver);
		blp.goTodressesCategory();

		//choose what item to add
		DressesPage dp = new DressesPage(driver);
		dp.addToCompare(2);

		dp = new DressesPage(driver);
		int actual = dp.numOfItemsToCompare();
		int expected = 1;
		Assert.assertEquals(actual, expected);		
	}

}
