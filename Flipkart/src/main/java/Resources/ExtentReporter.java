package Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporter {

	public static ExtentReports getReportObject() {

		String path = System.getProperty("user.dir") + "//Reports//index.html";
		ExtentSparkReporter report = new ExtentSparkReporter(path);
		report.config().setReportName("Flipkart Extent Report");
		report.config().setDocumentTitle("Aman Kumar Maurya");

		ExtentReports extent = new ExtentReports();
		extent.attachReporter(report);
		extent.setSystemInfo("Creator", "Aman");

		return extent;
	}

}
