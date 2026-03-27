package utils;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtils {
	
	public static String captureScreenshot(WebDriver driver, String testName) {
		
		if(driver == null) {
			return null;
		}
		
		String timestamp = LocalDateTime.now()
				.format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS"));
		
		long threadId = Thread.currentThread().threadId();
		
		String fileName = testName + "_T" + threadId + "_" + timestamp + ".png";
		
		String path = System.getProperty("user.dir") + "/screenshots/" + fileName;
		
		File directory = new File("screenshots");
		if(!directory.exists()) {
			directory.mkdirs();
		}
				
		try {			
			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			File destination = new File(path);
			
			FileUtils.copyFile(src, destination);
			
			return path;
		
		}catch(IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
