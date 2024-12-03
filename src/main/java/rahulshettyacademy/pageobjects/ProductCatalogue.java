package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {


	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

   
   @FindBy(css = ".offset-sm-1")
   List<WebElement> products;
   
   @FindBy( css = ".ng-animating")
   WebElement spinner;
   
   By productlists = By.cssSelector(".offset-sm-1");
   By addToCart = By.cssSelector("button.btn.w-10.rounded");
   By toastmessage = By.id("toast-container");
   
   public  List<WebElement> getProductList() {
	   
	   waitforElementtoAppear(productlists);
	   return products;
   }
   
   
   public WebElement getProductByName(String myproduct) {
	   WebElement prod = getProductList().stream().filter(p -> p.findElement(By.cssSelector("b")).getText()
				.equalsIgnoreCase(myproduct)).findFirst().orElse(null);   
	   return prod;
   }

	public void addProductToCart(String myproduct) {
		
		WebElement prod = getProductByName(myproduct);
		prod.findElement(addToCart).click();
		waitforElementtoAppear(toastmessage);
		waitforElementtoDisappear(spinner);
		
		

	}
	
	
}

