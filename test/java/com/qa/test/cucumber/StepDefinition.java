package com.qa.test.cucumber;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;

import com.qa.test.cucumber.teapages.CheckOutPage;
import com.qa.test.cucumber.teapages.HomePage;
import com.qa.test.cucumber.teapages.MenuPage;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDefinition {

	private RemoteWebDriver driver;
	private HomePage homePage;
	private MenuPage menuPage;
	private CheckOutPage checkOutPage;

	@Before
	public void init() {
		ChromeOptions opts = new ChromeOptions();
		opts.setHeadless(true);
		driver = new ChromeDriver(opts);
		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		homePage = PageFactory.initElements(driver, HomePage.class);
		menuPage = PageFactory.initElements(driver, MenuPage.class);
		checkOutPage = PageFactory.initElements(driver, CheckOutPage.class);
	}

	@Given("^the correct web address$")
	public void the_correct_web_address() throws Throwable {

		driver.get("http://www.practiceselenium.com/welcome.html");

	}

	@When("^I navigate to the 'Menu' page$")
	public void i_navigate_to_the_Menu_page() throws Throwable {

		homePage.navigateMenu();

	}

	@Then("^I can browse a list of the available products\\.$")
	public void i_can_browse_a_list_of_the_available_products() throws Throwable {

		assertEquals("Menu", menuPage.readHeader());

	}

	@When("^I click the checkout button$")
	public void i_click_the_checkout_button() throws Throwable {

		homePage.navigateMenu();
		menuPage.greenTeaCheckOut();

	}

	@Then("^I am taken to the checkout page$")
	public void i_am_taken_to_the_checkout_page() throws Throwable {

		assertEquals("Pay with Credit Card or Log In", checkOutPage.readHeader());

	}

	@After
	public void tearDown() {
		driver.quit();
	}

}
