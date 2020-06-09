package com.qa.test.shoppingsitepages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShippingPage {

	@FindBy(xpath = "//*[@id=\"cgv\"]")
	WebElement tOSButton;

	@FindBy(xpath = "//*[@id=\"form\"]/p/button")
	WebElement proceedToCheckoutButton;

	public void acceptShipping() {
		tOSButton.click();
		proceedToCheckoutButton.click();
	}

}
