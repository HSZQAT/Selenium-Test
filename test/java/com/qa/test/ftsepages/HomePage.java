package com.qa.test.ftsepages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {

	@FindBy(xpath = "//*[@id=\"cookie-policy-overlay\"]/div/a[2]")
	WebElement closeCookiesButton;

	@FindBy(xpath = "//*[@id=\"view-constituents\"]/ul/li[2]/a")
	WebElement risersTab;

	@FindBy(xpath = "//*[@id=\"view-constituents\"]/ul/li[3]")
	WebElement fallersTab;

	public void closeCookies() {
		closeCookiesButton.click();
	}

	public void viewRisers() {
		risersTab.click();
	}

	public void viewFallers() {
		fallersTab.click();
	}

}
