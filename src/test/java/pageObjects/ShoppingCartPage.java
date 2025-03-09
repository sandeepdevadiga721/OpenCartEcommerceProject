package pageObjects;

import java.util.NoSuchElementException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class ShoppingCartPage extends BasePage {

	public ShoppingCartPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//span[normalize-space()='Shopping Cart']")
	@CacheLookup
	private WebElement shoppingCart;

	@FindBy(xpath = "/html[1]/body[1]/main[1]/div[2]/div[1]/div[1]/div[1]/div[1]/table[1]/tbody[1]/tr[1]/td[1]/a[1]/img[1]")
	@CacheLookup
	private WebElement Item_imgName;

	@FindBy(xpath = "//td[@class='text-start text-wrap']//a[contains(text(),'iPhone')]")
	@CacheLookup
	private WebElement item_name;

	@FindBy(xpath = "//input[@name='quantity']")
	@CacheLookup
	private WebElement item_quantity;

	@FindBy(xpath = "//*[@id=\"shopping-cart\"]/div/table/tbody/tr/td[5]")
	@CacheLookup
	private WebElement UnitPrice;

	@FindBy(xpath = "//*[@id=\"checkout-total\"]/tr[4]/td[2]")
	@CacheLookup
	private WebElement item_total_price;

	@FindBy(xpath = "//button[@aria-label='Remove']//i[@class='fa-solid fa-circle-xmark']")
	@CacheLookup
	private WebElement removeItem;

	@FindBy(xpath = "//a[@class='btn btn-primary']")
	@CacheLookup
	private WebElement checkoutbtn;

	public void clickShoppingCart() {
		shoppingCart.click();
	}

	public boolean isItemimageExistsInCart() {

		try {
			return Item_imgName.isDisplayed();

		} catch (NoSuchElementException e) {
			System.out.println("Element not found: " + e.getMessage());
			return false;
		}
	}

	public boolean isItemNameExistsInCart() {

		try {
			return item_name.isDisplayed();
		}

		catch (NoSuchElementException e) {
			System.out.println("Element not found: " + e.getMessage());
			return false;
		}
	}

	public String getItemName() {
		return item_name.getText();

	}

	public int getQuantity() {
		return Integer.parseInt(item_quantity.getAttribute("value"));

	}

	public double getUnitPrice() {
		String priceText = UnitPrice.getText().replace("$", "").replace(",", "").trim(); // Assuming price is in dollars
		return Double.parseDouble(priceText);
	}

	public double getTotalPrice() {
		String totalText = item_total_price.getText().replace("$", "").replace(",", "").trim(); // Assuming price is in
																								// dollars
		return Double.parseDouble(totalText);

	}

	public void RemoveItemFromCart() {

		Actions actions = new Actions(driver);
		actions.moveToElement(removeItem).click().perform();

	}

	public void clickCheckoutbtn() {
		actions.moveToElement(checkoutbtn).perform();

		((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkoutbtn);

	}

}
