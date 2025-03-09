package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.CheckoutPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.ProductPage;
import pageObjects.SearchPage;
import pageObjects.ShoppingCartPage;
import pageObjects.SuccessMsgAfterOrderPage;
import testBase.BaseClass;

public class TC006_CheckOutProcess extends BaseClass {

	@Test(groups="Regression")
	public void CheckOutProductInCart() throws InterruptedException {

		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();

		LoginPage lp = new LoginPage(driver);
		lp.setEmail(p.getProperty("TRO_user"));
		lp.setPassword(p.getProperty("pwd1"));
		lp.clickLogin();

		Thread.sleep(1000);

		hp.PassValueToSearchBox(p.getProperty("searchproductname"));
		hp.clickSearchButton();

		SearchPage sp = new SearchPage(driver);

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

		scp.clickCheckoutbtn();

		Thread.sleep(1000);

		CheckoutPage cpg = new CheckoutPage(driver);

		cpg.selectAddressByValue("2");

		Thread.sleep(1000);

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

		// Thread.sleep(1000);
		String expectedMessageInPatmentMethod = "Success: You have changed payment method!";
		String actualMessageInPatmentMethod = cpg.getSuccessPaymentMethodMessageText();
		Assert.assertEquals(actualMessageInPatmentMethod, expectedMessageInPatmentMethod,
				"Success message text does not match!");

		cpg.ClickcloseIconPopup();

		cpg.setCommentAboutOrder("Iam Ordered" + " " + p.getProperty("searchproductname") + " " + "Product .");

		Thread.sleep(1000);
		String expectedMessageCommentAboutOrder = "Success: Comment added!";
		String actualMessageCommentAboutOrder = cpg.getSuccessPaymentMethodMessageText();
		Assert.assertEquals(actualMessageCommentAboutOrder, expectedMessageCommentAboutOrder,
				"Success message text does not match!");

		cpg.ClickcloseIconPopup();

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

		Thread.sleep(2000);

		SuccessMsgAfterOrderPage spg = new SuccessMsgAfterOrderPage(driver);

		String OrderPlacedMsg = spg.getOrderPlacedMsg();

		String getOrderSuccessfullMsg = spg.getOrderSuccessfullMsg();

		Assert.assertEquals(OrderPlacedMsg, "Your order has been placed!");

		Assert.assertEquals(getOrderSuccessfullMsg, "Your order has been successfully processed!");

		spg.ClickCountinueBtn();

	}

}
