package com.senelium.factories.driver;

import com.senelium.config.DriverConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.time.Duration;

public class EdgeDriverFactory implements DriverFactory<EdgeOptions> {

    @Override
    public void setHeadless(EdgeOptions options) {
        options.addArguments("--headless");
    }

    @Override
    public void setPageLoadTimeout(EdgeOptions options, int timeout) {
        options.setPageLoadTimeout(Duration.ofMillis(timeout));
    }

    @Override
    public WebDriver createLocalWebDriver(EdgeOptions options, String binary) {
        if (!binary.isEmpty()) {
            options.setBinary(binary);
        }
        return new EdgeDriver(options);
    }

    @Override
    public void setWindowSize(EdgeOptions options, DriverConfig config) {
        options.addArguments("--window-size=%d,%d".formatted(config.windowSize().width, config.windowSize().height));
    }
}
