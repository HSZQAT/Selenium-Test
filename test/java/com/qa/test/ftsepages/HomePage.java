package com.qa.test.ftsepages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

	@FindBy(xpath = "//*[@id=\"cookie-policy-overlay\"]/div/a[2]")
	WebElement closeCookiesButton;

	@FindBy(xpath = "//*[@id=\"view-constituents\"]/ul/li[2]/a")
	WebElement risersTab;

	@FindBy(xpath = "//*[@id=\"view-constituents\"]/ul/li[3]")
	WebElement fallersTab;

	public void closeCookies(WebDriver driver) {

		(new WebDriverWait(driver, 3)).until(ExpectedConditions.elementToBeClickable(closeCookiesButton)).click();
	}

	public void viewRisers() {
		risersTab.click();
	}

	public void viewFallers() {
		fallersTab.click();
	}

}
