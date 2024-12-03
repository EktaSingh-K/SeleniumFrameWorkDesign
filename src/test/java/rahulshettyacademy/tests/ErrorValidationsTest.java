package rahulshettyacademy.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;
import rahulshettyacademy.TestComponents.Retry;

public class ErrorValidationsTest extends BaseTest{
	
	@Test(groups = {"ErrorHandling"}, retryAnalyzer=Retry.class)
	
	public void LoginErrorValidation() throws IOException, InterruptedException {
		
		login.loginApplication("ekta128singh@gmail.com","Subw.ay@123" );
		Assert.assertEquals("Incorrect email or password.", login.getErrorMessage());
		
	}
	@Test(groups = "ErrorHandling")
	public void ProductErrorValidation() {
		String myproduct = "ADIDAS ORIGINAL"; 
		ProductCatalogue productcatalogue = login.loginApplication("ekta128singh@gmail.com","Subway@123");
		List<WebElement> productlist = productcatalogue.getProductList();
		productcatalogue.addProductToCart(myproduct);
		CartPage cartpage = productcatalogue.goToCartPage();	
		Boolean flag = cartpage.VerifyProductisDisplayed(myproduct);
		Assert.assertTrue(flag);
	
	}
}


