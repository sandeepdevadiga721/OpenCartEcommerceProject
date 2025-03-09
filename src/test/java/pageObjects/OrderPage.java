package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class OrderPage extends BasePage {

	public OrderPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//table[@class='table table-bordered table-hover']")
	private WebElement orderTable;

	@FindBy(xpath = "//table[@class='table table-bordered table-hover']//tbody/tr")
	private List<WebElement> orderRows;

	@FindBy(xpath = ".//td[last()]/a")
	private WebElement viewLink;

	@FindBy(xpath = "/html/body/main/div[2]/div/div/div[3]/a")
	private WebElement Continuebtn;

	// Method to get all rows from the table
	public List<WebElement> getAllRows() {
		return orderRows;
	}

	// Method to get specific cell data (e.g., Order ID from the first row)
	public String getOrderID(int rowIndex) {
		// Find the specific row and the Order ID cell (first column)
		WebElement row = getAllRows().get(rowIndex);
		WebElement orderIDCell = row.findElement(By.xpath("td[1]")); // Get the first column (Order ID)
		return orderIDCell.getText();
	}

	public String getUserName(int rowIndex) {
		WebElement row = getAllRows().get(rowIndex);
		WebElement userNameCell = row.findElement(By.xpath("td[2]")); // Get the second column (User name)
		return userNameCell.getText();
	}

	// Method to get No. of Products from a specific row
	public String getNoOfProducts(int rowIndex) {
		WebElement row = getAllRows().get(rowIndex);
		WebElement noOfProductsCell = row.findElement(By.xpath("td[3]")); // Get the third column (No. of Products)
		return noOfProductsCell.getText();
	}

	// Method to get Status of the Order from a specific row
	public String getStatusOfOrder(int rowIndex) {
		WebElement row = getAllRows().get(rowIndex);
		WebElement statusCell = row.findElement(By.xpath("td[4]")); // Get the fourth column (Status)
		return statusCell.getText();
	}

	// Method to get specific total amount for a row
	public String getTotalAmount(int rowIndex) {
		WebElement row = getAllRows().get(rowIndex);
		WebElement totalAmountCell = row.findElement(By.xpath("td[5]")); // Get the fifth column (Total)
		return (totalAmountCell.getText().replace("$", "").replace(",", "").trim());
	}

	// Method to get Date Added from a specific row
	public String getDateAdded(int rowIndex) {
		WebElement row = getAllRows().get(rowIndex);
		WebElement dateAddedCell = row.findElement(By.xpath("td[6]")); // Get the sixth column (Date Added)
		return dateAddedCell.getText();
	}

	// Method to click the 'View' link in the last column of the row
	public void clickViewLink(int rowIndex) {
		// Find the specific row
		WebElement row = getAllRows().get(rowIndex);

		// Wait for the 'View' link to be click able
		WebElement viewLink = wait
				.until(ExpectedConditions.elementToBeClickable(row.findElement(By.xpath(".//td[last()]/a"))));

		// Click the 'View' link
		viewLink.click();
	}

	public void clickContinuebtn() {
		actions.moveToElement(Continuebtn).perform();

		((JavascriptExecutor) driver).executeScript("arguments[0].click();", Continuebtn);

	}
}
