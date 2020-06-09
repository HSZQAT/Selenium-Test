package com.qa.test.shoppingsitepages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PaymentPage {

	@FindBy(xpath = "//*[@id=\"HOOK_PAYMENT\"]/div[1]/div/p")
	WebElement payByBankWireButton;

	public void payByBankWire() {
		payByBankWireButton.click();
	}

}
