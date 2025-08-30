package utils;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.ConfigLoader;

public class WaitUtils {
	private static WebDriverWait wait;

	public static WebDriverWait waiting(WebDriver driver) throws NumberFormatException, IOException {
		long waitTime = Long.parseLong(ConfigLoader.getProperty("implicit.wait"));
		wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
		return wait;
	}
}
