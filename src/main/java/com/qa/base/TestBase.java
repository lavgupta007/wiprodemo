package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.google.common.util.concurrent.Service;
import com.qa.util.TestUtil;
import com.qa.util.WebEventListener;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

/**
 * @author Lav Gupta
 * @date 20-Jul-2020
 * @description Testbase class for initialization and prerequisite configuration for help to launch the application. 
 */
public class TestBase {

	public static Properties prop;
	public  static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	public static String browserName;
	public static AppiumDriver<MobileElement> driver;
	public static String path,username,password,product;
	private static AppiumDriverLocalService service;


	public TestBase(){
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "/src/main/java/com/crm"
					+ "/qa/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}



	public static void initialization()
	{
		HashMap<String, String> capabilities = new HashMap<String, String>();
		capabilities.put("platformName", prop.getProperty("platformName"));
		capabilities.put("automationName", prop.getProperty("automationName"));
		capabilities.put("deviceName", prop.getProperty("deviceName"));
		capabilities.put("platformVersion", prop.getProperty("platformVersion"));
		username=prop.getProperty("username");
		password=prop.getProperty("password");
		product=prop.getProperty("product");
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.0");
		cap.setCapability("appPackage", "com.amazon.mShop.android.shopping");
		cap.setCapability("appActivity", "com.amazon.mShop.home.HomeActivity");
//		cap.setCapability(MobileCapabilityType.NO_RESET, "false");
		cap.setCapability(MobileCapabilityType.APP, "//Users//apple//Downloads//Amazon_shopping.apk");
		try {
			//			service = AppiumDriverLocalService.buildDefaultService();
			//			service.start();
			//			service.stop();
			//			driver = new AndroidDriver<MobileElement>(service.getUrl(), cap);
			driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), cap);
			System.out.println("*************** Application launched successfully ***************");
			TestUtil.wait(10);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		System.out.println("Session is created");
	}









}
