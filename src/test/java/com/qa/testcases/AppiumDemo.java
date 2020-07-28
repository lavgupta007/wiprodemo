package com.qa.testcases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.pages.LoginPage;
import com.qa.util.TestUtil;

public class AppiumDemo extends TestBase{
	TestUtil testUtil;
	LoginPage loginpage;

	public AppiumDemo() {
		super();
	}

	@BeforeClass
	public void setUp() {
		initialization();
		testUtil = new TestUtil();
		loginpage=new LoginPage();
	}

	@Test
	public void loginIntoApplicationViaEmail(){
		loginpage.clickOnAlreadyaCustomer();
		loginpage.loginViaEmailAddress();
	}

	@Test(dependsOnMethods = "loginIntoApplicationViaEmail")
	public void VerifyDesiredProductDetailsOnCheckoutPage(){
		loginpage.searchDesireProduct();
		loginpage.verifyProductDetails();
	}


	@AfterClass
	public void tearDown(){
		//		driver.quit();
	}
}
