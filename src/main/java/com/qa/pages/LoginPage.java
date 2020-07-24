package com.qa.pages;
import static java.time.Duration.ofSeconds;

import java.util.List;

import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import com.qa.base.TestBase;
import com.qa.util.TestUtil;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

/**
 * @author Lav Gupta
 * @date 21-Jul-2020
 * @description Page Factory of logging page with methods which are using for specific operations.
 */
public class LoginPage  extends TestBase {

	public String PRODUCT_NAME,PRODUCT_DESCRIPTION;
	public int PRODUCT_PRICE;

	@AndroidFindBy(xpath ="//android.widget.Button[@resource-id='com.amazon.mShop.android.shopping:id/sign_in_button']" )
	AndroidElement alreadyacustomer;

	@AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='ap_email_login']")
	MobileElement emailfield;

	@AndroidFindBy(xpath = "//android.widget.Button[@resource-id='continue']")
	MobileElement continuebutton;

	@AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='ap_password']")
	MobileElement passwordfield;

	@AndroidFindBy(xpath = "//android.widget.Button[@resource-id='signInSubmit']")
	MobileElement signinbutton;

	@AndroidFindBy(id = "com.amazon.mShop.android.shopping:id/rs_search_src_text")
	MobileElement productsearchfield;

	@AndroidFindBy(id = "com.amazon.mShop.android.shopping:id/iss_search_dropdown_item_text_layout")
	MobileElement searchresultoptions;

	@AndroidFindBy(xpath = "//android.view.ViewGroup[@resource-id='com.amazon.mShop.android.shopping:id/rs_results_styled_price_v2']/android.widget.TextView[1]/../../..//android.view.ViewGroup[@resource-id='com.amazon.mShop.android.shopping:id/rs_item_styled_byline']/android.widget.TextView")
	MobileElement productbrandname;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.amazon.mShop.android.shopping:id/item_title']")
	List<MobileElement> productdescriptionall;

	@AndroidFindBy(xpath = "//android.view.ViewGroup[@resource-id='com.amazon.mShop.android.shopping:id/rs_results_styled_price_v2']/android.widget.TextView[1]/../../..//android.widget.TextView[@resource-id='com.amazon.mShop.android.shopping:id/item_title']")
	MobileElement productdescription;

	@AndroidFindBy(xpath = "//android.view.ViewGroup[@resource-id='com.amazon.mShop.android.shopping:id/rs_results_styled_price_v2']/android.widget.TextView[1]")
	MobileElement productprice;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.amazon.mShop.android.shopping:id/rs_results_count_text']")
	MobileElement productresultcount;

	@AndroidFindBy(id = "com.amazon.mShop.android.shopping:id/loc_ux_pin_code_button")
	MobileElement selectpincodebbutton;


	@AndroidFindBy(id = "com.amazon.mShop.android.shopping:id/loc_ux_pin_code_text_pt1")
	MobileElement enterpincodetextfield;


	@AndroidFindBy(id = "com.amazon.mShop.android.shopping:id/loc_ux_update_pin_code")
	MobileElement undatepincodebutton;

	@AndroidFindBy(id = "add-to-cart-button")
	MobileElement addtocartbutton;

	@AndroidFindBy(xpath = "//android.view.View[@resource-id='a-autoid-3']")
	MobileElement nothanksbutton;

	@AndroidFindBy(id = "com.amazon.mShop.android.shopping:id/action_bar_cart")
	MobileElement cartbarlogo;



	@AndroidFindBy(xpath = "//android.view.View[@resource-id='bylineInfo']/android.widget.TextView")
	MobileElement checkoutpagebrandname;

	@AndroidFindBy(xpath = "//android.view.View[@resource-id='title_feature_div']/android.view.View")
	MobileElement checkoutpagedescription;

	@AndroidFindBy(xpath = "//android.view.View[@resource-id='newPitchPriceWrapper_feature_div']/android.view.View/android.widget.TextView[2]")
	MobileElement checkoutpageproductmainprice;
	
	@AndroidFindBy(xpath = "//android.view.View[@resource-id='newAccordionRow']//android.view.View/android.widget.RadioButton")
	MobileElement checkoutpageproductmainprice1;
	
	

	@AndroidFindBy(xpath = "//android.view.View[@resource-id='newPitchPriceWrapper_feature_div']/android.view.View/android.widget.TextView[3]")
	MobileElement checkoutpageproductcentsprice;

	@AndroidFindBy(id = "address-ui-widgets-enterAddressLine1")
	MobileElement addressline1;

	@AndroidFindBy(id = "address-ui-widgets-enterAddressCity")
	MobileElement city;

	@AndroidFindBy(id = "address-ui-widgets-enterAddressStateOrRegion")
	MobileElement state;

	@AndroidFindBy(id = "address-ui-widgets-enterAddressPostalCode")
	MobileElement postalcode;

	@AndroidFindBy(id = "address-ui-widgets-enterAddressPhoneNumber")
	MobileElement phonenumber;

	@AndroidFindBy(id = "address-ui-widgets-form-submit-button")
	MobileElement addaddress;


	// Initializing the Page Objects:
	public LoginPage() {
		//		PageFactory.initElements(driver, this);
		PageFactory.initElements(new AppiumFieldDecorator(driver, ofSeconds(5)), this);
	}

	public void clickOnAlreadyaCustomer(){
		TestUtil.waitForElementPresent(alreadyacustomer);
		alreadyacustomer.click();
		TestUtil.wait(5);
	}

	public void loginViaEmailAddress(){
		TestUtil.waitForElementPresent(emailfield);
		emailfield.sendKeys(username);
		continuebutton.click();
		TestUtil.waitForElementPresent(passwordfield);
		passwordfield.sendKeys(password);
		signinbutton.click();
		TestUtil.wait(5);
	}

	public void searchDesireProduct(){
		int loopcount=0;
		TestUtil.waitForElementPresent(productsearchfield);
		productsearchfield.click();
		TestUtil.wait(3);
		productsearchfield.sendKeys(product);
		TestUtil.waitForElementPresent(searchresultoptions);
		TestUtil.wait(3);
		searchresultoptions.click();
		TestUtil.wait(5);
		TestUtil.waitForElementPresent(productbrandname);
		loopcount=Integer.parseInt(productresultcount.getText().replaceAll("[^\\d.]", "").trim());
		System.out.println("Total results are showing on the page : "+loopcount);
		if(loopcount>100) {
			loopcount=3;

		}else {
			loopcount=2;
		}
		TestUtil.scrollUp(loopcount);
		TestUtil.wait(3);
		System.out.println("*************************************************");
		System.out.println("Selected Product Brand Name is : "+(PRODUCT_NAME=productbrandname.getText().split("\\s+")[1]));
		System.out.println("Selected Product Description is : "+(PRODUCT_DESCRIPTION=productdescription.getText()));
		System.out.println("Selected Product Description is : "+(PRODUCT_PRICE=Integer.parseInt(productprice.getText().replace("$", "").replace(",", "").replace(".", "").split("\\s+")[0].trim())));
		System.out.println("*************************************************");
		//		System.out.println(productdescriptionall.size());
		//		for(MobileElement me:productdescriptionall) {
		//			
		//			System.out.println(me.getText());
		//		}
		productbrandname.click();
		TestUtil.wait(2);
		TestUtil.waitForElementPresent(checkoutpagebrandname);
//		System.out.println(checkoutpagebrandname.getText());
//		System.out.println(checkoutpagedescription.getText());
//		TestUtil.scrollUp(1);
//		System.out.println((checkoutpageproductmainprice.getText()+""+checkoutpageproductcentsprice.getText()).trim());
	}

	public void verifyProductDetails()
	{
		try {
			String CHECKOUT_PRODUCT_NAME,CHECKOUT_PRODUCT_DESCRIPTION;
			int CHECKOUT_PRODUCT_PRICE;
			CHECKOUT_PRODUCT_NAME=checkoutpagebrandname.getText();
			CHECKOUT_PRODUCT_DESCRIPTION=checkoutpagedescription.getText();
			TestUtil.scrollUp(1);
			CHECKOUT_PRODUCT_PRICE=Integer.parseInt((checkoutpageproductmainprice.getText()+""+checkoutpageproductcentsprice.getText()).replace("$", "").replace(",", "").replace(".", "").replace("\\s", "").trim());
			System.out.println(CHECKOUT_PRODUCT_NAME);
			System.out.println(CHECKOUT_PRODUCT_DESCRIPTION);
			System.out.println(CHECKOUT_PRODUCT_PRICE);
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(CHECKOUT_PRODUCT_NAME, PRODUCT_NAME, "Desired Product Name Not Mached With Excepted Result");
			softAssert.assertEquals(CHECKOUT_PRODUCT_DESCRIPTION, PRODUCT_DESCRIPTION, "Desired Product Description Not Mached With Excepted Result");
			softAssert.assertEquals(CHECKOUT_PRODUCT_PRICE, PRODUCT_PRICE, "Desired Product Price Not Mached With Excepted Result");
			softAssert.assertAll();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println();
		}
		


	}
}

