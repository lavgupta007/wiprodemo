package com.crm.qa.testcases;

import java.util.Iterator;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.crm.qa.pages.NdtvWeather;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiDemo {
	
	public static float apitempdegree,apitempfahrenheit,apitemphumidity,temp;

	@Test
	public void fetchCityWeatherViaApi(String city) 
	{	
		// Specify the base URL to the RESTful web service
		RestAssured.baseURI ="http://api.openweathermap.org/data/2.5"; 
		RequestSpecification request = RestAssured.given();
		// Query Params
		Response response = request.queryParam("q", "Bengaluru,IN") 
				.queryParam("appid", "7fe67bf08c80ded756e598d6f8fedaea") 
				.get("/weather");

		String jsonString = response.asString();
		// Response.asString method will directly return the content of the body
		// as String
		System.out.println("Response Body is =>  " + response.asString());
		System.out.println("Response Status Code is =>  " + response.getStatusCode());

		// Get JSON Representation from Response Body 
		JsonPath jo = response.jsonPath();

		// Get specific element from JSON document 
		String tempbase = jo.get("base");
		System.out.println(tempbase);

		JsonObject jsonObject = new JsonParser().parse(jsonString).getAsJsonObject();
		String pageName = jsonObject.getAsJsonObject("main").get("temp").getAsString();
		System.out.println(pageName);

		// getting address 
		Map main = ((Map)jo.get("main")); 

		System.out.println("************************************************************************************************");
		System.out.println("API's Main Section Values are below");
		System.out.println("************************************************************************************************");
		// iterating address Map 
		Iterator<Map.Entry> itr1 = main.entrySet().iterator(); 
		while (itr1.hasNext()) 
		{ 
			Map.Entry pair = itr1.next(); 
			System.out.println(pair.getKey() + " : " + pair.getValue()); 
			if(pair.getKey().equals("temp")) {
				temp=Float.parseFloat(pair.getValue().toString());
				apitempdegree=(float) Math.floor(temp - 273.15);
				apitempfahrenheit=(float) Math.floor(((((temp - 273.15) * 9)/5)+32));
			}else if(pair.getKey().equals("humidity")) 
			{
				apitemphumidity=Float.parseFloat((pair.getValue().toString()));
			}
		} 
		System.out.println("************************************************************************************************");
		System.out.println("API's Weather Forcaste Values are below");
		System.out.println("************************************************************************************************");
		System.out.println("apitempdegree => "+apitempdegree);
		System.out.println("apitempfahrenheit => "+apitempfahrenheit);
		System.out.println("apitemphumidity => "+apitemphumidity);
		System.out.println("************************************************************************************************");
	}
	
	public void validateWeatherData() 
	{
		// Performed soft assertion
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(Math.abs((NdtvWeather.tempdegree-apitempdegree))<=10, "Temp in Degrees is not matched, difference found more then 5");
		softAssert.assertTrue(Math.abs((NdtvWeather.tempfahrenheit-apitempfahrenheit))<=10, "Temp in Fahrenheit is not matched, difference found more then 5");
		softAssert.assertTrue(Math.abs((NdtvWeather.temphumidity-apitemphumidity))<=10, "Humidity is not matched, difference found more then 5");
		softAssert.assertAll();
		
	}


}