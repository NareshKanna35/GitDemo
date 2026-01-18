package Framework.resources;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

	static ExtentReports result;

	public static ExtentReports getreportobject() {

		File file = new File(System.getProperty("user.dir") + "//reports//index.html");
		ExtentSparkReporter re = new ExtentSparkReporter(file);
		re.config().setDocumentTitle("Framework Result Page");
		re.config().setReportName("Test Results");

		result = new ExtentReports();
		result.attachReporter(re);
		result.setSystemInfo("Tester", "Naresh");
		return result;

	}

}
