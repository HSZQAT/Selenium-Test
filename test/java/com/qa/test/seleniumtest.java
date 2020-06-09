package com.qa.test;

import static org.junit.Assert.fail;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.qa.test.demositepages.LoginPage;
import com.qa.test.demositepages.RegisterPage;
import com.qa.test.ftsepages.FallersPage;
import com.qa.test.ftsepages.HomePage;
import com.qa.test.ftsepages.RisersPage;
import com.qa.test.shoppingsitepages.AccountCreationPage;
import com.qa.test.shoppingsitepages.AccountPage;
import com.qa.test.shoppingsitepages.AddressPage;
import com.qa.test.shoppingsitepages.BankWireConfirmationPage;
import com.qa.test.shoppingsitepages.CartPage;
import com.qa.test.shoppingsitepages.OrderConfirmationPage;
import com.qa.test.shoppingsitepages.PaymentPage;
import com.qa.test.shoppingsitepages.ProductPage;
import com.qa.test.shoppingsitepages.ResultPage;
import com.qa.test.shoppingsitepages.ShippingPage;
import com.qa.test.shoppingsitepages.SignInPage;

@RunWith(Parameterized.class)
public class seleniumtest {

	@Parameters
	public static Collection<Object[]> data() throws IOException {
		FileInputStream file = new FileInputStream("src/test/resources/SignInNames.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheetAt(0);

		Object[][] ob = new Object[sheet.getPhysicalNumberOfRows() - 1][9];

		// Reading
		for (int rowNum = 1; rowNum < sheet.getPhysicalNumberOfRows(); rowNum++) {
			ob[rowNum - 1][0] = sheet.getRow(rowNum).getCell(0).getStringCellValue();
			ob[rowNum - 1][1] = sheet.getRow(rowNum).getCell(1).getStringCellValue();
			ob[rowNum - 1][2] = sheet.getRow(rowNum).getCell(2).getStringCellValue();
			ob[rowNum - 1][3] = sheet.getRow(rowNum).getCell(3).getStringCellValue();
			ob[rowNum - 1][4] = sheet.getRow(rowNum).getCell(4).getStringCellValue();
			ob[rowNum - 1][5] = sheet.getRow(rowNum).getCell(5).getStringCellValue();
			ob[rowNum - 1][6] = sheet.getRow(rowNum).getCell(6).getStringCellValue();
			double postcode = sheet.getRow(rowNum).getCell(7).getNumericCellValue();
			int intpostcode = (int) postcode;
			double mobile = sheet.getRow(rowNum).getCell(8).getNumericCellValue();
			int intmobile = (int) mobile;
			ob[rowNum - 1][7] = String.valueOf(intpostcode);
			ob[rowNum - 1][8] = String.valueOf(intmobile);
		}

		workbook.close();
		return Arrays.asList(ob);

	}

	private WebDriver driver;
	private static ExtentReports report;
	private ExtentTest test;
	private String email;
	private String firstname;
	private String lastname;
	private String password;
	private String address;
	private String city;
	private String state;
	private String postcode;
	private String mobile;

	public seleniumtest(String email, String firstname, String lastname, String password, String address, String city,
			String state, String postcode, String mobile) {

		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
		this.address = address;
		this.city = city;
		this.state = state;
		this.postcode = postcode;
		this.mobile = mobile;

	}

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
		if (resultPage.confirmSearch().contains(clothing)) {
			test.pass("Correct search items appeared for " + clothing + "!");
		} else {
			test.fail("Incorrect search items for " + clothing + " appeared.");
			fail();
		}

		resultPage.selectFirstResult();
		productPage.addToCart();
		productPage.proceedToCheckout();
		cartPage.proceedToCheckout();
		signInPage.signIn(EMAIL, PASS);
		addressPage.acceptAddress();
		shippingPage.acceptShipping();
		paymentPage.payByBankWire();
		bankWireConfirmationPage.confirmOrder();
		if (orderConfirmationPage.getConfirmation().contentEquals("Your order on My Store is complete.")) {
			test.pass("Order completed!");
		} else {
			test.fail("Order unsuccessful.");
			fail();
		}

		System.out.println();

	}

	@Test
	public void ftseTest() {

		driver.get("https://www.hl.co.uk/shares/stock-market-summary/ftse-100 ");
		this.test = report.createTest("testFTSE");

		String largestRiser = "AVEVA Group plc"; // correct as of 09/06/2020
		String largestFaller = "Meggitt"; // correct as of 09/06/2020

		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		RisersPage risersPage = PageFactory.initElements(driver, RisersPage.class);
		FallersPage fallersPage = PageFactory.initElements(driver, FallersPage.class);

		homePage.closeCookies();
		homePage.viewRisers();
		if (risersPage.getLargestRiserName().equals(largestRiser)) {
			test.pass("Matches largest riser " + largestRiser + "! (correct as of 09/06/2020)");
		} else {
			test.fail("Doesn't match largest riser " + largestRiser
					+ " (correct as of 09/06/2020)\nValidation answer could be incorrect at current time, please check.");
			fail();
		}

		risersPage.returnHome();
		homePage.viewFallers();
		if (fallersPage.getLargestFallerName().equals(largestFaller)) {
			test.pass("Matches largest faller " + largestFaller + "! (correct as of 09/06/2020)");
		} else {
			test.fail("Doesn't match largest faller " + largestFaller
					+ " (correct as of 09/06/2020)\nValidation answer could be incorrect at current time, please check.");
			fail();
		}

	}

	@Test
	public void excelShoppingAccountTest() {

		driver.get("http://automationpractice.com/index.php");
		this.test = report.createTest("testExcelShoppingAccount");

		com.qa.test.shoppingsitepages.HomePage homePage = PageFactory.initElements(driver,
				com.qa.test.shoppingsitepages.HomePage.class);
		SignInPage signInPage = PageFactory.initElements(driver, SignInPage.class);
		AccountCreationPage accountCreationPage = PageFactory.initElements(driver, AccountCreationPage.class);
		AccountPage accountPage = PageFactory.initElements(driver, AccountPage.class);

		homePage.signIn();
//		signInPage.createAccount(email);
//		accountCreationPage.createNewAccount(firstname, lastname, password, address, city, state, postcode, mobile);
		signInPage.signIn(email, password);
		if (accountPage.getLoginStatus()
				.equals("Welcome to your account. Here you can manage all of your personal information and orders.")) {
			test.pass("Logged in successfully!");
		} else {
			test.fail("Failed to log in.");
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
