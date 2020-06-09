package com.qa.test.ftsepages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FallersPage {

	@FindBy(xpath = "//*[@id=\"ls-row-MGGT-L\"]/td[2]")
	WebElement largestFallerName;

	@FindBy(xpath = "//*[@id=\"tabletNav\"]/ul/li[1]/ul/li[1]/a")
	WebElement ftse100Link;

	public String getLargestFallerName() {
		return largestFallerName.getText();
	}

	public void returnHome() {
		ftse100Link.click();
	}
}
