package com.qa.test.shoppingsitepages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {

	@FindBy(id = "search_query_top")
	WebElement searchBar;

	@FindBy(xpath = "//*[@id=\"searchbox\"]/button")
	WebElement searchButton;

	@FindBy(xpath = "//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a")
	WebElement signInButton;

	public void search(String searchTerm) {
		searchBar.sendKeys(searchTerm);
		searchButton.click();
	}

	public void signIn() {
		signInButton.click();
	}

}
