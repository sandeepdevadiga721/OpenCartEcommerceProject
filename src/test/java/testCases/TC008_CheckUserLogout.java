package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.LogoutMsgPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC008_CheckUserLogout extends BaseClass {

	@Test(groups="Sanity")
	public void CheckUserLogout() throws InterruptedException {
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
