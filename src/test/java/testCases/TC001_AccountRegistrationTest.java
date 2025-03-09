package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {

	@Test(groups="Sanity")
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

		Thread.sleep(1000);

		regpage.clickContinuebtn2();
		
		Thread.sleep(1000);
	}

}
