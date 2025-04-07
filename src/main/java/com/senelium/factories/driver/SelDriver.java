package com.senelium.factories.driver;

import com.senelium.config.DriverConfig;
import com.senelium.config.Timeout;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

public class SelDriver {
    private final WebDriver driver;
    private final Actions actions;
    private final WebDriverWait defaultWaiter;
    private final DriverConfig driverConfig;

    private SelDriver(WebDriver driver, DriverConfig config) {
        Objects.requireNonNull(driver);
        this.driver = driver;
        this.actions = new Actions(this.driver);
        this.driverConfig = config;
        this.defaultWaiter = new WebDriverWait(
                this.driver,
                Duration.ofMillis(config.getTimeout().getElementWait()),
                Duration.ofMillis(config.getTimeout().getInterval())
        );
    }

    public static SelDriver newInstance(WebDriver driver, DriverConfig config) {
        return new SelDriver(driver, config);
    }

    public WebDriver getWebDriver() {
        return this.driver;
    }

    public Actions getActions() {
        return this.actions;
    }

    public WebDriverWait getDefaultWaiter() {
        return this.defaultWaiter;
    }

    public WebDriverWait getWaiter(Duration timeout) {
        return new WebDriverWait(driver, timeout, Duration.ofMillis(this.driverConfig.getTimeout().getInterval()));
    }

    public WebDriverWait getWaiter(Duration timeout, Duration interval) {
        return new WebDriverWait(driver, timeout, interval);
    }

    public DriverConfig getDriverConfig() {
        return this.driverConfig;
    }
}
