package com.senelium.config;

import com.senelium.factories.capabilities.manager.CapsFactoryManager;
import com.senelium.utils.ConfigUtils;
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

    private DriverConfig() {
        this.browser = ConfigUtils.get("browser", "BROWSER","chrome");
        this.remoteURL = ConfigUtils.get("remoteURL", "REMOTE_URL", "");
        this.headless = Boolean.parseBoolean(ConfigUtils.get("headless", "HEADLESS_MODE", "true"));
        this.timeout = new Timeout();
        this.timeout.setPageLoad(Integer.parseInt(ConfigUtils.get("pageLoadTimeout", "PAGE_LOAD_TIMEOUT", "60000")));
        this.timeout.setElementWait(Integer.parseInt(ConfigUtils.get("elementWaitTimeout", "ELEMENT_WAIT_TIMEOUT", "5000")));
        this.timeout.setInterval(Integer.parseInt(ConfigUtils.get("interval", "INTERVAL", "200")));
        this.windowMaximize = Boolean.parseBoolean(ConfigUtils.get("windowMaximize", "WINDOW_MAXIMIZE", "true"));
        this.binary = ConfigUtils.get("binary", "BINARY","");
        this.capabilities = this.getDefaultCapabilities();
    }

    // This method returns the default configuration, use Sel.getDriverConfig() to get the current driver configuration.
    public static DriverConfig getInfo() {
        return new DriverConfig();
    }

    public URL getRemoteAddress() {
        return UrlUtils.newUrl(this.remoteURL);
    }

    private MutableCapabilities getDefaultCapabilities() {
        var capabilities = CapsFactoryManager.findFactory(this.browser).createCapabilities();
        var isBiDiEnabled = "true".equalsIgnoreCase(ConfigUtils.get("enableBiDi", "ENABLE_BIDI", "true"));
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
