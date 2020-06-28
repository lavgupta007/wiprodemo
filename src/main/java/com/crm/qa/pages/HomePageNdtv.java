package com.crm.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;
import com.crm.qa.util.TestUtil;

public class HomePageNdtv extends TestBase {
	
	public static String topbusinesshref,buttombusinesshref;
	public static List<String> listbusinesshref = null;
	TestUtil testUtil = new TestUtil();
	// POM with method type

	public static List<WebElement> topLevelHrefList(){
		return driver.findElements(By.xpath("//div[@class='topnav_cont']//a"));
	}
	
	public static List<WebElement> buttomLevelHrefList(){
		return driver.findElements(By.xpath("//div[@class='footer_nav']//a"));
	}

	public static WebElement topBusinesslink(){
		return driver.findElement(By.xpath("//a[@class='select'][contains(text(),'Business')]"));
	}
	
	public static WebElement buttomBusinesslink(){
		return driver.findElement(By.xpath("//li[@class='more']//a[contains(text(),'Business')]"));
	}
	
	// Initializing the Page Objects:
	public HomePageNdtv() {
		PageFactory.initElements(driver, this);
	}

	public void printAllHrefTopevelMenu(){
		int i=1;
		testUtil.waitForElementPresent(topBusinesslink());
		HomePageNdtv.topbusinesshref=HomePageNdtv.topBusinesslink().getAttribute("href");
		System.out.println("**************** List of top level menu href's details ****************");
		System.out.println("Total count of top level menu href's are : "+ HomePageNdtv.topLevelHrefList().size());
		System.out.println("************************************************************************************************");
		for(WebElement e: HomePageNdtv.topLevelHrefList())
		{
			System.out.println(i+" HREF Title is : "+e.getAttribute("title")+" and href value is : "+e.getAttribute("href"));
			i++;
		}
		System.out.println("************************************************************************************************");
		TestUtil.wait(3);
	}
	
	
	public void printAllHrefbuttomevelMenu(){
		int i=1;
		testUtil.scrollTillBottom();
		buttombusinesshref=HomePageNdtv.buttomBusinesslink().getAttribute("href");
		System.out.println("**************** List of buttom level menu href's details ****************");
		System.out.println("Total count of buttom level menu href's are : "+ HomePageNdtv.buttomLevelHrefList().size());
		System.out.println("************************************************************************************************");
		for(WebElement e: HomePageNdtv.buttomLevelHrefList())
		{
			System.out.println(i+" HREF Title is : "+e.getAttribute("title")+" and href value is : "+e.getAttribute("href"));
			i++;
		}
		System.out.println("************************************************************************************************");
		TestUtil.wait(3);
	}
	
	public void verifyBusinessHref()
	{
		HomePageNdtv.buttombusinesshref=HomePageNdtv.buttomBusinesslink().getAttribute("href");
		System.out.println("************************************************************************************************");
		System.out.println("Top Business href value is : "+HomePageNdtv.topbusinesshref);
		System.out.println("Top Business href value is : "+HomePageNdtv.buttombusinesshref);
		System.out.println("************************************************************************************************");
	}
	
}
