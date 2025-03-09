package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.ProductPage;
import pageObjects.SearchPage;
import testBase.BaseClass;

public class TC004_AddtoCartTest extends BaseClass {

	@Test(groups="Smoke")
	public void addToCart() throws InterruptedException {
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
	}

}
