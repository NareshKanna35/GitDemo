package Naresh_QA.FrameworkTutorial;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Framework.pageobjectmodel.CartPage;
import Framework.pageobjectmodel.ProductPage;
import Framework.pageobjectmodel.paymentPage;
import NareshQA.TestComponents.BaseTest;

public class HashMapdataProvider extends BaseTest {

	@Test(dataProvider = "getdata", groups = { "PurchaseTests" })

	public void purchaseflow(HashMap<String, Object> data1) {

//		String[] productsreq = { "iphone 13 pro", "ZARA COAT 3" }; // Required products

		// Login
		ProductPage PP = LP.loginapplication((String)data1.get("email"), (String)data1.get("password"));
		List<WebElement> products = PP.productlist();
		PP.addtoCart((String[])data1.get("products"));

		CartPage CP = PP.gotocart();
		List<WebElement> cartitems = CP.cartlist();
		Assert.assertTrue(CP.cartverification((String[])data1.get("products")));

		paymentPage pay = CP.checkout();
		pay.country();
		Assert.assertEquals(pay.placeorder(), "THANKYOU FOR THE ORDER.");
		System.out.println("TEST CASE Passed");

	}

	@DataProvider
	public Object[][] getdata() {

		HashMap<String, Object> data1 = new HashMap<String, Object>();
		data1.put("email", "nareshkannapgn@gmail.com");
		data1.put("password", "Test@123");
		data1.put("products", new String[] { "ADIDAS original", "ZARA COAT 3" });
		
		return new Object[][] {{data1}};

//		return new Object[][] {
//				{ "nareshkannapgn@gmail.com", "Test@123", new String[] { "iphone 13 pro", "ZARA COAT 3" } },
//				{ "nareshkannapgn@gmail.com", "Test@123", new String[] { "ADIDAS original", "ZARA COAT 3" } } };
	}

}
