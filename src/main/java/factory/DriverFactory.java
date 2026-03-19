package factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	//ThreadLocal driver storage
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	
	//create and set driver
	public static void initDriver(String browser, boolean headless) {
		
		WebDriver localDriver;
		
		if(browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			
			ChromeOptions options = new ChromeOptions();
			if(headless) {
				options.addArguments("--headless=new");
			}
			
			localDriver = new ChromeDriver(options);
		}
		else if(browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			localDriver = new EdgeDriver();
		}
		else {
			throw new RuntimeException("Browser not supported" +browser);
		}
		
		//Store inside ThreadLocal
		driver.set(localDriver); 
	}
	
	//Get driver for current thread
	public static WebDriver getDriver() {
		return driver.get();
	}
	//Quit and remove driver safely
	public static void quitDriver() {
		if(driver.get() != null) {
			driver.get().quit();
			driver.remove();      //Prevent memory leak
		}
	}
}
