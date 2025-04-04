package com.senelium.config;

import com.senelium.factories.capabilities.manager.CapsFactoryManager;
import com.senelium.utils.UrlUtils;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.MutableCapabilities;

import java.net.URL;

@Getter
@Setter
public class DriverConfig {
    private final String browser;
    private final MutableCapabilities capabilities;
    private final String remoteURL;
    private final boolean headless;
    private final Timeout timeout;
    private final boolean windowMaximize;
    private final String binary;

    // Reading system properties is a minor task, apply Singleton is not necessary
    public DriverConfig() {
        this.browser = System.getProperty("browser", "chrome");
        this.remoteURL = System.getProperty("remoteURL", "");
        this.headless = Boolean.parseBoolean(System.getProperty("headless", "true"));
        this.timeout = new Timeout();
        this.timeout.setPageLoad(Integer.parseInt(System.getProperty("pageLoadTimeout", "60000")));
        this.timeout.setElementWait(Integer.parseInt(System.getProperty("elementWaitTimeout", "5000")));
        this.timeout.setInterval(Integer.parseInt(System.getProperty("interval", "200")));
        this.windowMaximize = Boolean.parseBoolean(System.getProperty("windowMaximize", "true"));
        this.binary = System.getProperty("binary", "");
        this.capabilities = this.getDefaultCapabilities();
    }

    public static DriverConfig getInfo() {
        return new DriverConfig();
    }

    public URL getRemoteAddress() {
        return UrlUtils.newUrl(this.remoteURL);
    }

    private MutableCapabilities getDefaultCapabilities() {
        var capabilities = CapsFactoryManager.findFactory(this.browser).createCapabilities();
        var isBiDiEnabled = "true".equalsIgnoreCase(System.getProperty("enableBiDi", "true"));
        capabilities.setCapability("webSocketUrl", isBiDiEnabled);
        return capabilities;
    }

    public void setWebDriverBiDi(boolean isEnable) {
        this.capabilities.setCapability("webSocketUrl", isEnable);
    }

    public String toString() {
        return "DriverConfig(" +
                " browser=" + this.getBrowser() +
                ", capabilities=" + this.getCapabilities() +
                ", remoteURL=" + this.getRemoteURL() +
                ", headless=" + this.isHeadless() +
                ", timeout=" + this.getTimeout() +
                ", windowMaximize=" + this.isWindowMaximize() +
                ", binary=" + this.getBinary() + " )";
    }
}
