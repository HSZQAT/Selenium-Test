package com.qa.test.shoppingsitepages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OrderConfirmationPage {

	@FindBy(xpath = "//*[@id=\"center_column\"]/div/p/strong")
	WebElement orderConfirmed;

	public String getConfirmation() {
		return orderConfirmed.getText();
	}
}
