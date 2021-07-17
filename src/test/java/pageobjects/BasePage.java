package pageobjects;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	WebDriver driver;
	Actions actions;
	WebDriverWait wait ; 
	JavascriptExecutor js; 


	public BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		actions = new Actions(driver);
		wait = new WebDriverWait(driver, 15);
		js=(JavascriptExecutor)driver; 
	}

	public void fillText(WebElement el,String text) {
		el.clear();
		el.sendKeys(text);
	}

	public void click(WebElement el) {
		waitToBeSeen(el);
		highlightElement(el, "yellow");
		el.click();
	}

	public String getText(WebElement el) {
		return el.getText();
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public boolean doesUrlInclude(String st) {
		if (driver.getCurrentUrl().toLowerCase().contains(st)) {
			return true;
		} else {
			return false;
		}
	}

	public void closeWindow() {
		driver.close();
	}

	public void switchToWindow(String windowName) {
		driver.switchTo().window(windowName);
	}

	public void switchToFrame(WebElement el) {
		driver.switchTo().frame(el);
	}

	public void switchBackFromFrame() {
		driver.switchTo().defaultContent();
	}

	public void refreshPage() {
		driver.navigate().refresh();
	}
	
	public void goToPreviousPage() {
		driver.navigate().back();
	}

	public void sleep(long mills) {
		try {
			Thread.sleep(mills);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void alertTextOK(String text) {
		driver.switchTo().alert().sendKeys(text);
		driver.switchTo().alert().accept();
	}

	public void alertOK() {
		waitForAlert();
		driver.switchTo().alert().accept();
	}

	public void waitToBeSeen(WebElement el) {
		wait.until(ExpectedConditions.visibilityOf(el));
	}

	public void waitForListBeSeen(List<WebElement> list) {
		wait.until(ExpectedConditions.visibilityOfAllElements(list));
	}

	public void waitToBeClickable(WebElement el) {
		wait.until(ExpectedConditions.elementToBeClickable(el));
	}

	public void waitForInvisibility(WebElement el) {
		wait.until(ExpectedConditions.invisibilityOf(el));
	}

	public void waitForAlert() {
		wait.until(ExpectedConditions.alertIsPresent());
	}

	public Set<String> handleWindows() {
		return driver.getWindowHandles();
	}


	//get main window
	public String mainWindow() {
		return driver.getWindowHandle();
	}

	//mouse hover
	public void mouseHoverTo (WebElement el) {
		waitToBeSeen(el);
		actions.moveToElement(el).build().perform();
	}
	
	//scroll to element 
	public void scrollTo(WebElement el) {
		actions.moveToElement(el);
	}


	/*
	 * Call this method with your element and a color like (red,green,orange etc...)
	 */
	private void highlightElement(WebElement element, String color) {
		//keep the old style to change it back
		String originalStyle = element.getAttribute("style");
		String newStyle = "background-color:yellow;border: 1px solid " + color + ";" + originalStyle;
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Change the style 
		js.executeScript("var tmpArguments = arguments;setTimeout(function () {tmpArguments[0].setAttribute('style', '" + newStyle + "');},0);",
				element);

		// Change the style back after few milliseconds
		js.executeScript("var tmpArguments = arguments;setTimeout(function () {tmpArguments[0].setAttribute('style', '"
				+ originalStyle + "');},400);", element);

	}
}
