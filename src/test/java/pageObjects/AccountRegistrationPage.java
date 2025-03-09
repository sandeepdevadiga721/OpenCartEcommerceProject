package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {

	public AccountRegistrationPage(WebDriver driver) {
		super(driver);

	}

	@FindBy(xpath = "//input[@id='input-firstname']")
	@CacheLookup
	private WebElement firstName;

	@FindBy(xpath = "//input[@id='input-lastname']")
	@CacheLookup
	private WebElement lastName;

	@FindBy(xpath = "//input[@id='input-email']")
	@CacheLookup
	private WebElement eMail;

	@FindBy(xpath = "//input[@id='input-password']")
	@CacheLookup
	private WebElement password;

	@FindBy(xpath = "//input[@name='agree']")
	@CacheLookup
	private WebElement agreebtn;

	@FindBy(xpath = "//button[normalize-space()='Continue']")
	@CacheLookup
	private WebElement continuebtn;

	@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
	@CacheLookup
	private WebElement confirmationMsg;

	@FindBy(xpath = "//*[@id='content']/div/a")
	@CacheLookup
	private WebElement continuebtn2;

	public void setFirstName(String fname) {
		firstName.sendKeys(fname);

	}

	public void setLastName(String lname) {
		lastName.sendKeys(lname);

	}

	public void setEmail(String email) {
		eMail.sendKeys(email);
	}

	public void setPassword(String pwd) {
		password.sendKeys(pwd);
	}

	public void clickAgeebtn() {
		// agreebtn.click();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", agreebtn);
	}

	public void clickContinuebtn() {
		// continuebtn.click();
		// continuebtn.submit();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", continuebtn);
	}

	public String getConfirmationMsg() {
		try {
			return (confirmationMsg.getText());
		} catch (Exception e) {
			return (e.getMessage());
		}
	}

	public void clickContinuebtn2() {
		// continuebtn2.click();
		// continuebtn2.submit();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", continuebtn2);
	}

}
