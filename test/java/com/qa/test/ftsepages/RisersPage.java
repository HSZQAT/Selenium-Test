package com.qa.test.ftsepages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RisersPage {

	@FindBy(xpath = "//*[@id=\"ls-row-AVV-L\"]/td[2]")
	WebElement largestRiserName;

	@FindBy(xpath = "//*[@id=\"tabletNav\"]/ul/li[1]/ul/li[1]/a")
	WebElement ftse100Link;

	public String getLargestRiserName() {
		return largestRiserName.getText();
	}

	public void returnHome() {
		ftse100Link.click();
	}

}
