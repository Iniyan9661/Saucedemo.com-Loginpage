package com.saucedemo.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public final class DriverFactory {
	private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

	private DriverFactory() {
	}

	public static void initDriver() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized", "--disable-notifications", "--disable-infobars");
		// Disable Chrome's password manager / breach-detection popups that appear after
		// login
		Map<String, Object> prefs = new HashMap<>();
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		prefs.put("profile.password_manager_leak_detection", false);
		options.setExperimentalOption("prefs", prefs);
		if (Boolean.parseBoolean(System.getProperty("headless", "false"))) {
			options.addArguments("--headless=new", "--window-size=1920,1080");
		}
		WebDriver driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		DRIVER.set(driver);
	}

	public static WebDriver getDriver() {
		return DRIVER.get();
	}

	public static void quitDriver() {
		WebDriver driver = DRIVER.get();
		if (driver != null) {
			driver.quit();
			DRIVER.remove();
		}
	}
}