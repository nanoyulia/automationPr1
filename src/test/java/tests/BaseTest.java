package tests;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.Utils;


public class BaseTest {
	WebDriver driver;

	@Parameters({"browser"})
	@BeforeClass
	protected void setup(@Optional("Chrome") String browser){
		switch (browser){
		case "Chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case "Firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case "EdgeDriver":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		default: throw new IllegalArgumentException("no such browser " + browser);
		}
		
		driver.manage().window().maximize();
		String url = Utils.readKey("url");
		driver.get(url);
		}

	
	@AfterClass 
	public void tearDown() {
		driver.quit();
	}


	//	  This method will run after wach test,
	//	  it will take screen shot only for tests that failed
	@AfterMethod
	public void failedTest(ITestResult result) {
		//check if the test failed
		if (result.getStatus() == ITestResult.FAILURE ){
			TakesScreenshot ts = (TakesScreenshot)driver;
			File srcFile = ts.getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(srcFile, new File("./ScreenShots/"+result.getName()+".jpg"));
			} catch (IOException e) {				
				e.printStackTrace();
			}
			//result.getname() method will give you current test case name. 
			//./ScreenShots/ tell you that, in your current directory, create folder ScreenShots. dot represents current directory
		}
	}
}
