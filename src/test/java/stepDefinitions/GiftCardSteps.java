package stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.GiftCardPage;

public class GiftCardSteps {
	WebDriver driver = Hooks.driver;
	GiftCardPage giftCardPage = Hooks.giftCardPage;
	
	@Given("user is on the BookMyShow home page and enter the city")
	public void user_is_on_the_book_my_show_home_page_and_enter_the_city() {
		giftCardPage.giveCity("Bengaluru");
	}

	@Given("user click the Gift Card button")
	public void user_click_the_gift_card_button() {
		giftCardPage.clickGiftCard();
	}

	@Then("user see the {string} icon")
	public void user_see_the_icon(String iconName) {
		boolean visible = giftCardPage.isCheckBalanceVisible();
        if(!visible) {
            throw new AssertionError(iconName + " is not visible!");
        }
	}

	@When("user click the {string} icon")
	public void user_click_the_icon(String string) {
		 giftCardPage.clickCheckBalanceIcon();
	}

	@When("user enters an invalid voucher code {string}")
	public void user_enters_an_invalid_voucher_code(String voucher) {
		giftCardPage.enterVoucher(voucher);
	}

	@When("user click the Check balance")
	public void user_click_the_check_balance() {
		 giftCardPage.clickCheckBalance();
	}

	@Then("user see the error message {string}")
	public void user_see_the_error_message(String expectedMessage) {
		String actualMessage = giftCardPage.getErrorMessage();
		Assert.assertTrue(actualMessage.contains(expectedMessage),
				"Expected error message " + expectedMessage + "but was" + actualMessage);
	}
}
