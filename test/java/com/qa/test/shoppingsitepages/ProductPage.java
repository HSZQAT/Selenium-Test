package com.qa.test.shoppingsitepages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage {

	@FindBy(xpath = "//*[@id=\"add_to_cart\"]/button")
	WebElement addToCartButton;

	@FindBy(xpath = "//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a")
	WebElement proceedToCheckoutButton;

	public void addToCart() {
		addToCartButton.click();
	}

	public void proceedToCheckout() {
		proceedToCheckoutButton.click();
	}

}
