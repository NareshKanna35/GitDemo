package Naresh_QA.FrameworkTutorial;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import Framework.pageobjectmodel.CartPage;
import Framework.pageobjectmodel.ProductPage;
import Framework.pageobjectmodel.orderPage;
import Framework.pageobjectmodel.paymentPage;
import NareshQA.TestComponents.BaseTest;
public class orderpageTest extends BaseTest {
	
	@Test
	public void submitorder1() throws IOException {
		String[] productsreq = { "iphone 13 pro", "ZARA COAT 3" }; // Required products

		// Login
		ProductPage PP = LP.loginapplication("nareshkannapgn@gmail.com", "Test@123");
		List<WebElement> products = PP.productlist();
		PP.addtoCart(productsreq);

		CartPage CP = PP.gotocart();
		List<WebElement> cartitems = CP.cartlist();
		Assert.assertTrue(CP.cartverification(productsreq));

		paymentPage pay = CP.checkout();
		pay.country();
		Assert.assertEquals(pay.placeorder(), "THANKYOU FOR THE ORDER.");
		System.out.println("TEST CASE Passed");
	}

	@Test (dependsOnMethods = {"submitorder1"})
	public void testordershistory() {
		
		String[] productsreq = { "iphone 13 pro", "ZARA COAT 3" };
		
		ProductPage PP = LP.loginapplication("nareshkannapgn@gmail.com", "Test@123");
		
		orderPage op = new orderPage(driver);
		Assert.assertTrue(op.checkorderhistory(productsreq));
		System.out.println("Success");
		
		
	}

}
