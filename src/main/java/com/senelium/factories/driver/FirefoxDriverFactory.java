package com.senelium.factories.driver;

import com.senelium.config.DriverConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class FirefoxDriverFactory implements DriverFactory<FirefoxOptions> {

    @Override
    public void setHeadless(FirefoxOptions options) {
        options.addArguments("--headless");
    }

    @Override
    public void setPageLoadTimeout(FirefoxOptions options, int timeout) {
        options.setPageLoadTimeout(Duration.ofMillis(timeout));
    }

    @Override
    public WebDriver createLocalWebDriver(FirefoxOptions options, String binary) {
        if (!binary.isEmpty()) {
            options.setBinary(binary);
        }
        return new FirefoxDriver(options);
    }

    @Override
    public void setWindowSize(FirefoxOptions options, DriverConfig config) {
//        options.addArguments("--width=%d".formatted(config.windowSize().width));
//        options.addArguments("--height=%d".formatted(config.windowSize().height));
    }
}
