package com.senelium.config;

import com.senelium.factories.capabilities.manager.CapsFactoryManager;
import com.senelium.utils.ConfigUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.MutableCapabilities;

@Getter
@Setter
@Accessors(fluent = true)
public class DriverConfig {
    private String browser;
    private MutableCapabilities capabilities;
    private String remoteURL;
    private boolean headless;
    private Timeout timeout;
    private Dimension windowSize;
    private String binary;
    private boolean isBiDiEnabled;

    private DriverConfig() {
        this.browser = ConfigUtils.get("browser", "BROWSER", "chrome");
        this.remoteURL = ConfigUtils.get("remoteURL", "REMOTE_URL", "");
        this.headless = Boolean.parseBoolean(ConfigUtils.get("headless", "HEADLESS_MODE", "true"));
        this.timeout = new Timeout();
        this.timeout.pageLoad(Integer.parseInt(ConfigUtils.get("pageLoadTimeout", "PAGE_LOAD_TIMEOUT", "60000")));
        this.timeout.elementWait(Integer.parseInt(ConfigUtils.get("elementWaitTimeout", "ELEMENT_WAIT_TIMEOUT", "5000")));
        this.timeout.interval(Integer.parseInt(ConfigUtils.get("interval", "INTERVAL", "200")));
        this.windowSize = this.getWindowDimensionConfig();
        this.binary = ConfigUtils.get("binary", "BINARY", "");
        this.isBiDiEnabled = "true".equalsIgnoreCase(ConfigUtils.get("enableBiDi", "ENABLE_BIDI", "true"));
        this.capabilities = CapsFactoryManager.findFactory(this.browser).createCapabilities();
    }

    // This method returns the default configuration, use Sel.getDriverConfig() to get the current driver configuration.
    public static DriverConfig defaultConfig() {
        return new DriverConfig();
    }

    private Dimension getWindowDimensionConfig() {
        var dimension = ConfigUtils.get("windowSize", "WINDOW_SIZE", "1920x1080");
        if (dimension.matches("^\\d{3,5}x\\d{3,5}$")) {
            var value = dimension.split("x");
            return new Dimension(Integer.parseInt(value[0]), Integer.parseInt(value[1]));
        }
        throw new IllegalArgumentException("Invalid format for window size. Expected format: widthxheight - e.g. 1920x1080");
    }

    public String toString() {
        return "DriverConfig(" +
                " browser=" + this.browser() +
                ", capabilities=" + this.capabilities() +
                ", remoteURL=" + this.remoteURL() +
                ", headless=" + this.headless() +
                ", timeout=" + this.timeout() +
                ", windowSize=" + this.windowSize() +
                ", binary=" + this.binary() + " )";
    }
}
