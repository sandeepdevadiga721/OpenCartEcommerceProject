package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {

	@Test(groups="Sanity")
	public void Verify_login() {
		try {

			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin();

			LoginPage lp = new LoginPage(driver);
			lp.setEmail(p.getProperty("TRO_user"));
			lp.setPassword(p.getProperty("pwd1"));
			lp.clickLogin();
			
			Thread.sleep(1000);

			MyAccountPage macc = new MyAccountPage(driver);
			boolean TargetPage = macc.isMyAccountPageExists();

			// Assert.assertEquals(TargetPage, true,"Login Failed");

			Assert.assertTrue(TargetPage);
		}

		catch (Exception e) {
			Assert.fail();
		}

	}

}
