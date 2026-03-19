package reporting;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
	
	private static ExtentReports extent;
	
	public static ExtentReports getInstance() {

		if(extent == null) {
			
			File reportDir = new File("reports");
			if (!reportDir.exists()) {
				reportDir.mkdir();
			}
			
			ExtentSparkReporter reporter =
					new ExtentSparkReporter("reports/AutomationReport.html"); 
			
			extent = new ExtentReports();
			extent.attachReporter(reporter);
		}
		
		return extent;
	}
}
