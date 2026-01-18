package Cucumber.StepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Framework.pageobjectmodel.CartPage;
import Framework.pageobjectmodel.LandingPage;
import Framework.pageobjectmodel.ProductPage;
import Framework.pageobjectmodel.paymentPage;
import NareshQA.TestComponents.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class stepdefintionimpl extends BaseTest {

	public LandingPage lp;
	public ProductPage pp;
	public CartPage cp;
	public paymentPage pay;

	@Given("i landed on the ecom page")
	public void ilandedontheecompage() throws IOException {
		lp = launchapplication();
	}

	@Given("^Log in with username (.+) and password (.+)$")
	public void Loginwithusernameandpassword(String name, String password) {
		pp = lp.loginapplication(name, password);
	}

	@When("^I add the products (.+) to cart$")
	public void Iaddtheproductstocart(String productslist) {
		String[] productsreq = productslist.split(",");

		for (int i = 0; i < productsreq.length; i++) {
			productsreq[i] = productsreq[i].trim();
			System.out.println(productsreq[i].toString());
		}

		List<WebElement> products = pp.productlist();
		pp.addtoCart(productsreq);
	}

	@And("^checkout (.+) and submit the order$")
	public void checkoutproductsandsubmittheorder(String productslist) {
		String[] productsreq = productslist.split(",");
		for (int i = 0; i < productsreq.length; i++) {
			productsreq[i] = productsreq[i].trim();
		}

		cp = pp.gotocart();
		List<WebElement> cartitems = cp.cartlist();
		Assert.assertTrue(cp.cartverification(productsreq));
	}

	@Then("{string} is displayed in the confirmation page")
	public void confirmationpagevalidation(String confirm) {
		pay = cp.checkout();
		pay.country();
		Assert.assertEquals(pay.placeorder(), confirm);
		driver.close();
	}
	
	@Then ("{string} message is displayed")
		public void errorcheck(String errormsg) {
		Assert.assertEquals(errormsg, lp.loginerror());
		driver.close();
		
	}

}
