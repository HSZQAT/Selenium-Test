package com.qa.test;

import static org.junit.Assert.fail;

import java.io.File;
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
import com.qa.test.shoppingsitepages.AccountCreationPage;
import com.qa.test.shoppingsitepages.AccountPage;
import com.qa.test.shoppingsitepages.SignInPage;

@RunWith(Parameterized.class)
public class seleniumTestExcel {

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

	public seleniumTestExcel(String email, String firstname, String lastname, String password, String address,
			String city, String state, String postcode, String mobile) {

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
		ChromeOptions opts = new ChromeOptions();
		opts.setHeadless(true);
		driver = new ChromeDriver(opts);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	}

	@Test
	public void excelShoppingAccountTest() throws IOException {

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

		TakesScreenshot scrShot = (TakesScreenshot) driver;
		File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
		final String scrShotPath = "test-output/screenshots/exceltest.png";
		File targetFile = new File(scrShotPath);
		Files.copy(srcFile, targetFile);

		if (accountPage.getLoginStatus()
				.equals("Welcome to your account. Here you can manage all of your personal information and orders.")) {
			test.pass("Logged in successfully!").addScreenCaptureFromPath(scrShotPath);
		} else {
			test.fail("Failed to log in.").addScreenCaptureFromPath(scrShotPath);
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
