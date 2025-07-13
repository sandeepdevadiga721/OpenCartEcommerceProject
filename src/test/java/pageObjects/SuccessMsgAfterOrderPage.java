package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import testBase.BaseClass;

public class SuccessMsgAfterOrderPage extends BasePage {

	public SuccessMsgAfterOrderPage(WebDriver driver) {
		super(driver);

	}
	JavascriptExecutor js = (JavascriptExecutor) driver;

	@FindBy(xpath = "//h1[normalize-space()='Your order has been placed!']")
	@CacheLookup
	private WebElement OrderPlacedMsg;

	@FindBy(xpath = "//p[normalize-space()='Your order has been successfully processed!']")
	@CacheLookup
	private WebElement OrderSuccessfullMsg;

	@FindBy(xpath = "//a[normalize-space()='history']")
	@CacheLookup
	private WebElement historybtn;

	@FindBy(xpath = "//a[normalize-space()='Continue']")
	@CacheLookup
	private WebElement continuebtn;

	public void ClickCountinueBtn() {
		BaseClass.scrollIntoView(driver,continuebtn);
		js.executeScript("arguments[0].click();", continuebtn);
		//continuebtn.click();
	}

	public String getOrderPlacedMsg() {
		try {
			return (OrderPlacedMsg.getText());
		} catch (Exception e) {
			return (e.getMessage());
		}
	}

	public String getOrderSuccessfullMsg() {
		try {
			return (OrderSuccessfullMsg.getText());
		} catch (Exception e) {
			return (e.getMessage());
		}
	}

	public void ClickHistoryBtn() {
		historybtn.click();
	}

}
