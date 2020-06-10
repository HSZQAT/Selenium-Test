package com.qa.test.cucumber.teapages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MenuPage {

	@FindBy(xpath = "//*[@id=\"wsb-element-00000000-0000-0000-0000-000450914921\"]/div")
	WebElement pageHeader;

	@FindBy(xpath = "//*[@id=\"wsb-button-00000000-0000-0000-0000-000451955160\"]")
	WebElement greenTeaCheckOutButton;

	public String readHeader() {
		return pageHeader.getText();
	}

	public void greenTeaCheckOut() {
		greenTeaCheckOutButton.click();
	}

}
