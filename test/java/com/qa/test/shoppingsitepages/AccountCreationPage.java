package com.qa.test.shoppingsitepages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountCreationPage {

	@FindBy(xpath = "//*[@id=\"customer_firstname\"]")
	WebElement firstNameInput;

	@FindBy(xpath = "//*[@id=\"customer_lastname\"]")
	WebElement lastNameInput;

	@FindBy(xpath = "//*[@id=\"passwd\"]")
	WebElement passwordInput;

	@FindBy(xpath = "//*[@id=\"address1\"]")
	WebElement addressInput;

	@FindBy(xpath = "//*[@id=\"city\"]")
	WebElement cityInput;

	@FindBy(xpath = "//*[@id=\"id_state\"]")
	WebElement stateInput;

	@FindBy(xpath = "//*[@id=\"postcode\"]")
	WebElement postcodeInput;

	@FindBy(xpath = "//*[@id=\"phone_mobile\"]")
	WebElement mobileInput;

	@FindBy(xpath = "//*[@id=\"submitAccount\"]")
	WebElement registerButton;

	public void createNewAccount(String firstname, String lastname, String password, String address, String city,
			String state, String postcode, String mobile) {
		firstNameInput.sendKeys(firstname);
		lastNameInput.sendKeys(lastname);
		passwordInput.sendKeys(password);
		addressInput.sendKeys(address);
		cityInput.sendKeys(city);
		stateInput.sendKeys(state);
		postcodeInput.sendKeys(postcode);
		mobileInput.sendKeys(mobile);
		registerButton.click();
	}

}
