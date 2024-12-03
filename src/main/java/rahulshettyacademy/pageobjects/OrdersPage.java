package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.stream.Stream;


import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class OrdersPage extends AbstractComponent {

	WebDriver driver;
	
	@FindBy(css = "tr td:nth-child(3)")
	 List<WebElement> ordersName;
	
	public OrdersPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver =  driver;
		PageFactory.initElements(driver, this);
	}

	public Boolean VerifyOrderisDisplayed(String myproduct) {
		for(WebElement Orderlist: ordersName) {
			System.out.println(Orderlist.getText());
		}
		Boolean flag = ordersName.stream().anyMatch(a-> a.getText().equalsIgnoreCase(myproduct));
		return flag;

	}
	
	
	
}
