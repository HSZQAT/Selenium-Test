package com.qa.test;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.google.common.io.Files;
import com.qa.test.demositepages.LoginPage;
import com.qa.test.demositepages.RegisterPage;
import com.qa.test.ftsepages.FallersPage;
import com.qa.test.ftsepages.HomePage;
import com.qa.test.ftsepages.RisersPage;
import com.qa.test.shoppingsitepages.AddressPage;
import com.qa.test.shoppingsitepages.BankWireConfirmationPage;
import com.qa.test.shoppingsitepages.CartPage;
import com.qa.test.shoppingsitepages.OrderConfirmationPage;
import com.qa.test.shoppingsitepages.PaymentPage;
import com.qa.test.shoppingsitepages.ProductPage;
import com.qa.test.shoppingsitepages.ResultPage;
import com.qa.test.shoppingsitepages.ShippingPage;
import com.qa.test.shoppingsitepages.SignInPage;

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
		ChromeOptions opts = new ChromeOptions();
		opts.setHeadless(true);
		driver = new ChromeDriver(opts);
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
	public void testDemoSitePOM() throws IOException {

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

		TakesScreenshot scrShot = (TakesScreenshot) driver;
		File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
		final String scrShotPath = "test-output/screenshots/demositetest.png";
		File targetFile = new File(scrShotPath);
		Files.copy(srcFile, targetFile);

		if (loginPage.getStatus().equals("**Successful Login**")) {
			test.pass("Login attempt successful!").addScreenCaptureFromPath(scrShotPath);
		} else {
			test.fail("Login attempt unsuccessful.").addScreenCaptureFromPath(scrShotPath);
			fail();
		}

	}

	@Test
	public void testShoppingSiteSearch() throws IOException {

		driver.get("http://automationpractice.com/index.php");
		this.test = report.createTest("testShoppingSiteSearch");

		String clothing = new String("Dress");

		com.qa.test.shoppingsitepages.HomePage homePage = PageFactory.initElements(driver,
				com.qa.test.shoppingsitepages.HomePage.class);
		ResultPage resultPage = PageFactory.initElements(driver, ResultPage.class);

		homePage.search(clothing);
//		assertTrue(resultPage.confirmSearch().contains(clothing));

		TakesScreenshot scrShot = (TakesScreenshot) driver;
		File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
		final String scrShotPath = "test-output/screenshots/shoppingsearchtest.png";
		File targetFile = new File(scrShotPath);
		Files.copy(srcFile, targetFile);

		if (resultPage.confirmSearch().contains(clothing)) {
			test.pass("Correct search items appeared for " + clothing + "!").addScreenCaptureFromPath(scrShotPath);
			;
		} else {
			test.fail("Incorrect search items for " + clothing + " appeared.").addScreenCaptureFromPath(scrShotPath);
			;
			fail();
		}

		System.out.println();

	}

	@Test
	public void testShoppingSiteShop() throws IOException {

		driver.get("http://automationpractice.com/index.php");
		this.test = report.createTest("testShoppingSiteShop");

		String clothing = new String("Dress");
		final String EMAIL = "niccage@conair.com";
		final String PASS = "hello1";

		com.qa.test.shoppingsitepages.HomePage homePage = PageFactory.initElements(driver,
				com.qa.test.shoppingsitepages.HomePage.class);
		ResultPage resultPage = PageFactory.initElements(driver, ResultPage.class);
		ProductPage productPage = PageFactory.initElements(driver, ProductPage.class);
		CartPage cartPage = PageFactory.initElements(driver, CartPage.class);
		SignInPage signInPage = PageFactory.initElements(driver, SignInPage.class);
		AddressPage addressPage = PageFactory.initElements(driver, AddressPage.class);
		ShippingPage shippingPage = PageFactory.initElements(driver, ShippingPage.class);
		PaymentPage paymentPage = PageFactory.initElements(driver, PaymentPage.class);
		BankWireConfirmationPage bankWireConfirmationPage = PageFactory.initElements(driver,
				BankWireConfirmationPage.class);
		OrderConfirmationPage orderConfirmationPage = PageFactory.initElements(driver, OrderConfirmationPage.class);

		homePage.search(clothing);
//		assertTrue(resultPage.confirmSearch().contains(clothing));
		resultPage.selectFirstResult();
		productPage.addToCart();
		productPage.proceedToCheckout();
		cartPage.proceedToCheckout();
		signInPage.signIn(EMAIL, PASS);
		addressPage.acceptAddress();
		shippingPage.acceptShipping();
		paymentPage.payByBankWire();
		bankWireConfirmationPage.confirmOrder();

		TakesScreenshot scrShot = (TakesScreenshot) driver;
		File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
		final String scrShotPath = "test-output/screenshots/shoppingshoptest.png";
		File targetFile = new File(scrShotPath);
		Files.copy(srcFile, targetFile);

		if (orderConfirmationPage.getConfirmation().contentEquals("Your order on My Store is complete.")) {
			test.pass("Order completed!").addScreenCaptureFromPath(scrShotPath);
		} else {
			test.fail("Order unsuccessful.").addScreenCaptureFromPath(scrShotPath);
			fail();
		}

		System.out.println();

	}

	@Test
	public void ftseTestRiser() throws IOException {

		driver.get("https://www.hl.co.uk/shares/stock-market-summary/ftse-100 ");
		this.test = report.createTest("testRiserFTSE");

		String largestRiser = "London Stock Exchange Group plc"; // correct as of 10/06/2020

		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		RisersPage risersPage = PageFactory.initElements(driver, RisersPage.class);

		homePage.closeCookies(driver);
		homePage.viewRisers();

		TakesScreenshot scrShot = (TakesScreenshot) driver;
		File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
		final String scrShotPath = "test-output/screenshots/ftserisertest.png";
		File targetFile = new File(scrShotPath);
		Files.copy(srcFile, targetFile);

		if (risersPage.getLargestRiserName().equals(largestRiser)) {
			test.pass("Matches largest riser " + largestRiser + "! (correct as of 10/06/2020)")
					.addScreenCaptureFromPath(scrShotPath);
		} else {
			test.fail("Doesn't match largest riser " + largestRiser
					+ " (correct as of 10/06/2020)\nValidation answer could be incorrect at current time, please check.")
					.addScreenCaptureFromPath(scrShotPath);
			fail();
		}
	}

	@Test
	public void ftseTestFaller() throws IOException {

		driver.get("https://www.hl.co.uk/shares/stock-market-summary/ftse-100 ");
		this.test = report.createTest("testFallerFTSE");

		String largestFaller = "Rolls Royce Holdings Plc"; // correct as of 10/06/2020

		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		FallersPage fallersPage = PageFactory.initElements(driver, FallersPage.class);

		homePage.closeCookies(driver);
		homePage.viewFallers();

		TakesScreenshot scrShot = (TakesScreenshot) driver;
		File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
		final String scrShotPath = "test-output/screenshots/ftsefallertest.png";
		File targetFile = new File(scrShotPath);
		Files.copy(srcFile, targetFile);

		if (fallersPage.getLargestFallerName().equals(largestFaller)) {
			test.pass("Matches largest faller " + largestFaller + "! (correct as of 09/06/2020)")
					.addScreenCaptureFromPath(scrShotPath);
		} else {
			test.fail("Doesn't match largest faller " + largestFaller
					+ " (correct as of 09/06/2020)\nValidation answer could be incorrect at current time, please check.")
					.addScreenCaptureFromPath(scrShotPath);
			fail();
		}

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
