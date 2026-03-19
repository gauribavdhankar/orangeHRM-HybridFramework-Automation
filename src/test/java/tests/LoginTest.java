package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;
import factory.DriverFactory;
import pages.DashboardPage;
import pages.LoginPage;
import utils.TestDataProvider;

public class LoginTest extends BaseTest{
	@Test(dataProvider = "loginData",
			dataProviderClass = TestDataProvider.class
			)
	public void VerifyLoginPage(String username, String password) {
		LoginPage login = new LoginPage(DriverFactory.getDriver());
		
		DashboardPage dashboard = login.login(username, password);
		
		Assert.assertTrue(dashboard.isDashboardDisplayed(), 
				"Dashboard is not displayed after login");
		Assert.assertTrue(true);
	}
	
	@Test
	public void LoginTest2() {
		Assert.assertTrue(true);
	}

}
