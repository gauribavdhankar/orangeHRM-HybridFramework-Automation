package base;

import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import config.ConfigReader;
import factory.DriverFactory;


public class BaseTest {

	protected ConfigReader config;
	protected static Logger logger = LogManager
			.getLogger(BaseTest.class);
	
	@BeforeMethod
	public void setUp(Method method) {	
		
		config = new ConfigReader();

		DriverFactory.initDriver(
				config.getBrowser(), 
				config.isHeadless(),
				config.getExecutionMode(),
				config.getGridUrl());
		
		DriverFactory.getDriver().manage().window().maximize();
		DriverFactory.getDriver().get(config.getUrl());
		
		logger.info("starting test setup");
		logger.info("Launching Browser: " + config.getBrowser());
		logger.info("Navigating to URL:" + config.getUrl());
		}
		
	@AfterMethod
	public void tearDown() {
		
		DriverFactory.quitDriver();
		
		logger.info("Closing browser");
	}
}
