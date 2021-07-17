package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import pageobjects.AuthenticationPage;
import pageobjects.CartPage;
import pageobjects.DressesPage;
import pageobjects.IndexPage;
import pageobjects.OrderAddressPage;
import pageobjects.OrderBankwirePage;
import pageobjects.OrderChequePaymentPage;
import pageobjects.OrderConfirmationPage;
import pageobjects.OrderPaymentPage;
import pageobjects.OrderShippingPage;
import pageobjects.TshirtsPage;

public class CheckoutTest extends BaseTest {

	@Severity(SeverityLevel.NORMAL)
	@Test (description = "trying to checkout an empty cart")
	@Description ("Attemp to chechkout without adding atiems")
	public void tc30_checkoutEmpty() {
		//go to cart
		IndexPage ip = new IndexPage(driver);
		ip.clickOnCartLink();

		//get empty cart alert
		CartPage cp = new CartPage(driver);
		String actual = cp.cartAlertText();
		String expected = "Your shopping cart is empty.";
		Assert.assertEquals(actual, expected);
	}


	@Severity(SeverityLevel.NORMAL)
	@Test (description = "can't checkout without checking terms of condition")
	@Description ("Try to checkout without clicking the checkbox with terms")
	public void tc31_checkout() {
		//go to shirt category
		CartPage cp = new CartPage(driver);
		cp.goToTshirtCategory();

		//add an item
		TshirtsPage tp = new TshirtsPage(driver);
		tp.addShirt("s", 1);

		//go to cart
		tp = new TshirtsPage(driver);
		tp.clickOnCartLink();

		//click checkout
		cp = new CartPage(driver);
		cp.clickOnCheckoutBtn();

		//sign in
		AuthenticationPage ap = new AuthenticationPage(driver);
		ap.registeredUserSignIn("098oiu@gmail.com", "pass123");

		//click proceed to checkout
		OrderAddressPage oap = new OrderAddressPage(driver);
		oap.clickProceedToShipping();

		//
		OrderShippingPage osp = new OrderShippingPage(driver);
		osp.clickProceedToPaymentBtn();

		//alert shoul pop up
		osp = new OrderShippingPage(driver);
		String actual = osp.getAlertMsgText();

		//close the alert
		osp = new OrderShippingPage(driver);
		osp.clickCloseAlert();

		String expected = "You must agree to the terms of service before continuing.";
		Assert.assertEquals(actual, expected);
	}

	@Severity(SeverityLevel.CRITICAL)
	@Test (description = "check terms and go to checkout, pay with bankwire")
	@Description ("Successful checkout after clicking the checkbox")
	public void tc32_completePurchase() {
		//click on checkbox
		OrderShippingPage osp = new OrderShippingPage(driver);
		osp.checkTerms();

		//click proceed button
		osp = new OrderShippingPage(driver);
		osp.clickProceedToPaymentBtn();

		//choose bankwire
		OrderPaymentPage opp = new OrderPaymentPage(driver);
		opp.clickBankwireBtn();

		//click "confirm order"
		OrderBankwirePage obp = new OrderBankwirePage(driver);
		obp.clickConfirmOrderBtn();

		//get confirmation message
		OrderConfirmationPage ocp = new OrderConfirmationPage(driver);
		String actual = ocp.getConfirmationMsg();

		String expected = "Your order on My Store is complete.";
		Assert.assertEquals(actual, expected);
	}

	@Severity(SeverityLevel.CRITICAL)
	@Test (description = "another purchase")
	@Description ("Successful checkout with different paying method")
	public void tc33_completePurchase() {
		//go to dresses category
		OrderConfirmationPage ocp = new OrderConfirmationPage(driver);
		ocp.goTodressesCategory();

		//choose what dress to add (first/second..etc)
		DressesPage dp = new DressesPage(driver);
		dp.addDressToCart(2);

		//go to cart
		dp = new DressesPage(driver);
		dp.clickOnCartLink();

		//click checkout
		CartPage cp = new CartPage(driver);
		cp.clickOnCheckoutBtn();

		//click proceed
		OrderAddressPage oap = new OrderAddressPage(driver);
		oap.clickProceedToShipping();

		//click on checkbox
		OrderShippingPage osp = new OrderShippingPage(driver);
		osp.checkTerms();

		//click proceed
		osp = new OrderShippingPage(driver);
		osp.clickProceedToPaymentBtn();

		//click on cheque option
		OrderPaymentPage opp = new OrderPaymentPage(driver);
		opp.clickChequeBtn();

		//click confirm order
		OrderChequePaymentPage och = new OrderChequePaymentPage(driver);
		och.clickConfirmOrderBtn();

		//get confirmation message
		ocp = new OrderConfirmationPage(driver);
		String actual = ocp.getConfirmationAlertText();

		String expected = "Your order on My Store is complete.";
		Assert.assertEquals(actual, expected);
	}


}
