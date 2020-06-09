package com.qa.test.shoppingsitepages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddressPage {

	@FindBy(xpath = "//*[@id=\"center_column\"]/form/p/button")
	WebElement proceedToCheckoutButton;

	public void acceptAddress() {
		proceedToCheckoutButton.click();
	}
}
