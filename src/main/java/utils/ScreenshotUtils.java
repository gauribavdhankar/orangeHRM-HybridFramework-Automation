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
		
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		
		String timestamp = LocalDateTime.now()
				.format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
		
		String fileName = testName + "_" + System.currentTimeMillis() + ".png";
		
		File directory = new File("screenshots");
		if(!directory.exists()) {
			directory.mkdir();
		}
		
		File destination = new File(directory, fileName);		
		try {
			FileUtils.copyFile(source, destination);
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		return "screenshots/" + fileName;
	}
	
}
