package rahulshettyacademy.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.LoginPage;
import rahulshettyacademy.pageobjects.OrderConfirmationPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class StepDefinitionImpl extends BaseTest{
	
    public LoginPage loginpage;
    public ProductCatalogue productcatalogue;
    public OrderConfirmationPage confirmationpage;
    
	@Given("I landed on Ecommerce Page")
    public void I_landed_on_Ecommerce_Page() throws IOException
    {
		loginpage = launchApplication();
    }

    @Given("^Logged in with username (.+) and password (.+)$")
    public void logged_in_username_and_password(String username , String password)
    {
		productcatalogue = login.loginApplication(username,password);

    }

    
    @When("^I add product (.+) to Cart$")
    public void i_add_product_to_Cart(String productName)
    {
    	List<WebElement> productlist = productcatalogue.getProductList();
		productcatalogue.addProductToCart(productName);
    	
    }
    
    @When("^Checkout (.+) and submit the Order$")
    public void checkout_submit_order(String productName) throws InterruptedException
    {
    	CartPage cartpage = productcatalogue.goToCartPage();
		Boolean flag = cartpage.VerifyProductisDisplayed(productName);
		Assert.assertTrue(flag);
		CheckoutPage checkoutpage = cartpage.gotoCheckout();
		checkoutpage.SelectCountry("india");
		confirmationpage = checkoutpage.submit();
    }

    @Then("{string} message is displayed on ConfirmationPage")
    public void message_is_displayed_on_ConfirmationPage(String string)
    {
    	String message = confirmationpage.getConfirmationMessage();
		Assert.assertTrue(message.equalsIgnoreCase(string));
	    driver.close();    
	    }
    
    @Then("{string} message is displayed")
    public void error_message_is_displayed_(String string)
    {
		Assert.assertEquals(string, login.getErrorMessage());
	    }
}
