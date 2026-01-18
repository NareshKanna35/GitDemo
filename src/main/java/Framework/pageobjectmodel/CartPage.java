package Framework.pageobjectmodel;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Framework.Abstract.Abstractmethods;

public class CartPage extends Abstractmethods {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "div[class='cartSection'] h3")
	List<WebElement> itemsincart;

	@FindBy(xpath = "//button[text()='Checkout']")
	WebElement checkout;

	By spinner = By.cssSelector(".ngx-spinner-overlay");

	public Boolean cartverification(String[] productsreq) {
		List<String> cartitems = Arrays.asList(productsreq);

		for (String item : cartitems) {
			Boolean cartcheck = cartlist().stream().anyMatch(p -> p.getText().equalsIgnoreCase(item));
			if (!cartcheck) {
				return false;
			}
		}
		return true;

	}
	
	public Boolean cartverification(List<String> productsreq) {
		for (String item : productsreq) {
			Boolean cartcheck = cartlist().stream().anyMatch(p -> p.getText().equalsIgnoreCase(item));
			if (!cartcheck) {
				return false;
			}
		}
		return true;

	}

	public List<WebElement> cartlist() {
		return itemsincart;
	}

	public paymentPage checkout() {
		scrollIntoView(checkout);
		invisibilityOfElementLocated(spinner);
		checkout.click();
		paymentPage pay = new paymentPage(driver);
		return pay;
	}

}
