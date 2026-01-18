package Framework.Abstract;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

import Framework.pageobjectmodel.CartPage;

public class Abstractmethods {

// this change is done to test the git 18012026
	// second git test
	
	// develope branch creation
	WebDriver driver;
	public WebDriverWait wait;

	public Abstractmethods(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(6));
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//button[@routerlink='/dashboard/cart']")
	WebElement cartbutton;

	By spinner = By.cssSelector(".ngx-spinner-overlay");

	public void invisibilityOfElementLocated(By findby) {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(findby));
	}

	public void elementvisible(WebElement ele) {
		wait.until(ExpectedConditions.visibilityOf(ele));
	}

	public CartPage gotocart() {
		invisibilityOfElementLocated(spinner);
//		scroll (0);
		scrollToTop();
		safeClick(cartbutton);
		CartPage CP = new CartPage(driver);
		return CP;

	}

	public void elementToBeClickable(WebElement ele) {
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	}

	public void elementToBeClickable(By findby) {
		wait.until(ExpectedConditions.elementToBeClickable(findby));
	}

//	public void scroll(int y) {
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("window.scrollBy(0,arguments[0])", y);
//	}

	public void scrollToTop() {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
	}

	public void scrollIntoView(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", element);
	}

	public void safeClick(WebElement element) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element));
			element.click();
		} catch (Exception e) {
			// fallback for animation overlap
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
		}
	}

	public void screenshot() throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		Files.copy(src, new File("C:\\Users\\Public\\3.png"));
	}

	

}
