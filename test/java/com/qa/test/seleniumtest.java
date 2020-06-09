package com.qa.test;

import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.qa.test.demositepages.LoginPage;
import com.qa.test.demositepages.RegisterPage;
import com.qa.test.shoppingsitepages.HomePage;
import com.qa.test.shoppingsitepages.ResultPage;

public class seleniumtest {

	private WebDriver driver;
	private static ExtentReports report;
	private ExtentTest test;

	@BeforeClass
	public static void setup() {
		report = new ExtentReports();
		ExtentHtmlReporter htmlReport = new ExtentHtmlReporter("test-output/html/extentReport.html");
		htmlReport.config().setAutoCreateRelativePathMedia(true);
		report.attachReporter(htmlReport);
	}

	@Before
	public void init() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

//	@Test
//	public void testDemoSite() {
//
//		driver.manage().window().maximize();
//		driver.get("http://thedemosite.co.uk/addauser.php");
//
//		final String username = "NicCage";
//		final String password = "IsTheBes";
//
//		WebElement user = driver.findElement(By.xpath(
//				"/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[1]/td[2]/p/input"));
//		user.sendKeys(username);
//
//		WebElement pass = driver.findElement(By.xpath(
//				"/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[2]/td[2]/p/input"));
//		pass.sendKeys(password);
//
//		WebElement saveButton = driver.findElement(By.xpath(
//				"/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[3]/td[2]/p/input"));
//		saveButton.click();
//
//		WebElement loginPage = driver.findElement(
//				By.xpath("/html/body/div/center/table/tbody/tr[2]/td/div/center/table/tbody/tr/td[2]/p/small/a[4]"));
//		loginPage.click();
//
//		WebElement loginUser = driver.findElement(By.xpath(
//				"/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[1]/td[2]/p/input"));
//		loginUser.sendKeys(username);
//
//		WebElement loginPass = driver.findElement(By.xpath(
//				"/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[2]/td[2]/p/input"));
//		loginPass.sendKeys(password);
//
//		WebElement testLoginButton = driver.findElement(By.xpath(
//				"/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[3]/td[2]/p/input"));
//		testLoginButton.click();
//
//		WebElement loginStatus = driver
//				.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/big/blockquote/blockquote/font/center/b"));
//
//		System.out.println();
//
//		assertEquals("**Successful Login**", loginStatus.getText());
//	}

	@Test
	public void testDemoSitePOM() {

		driver.get("http://thedemosite.co.uk/addauser.php");
		this.test = report.createTest("testDemoSitePOM");

		final String username = "NicCage";
		final String password = "IsTheBes";

		RegisterPage registerPage = PageFactory.initElements(driver, RegisterPage.class);
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);

		registerPage.registerUser(username, password);
		registerPage.navigateToLogin();
		loginPage.login(username, password);
//		assertEquals("**Successful Login**", loginPage.getStatus());
		if (loginPage.getStatus().equals("**Successful Login**")) {
			test.pass("Login attempt successful!");
		} else {
			test.fail("Login attempt unsuccessful.");
			fail();
		}

	}

	@Test
	public void testShoppingSite() {

		driver.get("http://automationpractice.com/index.php");
		this.test = report.createTest("testShoppingSitePOM");

		String clothing = new String("Dress");

		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		ResultPage resultPage = PageFactory.initElements(driver, ResultPage.class);

		homePage.search(clothing);
//		assertTrue(resultPage.confirmSearch().contains(clothing));
		if (resultPage.confirmSearch().contains(clothing)) {
			test.pass("Correct search items appeared for " + clothing + "!");
		} else {
			test.fail("Incorrect search items for " + clothing + " appeared.");
			fail();
		}

		System.out.println();

	}

	@After
	public void tearDown() {
		driver.quit();
	}

	@AfterClass
	public static void tearDownClass() {
		report.flush();
	}
}
