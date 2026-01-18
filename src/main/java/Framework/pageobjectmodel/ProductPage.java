package Framework.pageobjectmodel;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Framework.Abstract.Abstractmethods;

public class ProductPage extends Abstractmethods {

	WebDriver driver;

	// Constructor
	public ProductPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".mb-3")
	List<WebElement> products;

	By addtocartbtn = By.xpath(".//div[@class='card-body']//button[2]");

	By productname = By.xpath(".//div[@class='card-body']//b");

	By spinner = By.cssSelector(".ngx-spinner-overlay");

	public List<WebElement> productlist() {
		invisibilityOfElementLocated(spinner);
		return products;
	}

	public WebElement getprod(String item) {
		WebElement prod = productlist().stream()
				.filter(product -> product.findElement(productname).getText().equalsIgnoreCase(item)).findFirst()
				.orElse(null);
		return prod;

	}

	public void addtoCart(String[] productsreq) {
		List<String> items = Arrays.asList(productsreq);
		for (String item : items) {

			WebElement prod = getprod(item);
			invisibilityOfElementLocated(spinner);
			WebElement addButton = prod.findElement(addtocartbtn);
			
			scrollIntoView(addButton);

			elementToBeClickable(addButton);
			
//			addButton.click();
			safeClick(addButton);
			invisibilityOfElementLocated(spinner);
		}
	}
	
	
	public void addtoCart(List<String> productsreq) {

//		List<String> items = Arrays.asList(productsreq);
		for (String item : productsreq) {

			WebElement prod = getprod(item);
			invisibilityOfElementLocated(spinner);
			WebElement addButton = prod.findElement(addtocartbtn);
			
			scrollIntoView(addButton);

			elementToBeClickable(addButton);
			
//			addButton.click();
			safeClick(addButton);
			invisibilityOfElementLocated(spinner);
		}
	
	}
}
