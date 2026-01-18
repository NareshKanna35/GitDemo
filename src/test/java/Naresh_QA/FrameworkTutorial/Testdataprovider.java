package Naresh_QA.FrameworkTutorial;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Framework.pageobjectmodel.CartPage;
import Framework.pageobjectmodel.ProductPage;
import Framework.pageobjectmodel.paymentPage;
import NareshQA.TestComponents.BaseTest;

public class Testdataprovider extends BaseTest {

	@Test(dataProvider = "getdata", groups = { "PurchaseTests" })

	public void purchaseflow(String email, String password, String[] productsreq) {

//		String[] productsreq = { "iphone 13 pro", "ZARA COAT 3" }; // Required products

		// Login
		ProductPage PP = LP.loginapplication(email, password);
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

	@DataProvider
	public Object[][] getdata() {
		return new Object[][] {
				{ "nareshkannapgn@gmail.com", "Test@123", new String[] { "iphone 13 pro", "ZARA COAT 3" } },
				{ "nareshkannapgn@gmail.com", "Test@123", new String[] { "ADIDAS original", "ZARA COAT 3" } } };
	}

}
