package stepDefinitions;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;

public class LoginSteps {

	WebDriver driver = Hooks.driver;
	LoginPage loginPage = Hooks.loginPage;

	@Given("user is on login page")
	public void user_is_on_login_page() throws Exception {
		loginPage = new LoginPage(driver);
	}

	@When("I select my city")
	public void i_select_my_city() {
		loginPage.selectCity("Angul");
	}

	@When("I logs in with mobile and otp")
	public void i_logs_in_with_mobile_and_otp() throws Exception, IOException {
		loginPage.clickSignIn();
		loginPage.clickNumberBar();
		loginPage.fillNumber("7855009944");
		loginPage.clickContinue();

		Assert.assertTrue(loginPage.isLoginDisplayed(), "OTP page should be displayed");
		loginPage.clickBack();
		loginPage.clickClose();
	}

	@Then("login should be successful")
	public void login_should_be_successful() {
		Assert.assertTrue(loginPage.isLoginDisplayed(), "OTP page visible");
	}

	@When("I am login with wrong mobile and otp")
	public void i_am_login_with_wrong_mobile_and_otp() throws NumberFormatException, Exception {
		loginPage.selectCity("Angul");
		loginPage.clickSignIn();
		loginPage.clickNumberBar();
		loginPage.fillNumber("123456789");
		loginPage.clickContinue();
	}

	@Then("login should be failed")
	public void login_should_be_failed() {
		String errorText = loginPage.showErrMsg();
		Assert.assertTrue(errorText.contains("Invalid"), "Error message should be Visible");
	}

	@Then("all login UI elements should be visible")
	public void all_login_ui_elements_should_be_visible() {
		loginPage.selectCity("Angul");
		loginPage.clickSignIn();
		Assert.assertTrue(loginPage.visibleUIElement(), "All loginOptions visible");
	}
}
