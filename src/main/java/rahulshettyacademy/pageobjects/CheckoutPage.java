package rahulshettyacademy.pageobjects;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {

	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
	}

	@FindBy(xpath = "//input[@placeholder = 'Select Country']")
	WebElement country;

	@FindBy(css = ".ta-results button")
	List<WebElement> list;
	
	@FindBy(css = ".btnn.action__submit.ng-star-inserted")
	WebElement submit;

//	@FindBy(css = ".action_submit")
//	WebElement submit;

//	@FindBy(xpath = "//a[@class='btnn action__submit ng-star-inserted']")

	By results = By.cssSelector(".ta-results");

	public void SelectCountry(String CountryName) throws InterruptedException {

		Actions act = new Actions(driver);
		act.sendKeys(country, CountryName).build().perform();
		waitforElementtoAppear(results);

		List<WebElement> clct = list.stream().filter(a -> a.getText().equalsIgnoreCase(CountryName))
				.collect(Collectors.toList());

		if (!clct.isEmpty()) {
			System.out.println(clct.get(0).getText());

			clct.get(0).click(); // Click on the first matching element
			Thread.sleep(5);
		} else {
			System.out.println("Option 'India' not found in the dropdown list.");
		}

	}

	public OrderConfirmationPage submit() throws InterruptedException {
		scrollToElement(submit);
		Thread.sleep(5000L);
		submit.click();
		return new OrderConfirmationPage(driver);
	}

}
