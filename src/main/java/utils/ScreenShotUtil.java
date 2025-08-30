package utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import constants.FrameworkConstants;

public class ScreenShotUtil {
	public static String captureScreenshot(WebDriver driver, String testName) {
		// Check Screenshots folder exists
		File screenshotDir = new File(FrameworkConstants.SCREENSHOT_PATH);
		if (!screenshotDir.exists()) {
			screenshotDir.mkdir();
		}

		// Add timestamp to screenshot name
		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String filePath = FrameworkConstants.SCREENSHOT_PATH + testName.replace(" ", "_") + "_" + timestamp + ".png";

		// Capture screenshot
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File dest = new File(filePath);

		try {
			FileUtils.copyFile(src, dest);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return filePath;
	}
}
