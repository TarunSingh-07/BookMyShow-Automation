package pages;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WaitUtils;

public class MoviePage {
	WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor js;

	private By firstRecommendMovie = By.xpath("(//div[@class='sc-lnhrs7-4 hQMAVG']//a)[1]");
	private By movieNameTitle = By
			.xpath("(//div[@class='sc-lnhrs7-4 hQMAVG']//a)[1]//div[@class='sc-7o7nez-0 lkwOqB']");
	private By movieDetailsTitle = By.xpath("//h1[contains(@class,'sc-qswwm9-6')]");

	private By moviesTab = By.xpath("//a[contains(text(),'Movies')]");
    private By movieSearchBoxPlaceholder = By.xpath("//span[text()='Search for Movies, Events, Plays, Sports and Activities']");  

	private By exploreUpcomingMovies = By.xpath("//img[@alt='Coming Soon']");
	private By inCinemasNearYouSection = By.xpath("//img[@alt='Now Showing']");

	public MoviePage(WebDriver driver) throws NumberFormatException, IOException {
		this.driver = driver;
		this.wait = WaitUtils.waiting(driver);
		this.js = (JavascriptExecutor) driver;
	}

	// First Scenario
	public String getFirstMovieName() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(movieNameTitle)).getText().trim();
	}

	public void clickFirstRecommendMovie() {
		wait.until(ExpectedConditions.elementToBeClickable(firstRecommendMovie)).click();
	}

	public String getMovieNameOnDetailsPage() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(movieDetailsTitle)).getText().trim();
	}

	// Scenario 2
	public boolean isMoviesTabVisible() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(moviesTab)).isDisplayed();
	}

   public boolean isMovieSearchBoxVisible() {
      try {
          WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(movieSearchBoxPlaceholder));
          return searchBox.isDisplayed();
      } catch (Exception e) {
          System.out.println("Search box placeholder not visible: " + e.getMessage());
          return false;
         }
    }

   // Scenario 3
	public void clickMoviesTab() {
		wait.until(ExpectedConditions.elementToBeClickable(moviesTab)).click();
	}

	public void clickExploreUpcomingMovies() {
		WebElement el = wait.until(ExpectedConditions.elementToBeClickable(exploreUpcomingMovies));
		js.executeScript("arguments[0].scrollIntoView(true);", el);
		el.click();
	}

	public boolean isInCinemasSectionVisible() {
		try {
			WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(inCinemasNearYouSection));
			js.executeScript("arguments[0].scrollIntoView(true);", el);
			return el.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
}
