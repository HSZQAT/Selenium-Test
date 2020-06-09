package com.qa.test.shoppingsitepages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage {

	@FindBy(xpath = "//*[@id=\"center_column\"]/p[2]/a[1]")
	WebElement proceedToCheckoutButton;

	public void proceedToCheckout() {
		proceedToCheckoutButton.click();
	}

}
