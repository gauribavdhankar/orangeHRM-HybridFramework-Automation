package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;

import factory.DriverFactory;
import reporting.ExtentManager;
import reporting.ExtentTestManager;
import utils.ScreenshotUtils;

public class ExtentListener implements ITestListener {
	
	private static ExtentReports extent = ExtentManager.getInstance();
	
	@Override
	public void onTestStart(ITestResult result) {
		
		//Always create new Extent test id exist -do not create new
		ExtentTestManager.setTest(
				extent.createTest(
						result.getMethod().getMethodName()
						+ " - Attempt "
						+result.getMethod().getCurrentInvocationCount()
						)
				);
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		
		//If retry will happen , just log info
		ExtentTestManager.getTest()
			.log(Status.FAIL, result.getThrowable());
			
		//screenshot
		String path = ScreenshotUtils.captureScreenshot(
				DriverFactory.getDriver(),            //we will adjust this
				result.getMethod().getMethodName()
			);
		
		if (path != null) {
			ExtentTestManager.getTest()
				.addScreenCaptureFromPath(path);
		}
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		ExtentTestManager.getTest().log(Status.PASS, "Test Passed");
	}
	
	@Override
	public void onFinish(ITestContext context) {
		
		extent.flush();
	}
}
