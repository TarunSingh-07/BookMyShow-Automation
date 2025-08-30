package base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import constants.FrameworkConstants;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverSetup {
	
	public static WebDriver driver;
	
	public static void OpenBrowser() throws Exception {
		String browser = ConfigLoader.getProperty("browser");

		switch (browser.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;

		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;

		default:
			throw new Exception("Invalid browser type in config.properties");
		}
		driver.manage().window().maximize();

		long waitTime = Long.parseLong(ConfigLoader.getProperty("implicit.wait"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(waitTime));

		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(FrameworkConstants.PAGE_LOAD_TIMEOUT));
	}
	
	public static WebDriver getDriver() {
		return driver;
	}
	
	public static void CloseBrowser() throws InterruptedException {
		if(driver != null) {
			driver.quit();
		}
	}
}
