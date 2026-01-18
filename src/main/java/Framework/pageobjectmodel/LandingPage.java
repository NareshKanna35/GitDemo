package Framework.pageobjectmodel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Framework.Abstract.Abstractmethods;

public class LandingPage extends Abstractmethods {

	// 1. define the different webelements for the page --> Use @FindBy
	// 2. define the methods to perform action on that page

	WebDriver driver;

	// Constructor
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@id='userEmail']")
	WebElement emailid;
	@FindBy(css = "#userPassword")
	WebElement password;
	@FindBy(css = "#login")
	WebElement loginbutton;
	@FindBy(xpath = "//*[@id='toast-container']/div/div")
	WebElement notification;

	public void gotopage(String url) {
		driver.get(url);
	}

	public ProductPage loginapplication(String inputemail, String inputpassword) {
		emailid.sendKeys(inputemail);
		password.sendKeys(inputpassword);
		loginbutton.click();
		ProductPage PP = new ProductPage(driver);
		return PP;
		
	}
	
	public String loginerror() {
		elementvisible(notification);
		return notification.getText(); 
	}

}
