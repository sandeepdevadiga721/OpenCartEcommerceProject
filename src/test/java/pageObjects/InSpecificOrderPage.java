package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import testBase.BaseClass;

public class InSpecificOrderPage extends BasePage {

	public InSpecificOrderPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "/html/body/main/div[2]/div/div/div[1]/div[1]/table/tbody/tr[1]/td[2]")
	private WebElement OrderId;

	@FindBy(xpath = "/html/body/main/div[2]/div/div/div[1]/div[1]/table/tbody/tr[2]/td[2]")
	private WebElement OrderStatus;

	@FindBy(xpath = "/html/body/main/div[2]/div/div/div[1]/div[2]/table/tbody/tr[3]/td[2]")
	private WebElement OrderDate;

	@FindBy(xpath = "/html/body/main/div[2]/div/div/div[2]/table/tfoot/tr[3]/td[3]")
	private WebElement OrderedTotalAmount;

	@FindBy(xpath = "/html/body/main/div[2]/div/div/div[2]/table/tbody/tr/td[3]")
	private WebElement OrderedQuantity;

	@FindBy(xpath = "/html/body/main/div[2]/div/div/div[2]/table/tbody/tr/td[1]")
	private WebElement OrderedProductName;

	@FindBy(xpath = " //*[@id='content']/div[4]/a")
	private WebElement Continuebtn;

	public String getOrderId() {
		return OrderId.getText();

	}

	public String getOrderStatus() {
		return OrderStatus.getText();

	}

	public String getOrderedDate() {

		return OrderDate.getText();
	}

	public String getOrderedTotalAmount() {
		actions.moveToElement(OrderedTotalAmount).perform();
		String totalText = OrderedTotalAmount.getText().replace("$", "").replace(",", "").trim(); // Assuming price is
																									// in dollars
		return totalText;

	}

	public String getOrderedQuantity() {
		return OrderedQuantity.getText();

	}

	public String getOrderedProductName() {
		return OrderedProductName.getText();

	}

	public void clickContinuebtn() {
		//actions.moveToElement(Continuebtn).perform();
		BaseClass.scrollIntoView(driver,Continuebtn);

		((JavascriptExecutor) driver).executeScript("arguments[0].click();", Continuebtn);
	}

}
