package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	JavascriptExecutor js = (JavascriptExecutor) driver;

	@FindBy(xpath = "//input[@id='input-email']")
	@CacheLookup
	private WebElement eMailAddress;

	@FindBy(xpath = "//input[@id='input-password']")
	@CacheLookup
	private WebElement password;

	@FindBy(xpath = "//button[normalize-space()='Login']")
	@CacheLookup
	private WebElement loginbtn;

	public void setEmail(String email) {
		eMailAddress.sendKeys(email);
	}

	public void setPassword(String pwd) {
		password.sendKeys(pwd);
	}

	public void clickLogin() {
		
		js.executeScript("arguments[0].scrollIntoView(true);", loginbtn);
		js.executeScript("arguments[0].click();", loginbtn);
		//loginbtn.click();
	}

}
