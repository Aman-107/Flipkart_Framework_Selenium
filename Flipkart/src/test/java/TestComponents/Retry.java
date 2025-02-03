package TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{

	int retryCount  = 0; 
	int maxTry = 1; 
	
	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		if(retryCount  < maxTry) {
			retryCount ++;
			return true;  
		}
		return false; 
	} 
	
}
