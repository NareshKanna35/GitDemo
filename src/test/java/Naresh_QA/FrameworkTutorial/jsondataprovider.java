package Naresh_QA.FrameworkTutorial;

import java.io.IOException;
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

public class jsondataprovider extends BaseTest {

	@Test(dataProvider = "getdata", groups = { "PurchaseTests" })

	public void purchaseflow(HashMap<String, Object> datajsondata1) {

//		String[] productsreq = { "iphone 13 pro", "ZARA COAT 3" }; // Required products

		// Login
		ProductPage PP = LP.loginapplication((String) datajsondata1.get("email"),
				(String) datajsondata1.get("password"));

		List<WebElement> products = PP.productlist();
		@SuppressWarnings("unchecked")
		List<String> productsReq = (List<String>) datajsondata1.get("products");
		PP.addtoCart(productsReq);

		CartPage CP = PP.gotocart();
		List<WebElement> cartitems = CP.cartlist();
		Assert.assertTrue(CP.cartverification((String[]) datajsondata1.get("products")));

		paymentPage pay = CP.checkout();
		pay.country();
		Assert.assertEquals(pay.placeorder(), "THANKYOU FOR THE ORDER.");
		System.out.println("TEST CASE Passed");

	}

	@DataProvider
	public Object[][] getdata() throws IOException {

		List<HashMap<String, Object>> datajson = getdatafromjson(
				System.getProperty("user.dir") + "\\src\\main\\java\\Framework\\resources\\testdata.json");

		return new Object[][] { { datajson.get(0) }, { datajson.get(1) } };

//		method 2: HashMap
//		HashMap<String, Object> data1 = new HashMap<String, Object>();
//		data1.put("email", "nareshkannapgn@gmail.com");
//		data1.put("password", "Test@123");
//		data1.put("products", new String[] { "ADIDAS original", "ZARA COAT 3" });
//		
//		return new Object[][] {{data1}};

//		method 1: 
//		return new Object[][] {
//				{ "nareshkannapgn@gmail.com", "Test@123", new String[] { "iphone 13 pro", "ZARA COAT 3" } },
//				{ "nareshkannapgn@gmail.com", "Test@123", new String[] { "ADIDAS original", "ZARA COAT 3" } } };
	}

}
