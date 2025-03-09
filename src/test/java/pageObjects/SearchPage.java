package pageObjects;

import java.util.NoSuchElementException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SearchPage extends BasePage {

	public SearchPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//div[@class='image']//img[@title='iPhone']")
	@CacheLookup
	private WebElement item_img;

	@FindBy(xpath = "//a[contains(text(),'iPhone')]")
	@CacheLookup
	private WebElement item_name;

	public boolean isItemimageExists() {

		try {
			wait.until(ExpectedConditions.visibilityOf(item_img));
			return item_img.isDisplayed();

		} catch (NoSuchElementException e) {
			System.out.println("Element not found: " + e.getMessage());
			return false;
		}
	}

	public void clickIphoneItem() {

		wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState")
				.equals("complete"));
		try {
			wait.until(ExpectedConditions.visibilityOf(item_img));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", item_img);
		} catch (NoSuchElementException e) {
			System.out.println("Element not found: " + e.getMessage());
		}

	}

}
