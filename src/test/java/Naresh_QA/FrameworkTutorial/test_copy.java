package Naresh_QA.FrameworkTutorial;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Framework.pageobjectmodel.CartPage;
import Framework.pageobjectmodel.LandingPage;
import Framework.pageobjectmodel.ProductPage;
import Framework.pageobjectmodel.paymentPage;

public class test_copy {

	public static void main(String[] args) {

		String[] productsreq = { "iphone 13 pro", "ZARA COAT 3" }; // Required products

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		// Login
		LandingPage LP = new LandingPage(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

		LP.gotopage("https://rahulshettyacademy.com/client");
//		driver.findElement(By.xpath("//input[@id='userEmail']")).sendKeys("nareshkannapgn@gmail.com");
//		driver.findElement(By.cssSelector("#userPassword")).sendKeys("Test@123");
//		driver.findElement(By.cssSelector("#login")).click();
		LP.loginapplication("nareshkannapgn@gmail.com", "Test@123");

		ProductPage PP = new ProductPage(driver);
		List<WebElement> products = PP.productlist();
		// Adding items to cart and navigating to cart page.
//		for (String item : items) {
//			System.out.println(item);
//			List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
////			for (WebElement x : products) {
////				System.out.println(x.findElement(By.xpath(".//div[@class='card-body']//b")).getText());
////			}
//
//			WebElement prod = products.stream().filter(product -> product
//					.findElement(By.xpath(".//div[@class='card-body']//b")).getText().equalsIgnoreCase(item))
//					.findFirst().orElse(null);
//			prod.findElement(By.xpath(".//div[@class='card-body']//button[2]")).click();
//			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ngx-spinner-overlay")));
//		}

//		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ngx-spinner-overlay")));
		PP.addtoCart(productsreq);

//		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		PP.gotocart();

		// Cart Verification
//		List<String> items = Arrays.asList(productsreq);
//		for (String item : items) {
//			List<WebElement> addeditems = driver.findElements(By.cssSelector("div[class='cartSection'] h3"));
//			Boolean cartcheck = addeditems.stream().anyMatch(p -> p.getText().equalsIgnoreCase(item));
//			Assert.assertTrue(cartcheck);
//
//		}
		CartPage CP = new CartPage(driver);
		List<WebElement> cartitems = CP.cartlist();
		Assert.assertTrue(CP.cartverification(productsreq));

		CP.checkout();

//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("window.scrollBy(0,1000)");
//		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Checkout']")));
//		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ngx-spinner-overlay")));
//		driver.findElement(By.xpath("//button[text()='Checkout']")).click();

//		js.executeScript("window.scrollBy(0,1000)");
//		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@fdprocessedid='mbqwn1']")));

//		driver.findElement(By.xpath("//input[@placeholder= 'Select Country']")).sendKeys("ind");
//		wait.until(
//				ExpectedConditions.elementToBeClickable(By.xpath("//button[@type= 'button'] //span[text()=' India']")));
//		driver.findElement(By.xpath("//button[@type= 'button'] //span[text()=' India']")).click();

//		driver.findElement(By.xpath("//a[text()='Place Order ']")).click();

		paymentPage pay = new paymentPage(driver);
		pay.country();
		Assert.assertEquals(pay.placeorder(), "THANKYOU FOR THE ORDER.");
		System.out.println("TEST CASE Passed");
	}

}
