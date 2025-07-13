package testCases;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.CheckoutPage;
import pageObjects.HomePage;
import pageObjects.InSpecificOrderPage;
import pageObjects.LogoutMsgPage;
import pageObjects.MyAccountPage;
import pageObjects.OrderPage;
import pageObjects.ProductPage;
import pageObjects.SearchPage;
import pageObjects.ShoppingCartPage;
import pageObjects.SuccessMsgAfterOrderPage;
import testBase.BaseClass;

public class TC009_OrderProduct_EndToEnd_testcase extends BaseClass {

	@Test(groups="Regression")
	public void verify_account_registration() throws InterruptedException {
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickRegister();

		AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
		String Randomfirstname = generateFirstName();
		String Randomlastname = generateLastName();
		regpage.setFirstName(Randomfirstname);
		regpage.setLastName(Randomlastname);
		regpage.setEmail(generateEmail(Randomfirstname, Randomlastname));
		regpage.setPassword(generatePassword(Randomfirstname));

		regpage.clickAgeebtn();
		regpage.clickContinuebtn();

		String confirmmmsg = regpage.getConfirmationMsg();

		Assert.assertEquals(confirmmmsg, "Your Account Has Been Created!");

		regpage.clickContinuebtn2();

		MyAccountPage macc = new MyAccountPage(driver);
		boolean TargetPage = macc.isMyAccountPageExists();

		Assert.assertEquals(TargetPage, true, "Login Failed");

		hp.PassValueToSearchBox(p.getProperty("searchproductname"));
		hp.clickSearchButton();

		SearchPage sp = new SearchPage(driver);

		Assert.assertTrue(sp.isItemimageExists(), "iPhone image is not displayed on the search page");

		sp.clickIphoneItem();

		ProductPage pg = new ProductPage(driver);
		pg.clickAddtoCart();

		// Verify the success message is displayed
		Assert.assertTrue(pg.isSuccessMessageDisplayed(), "Success message is not displayed!");

		// Verify the success message text
		String expectedMessage = "Success: You have added iPhone to your shopping cart!";
		String actualMessage = pg.getSuccessMessageText();
		Assert.assertEquals(actualMessage, expectedMessage, "Success message text does not match!");

		Thread.sleep(1000);

		pg.ClickPopupcloseIcon();

		ShoppingCartPage scp = new ShoppingCartPage(driver);

		scp.clickShoppingCart();

		Thread.sleep(1000);

		// Get the item Name of in the cart
		String ItemName = scp.getItemName();

		// Get the quantity of iPhone in the cart
		int quantity = scp.getQuantity();

		// Get the unit price of iPhone
		double unitPrice = scp.getUnitPrice();

		// Calculate the expected total
		double expectedTotal = quantity * unitPrice;

		// Get the actual total price from the cart
		double actualTotal = scp.getTotalPrice();
		double tolerance = 0.0001; // Set a small tolerance for floating-point comparison

		Assert.assertEquals(actualTotal, expectedTotal, tolerance,
				"Total price in the cart does not match the expected value.");

		Assert.assertTrue(scp.isItemimageExistsInCart(), "iPhone image is not displayed on the Cart page");

		Assert.assertTrue(scp.isItemNameExistsInCart(), "iPhone text name is not displayed on the Cart page");
		
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

		scp.clickCheckoutbtn();

		Thread.sleep(1000);

		CheckoutPage cpg = new CheckoutPage(driver);

		cpg.setFirstNametxt(Randomfirstname);

		cpg.setLastNametxt(Randomlastname);

		cpg.setCompanytxt("TCS");

		cpg.setAddress1txt("Sirsi");

		cpg.setAddress2txt("Uk");

		cpg.setCitytxt("Sirsi");

		cpg.setPostcodetxt("581402");

		cpg.selectCountry("India");

		cpg.selectRegionState("Karnataka");

		cpg.ClickCountinueBtn();

		Thread.sleep(2000);
		
		cpg.ClickcloseIconPopup();

		cpg.ClickShoppingMethodchoose();

		cpg.ClickflatShippingRateRadio();

		cpg.ClickCountinueBtn2();

		Thread.sleep(1000);

		String expectedMessageInShipmentMethod = "Success: You have changed shipping method!";
		String actualMessageInShipmentMethod = cpg.getSuccessShipmentMethodMessageText();
		Assert.assertEquals(actualMessageInShipmentMethod, expectedMessageInShipmentMethod,
				"Success message text does not match!");

		cpg.ClickcloseIconPopup();

		cpg.ClickPaymentMethodchoose();
		cpg.ClickCashOnDelivery();
		cpg.ClickCountinueBtn3();

		Thread.sleep(1000);

		String expectedMessageInPatmentMethod = "Success: You have changed payment method!";
		String actualMessageInPatmentMethod = cpg.getSuccessPaymentMethodMessageText();
		Assert.assertEquals(actualMessageInPatmentMethod, expectedMessageInPatmentMethod,
				"Success message text does not match!");

		cpg.ClickcloseIconPopup();
		
		Thread.sleep(1000);

		cpg.setCommentAboutOrder("Iam Ordered" + " " + p.getProperty("searchproductname") + " " + "Product .");

		Thread.sleep(1000);

		String expectedMessageCommentAboutOrder = "Success: Comment added!";
		String actualMessageCommentAboutOrder = cpg.getSuccessPaymentMethodMessageText();
		Assert.assertEquals(actualMessageCommentAboutOrder, expectedMessageCommentAboutOrder,
				"Success message text does not match!");

		cpg.ClickcloseIconPopup();
		
		Thread.sleep(1000);

		String Item_Name_quantity = cpg.getItemNamequantity();

		Object[] productInfo = extractQuantityAndProductName(Item_Name_quantity);

		int OrderedQuantity = (int) productInfo[0];
		String ProductName = (String) productInfo[1];

		Assert.assertEquals(OrderedQuantity, quantity,
				"Total quantity in the checkout page does not match the expected value.");
		Assert.assertEquals(ProductName, ItemName, "ItemName in the checkout Page does not match the expected value.");

		// Calculate the Final total of the items

		double ExpectedFinalTotalAmountOfOrder = cpg.getTotalAmountOfProduct() + cpg.getFlat_shipping_rate();

		// get the actual Final total of the items

		double actualFinalTotalAmountOfOrder = cpg.getFinalTotalAmountOfOrderPrice();
		Assert.assertEquals(actualFinalTotalAmountOfOrder, ExpectedFinalTotalAmountOfOrder, tolerance,
				"Total price of the Item does not match the expected value.");

		cpg.ClickConfirmOrder();

		Thread.sleep(1000);

		SuccessMsgAfterOrderPage spg = new SuccessMsgAfterOrderPage(driver);

		String OrderPlacedMsg = spg.getOrderPlacedMsg();

		String getOrderSuccessfullMsg = spg.getOrderSuccessfullMsg();

		Assert.assertEquals(OrderPlacedMsg, "Your order has been placed!");

		Assert.assertEquals(getOrderSuccessfullMsg, "Your order has been successfully processed!");

		spg.ClickHistoryBtn();

		Thread.sleep(1000);

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
		
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

		// Verify the order details page is loaded (you can verify the URL contains the
		// order ID)
		String currentURL = driver.getCurrentUrl();
		Assert.assertTrue(currentURL.contains("order_id=" + orderID.replace("#", "").trim()),
				"Order details page not loaded for Order ID: " + orderID);

		InSpecificOrderPage sopg = new InSpecificOrderPage(driver);

		String final_orderID = sopg.getOrderId();
		String final_orderStatus = sopg.getOrderStatus();

		String finalAmount = sopg.getOrderedTotalAmount();
		int finalQuantiy = Integer.parseInt(sopg.getOrderedQuantity());
		String final_orderDate = sopg.getOrderedDate();

		System.out.println("Ordered ProductName: " + ProductName);

		Assert.assertEquals(orderID, final_orderID, "Order id is not matching");
		Assert.assertEquals(orderStatus, final_orderStatus, "Ordered status is not matching");
		Assert.assertEquals(OrderedQuantity, finalQuantiy, "Ordered quantity is not matching");
		Assert.assertEquals(totalAmount, finalAmount, "Ordered amount is not matching");
		Assert.assertEquals(dateAdded, final_orderDate, "Ordered date is not matching");

	    
	    
		sopg.clickContinuebtn();

		opg.clickContinuebtn();

		Thread.sleep(1000);
		
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

		macc.ClickLogoutbtn();

		LogoutMsgPage lpg = new LogoutMsgPage(driver);
		String LogoutSucessfullTextMsg1 = lpg.getLogoutTextMsg1();
		String LogoutSucessfullTextMsg2 = lpg.getLogoutTextMsg2();

		Assert.assertEquals(LogoutSucessfullTextMsg1, "Account Logout");

		Assert.assertEquals(LogoutSucessfullTextMsg2,
				"You have been logged off your account. It is now safe to leave the computer.");

		lpg.clickcontinuebtn();

		Thread.sleep(1000);

	}

}