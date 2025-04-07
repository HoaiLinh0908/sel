package com.senelium.factories.driver;

import com.senelium.config.DriverConfig;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.time.Duration;

public interface DriverFactory<T extends MutableCapabilities> {

    default SelDriver createDriver(DriverConfig config) {
        T caps = (T) config.getCapabilities();
        if (config.isHeadless()) setHeadless(caps);
        //setPageLoadTimeout(caps, config.getTimeout().getPageLoad());

        WebDriver webDriver;
        if (!config.getRemoteURL().isEmpty()) {
            webDriver = createRemoteWebDriver(config.getRemoteAddress(), caps);
        } else {
            webDriver = createLocalWebDriver(caps, config.getBinary());
            if (config.isWindowMaximize()) setWindowSize(webDriver);
        }
        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        return SelDriver.newInstance(webDriver, config);
    }

    void setHeadless(T caps);

    void setPageLoadTimeout(T caps, int timeout);

    default WebDriver createRemoteWebDriver(URL url, T caps) {
        return new RemoteWebDriver(url, caps);
    }

    WebDriver createLocalWebDriver(T caps, String binary);

    default void setWindowSize(WebDriver driver) {
        driver.manage().window().maximize();
    }
}
