package rahulshettyacademy.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.LoginPage;
import rahulshettyacademy.pageobjects.OrderConfirmationPage;
import rahulshettyacademy.pageobjects.OrdersPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {
	String myproduct = "ADIDAS ORIGINAL";

	@Test(dataProvider="getData", groups = {"Purchase"})

	public void submitOrder(HashMap<String , String> input) throws IOException, InterruptedException {

		String CountryName = "India";
		ProductCatalogue productcatalogue = login.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> productlist = productcatalogue.getProductList();
		productcatalogue.addProductToCart(input.get("myproduct"));
		CartPage cartpage = productcatalogue.goToCartPage();
		Boolean flag = cartpage.VerifyProductisDisplayed(input.get("myproduct"));
		Assert.assertTrue(flag);
		CheckoutPage checkoutpage = cartpage.gotoCheckout();
		checkoutpage.SelectCountry(CountryName);
		OrderConfirmationPage confirmationpage = checkoutpage.submit();
		String message = confirmationpage.getConfirmationMessage();
		Assert.assertTrue(message.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

	@Test(dependsOnMethods = {"submitOrder"})
	public void OrderHistoryTest() {
		ProductCatalogue productcatelogue =  login.loginApplication("ekta128singh@gmail.com", "Subway@123");
		OrdersPage myorder = productcatelogue.goToOrdersPage();
		Assert.assertTrue(myorder.VerifyOrderisDisplayed(myproduct));
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
//		HashMap<String , String> map = new HashMap<String,String>();
//		map.put("email", "ekta128singh@gmail.com");
//		map.put("password","Subway@123");
//		map.put("myproduct", "ZARA COAT 3");
//		
//		HashMap<String , String> map1 = new HashMap<String,String>();
//		map1.put("email", "anshika@gmail.com");
//		map1.put("password","Iamking@000");
//		map1.put("myproduct", "ADIDAS ORIGINAL");
		
		List<HashMap<String,String>> data = getJsonDatatoMap(System.getProperty("user.dir")+"\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)} , {data.get(1)}};
		
		
	}
//	@DataProvider
//	public Objegh128@gmail.com","Subway@123","ADIDAS ORIGINAL"},{"anshika@gmail.com","Iamking@000","ADIDAS ORIGINAL"}};
//	ct[][] getData() {
//		return new Object[][] {{"ekta128singh@gmail.com","Subway@123","ZARA COAT 3"}
//		,{"ktsn
//		
//	}

	
}
