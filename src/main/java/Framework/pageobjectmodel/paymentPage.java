package Framework.pageobjectmodel;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Framework.Abstract.Abstractmethods;

public class paymentPage extends Abstractmethods {

	WebDriver driver;

	public paymentPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@placeholder= 'Select Country']")
	WebElement dropdown;
	
	@FindBy(xpath = "//button[@type= 'button'] //span[text()=' India']")
	WebElement country;
	
	@FindBy(xpath = "//a[text()='Place Order ']")
	WebElement orderbtn;
	
	@FindBy(xpath = "//h1")
	WebElement msg;

	public void country() {	
		scrollIntoView(dropdown);
		dropdown.sendKeys("Ind");
		elementToBeClickable(country);
		country.click();
	}
	
	public String placeorder() {
		orderbtn.click();
		String greeting = msg.getText();
		return greeting;
	}
 
}
