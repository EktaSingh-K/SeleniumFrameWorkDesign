package rahulshettyacademy.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.OrderConfirmationPage;
import rahulshettyacademy.pageobjects.OrdersPage;

public class AbstractComponent {

	WebDriver driver;

	@FindBy(xpath = "//button[@routerlink='/dashboard/cart']")
	WebElement carticon;
	
	@FindBy(css = "[routerlink*='/dashboard/myorders']")
	WebElement orders;

	public AbstractComponent(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void waitforElementtoAppear(By findBy) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));

	}

	public void waitforWebElementtoAppear(WebElement findBy) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		wait.until(ExpectedConditions.visibilityOf(findBy));

	}

	public void waitforElementtoDisappear(WebElement spinner) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(spinner));

	}

	public CartPage goToCartPage() {

		carticon.click();
		CartPage cartpage = new CartPage(driver);
		return cartpage;

	}
	public OrdersPage goToOrdersPage() {

		orders.click();
		OrdersPage orderspage = new OrdersPage(driver);
		return orderspage;

	}
	

	public void scrollToElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

}
