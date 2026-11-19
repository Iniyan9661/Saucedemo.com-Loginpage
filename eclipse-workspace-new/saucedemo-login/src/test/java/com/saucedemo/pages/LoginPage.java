package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
	private static final String URL = "https://www.saucedemo.com/";
	private final WebDriver driver;
	private final WebDriverWait wait;
	private final By usernameInput = By.id("user-name");
	private final By passwordInput = By.id("password");
	private final By loginButton = By.id("login-button");
	private final By errorMessage = By.cssSelector("[data-test='error']");
	private final By productsTitle = By.cssSelector("[data-test='title']");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public void open() {
		driver.get(URL);
		wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton));
	}

	public void enterUsername(String username) {
		driver.findElement(usernameInput).clear();
		driver.findElement(usernameInput).sendKeys(username);
	}

	public void enterPassword(String password) {
		driver.findElement(passwordInput).clear();
		driver.findElement(passwordInput).sendKeys(password);
	}

	public void clickLogin() {
		driver.findElement(loginButton).click();
	}

	public void login(String username, String password) {
		enterUsername(username);
		enterPassword(password);
		clickLogin();
	}

	public String getErrorMessage() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText().trim();
	}

	public boolean isOnProductsPage() {
		wait.until(ExpectedConditions.urlContains("inventory.html"));
		return driver.getCurrentUrl().contains("inventory.html") && "Products"
				.equals(wait.until(ExpectedConditions.visibilityOfElementLocated(productsTitle)).getText().trim());
	}
}