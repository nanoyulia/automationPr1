package pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Step;

public class SearchResultPage extends MenuBarPage {

	@FindBy (css = ".product_list.grid.row .product-name")
	private List<WebElement> productNames;

	//constructor
	public SearchResultPage(WebDriver driver) {
		super(driver);
	}

	@Step("Check if result contains the searched product: {product}")
	public boolean isSearchCorrect(String product) {
		boolean result = false;
		for (WebElement el : productNames) {
			if (el.getText().toLowerCase().contains(product)) {
				result = true;
			} else {
				result = false;
			} 
		}
		return result;
	}	
}

