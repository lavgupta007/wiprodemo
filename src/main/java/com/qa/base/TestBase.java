package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.qa.util.TestUtil;
import com.qa.util.WebEventListener;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

/**
 * @author Lav Gupta
 * @date 20-Jul-2020
 * @description Testbase class for initialization and prerequisite configuration for help to launch the application and
 * all generic methods like scroll, wait, waitForElementPresent etc. 
 */
public class TestBase {

	public static Logger logger = LoggerFactory.getLogger(TestBase.class);
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
		cap.setCapability(MobileCapabilityType.APP, "//Users//apple//Downloads//Amazon_shopping.apk");
		try {
			driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), cap);
			logger.info("*************** Application launched successfully ***************");
			TestUtil.wait(10);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		logger.info("Session is created");
	}


	public void switchToFrame() {
		driver.switchTo().frame("mainpanel");
	}

	public void switchToFrameDemo() {
		//		driver.switchTo().frame(0);
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='demo-frame']")));

	}

	public static void scrollTillBottom() {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		TestUtil.wait(5);
	}

	public static void waitForElementPresent(WebElement we)
	{
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.elementToBeClickable(we));
	}

	public static void wait(int t)  {
		try {
			Thread.sleep(1000*t);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void scrollUp2(MobileElement me) 
	{
		while(true)
		{
			Point point = me.getCenter();
			TouchAction action = new TouchAction(driver);
			System.out.println(point.getX()+" "+point.getY());
			action.press(PointOption.point(point.getX(),point.getY())).waitAction(WaitOptions.waitOptions(Duration.ofMillis(50))).release().perform();
		}

	}

	public static void scrollUp(int loop) 
	{
		int i=0;
		while(i<loop)
		{
			TouchAction action = new TouchAction(driver);
			PointOption p1= new PointOption();
			Dimension dimensions = driver.manage().window().getSize();
			Double screenHeightStart = dimensions.getHeight() * 0.5;
			int h1 = screenHeightStart.intValue();
			Double screenHeightEnd = dimensions.getHeight() * 0.2;
			int h2 = screenHeightEnd.intValue();
			action.press(PointOption.point(0,h1))
			.waitAction(new WaitOptions().withDuration(Duration.ofMillis(600)))
			.moveTo(PointOption.point(0, h2))
			.release()
			.perform();
			i++;
		}
	}







}
