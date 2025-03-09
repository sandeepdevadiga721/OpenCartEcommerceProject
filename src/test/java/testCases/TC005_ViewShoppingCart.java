package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.ProductPage;
import pageObjects.SearchPage;
import pageObjects.ShoppingCartPage;
import testBase.BaseClass;

public class TC005_ViewShoppingCart extends BaseClass {

	@Test(groups="Smoke")
	public void ViewCart() throws InterruptedException {

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

		Thread.sleep(1000);

		pg.ClickPopupcloseIcon();

		ShoppingCartPage scp = new ShoppingCartPage(driver);

		scp.clickShoppingCart();

		Thread.sleep(1000);

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

	}
}
