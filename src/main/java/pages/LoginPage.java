package pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.WaitUtils;

public class LoginPage {
	WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor js;

	private By city = By.xpath("//input[@id = 'dummy']");
	private By signInBtn = By.xpath("//div[text() = 'Sign in']");
	private By numberBar = By.id("userMobileNumber");
	private By sendNumber = By.xpath("//input[@type = 'tel']");
	private By continueBtn = By.xpath("//div[text() = 'Continue']");
	private By backBtn = By.xpath("//div[@class = 'sc-1ydq0aj-0 bIaakI']");
	private By closeBtn = By.xpath("//div[@class = 'sc-1ydq0aj-6 gnsbYm']");

	private By errorMsg = By.xpath("//div[text() = 'Invalid mobile number']");

	private By googleBtn = By.xpath("//div[contains(text(),'Continue with Google')]");
	private By emailBtn = By.xpath("//div[contains(text(),'Continue with Email')]");
	private By appleBtn = By.xpath("//div[contains(text(),'Continue with Apple')]");

	public LoginPage(WebDriver driver) throws NumberFormatException, IOException, Exception {
		this.driver = driver;
		this.wait = WaitUtils.waiting(driver);
		this.js = (JavascriptExecutor) driver;
	}

	public void selectCity(String cityName) {
		driver.findElement(city).click();
		driver.findElement(city).sendKeys(cityName);
		driver.findElement(city).sendKeys(Keys.ENTER);
	}

	public void clickSignIn() {
		wait.until(ExpectedConditions.elementToBeClickable(signInBtn));
		driver.findElement(signInBtn).click();
	}

	public void clickNumberBar() throws NumberFormatException, IOException {
		WebElement numberElement = wait.until(ExpectedConditions.elementToBeClickable(numberBar));

		js.executeScript("arguments[0].scrollIntoView(true);", numberElement);
		numberElement.click();
	}

	public void fillNumber(String number) {
		WebElement numberInput = wait.until(ExpectedConditions.visibilityOfElementLocated(sendNumber));
		numberInput.sendKeys(number);
	}

	public void clickContinue() throws InterruptedException {
		WebElement continueBtnElement = wait.until(ExpectedConditions.elementToBeClickable(continueBtn));
		continueBtnElement.click();
	}

	public void clickBack() {
		WebElement backElement = wait.until(ExpectedConditions.elementToBeClickable(backBtn));
		backElement.click();
	}

	public void clickClose() {
		WebElement closeBtnElement = wait.until(ExpectedConditions.elementToBeClickable(closeBtn));
		closeBtnElement.click();
	}

	// scenario 2
	public String showErrMsg() {
		WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMsg));
		return errorElement.getText();
	}

	// scenario 3
	public boolean visibleUIElement() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(googleBtn));
		wait.until(ExpectedConditions.visibilityOfElementLocated(emailBtn));
		wait.until(ExpectedConditions.visibilityOfElementLocated(appleBtn));

		return driver.findElement(googleBtn).isDisplayed() && driver.findElement(emailBtn).isDisplayed()
				&& driver.findElement(appleBtn).isDisplayed();
	}

	// validate
	public boolean isLoginDisplayed() {
		WebElement signIn = wait.until(ExpectedConditions.visibilityOfElementLocated(signInBtn));
		return signIn.isDisplayed();
	}
}