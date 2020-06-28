package com.crm.qa.pages;

import java.util.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.crm.qa.base.TestBase;
import com.crm.qa.util.TestUtil;

public class NdtvWeather extends TestBase {

	public static double tempdegree,tempfahrenheit,tempdef,temphumidity;
	public static List<String> listbusinesshref = null;
	TestUtil testUtil = new TestUtil();

	// POM with method type

	public static WebElement searchBox(){
		return driver.findElement(By.id("searchBox"));
	}

	public static WebElement searchResultOption(String city){
		return driver.findElement(By.xpath("//label[contains(text(),'"+city+"')]"));
	}

	public static List<WebElement> outerContainer(String city){
		return driver.findElements(By.xpath("//div[@class='outerContainer' and @title='"+city+"']"));
	}

	public static WebElement temperatureContainerDegree(String city){
		return driver.findElement(By.xpath("//div[@class='outerContainer' and @title='"+city+"']/*[@class='temperatureContainer']/span[1]"));
	}

	public static WebElement temperatureContainerFahrenheit(String city){
		return driver.findElement(By.xpath("//div[@class='outerContainer' and @title='"+city+"']/*[@class='temperatureContainer']/span[2]"));
	}



	public static List<WebElement> temperaturepopupwrapper(String city){
		return driver.findElements(By.xpath("//div[@class='leaflet-popup-content-wrapper']//span/b"));
	}

	// Initializing the Page Objects:
	public NdtvWeather() {
		PageFactory.initElements(driver, this);
	}

	public void searchCityAndFetchWeatherDetails(String city)
	{
		if(!(NdtvWeather.outerContainer(city).size()>0)) 
		{
			NdtvWeather.searchBox().sendKeys(city);
			testUtil.wait(2);
			NdtvWeather.searchResultOption(city).click();
			testUtil.wait(2);
		}

		NdtvWeather.temperatureContainerDegree(city).click();
		testUtil.wait(2);
		temphumidity= Integer.valueOf(NdtvWeather.temperaturepopupwrapper(city).get(2).getText().replaceAll("[^\\d]", " ").trim());
		tempdegree=Integer.valueOf(NdtvWeather.temperaturepopupwrapper(city).get(3).getText().replaceAll("[^\\d]", " ").trim());
		tempfahrenheit= Integer.valueOf(NdtvWeather.temperaturepopupwrapper(city).get(4).getText().replaceAll("[^\\d]", " ").trim());
		System.out.println("************************************************************************************************");
		System.out.println("NDTV Website Weather Forcaste Values Are Below");
		System.out.println("************************************************************************************************");
		System.out.println("On Ndtv weather page temp in degrees value is : "+NdtvWeather.tempdegree);
		System.out.println("On Ndtv weather page fahrenheit value is : "+NdtvWeather.tempfahrenheit);
		System.out.println("On Ndtv weather page Humidiy value is : "+NdtvWeather.temphumidity);
		System.out.println("************************************************************************************************");	

	}
}
