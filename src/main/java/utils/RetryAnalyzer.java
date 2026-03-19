package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer{

	private static final Logger logger = LogManager.getLogger(RetryAnalyzer.class);

	private int retryCount = 0;
	private static final int maxRetryCount = 2; //total retries
	
	@Override
	public boolean retry(ITestResult result) {
		
		if(retryCount < maxRetryCount) {
			retryCount++;
			result.setAttribute("retry", true);
			
			logger.warn("Retrying test: " +result.getName()
						+ " | Attempt: " + retryCount);
			
			return true;     //retry test
		}
		
		return false;   //stop retrying
	}
}
