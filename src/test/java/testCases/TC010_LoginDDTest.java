package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC010_LoginDDTest extends BaseClass {
	
	@Test(groups="Datadriven",dataProvider="LoginData",dataProviderClass=DataProviders.class)
	public void Verify_loginDDT(String email, String pwd, String exp) {
		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin();

			LoginPage lp = new LoginPage(driver);
			lp.setEmail(email);
			Thread.sleep(1000);
			lp.setPassword(pwd);
			lp.clickLogin();

			Thread.sleep(1000);

			MyAccountPage macc = new MyAccountPage(driver);
			boolean TargetPage = macc.isMyAccountPageExists();
            
			if(exp.equalsIgnoreCase("Valid"))
			{
				if(TargetPage==true)
				{
					macc.ClickLogoutbtn();
					Thread.sleep(3000);
					Assert.assertTrue(true);
					
				}
				else
				{
					Assert.assertTrue(false);
				}
			}
			
			if(exp.equalsIgnoreCase("Invalid"))
			{
				if(TargetPage==true)
				{
					macc.ClickLogoutbtn();
					Thread.sleep(3000);
					Assert.assertTrue(false);
					
					
				}
				else
				{
					Assert.assertTrue(true);
				}
			}
		
		}
		catch (Exception e) {
			Assert.fail();
		}

	}

}


