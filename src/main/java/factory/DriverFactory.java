package factory;

import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	//ThreadLocal driver storage
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	
	//create and set driver
	public static void initDriver(String browser, boolean headless, String executionMode, String gridUrl) {
		
		WebDriver localDriver;
		
		try {
			//Remote Execution
			if(executionMode.equalsIgnoreCase("remote")){
				
				if (browser.equalsIgnoreCase("chrome")) {
			
				ChromeOptions options = new ChromeOptions();
				
				if(headless) {
					options.addArguments("--headless=new");
				}
				
				localDriver = new RemoteWebDriver(new URL(gridUrl), options);
				}
				
				else if (browser.equalsIgnoreCase("edge")) {
					
					EdgeOptions options = new EdgeOptions();
					localDriver = new RemoteWebDriver(new URL(gridUrl), options);
				}
				
				else {
					throw new RuntimeException("Unsupported browser for remote:" +browser);
				}
			}
			
			//Local Execution
			else if(browser.equalsIgnoreCase("local")) {
				
				if (browser.equalsIgnoreCase("chrome")) {
					
					WebDriverManager.chromedriver().setup();
					
					ChromeOptions options = new ChromeOptions();
					
					if (headless) {
						options.addArguments("---headless=new");
					}
					
					localDriver = new ChromeDriver(options);
				}
				
				else if (browser.equalsIgnoreCase("edge")) {
					
					WebDriverManager.edgedriver().setup();
					localDriver = new EdgeDriver();
			}
			
			else {
				throw new RuntimeException("Browser not supported" +browser);
				}
			}
			//invalid mode error
			else {
				throw new RuntimeException("Invalid execution mode:" + executionMode);
			}
			
			//Safety check
			if (localDriver == null) {
				throw new RuntimeException("Driver is NULL. Chek configuration.");
			}
			//Store local driver
			driver.set(localDriver);
			
		}catch (Exception e) {
			throw new RuntimeException("Driver initialization failed", e);
		}
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
