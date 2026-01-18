package Naresh_QA.FrameworkTutorial;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.io.Files;

import Framework.pageobjectmodel.CartPage;
import Framework.pageobjectmodel.ProductPage;
import Framework.pageobjectmodel.paymentPage;
import NareshQA.TestComponents.BaseTest;
import NareshQA.TestComponents.retryflaky;

public class LoginErrorTest extends BaseTest {

//	@Test(retryAnalyzer = retryflaky.class)
//	public void loginerrormessage() {
//		LP.loginapplication("nareshkannapgn@gmail.com", "Test123");
//		// *[@id="toast-container"]/div/div
//		Assert.assertEquals("Incorrect email or password", LP.loginerror());
//
//	}
	
	@Test
	public void loginerrormessage() {
		LP.loginapplication("nareshkannapgn@gmail.com", "Test123");
		// *[@id="toast-container"]/div/div
		Assert.assertEquals("Incorrect email or password.", LP.loginerror());

	}


	@Test(groups = { "ErrorHandling" })
	public void loginerrorhandling() throws IOException, InterruptedException {
		LP.loginapplication("nareshkannapgn@gmail.com", "Test123");
		// *[@id="toast-container"]/div/div
		Assert.assertEquals("Incorrect email or password.", LP.loginerror());

		driver.navigate().refresh();
		LP.loginapplication("nareshkannapgn@gmail.com", "Test@123");
		Thread.sleep(2000);
		LP.screenshot();
	}

	@Test
	public void carterrorvalidation() {
		String[] productsreq = { "iphone 13 pro", "ZARA COAT 3" }; // Required products

		// Login
		ProductPage PP = LP.loginapplication("nareshkannapgn@gmail.com", "Test@123");
		List<WebElement> products = PP.productlist();
		PP.addtoCart(productsreq);

		CartPage CP = PP.gotocart();
		List<WebElement> cartitems = CP.cartlist();
		Assert.assertTrue(CP.cartverification(productsreq));
	}

}
