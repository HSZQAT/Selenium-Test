package com.qa.test.shoppingsitepages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountPage {

	@FindBy(xpath = "//*[@id=\"center_column\"]/p")
	WebElement accountHeader;

	public String getLoginStatus() {
		return accountHeader.getText();
	}

}
