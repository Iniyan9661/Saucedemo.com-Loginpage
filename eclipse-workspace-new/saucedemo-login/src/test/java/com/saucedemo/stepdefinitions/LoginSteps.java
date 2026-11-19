package com.saucedemo.stepdefinitions;

import com.saucedemo.pages.LoginPage;
import com.saucedemo.utils.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class LoginSteps {
	private LoginPage loginPage;

	@Given("the user is on the SauceDemo login page")
	public void userIsOnLoginPage() {
		loginPage = new LoginPage(DriverFactory.getDriver());
		loginPage.open();
	}

	@When("the user logs in with username {string} and password {string}")
	public void userLogsIn(String username, String password) {
		loginPage.login(username, password);
	}

	@Then("the user should land on the products page")
	public void userLandsOnProductsPage() {
		Assert.assertTrue(loginPage.isOnProductsPage(), "Products page was not displayed after login");
	}

	@Then("the error message should be {string}")
	public void errorMessageShouldBe(String expected) {
		Assert.assertEquals(loginPage.getErrorMessage(), expected);
	}
}