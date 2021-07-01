package com.iNetBanking.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.iNetBanking.pageObjects.LoginPage;

public class TC_LoginTest_001 extends BaseClass {

	@Test
	public void loginTest() {
		logger.info("URL is opened");
		LoginPage lp = new LoginPage(d);
		lp.setUsername(username);
		logger.info("Entered Username");
		lp.setPassword(password);
		logger.info("Entered password");

		lp.clickSubmit();

		if (d.getTitle().equals("Guru99 Bank Manager HomePage")) {
			Assert.assertTrue(true);
			logger.info("Login test Passed");

		} else {
			Assert.assertTrue(false);
			logger.info("Login test Failed");

		}

	}

}
