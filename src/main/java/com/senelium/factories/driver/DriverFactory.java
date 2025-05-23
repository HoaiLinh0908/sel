package com.senelium.factories.driver;

import com.senelium.config.DriverConfig;
import com.senelium.utils.UrlUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.time.Duration;

// TODO: Store properties instead of passing in methods
public interface DriverFactory<T extends MutableCapabilities> {

    default SelDriver createDriver(DriverConfig config) {
        T caps = (T) config.capabilities();
        // Whether to enable WebDriver BiDi
        this.enableBiDi(caps, config.isBiDiEnabled());
        // Set the window resolution
        this.setWindowSize(caps, config);
        // Set headless mode
        if (config.headless()) this.setHeadless(caps); // TODO: move conditional into setHeadless
        // Set page load timeout
        this.setPageLoadTimeout(caps, config.timeout().pageLoad());

        WebDriver webDriver;
        if (!config.remoteURL().isEmpty()) {
            webDriver = this.createRemoteWebDriver(UrlUtils.newUrl(config.remoteURL()), caps);
        } else {
            webDriver = this.createLocalWebDriver(caps, config.binary());
        }
        return SelDriver.newInstance(webDriver, config);
    }

    void setHeadless(T caps);

    void setPageLoadTimeout(T caps, int timeout);

    default WebDriver createRemoteWebDriver(URL url, T caps) {
        var remoteDriver = new RemoteWebDriver(url, caps);
        remoteDriver.setFileDetector(new LocalFileDetector());
        return remoteDriver;
    }

    WebDriver createLocalWebDriver(T caps, String binary);

    void setWindowSize(T options, DriverConfig config);

    default void enableBiDi(T options, boolean enabled) {
        options.setCapability("webSocketUrl", enabled);
    }
}
