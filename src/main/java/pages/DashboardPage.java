package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.ReportUtils;
import utils.WaitUtils;

public class DashboardPage {
	private WebDriver driver;
	private WaitUtils waitUtils ;
	
	//constructor
	public DashboardPage(WebDriver driver) {
		this.driver = driver;
		this.waitUtils = new WaitUtils(driver);
	}
	
	//Locator
	private By dashboardHeader = By.xpath("//h6[normalize-space()='Dashboard']");
	
	//verification
	public boolean isDashboardDisplayed() {
		waitUtils.waitForElementVisible(dashboardHeader);
		ReportUtils.info("Validating dashboard is displayed");
		return driver.findElement(dashboardHeader).isDisplayed();
	}
}
