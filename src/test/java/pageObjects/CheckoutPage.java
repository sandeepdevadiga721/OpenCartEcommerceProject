package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class CheckoutPage extends BasePage {

	public CheckoutPage(WebDriver driver) {
		super(driver);

	}

	@FindBy(xpath = "//input [@value='1']")
	@CacheLookup
	private WebElement iWantToUseAnExistingAddrRadiobtn;

	@FindBy(xpath = "//input [@value='0']")
	@CacheLookup
	private WebElement iWantToUseANewAddressRadiobtn;

	@FindBy(xpath = "//input[@name='firstname']")
	@CacheLookup
	private WebElement firstNametxt;

	@FindBy(xpath = "//input[@name='lastname']")
	@CacheLookup
	private WebElement lastNametxt;

	@FindBy(xpath = "//input[@name='company']")
	@CacheLookup
	private WebElement companynametxt;

	@FindBy(xpath = "//input[@name='address_1']")
	@CacheLookup
	private WebElement address1txt;

	@FindBy(xpath = "//input[@name='address_2']")
	@CacheLookup
	private WebElement address2txt;

	@FindBy(xpath = "//input[@name='city']")
	@CacheLookup
	private WebElement citytxt;

	@FindBy(xpath = "//input[@name='postcode']")
	@CacheLookup
	private WebElement postCodetxt;

	@FindBy(xpath = "//select[@name='country_id']")
	@CacheLookup
	private WebElement countrydropdown;

	@FindBy(xpath = "//select[@name='zone_id']")
	@CacheLookup
	private WebElement regionStatedropdown;

	@FindBy(xpath = "//button[text()='Continue']")
	@CacheLookup
	private WebElement continuebtn;

	@FindBy(xpath = "//select[@id='input-shipping-address']")
	@CacheLookup
	private WebElement addressSelectDropdown;

	@FindBy(xpath = "//button[@id='button-shipping-methods']")
	@CacheLookup
	private WebElement ShoppingMethodchoosebtn;

	@FindBy(xpath = "//input[@value='flat.flat']")
	@CacheLookup
	private WebElement flatShippingRatebtn;

	@FindBy(xpath = "/html/body/div[2]/div/div/div[2]/form/div[2]/button")
	@CacheLookup
	private WebElement continuebtn2;

	@FindBy(xpath = "/html/body/div[1]/div")   ///html/body/div[1]/div/button
	@CacheLookup
	private WebElement closeIconPopup;

	@FindBy(xpath = "/html/body/div[1]/div")
	@CacheLookup
	private WebElement ShippmentMethodSuccessMsg;

	@FindBy(xpath = "//button[@id='button-payment-methods']")
	@CacheLookup
	private WebElement PaymentMethodchoosebtn;

	@FindBy(xpath = "//input[@id='input-payment-method-cod-cod']")
	@CacheLookup
	private WebElement cashOnDeliverybtn;

	@FindBy(xpath = "//button[@id='button-payment-method']")
	@CacheLookup
	private WebElement continuebtn3;

	@FindBy(xpath = "/html/body/div[1]/div")
	@CacheLookup
	private WebElement PaymentMethodSuccessMsg;

	@FindBy(xpath = "//textarea[@id='input-comment']")
	@CacheLookup
	private WebElement addCommentsAboutYourOrder;

	@FindBy(xpath = "/html/body/div[1]/div")
	@CacheLookup
	private WebElement CommentSuccessMsg;

	@FindBy(xpath = "//body[1]/main[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[3]/div[1]/table[1]/tbody[1]/tr[1]/td[1]")
	@CacheLookup
	private WebElement item_Name_quantity;

	@FindBy(xpath = "//*[@id='checkout-confirm']/div[1]/table/tfoot/tr[2]/td[2]")
	@CacheLookup
	private WebElement flat_shipping_rate;

	@FindBy(xpath = "//*[@id='checkout-confirm']/div[1]/table/tbody/tr/td[2]")
	@CacheLookup
	private WebElement total_amount_of_product;

	@FindBy(xpath = "//*[@id='checkout-confirm']/div[1]/table/tfoot/tr[3]/td[2]")
	@CacheLookup
	private WebElement FinalTotalAmountOfOrder;

	@FindBy(xpath = "(//button[normalize-space()='Confirm Order'])[1]")
	@CacheLookup
	private WebElement confirmOrderbtn;

	public void ClickExistingAddressbtn() {
		iWantToUseAnExistingAddrRadiobtn.click();
	}

	public void ClickNewAddressbtn() {
		iWantToUseANewAddressRadiobtn.click();
	}

	public void setFirstNametxt(String fname) {
		firstNametxt.sendKeys(fname);

	}

	public void setLastNametxt(String fname) {
		lastNametxt.sendKeys(fname);

	}

	public void setCompanytxt(String cname) {
		companynametxt.sendKeys(cname);

	}

	public void setAddress1txt(String adress1) {
		address1txt.sendKeys(adress1);

	}

	public void setAddress2txt(String adress2) {
		address2txt.sendKeys(adress2);

	}

	public void setCitytxt(String city) {
		citytxt.sendKeys(city);

	}

	public void setPostcodetxt(String postcode) {
		postCodetxt.sendKeys(postcode);

	}

	// Method to select a country from the drop down
	public void selectCountry(String countryName) {

		if (countrydropdown.isEnabled()) {
			Select countrySelect = new Select(countrydropdown);
			countrySelect.selectByVisibleText(countryName);
		}

		else {
			System.out.println("The dropdown is disabled and cannot be interacted with.");
		}
	}

	// Method to select a region/state from the drop down
	public void selectRegionState(String regionStateName) {
		
		wait.until(ExpectedConditions.elementToBeClickable(regionStatedropdown));
		Select regionStateSelect = new Select(regionStatedropdown);
		regionStateSelect.selectByVisibleText(regionStateName);
	}

	public void ClickCountinueBtn() {
		actions.moveToElement(continuebtn).perform();
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", continuebtn);
	}

	public void selectAddressByValue(String value) {
		Select select = new Select(addressSelectDropdown);
		select.selectByValue(value); // Select by the value attribute of <option> tag
	}

	public void ClickShoppingMethodchoose() {
		ShoppingMethodchoosebtn.click();
	}

	public void ClickflatShippingRateRadio() {
		flatShippingRatebtn.click();
	}

	public void ClickCountinueBtn2() {
		Actions actions = new Actions(driver);
		actions.moveToElement(continuebtn2).perform();
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", continuebtn2);

	}

	public boolean isSuccessShoppingMethodchooseDisplayed() {
		try {
			// Re-locate the element before checking its visibility
			ShippmentMethodSuccessMsg = driver
					.findElement(By.xpath("//div[contains(@class, 'alert alert-success alert-dismissible')]"));
			return ShippmentMethodSuccessMsg.isDisplayed();
		} catch (Exception e) {
			return false; // Return false if any exception occurs
		}
	}

	// Method to get the text of the success message

	public String getSuccessShipmentMethodMessageText() {
		try {
			// Ensure the success message is visible before getting text
			wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//div[contains(@class, 'alert alert-success alert-dismissible')]")));
			ShippmentMethodSuccessMsg = driver
					.findElement(By.xpath("//div[contains(@class, 'alert alert-success alert-dismissible')]")); // Re-locate
																												// element
			return ShippmentMethodSuccessMsg.getText();
		} catch (Exception e) {
			System.err.println("Error getting success message text: " + e.getMessage());
			return null;
		}
	}

	public void ClickcloseIconPopup() {
		WebElement closeIconPopup = null;
		try {
			closeIconPopup = driver.findElement(By.xpath("/html/body/div[1]/div/button"));
			if (closeIconPopup.isDisplayed()) {
				closeIconPopup.click();
			}
		} catch (StaleElementReferenceException e) {
			// If the element is stale, try locating it again
			closeIconPopup = driver.findElement(By.xpath("/html/body/div[1]/div/button"));
			closeIconPopup.click(); // Click again after re-locating
		}

	}

	public void ClickPaymentMethodchoose() {
		PaymentMethodchoosebtn.click();
	}

	public void ClickCashOnDelivery() {
		cashOnDeliverybtn.click();
	}

	public void ClickCountinueBtn3() {
		continuebtn3.click();
	}

	public boolean isSuccessPaymentMethodchooseDisplayed() {
		try {
			// Re-locate the element before checking its visibility
			PaymentMethodSuccessMsg = driver
					.findElement(By.xpath("//div[contains(@class, 'alert alert-success alert-dismissible')]"));
			return PaymentMethodSuccessMsg.isDisplayed();
		} catch (Exception e) {
			return false; // Return false if any exception occurs
		}
	}

	// Method to get the text of the success message

	public String getSuccessPaymentMethodMessageText() {
		try {
			// Ensure the success message is visible before getting text
			wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//div[contains(@class, 'alert alert-success alert-dismissible')]")));
			PaymentMethodSuccessMsg = driver
					.findElement(By.xpath("//div[contains(@class, 'alert alert-success alert-dismissible')]")); // Re-locate
																												// element
			return PaymentMethodSuccessMsg.getText();
		} catch (Exception e) {
			System.err.println("Error getting success message text: " + e.getMessage());
			return null;
		}
	}

	public void setCommentAboutOrder(String comment) {
		addCommentsAboutYourOrder.sendKeys(comment);

	}

	public boolean isSuccessCommentAddedDisplayed() {
		try {
			// Re-locate the element before checking its visibility
			CommentSuccessMsg = driver
					.findElement(By.xpath("//div[contains(@class, 'alert alert-success alert-dismissible')]"));
			return CommentSuccessMsg.isDisplayed();
		} catch (Exception e) {
			return false; // Return false if any exception occurs
		}
	}

	// Method to get the text of the success message

	public String getSuccessCommentAddedMessageText() {
		try {
			// Ensure the success message is visible before getting text
			wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//div[contains(@class, 'alert alert-success alert-dismissible')]")));
			CommentSuccessMsg = driver
					.findElement(By.xpath("//div[contains(@class, 'alert alert-success alert-dismissible')]")); // Re-locate
																												// element
			return CommentSuccessMsg.getText();
		} catch (Exception e) {
			System.err.println("Error getting success message text: " + e.getMessage());
			return null;
		}
	}

	public String getItemNamequantity() {

		return item_Name_quantity.getText();
	}

	public double getTotalAmountOfProduct() {
		String priceText = total_amount_of_product.getText().replace("$", "").replace(",", "").trim(); // Assuming price
																										// is in dollars
		return Double.parseDouble(priceText);
	}

	public double getFlat_shipping_rate() {
		String priceText = flat_shipping_rate.getText().replace("$", "").replace(",", "").trim(); // Assuming price is
																									// in dollars
		return Double.parseDouble(priceText);
	}

	public double getFinalTotalAmountOfOrderPrice() {
		String totalText = FinalTotalAmountOfOrder.getText().replace("$", "").replace(",", "").trim(); // Assuming price
																										// is in dollars
		return Double.parseDouble(totalText);

	}

	public void ClickConfirmOrder() {
		actions.moveToElement(confirmOrderbtn).perform();

		((JavascriptExecutor) driver).executeScript("arguments[0].click();", confirmOrderbtn);
	}

}
