package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//div[@class='nav float-end']//i[@class='fa-solid fa-caret-down']")
	@CacheLookup
	private WebElement myAccount;

	@FindBy(xpath = "//a[@class='dropdown-item'][normalize-space()='Register']")
	@CacheLookup
	private WebElement register;

	@FindBy(xpath = "//a[@class='dropdown-item'][normalize-space()='Login']")
	@CacheLookup
	private WebElement login;

	@FindBy(xpath = "//input[@placeholder='Search']")
	@CacheLookup
	private WebElement searchbox;

	@FindBy(xpath = "//i[@class='fa-solid fa-magnifying-glass']")
	@CacheLookup
	private WebElement searchbtn;

	public void clickMyAccount() {
		myAccount.click();
	}

	public void clickRegister() {
		register.click();
	}

	public void clickLogin() {
		login.click();
	}

	public void PassValueToSearchBox(String itemname) {
		searchbox.sendKeys(itemname);
	}

	public void clickSearchButton() {
		searchbtn.click();
	}

}
