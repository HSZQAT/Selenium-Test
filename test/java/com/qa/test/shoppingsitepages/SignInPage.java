package com.qa.test.shoppingsitepages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignInPage {

	@FindBy(xpath = "//*[@id=\"email\"]")
	WebElement emailInput;

	@FindBy(xpath = "//*[@id=\"passwd\"]")
	WebElement passwordInput;

	@FindBy(xpath = "//*[@id=\"SubmitLogin\"]")
	WebElement signInButton;

	@FindBy(xpath = "//*[@id=\"email_create\"]")
	WebElement newEmailInput;

	@FindBy(xpath = "//*[@id=\"SubmitCreate\"]")
	WebElement createAccountButton;

	public void signIn(String email, String password) {
		emailInput.sendKeys(email);
		passwordInput.sendKeys(password);
		signInButton.click();
	}

	public void createAccount(String email) {
		newEmailInput.sendKeys(email);
		createAccountButton.click();
	}
}
