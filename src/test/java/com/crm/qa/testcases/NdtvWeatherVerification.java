package com.crm.qa.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.NdtvWeather;
import com.crm.qa.util.TestUtil;

public class NdtvWeatherVerification extends TestBase {

	TestUtil testUtil;
	NdtvWeather ndtvweather;
	ApiDemo apidemo;
	String classname;

	public NdtvWeatherVerification() {
		super();
	}

	//test cases should be separated -- independent with each other
	//before each test case -- launch the browser and login
	//@test -- execute test case
	//after each test case -- close the browser

	@BeforeMethod
	public void setUp() {
		classname=this.getClass().getName();
		initialization(classname);
		testUtil = new TestUtil();
		ndtvweather=new NdtvWeather();
		apidemo=new ApiDemo();
	}

	@Test(priority=1)
	public void verifyWeatherDefaultCityOnPage(){
		ndtvweather.searchCityAndFetchWeatherDetails("Bengaluru");
		apidemo.fetchCityWeatherViaApi("Bengaluru");
		apidemo.validateWeatherData();
}
	
	@Test(priority=2)
	public void verifyWeatherSearchByCityOnPage(){
		ndtvweather.searchCityAndFetchWeatherDetails("Aligarh");
		apidemo.fetchCityWeatherViaApi("Aligarh");
		apidemo.validateWeatherData();
}
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}

}
