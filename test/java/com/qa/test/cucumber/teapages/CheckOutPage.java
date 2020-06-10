package com.qa.test.cucumber.teapages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckOutPage {

	@FindBy(xpath = "//*[@id=\"wsb-element-00000000-0000-0000-0000-000451989411\"]/div")
	WebElement pageHeader;

	public String readHeader() {
		return pageHeader.getText();
	}

}
