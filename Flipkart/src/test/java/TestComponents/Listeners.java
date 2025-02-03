package TestComponents;

import java.io.IOException;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Resources.ExtentReporter;

public class Listeners extends BaseTest implements ITestListener  {
	ExtentReports extent = ExtentReporter.getReportObject();
	ExtentTest test;
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	
	 @Override
	    public void onTestStart(ITestResult result) {
		 test = extent.createTest(result.getMethod().getMethodName()); 
			extentTest.set(test);
	    }

	    @Override
	    public void onTestSuccess(ITestResult result) {
	        test.log(Status.PASS, "Test Pass");
	    }

	    @Override
	    public void onTestFailure(ITestResult result) {
	    	extentTest.get().fail(result.getThrowable());
	    	  // Screenshot , Attaching it
			 try {
				driver = (ChromeDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			}
			 catch (Exception e) {  
				e.printStackTrace();
			}
			 String filePath = null;
				try {
					filePath = getScreenshot(result.getMethod().getMethodName(),driver);
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
				extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName()); // adding ss to extent report
			  }

	    @Override
	    public void onTestSkipped(ITestResult result) {
	       
	    }

	    @Override
	    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	        
	    }

	    public void onTestFailedWithTimeout(ITestResult result) {
		    onTestFailure(result);
		  }
	    
	    @Override
	    public void onStart(ITestContext context) {
	        
	    }

	    @Override
	    public void onFinish(ITestContext context) {
	    	extent.flush();
	    }
	
}
