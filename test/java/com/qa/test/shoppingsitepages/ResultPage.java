package com.qa.test.shoppingsitepages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ResultPage {

	@FindBy(xpath = "//*[@id=\"center_column\"]/ul/li[1]/div/div[2]/h5/a")
	WebElement firstResultName;

	public String confirmSearch() {
//		(new WebDriverWait(driver,10)).until(ExpectedCondition.visibilityOf());
		return this.firstResultName.getText();
	}

	public void selectFirstResult() {
		firstResultName.click();

	}
}
