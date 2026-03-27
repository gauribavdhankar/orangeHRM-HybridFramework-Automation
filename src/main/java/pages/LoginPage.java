package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.ReportUtils;
import utils.WaitUtils;

public class LoginPage {
	private WebDriver driver;
	private WaitUtils waitUtils;
	private static final Logger logger = LogManager.getLogger(LoginPage.class);
	
	//constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		this.waitUtils = new WaitUtils(driver);
		
		// IMPORTANT → wait for page ready
	    waitUtils.waitForElementVisible(usernameField);
	}
	
	//Locators
	private By usernameField = By.name("username");
	private By passwordField = By.name("password");
	private By loginButton = By.xpath("//button[@type='submit']");
	
	public void enterUsername(String username) {
		ReportUtils.info("Entering username");
		waitUtils.waitForElementVisible(usernameField).sendKeys(username);
	}
	
	public void enterPassword(String password) {
		waitUtils.waitForClickability(passwordField).sendKeys(password);
	}
	
	public void clickLogin() {
		ReportUtils.info("Clicking login button");
		waitUtils.waitForClickability(loginButton).click();
	}
	
	public DashboardPage login(String username, String password) {
		enterUsername(username);
		enterPassword(password);
		clickLogin();	
		
		return new DashboardPage(driver);
	}
	
}
