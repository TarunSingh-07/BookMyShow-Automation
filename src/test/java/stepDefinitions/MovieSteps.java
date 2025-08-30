package stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.GiftCardPage;
import pages.MoviePage;

public class MovieSteps {
	WebDriver driver = Hooks.driver;
	MoviePage moviePage = Hooks.moviePage;
	GiftCardPage giftCardPage = Hooks.giftCardPage;

	String firstMovieTitle;

	@Given("user is on the city selection page and choose the city {string}")
	public void user_is_on_the_city_selection_page_and_choose_the_city(String cityname) {
		giftCardPage.giveCity(cityname);
	}

	@Given("user notes the first recommended movie title")
	public void user_notes_the_first_recommended_movie_title() {
		firstMovieTitle = moviePage.getFirstMovieName();
		System.out.println("First recommended movie: " + firstMovieTitle);
	}

	@When("user clicks on the first recommended movie card")
	public void user_clicks_on_the_first_recommended_movie_card() {
		moviePage.clickFirstRecommendMovie();
	}

	@Then("the movie title on details page should match the noted title")
	public void the_movie_title_on_details_page_should_match_the_noted_title() {
		String detailPageTitle = moviePage.getMovieNameOnDetailsPage();
		System.out.println("Movie title on detail page: " + detailPageTitle);
		Assert.assertEquals(detailPageTitle, firstMovieTitle, "Movie title does not match the recommended movie!");
	}

	@Then("movies tab should be visible")
	public void movies_tab_should_be_visible() {
		Assert.assertTrue(moviePage.isMoviesTabVisible(), "Movies tab is not visible");
	}

	@Then("movie search box should be visible")
	public void movie_search_box_should_be_visible() {
		Assert.assertTrue(moviePage.isMovieSearchBoxVisible(), "Movie search box is not visible");
	}

	@When("user clicks on the Movies tab in the Home screen")
	public void user_clicks_on_the_movies_tab_in_the_home_screen() {
		moviePage.clickMoviesTab();
	}

	@When("user clicks on {string} section")
	public void user_clicks_on_section(String sectionName) {
		if (sectionName.equalsIgnoreCase("Explore Upcoming Movies")) {
			moviePage.clickExploreUpcomingMovies();
		}
	}

	@Then("{string} section should visible")
	public void section_should_visible(String expectedSection) {
		if (expectedSection.equalsIgnoreCase("In Cinemas Near You")) {
			Assert.assertTrue(moviePage.isInCinemasSectionVisible(), "In Cinemas Near You section not visible");
		}
	}

}
