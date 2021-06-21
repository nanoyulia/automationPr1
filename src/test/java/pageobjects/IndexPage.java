package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Step;

public class IndexPage extends MenuBarPage {

	@FindBy (css = ".alert")
	private WebElement newsletterAlert;

	//constructor
	public IndexPage(WebDriver driver) {
		super(driver);
	}

	@Step("Get alert text")
	public String getNewsletterAlertText() {
		waitToBeSeen(newsletterAlert);
		return getText(newsletterAlert);
	}
}
