package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import testBase.BaseClass;

public class MyAccountPage extends BasePage {

	public MyAccountPage(WebDriver driver) {
		super(driver);
	}
	
	 JavascriptExecutor js = (JavascriptExecutor) driver;

	@FindBy(xpath = "//h2[normalize-space()='My Account']")
	@CacheLookup // my account page heading
	private WebElement myAccount;

	@FindBy(xpath = "//a[@class='list-group-item'][normalize-space()='Order History']")
	@CacheLookup
	private WebElement orderHistorybtn;

	@FindBy(xpath = "//*[@id='column-right']/div/a[13]")
	@CacheLookup
	private WebElement logoutbtn;

	public boolean isMyAccountPageExists() {
		try {
			return (myAccount.isDisplayed());
		} catch (Exception e) {
			return false;
		}
	}

	public void ClickOrderHistoryPage() {
		orderHistorybtn.click();
	}

	public void ClickLogoutbtn() {
		//actions.moveToElement(logoutbtn).perform();
		BaseClass.scrollIntoView(driver,logoutbtn);
		js.executeScript("arguments[0].click();", logoutbtn);
	}

}
