package com.senelium.factories.driver;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

@Slf4j
public class ChromeDriverFactory implements DriverFactory<ChromeOptions> {

    @Override
    public void setHeadless(ChromeOptions options) {
        options.addArguments("--headless=new");
    }

    @Override
    public void setPageLoadTimeout(ChromeOptions options, int timeout) {
        options.setPageLoadTimeout(Duration.ofMillis(timeout));
    }

    @Override
    public WebDriver createLocalWebDriver(ChromeOptions options, String binary) {
        if (!binary.isEmpty()) {
            options.setBinary(binary);
        }
        return new ChromeDriver(options);
    }
}
