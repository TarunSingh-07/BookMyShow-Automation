package pages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.WaitUtils;

public class CityPage {
	WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor js;

	private By city = By.xpath("//input[@id = 'dummy']");
	private By cityDropDown = By.cssSelector("#common-header-region span.sc-1or3vea-16.gPcyDI");
	private By selectedCityText = By.cssSelector("span.sc-1or3vea-16.gPcyDI");

	private By cityNotFound = By.xpath("//div[text() = 'No results found.']");

	private By cityIcon = By.xpath("//li//p"); 
	private By hydText = By.cssSelector("span.sc-1or3vea-16.gPcyDI"); 
	
	private By viewAllCity = By.xpath("//div[@class = 'sc-yuf6si-0 ghyujE']");
	private By allCityName = By.xpath("//ul[@class='sc-yuf6si-1 idrZHM']/li");

	
	public CityPage(WebDriver driver) throws NumberFormatException, IOException {
		this.driver = driver;
		this.wait = WaitUtils.waiting(driver);
		this.js = (JavascriptExecutor) driver;
	}

	public void giveCity(String name) {
		driver.findElement(city).click();
		driver.findElement(city).sendKeys(name);
		driver.findElement(city).sendKeys(Keys.ENTER);
	}

	// Scenario 1
	public void openCitySection() {
		WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(cityDropDown));

		js.executeScript("arguments[0].scrollIntoView(true);", dropdown);
		dropdown.click();
	}

	public String getSelectedCity() {
		WebElement cityText = wait.until(ExpectedConditions.visibilityOfElementLocated(selectedCityText));
		return cityText.getText();
	}

	// Scenario 2
	public void cityByName(String cityName) {
		openCitySection();
		WebElement nameEnter = wait.until(ExpectedConditions.elementToBeClickable(city));
		nameEnter.clear();
		nameEnter.sendKeys(cityName);
		nameEnter.sendKeys(Keys.ENTER);
	}

	public String noCity() {
		WebElement errorCity = wait.until(ExpectedConditions.visibilityOfElementLocated(cityNotFound));
		return errorCity.getText();
	}

	// Scenario 3
	public void clickCityIcon(String cityname) {
		openCitySection();
		List<WebElement> cities = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(cityIcon));

		for (WebElement city : cities) {
			if (city.getText().equalsIgnoreCase(cityname)) { 
				js.executeScript("arguments[0].scrollIntoView(true);", city);
				city.click(); 
				break;
			}
		}
	}
	public String showIconCity() {
		WebElement textHYD = wait.until(ExpectedConditions.visibilityOfElementLocated(hydText));
		return textHYD.getText();	
	}

	// Scenario 4
	public void viewAllCityShow() throws InterruptedException {
		openCitySection();
		wait.until(ExpectedConditions.elementToBeClickable(viewAllCity)).click();
		Thread.sleep(3000);
	}

	public List<String> getAllCityNames() {
		List<WebElement> cityElements = driver.findElements(allCityName);
		List<String> cityNames = new ArrayList<>();
		for (WebElement city : cityElements) {
			cityNames.add(city.getText().trim());
		}
		return cityNames;
	}

	    // Check if a city is visible in the list
	    public boolean isCityVisible(String cityName) {
	        return getAllCityNames().contains(cityName);
	    }
}