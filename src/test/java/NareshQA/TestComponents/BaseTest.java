package NareshQA.TestComponents;

import org.openqa.selenium.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Framework.pageobjectmodel.LandingPage;

public class BaseTest {

	public WebDriver driver;
	public LandingPage LP;

	public WebDriver initializedriver() throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\Framework\\resources\\globaldata.properties");
		prop.load(fis);

		String browser = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("browser");

//		 = prop.getProperty("browser");+		

		if (browser.contains("chrome")) {
			ChromeOptions option = new ChromeOptions();

			if (browser.contains("headless")) {
				 option.addArguments("--headless=new");
	                option.addArguments("--window-size=1920,1080");
	                option.addArguments("--disable-gpu");
	                option.addArguments("--no-sandbox");
	                option.addArguments("--disable-dev-shm-usage");
	                option.addArguments("--disable-extensions");
	                option.addArguments("--disable-popup-blocking");
	                option.addArguments("--remote-allow-origins=*");
			}
			driver = new ChromeDriver(option);
			driver.manage().window().setSize(new Dimension(1920, 1080));

		} else if (browser.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (browser.equals("edge")) {
			driver = new EdgeDriver();
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.manage().window().maximize();
		return driver;

	}

	public List<HashMap<String, Object>> getdatafromjson(String filepath) throws IOException {
		String json = Files.readString(Paths.get(filepath));

		ObjectMapper obj = new ObjectMapper();
		List<HashMap<String, Object>> datajson = obj.readValue(json,
				new TypeReference<List<HashMap<String, Object>>>() {
				});
		return datajson;
	}

	@BeforeMethod(alwaysRun = true)
	public LandingPage launchapplication() throws IOException {
		driver = initializedriver();
		LP = new LandingPage(driver);
		LP.gotopage("https://rahulshettyacademy.com/client");
		return LP;

	}

	@AfterMethod(alwaysRun = true)
	public void exitbrowser() {
		driver.quit();
	}

	public String screenshot(String testcaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File Source = ts.getScreenshotAs(OutputType.FILE);
		Path dest = Paths.get(System.getProperty("user.dir") + "//reports//" + testcaseName + ".png");
		
//		File destfile = dest.toFile();
//		Files.copy(Source.toPath(), dest);
		return System.getProperty("user.dir") + "//reports//" + testcaseName + ".png";
	}

}
