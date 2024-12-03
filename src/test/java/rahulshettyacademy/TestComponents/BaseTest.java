package rahulshettyacademy.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.LoginPage;

public class BaseTest {

	public WebDriver driver;
    public LoginPage login;
	public WebDriver initializeDriver() throws IOException {
		// properties class
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "\\src\\main\\java\\rahulshettyacademy\\resources\\GlobalData.properties");
		prop.load(fis);
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
//		prop.getProperty("browser");

		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("chromedriver.chrome.driver", "chrome.exe");
			driver = new ChromeDriver();
		} else if (browserName.contains("edge")) {
			EdgeOptions options = new EdgeOptions();
			WebDriverManager.edgedriver().setup();
			if(browserName.contains("headless")) {
			options.addArguments("headless");	
			}
			driver = new EdgeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,900)); //full screen

		} else if (browserName.equalsIgnoreCase("firefox")) {

			System.setProperty("webdriver.gecko.driver", "D:\\Drivers\\Firefox\\geckodriver.exe");
			driver = new FirefoxDriver();
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;

	}
	
	public List<HashMap<String,String>> getJsonDatatoMap(String filePath) throws IOException   {
		
		String jsonContentFile =	FileUtils.readFileToString(new File (System.getProperty("user.dir")+"\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json"),
				StandardCharsets.UTF_8);
			
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>>  data = mapper.readValue(jsonContentFile, new TypeReference<List<HashMap<String,String>>>(){});
		return data;
	}
public String getScreenshot(String testCaseName , WebDriver driver) throws IOException {
		
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"//reports//" + testCaseName +".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+ "//reports//" + testCaseName +".png";
	}

    @BeforeMethod(alwaysRun = true)
	public LoginPage launchApplication() throws IOException {

		driver = initializeDriver();
	    login = new LoginPage(driver);
		login.url();
		return login;
	}
//	@AfterMethod(alwaysRun = true)
//	public void closetab() {
//		driver.close();
//	}
	
}
