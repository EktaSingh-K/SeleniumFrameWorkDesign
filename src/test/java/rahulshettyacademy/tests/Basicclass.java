package rahulshettyacademy.tests;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Basicclass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriverManager.edgedriver().setup();
		WebDriver driver = new EdgeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client/");
		driver.findElement(By.id("userEmail")).sendKeys("ekta128singh@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Subway@123");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(5));
		driver.findElement(By.xpath("//button[@class='btn w-10 rounded']")).click();

		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		driver.findElement(By.xpath("//li[@class='totalRow']//button")).click();
		Actions act = new Actions(driver);
		act.sendKeys(driver.findElement(By.xpath("//input[@placeholder = 'Select Country']")), "India").build().perform();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
		List<WebElement> list = driver.findElements(By.cssSelector(".ta-results button"));
		List<WebElement> clct = list.stream().filter(a->a.getText().equalsIgnoreCase("India")).collect(Collectors.toList());

		if (!clct.isEmpty()) {
			System.out.println(clct.get(0).getText());

		    clct.get(0).click(); // Click on the first matching element
		} else {
		    System.out.println("Option 'India' not found in the dropdown list.");
		}
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true); arguments[0].click();", 
		                 driver.findElement(By.cssSelector(".btnn.action__submit.ng-star-inserted")));
	}
	

}
