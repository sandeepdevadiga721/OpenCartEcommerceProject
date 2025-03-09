package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class LogoutMsgPage extends BasePage {

	public LogoutMsgPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//*[@id='content']/h1")
	@CacheLookup
	private WebElement logoutMsg1;

	@FindBy(xpath = "//*[@id='content']/p[1]")
	@CacheLookup
	private WebElement logoutMsg2;

	@FindBy(xpath = "//*[@id='content']/div/a")
	@CacheLookup
	private WebElement countinuebtn;

	public String getLogoutTextMsg1() {
		try {
			return (logoutMsg1.getText());
		} catch (Exception e) {
			return (e.getMessage());
		}
	}

	public String getLogoutTextMsg2() {
		try {
			return (logoutMsg2.getText());
		} catch (Exception e) {
			return (e.getMessage());
		}
	}

	public void clickcontinuebtn() {
		countinuebtn.click();
	}
}
