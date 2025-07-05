package testCases;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.InSpecificOrderPage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import pageObjects.OrderPage;
import testBase.BaseClass;

public class TC007_CheckingOrderHistory extends BaseClass {

	@Test(groups="Smoke")
	public void CheckOrderHistory() throws InterruptedException {

		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();

		LoginPage lp = new LoginPage(driver);
		lp.setEmail(p.getProperty("TRO_user"));
		lp.setPassword(p.getProperty("pwd1"));
		lp.clickLogin();

		MyAccountPage macc = new MyAccountPage(driver);
		boolean TargetPage = macc.isMyAccountPageExists();

		Assert.assertEquals(TargetPage, true, "Login Failed");

		macc.ClickOrderHistoryPage();

		OrderPage opg = new OrderPage(driver);

		int latestOrderIndex = 0;

		// Retrieve and verify the order details
		String orderID = opg.getOrderID(latestOrderIndex);
		String userName = opg.getUserName(latestOrderIndex);
		String noOfProducts = opg.getNoOfProducts(latestOrderIndex);
		String totalAmount = opg.getTotalAmount(latestOrderIndex);
		String orderStatus = opg.getStatusOfOrder(latestOrderIndex);
		String dateAdded = opg.getDateAdded(latestOrderIndex);

		System.out.println("Order ID: " + orderID);
		System.out.println("User Name: " + userName);
		System.out.println("No of Products: " + noOfProducts);
		System.out.println("Total Amount: " + totalAmount);
		System.out.println("Order Status: " + orderStatus);
		System.out.println("Order date: " + dateAdded);

		Thread.sleep(1000);

		// Click the 'View' link for the latest order
		opg.clickViewLink(latestOrderIndex);

		Thread.sleep(1000);
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

		// Verify the order details page is loaded (you can verify the URL contains the order ID)
		String currentURL = driver.getCurrentUrl();
		Assert.assertTrue(currentURL.contains("order_id=" + orderID.replace("#", "").trim()),
				"Order details page not loaded for Order ID: " + orderID);

		InSpecificOrderPage sopg = new InSpecificOrderPage(driver);

		String final_orderID = sopg.getOrderId();
		String final_orderStatus = sopg.getOrderStatus();

		String finalAmount = sopg.getOrderedTotalAmount();
		// String finalQuantiy=sopg.getOrderedQuantity();
		String ProductName = sopg.getOrderedProductName();
		String final_orderDate = sopg.getOrderedDate();

		System.out.println("Ordered ProductName: " + ProductName);

		Assert.assertEquals(orderID, final_orderID, "Order id is not matching");
		Assert.assertEquals(orderStatus, final_orderStatus, "Ordered status is not matching");
		// Assert.assertEquals(noOfProducts,finalQuantiy,"Ordered quantity is not
		// matching");
		Assert.assertEquals(totalAmount, finalAmount, "Ordered amount is not matching");
		Assert.assertEquals(dateAdded, final_orderDate, "Ordered date is not matching");

		sopg.clickContinuebtn();

		opg.clickContinuebtn();

		Thread.sleep(1000);

	}
}
