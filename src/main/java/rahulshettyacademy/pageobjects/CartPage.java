package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {

	WebDriver driver;
	@FindBy(css = ".cartSection h3")
	List<WebElement> prodlist;
	
	@FindBy(xpath = "//li[@class='totalRow']//button")
	WebElement checkouticon;

	public CartPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver =  driver;
	}

	
//	List<WebElement> prodlist = driver.findElements(By.cssSelector(".cartSection h3"));
	
	public Boolean VerifyProductisDisplayed(String myproduct) {
		for(WebElement prodlst: prodlist) {
			System.out.println(prodlst.getText());
		}		
		Boolean flag = prodlist.stream().anyMatch(a-> a.getText().equalsIgnoreCase(myproduct));
		return flag;

	}
	
	public CheckoutPage gotoCheckout() {
		
		checkouticon.click();
		CheckoutPage checkout = new CheckoutPage(driver);
		return checkout;
	}
	
}
