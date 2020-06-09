package com.qa.test.shoppingsitepages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BankWireConfirmationPage {

	@FindBy(xpath = "//*[@id=\"cart_navigation\"]/button")
	WebElement confirmOrderButton;

	public void confirmOrder() {
		confirmOrderButton.click();
	}

}
