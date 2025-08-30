package pages;

import java.io.IOException;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import constants.FrameworkConstants;
import utils.WaitUtils;

public class GiftCardPage {
	WebDriver driver;
	WebDriverWait wait;
	
	private By city = By.xpath("//input[@id = 'dummy']");
	private By gitCardBtn = By.xpath("//a[contains(text(),'Gift Cards') or @href='/giftcards']");
	private By checkBalanceIcon = By.xpath("//div[contains(text(),'Check Gift Card Balance')]");
	private By voucherCodeBar =  By.id("gift-voucher");
	private By checkBalance = By.xpath("//div[contains(text(),'Check Balance')]");
	private By errorMessage = By.xpath("//p[contains(@class, 'sc-12r1n02-9')]");
	
	public GiftCardPage(WebDriver driver) throws NumberFormatException, IOException {
		this.driver = driver;
		this.wait = WaitUtils.waiting(driver);
	}

	public void giveCity(String name) {
		driver.findElement(city).click();
		driver.findElement(city).sendKeys(name);
		driver.findElement(city).sendKeys(Keys.ENTER);
	}
	
	public void clickGiftCard() {
		wait.until(ExpectedConditions.elementToBeClickable(gitCardBtn)).click();
	}
	
	public boolean isCheckBalanceVisible() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(checkBalanceIcon)).isDisplayed();
	}
	
	public void clickCheckBalanceIcon() {
	     wait.until(ExpectedConditions.elementToBeClickable(checkBalanceIcon)).click();
	}
	
	public void enterVoucher(String voucher) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(voucherCodeBar)).click();
        driver.findElement(voucherCodeBar).sendKeys(voucher);
    }
	
	public void clickCheckBalance() {
		wait.until(ExpectedConditions.elementToBeClickable(checkBalance)).click();
	}

	public String getErrorMessage() {
		WebElement errorEl = new WebDriverWait(driver, Duration.ofSeconds(FrameworkConstants.EXPLICIT_WAIT))
				.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
		return errorEl.getText().trim();
	}
}
