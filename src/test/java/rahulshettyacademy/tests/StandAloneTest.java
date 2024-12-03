package rahulshettyacademy.tests;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class StandAloneTest {

	@SuppressWarnings("unlikely-arg-type")
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		WebDriverManager.edgedriver().setup();
		WebDriver driver = new EdgeDriver();
		String myproduct = "ADIDAS ORIGINAL";
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client/");
		driver.findElement(By.id("userEmail")).sendKeys("ekta128singh@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Subway@123");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(5));

		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".offset-md-0")));
		
		List<WebElement>  productlist = driver.findElements(By.cssSelector(".offset-sm-1"));
	
		WebElement product = productlist.stream().filter(prod -> prod.findElement(By.cssSelector("b")).getText()
				.equalsIgnoreCase(myproduct)).findFirst().orElse(null);
		
		System.out.println(product.getText());
//		product.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		product.findElement(By.cssSelector("button.btn.w-10.rounded")).click();

//		product.findElement(By.xpath("//div[@class='card-body']//button[@class = 'btn w-10 rounded']")).click();

//		product.findElement(By.xpath("//button[@class = 'btn w-10 rounded']")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));

		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
	
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		
		List<WebElement> prodlist = driver.findElements(By.cssSelector(".cartSection h3"));
		for(WebElement prodlst: prodlist) {
			System.out.println(prodlst.getText());
		}
		Boolean flag = prodlist.stream().anyMatch(a-> a.getText().equalsIgnoreCase(myproduct));
		Assert.assertTrue(flag);
		
		driver.findElement(By.xpath("//li[@class='totalRow']//button")).click();
		Actions act = new Actions(driver);
		act.sendKeys(driver.findElement(By.xpath("//input[@placeholder = 'Select Country']")), "India").build().perform();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
		List<WebElement> list = driver.findElements(By.cssSelector(".ta-results button"));
		System.out.println(list.size());
		
//		List<WebElement> clct = list.stream().filter(a->a.getText()
		List<WebElement> clct = list.stream().filter(a->a.getText().equalsIgnoreCase("India")).collect(Collectors.toList());

		if (!clct.isEmpty()) {
			System.out.println(clct.get(0).getText());

		    clct.get(0).click(); // Click on the first matching element
		} else {
		    System.out.println("Option 'India' not found in the dropdown list.");
		}
	}

}
