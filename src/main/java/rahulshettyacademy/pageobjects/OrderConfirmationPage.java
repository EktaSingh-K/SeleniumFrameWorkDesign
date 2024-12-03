package rahulshettyacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class OrderConfirmationPage extends AbstractComponent{

	WebDriver driver;
	
	public OrderConfirmationPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = ".hero-primary")
	
	WebElement ConfirmationMessage;
	
	public  String getConfirmationMessage() {
		return ConfirmationMessage.getText();
	}
	
	

}
