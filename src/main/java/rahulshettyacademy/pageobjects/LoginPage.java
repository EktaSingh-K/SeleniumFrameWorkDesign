package rahulshettyacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class LoginPage extends AbstractComponent{


	WebDriver driver;

	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


   @FindBy(id="userEmail")
   WebElement userEmail;
   
   @FindBy(id="userPassword")
   WebElement userPassword;
   
   @FindBy(id="login")
   WebElement submit;
   
   @FindBy(css = "[class*='flyInOut']")
   WebElement errormessage;
   
   public ProductCatalogue loginApplication(String email, String passwrd) {
	   
	   userEmail.sendKeys(email);
	   userPassword.sendKeys(passwrd);
	   submit.click();
	   ProductCatalogue productcatalogue = new ProductCatalogue(driver);
       return productcatalogue;
   }

   public void url() {
	   
		driver.get("https://rahulshettyacademy.com/client/");

   }
   
   public String getErrorMessage() {
	   waitforWebElementtoAppear(errormessage);
	   return errormessage.getText();
   }
   
   
}

