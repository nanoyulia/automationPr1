package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import pageobjects.AuthenticationPage;
import pageobjects.ForgotPswdPage;
import pageobjects.IndexPage;
import pageobjects.MyAccountPage;
import pageobjects.RegistrationPage;

public class LoginTests extends BaseTest {

	@Severity(SeverityLevel.CRITICAL)
	@Test (description = "Try to login with non registered email")
	@Description ("Attempt to login with non registered email")
	public void tc01_failedLogin() {
		//click sign in link
		IndexPage ip = new IndexPage(driver);
		ip.clickSigninLink();

		//fill email and click submit
		AuthenticationPage ap = new AuthenticationPage(driver);
		ap.registeredUserSignIn("1223@yahoo.com", "12121");
		ap = new AuthenticationPage(driver);
		boolean expected = true;
		boolean actual;

		//if message is one of those:
		if (ap.failedAuthenticationMsg().equalsIgnoreCase("Authentication failed.")  || 
				ap.failedAuthenticationMsg().equalsIgnoreCase("Password is required.") || 
				ap.failedAuthenticationMsg().equalsIgnoreCase("Invalid password.")) {
			actual = true;
		} else {
			actual = false;
		}
		Assert.assertEquals(actual, expected);
	}

	
	@Severity(SeverityLevel.NORMAL)
	@Test (description = "attempt to retrieve password with non registered email")
	@Description ("Attempt to retrieve password with non registered email")
	public void tc02_forgotPswd() {
		//go to forgot password page
		AuthenticationPage ap = new AuthenticationPage(driver);
		ap.clickForgotPswdLink();

		//fill in non registered or invalid email
		ForgotPswdPage fp = new ForgotPswdPage(driver);
		fp.fillForgotPswd("rtrt@gmail.com"); 

		//get error message
		fp = new ForgotPswdPage(driver);
		String errorMsg = fp.errorrMsgText();

		boolean expected = true;
		boolean actual;

		//check if the error message is one of those:
		if (errorMsg.equalsIgnoreCase("There is no account registered for this email address.")||
				errorMsg.equalsIgnoreCase("Invalid email address.")) {
			actual = true;
		} else {
			actual = false;
		}

		Assert.assertEquals(actual, expected);
	}

	@Severity(SeverityLevel.CRITICAL)
	@Test (description = "successful retrieve password")
	@Description("Successful recovering of password")
	public void tc03_forgotPswd2() {
		//fill correct email
		ForgotPswdPage fp = new ForgotPswdPage(driver);
		fp.fillForgotPswd("mhd9k5@gmail.com");

		boolean actual;
		boolean expected = true;

		//get success message and compare with expected message
		fp = new ForgotPswdPage(driver);
		if (fp.successMsgText().contains("A confirmation email has been sent to your address:")) {
			actual = true;
		} else {
			actual = false;
		}
		Assert.assertEquals(actual, expected);
	}

	@Severity(SeverityLevel.CRITICAL)
	@Test (description = "successfull sign in")
	@Description ("Successfull sign in")
	public void tc04_successSignin() {
		//return to authentication page
		ForgotPswdPage fp = new ForgotPswdPage(driver);
		fp.clickBackToLogin();

		//fill in correct info
		AuthenticationPage ap = new AuthenticationPage(driver);
		ap.registeredUserSignIn("098oiu@gmail.com", "pass123");

		//check if it is 'my account' page
		MyAccountPage mp = new MyAccountPage(driver);
		String actual = mp.getTitle();
		String expected = "My account - My Store";
		Assert.assertEquals(actual, expected);
	}

	@Severity(SeverityLevel.NORMAL)
	@Test (description = "successful sign out")
	@Description ("Successful sign out ")
	public void tc05_successSignout() {
		//click sign out
		MyAccountPage mp = new MyAccountPage(driver);
		mp.clickLogout();

		//check if this is 'authentication' page
		AuthenticationPage ap = new AuthenticationPage(driver);
		String actual = ap.getTitle();
		String expected = "Login - My Store";
		Assert.assertEquals(actual, expected);

	}

	@Severity(SeverityLevel.NORMAL)
	@Test (dataProvider="getData1",description = "try to register with already registered or invalid email ")
	@Description ("Attempt to register with already redistered or invalid email")
	public void tc06_failedRegistration(String mailField) {
		//fill email and click submit
		AuthenticationPage ap = new AuthenticationPage(driver);
		ap.createAccount(mailField); 
		boolean expected = true;
		boolean actual;

		//get error message
		String errorMsg = ap.createAccErrorMsg();
		if (errorMsg.equalsIgnoreCase("An account using this email address has already been registered. Please enter a valid password or request a new one.")||
				errorMsg.equalsIgnoreCase("invalid email address.")	) {
			actual = true;
		} else {
			actual = false;
		}
		Assert.assertEquals(actual, expected);
	}

	@Severity(SeverityLevel.CRITICAL)
	@Test (description = "succesful registration")
	@Description ("Successful registration ")
	public void tc07_registration() {
		//fill in correct email 
		AuthenticationPage ap = new AuthenticationPage(driver);
		ap.createAccount("12123434@gmail.com");

		//fill the necessary info
		RegistrationPage rp = new RegistrationPage(driver);
		rp.fillRegistrationInfo("Sheli", "Levin", "12123434@gmail.com", "fldjf19273", "3", "7", "1990", "Streetname 102", "San Diego", 
				"California", "12345", "0987654321");
		//		MyAccountPage mp = new MyAccountPage(driver);
		//		String actual = mp.getTitle();
		//		String expected = "My account - My Store";
		//		Assert.assertEquals(actual, expected);

	}




	@DataProvider
	public Object[][] getData1(){
		Object[][] myData = {
				{"mhd9k5@gmail.com"},
				{"l23nj23"},
				{"dsa@gmail.com"},
				{"098oiu@gmail.com"},
				{"rtrt@gmail"},
				{"bnbn5656@gmail.com"}
		};
		return myData;
	}

}
