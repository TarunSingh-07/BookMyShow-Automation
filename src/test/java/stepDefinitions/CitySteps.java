package stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CityPage;

public class CitySteps {

	WebDriver driver = Hooks.driver;
	CityPage cityPage = Hooks.cityPage;

	@Given("user is on the city selection page")
	public void user_is_on_the_city_selection_page() throws Exception {
		cityPage.giveCity("Angul");
	}

	@When("user enters a valid city name {string}")
	public void user_enters_a_valid_city_name(String cityname) throws InterruptedException {
		cityPage.openCitySection();
		Thread.sleep(5000);
		cityPage.giveCity(cityname);
		Thread.sleep(5000);
	}

	@Then("the correct city {string} should be displayed")
	public void the_correct_city_should_be_displayed(String cityname) {
		String selectedCity = cityPage.getSelectedCity();
		Assert.assertEquals(selectedCity, cityname);
	}

	// Scenario 2
	@When("user enter an invalid city name {string}")
	public void user_enter_an_invalid_city_name(String cityname) {
		cityPage.cityByName(cityname);
	}

	@Then("an error message {string} shoulld be displayed")
	public void an_error_message_shoulld_be_displayed(String expectedMsg) {
		String actualMsg = cityPage.noCity();
		Assert.assertEquals(actualMsg, expectedMsg);
	}

	// Scenario 3
	@When("user clicks on the city icon {string}")
	public void user_clicks_on_the_city_icon(String cityname) {
		cityPage.clickCityIcon(cityname);
	}

	@Then("the correct city {string} should be selected")
	public void the_correct_city_should_be_selected(String cityname) {
		String hyderabad = cityPage.showIconCity();
		Assert.assertEquals(hyderabad, cityname);
	}

	// Scenario 4
	@When("user clicks on city dropdown and selects {string}")
	public void user_clicks_on_city_dropdown_and_selects(String option) throws InterruptedException {
		cityPage.viewAllCityShow();
	}

	@Then("I should see the cities {string} in All Cities")
	public void i_should_see_the_cities_in_all_cities(String expectedCity) {
		// City Visibilty
		Assert.assertTrue(cityPage.isCityVisible(expectedCity), "City not found in All Cities: " + expectedCity);
	}
}
