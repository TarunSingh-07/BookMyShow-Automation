package hooks;


import org.openqa.selenium.WebDriver;
import base.ConfigLoader;
import base.DriverSetup;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import pages.CityPage;
import pages.LoginPage;
import pages.MoviePage;
import utils.ScreenShotUtil;
import pages.GiftCardPage;

public class Hooks {
	public static WebDriver driver;
	public static LoginPage loginPage;
	public static CityPage cityPage;
	public static GiftCardPage giftCardPage;
	public static MoviePage moviePage;
	
	@Before
	public void setUp() throws Exception {
		DriverSetup.OpenBrowser();
		driver = DriverSetup.getDriver();
		driver.get(ConfigLoader.getProperty("base.url"));
		loginPage = new LoginPage(driver);
		cityPage = new CityPage(driver);
		giftCardPage = new GiftCardPage(driver);
		moviePage = new MoviePage(driver);
	}
	
	@After
	public void tearDown(Scenario scenario) throws InterruptedException {
		if (scenario.isFailed() && driver != null) {
			String screenshotPath = ScreenShotUtil.captureScreenshot(driver, scenario.getName());
			System.out.println("Screenshot saved at: " + screenshotPath);
		}
		DriverSetup.CloseBrowser();
	}
}
