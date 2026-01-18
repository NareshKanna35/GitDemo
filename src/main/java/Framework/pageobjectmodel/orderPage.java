package Framework.pageobjectmodel;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Framework.Abstract.Abstractmethods;

public class orderPage extends Abstractmethods {

	WebDriver driver;

	public orderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//td[2]")
	List<WebElement> names;
	
	@FindBy (xpath = "//button[@routerlink='/dashboard/myorders']")
	WebElement orderhistory;

	public boolean checkorderhistory(String[] a) {
		
		
		List<String> itemlist = Arrays.asList(a);
		
		safeClick(orderhistory);

		for (String item : itemlist) {

			Boolean orders = names.stream().anyMatch(p -> p.getText().equalsIgnoreCase(item));
			if (!orders) {
				return false;
			}
		}
		return true;
	}

}
