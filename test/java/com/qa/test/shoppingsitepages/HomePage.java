package com.qa.test.shoppingsitepages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {

	@FindBy(id = "search_query_top")
	WebElement searchBar;

	@FindBy(xpath = "//*[@id=\"searchbox\"]/button")
	WebElement searchButton;

	public void search(String searchTerm) {
		searchBar.sendKeys(searchTerm);
		searchButton.click();
	}

}
