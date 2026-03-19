package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aventstack.extentreports.Status;

import reporting.ExtentTestManager;

public class ReportUtils {
	private static final Logger logger =
			LogManager.getLogger(ReportUtils.class);
	
	public static void info(String message) {
		logger.info(message);
		ExtentTestManager.getTest().log(Status.INFO, message);
		System.out.println("Status.Info Executed");
	}
	
	public static void pass(String message) {
		logger.info(message);
		ExtentTestManager.getTest().log(Status.PASS, message);
		
	}
	
	public static void fail(String message) {
		logger.info(message);
		ExtentTestManager.getTest().log(Status.FAIL, message);
	}
	
	public static void warning(String message) {
		logger.info(message);
		ExtentTestManager.getTest().log(Status.WARNING, message);
	}
}
