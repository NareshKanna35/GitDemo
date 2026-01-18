package NareshQA.TestComponents;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentReporter;

import Framework.resources.ExtentReporterNG;

public class listen extends BaseTest implements ITestListener {

	WebDriver driver;
	ExtentTest test;
	ExtentReports report = ExtentReporterNG.getreportobject();

	ThreadLocal<ExtentTest> threads = new ThreadLocal<ExtentTest>();

	@Override
	public void onTestStart(ITestResult result) {
		test = report.createTest(result.getMethod().getMethodName());
		threads.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		threads.get().log(Status.PASS, "Success");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		threads.get().fail(result.getThrowable()); // to print logs if test fails

		String filepath = null;
		try {
			filepath = screenshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			e.printStackTrace();
		}
		threads.get().addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());

	}

	@Override
	public void onFinish(ITestContext result) {
		report.flush();
	}

}
