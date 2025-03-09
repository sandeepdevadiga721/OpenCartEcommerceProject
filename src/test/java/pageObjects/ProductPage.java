package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends BasePage {

	public ProductPage(WebDriver driver) {
		super(driver);

	}

	@FindBy(xpath = "//input[@id='input-quantity']")
	@CacheLookup
	private WebElement quantity;

	@FindBy(xpath = "//button[@id='button-cart']")
	@CacheLookup
	private WebElement addToCartbtn;

	@FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
	@CacheLookup
	private WebElement AddtoCartSuccessMsg;

	@FindBy(xpath = "//button[@class='btn-close']")
	@CacheLookup
	private WebElement PopupcloseIcon;

	public void clickAddtoCart() {
		actions.moveToElement(addToCartbtn).perform();
		addToCartbtn.click();

	}

	public boolean isSuccessMessageDisplayed() {
		try {

			return AddtoCartSuccessMsg.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	// Method to get the text of the success message

	public String getSuccessMessageText() {
		return AddtoCartSuccessMsg.getText();
	}

	public void ClickPopupcloseIcon() {
		PopupcloseIcon.click();
	}

}
