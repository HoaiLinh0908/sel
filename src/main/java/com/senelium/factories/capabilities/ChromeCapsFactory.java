package com.senelium.factories.capabilities;

import com.senelium.utils.FileUtils;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

public class ChromeCapsFactory implements CapabilitiesFactory {

    @Override
    public ChromeOptions createCapabilities() {
        ChromeOptions options = new ChromeOptions();
        options.setCapability(ChromeOptions.LOGGING_PREFS, getLoggingPreferences());
        options.addArguments("--start-maximized");
        options.addArguments("--disable-extensions");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-gpu");
        //options.setCapability("webSocketUrl", true); //Issue with alert and right click when turn this on (ContextMenuTests class)

        // Set download directory
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", FileUtils.getDownloadDir());
        options.setExperimentalOption("prefs", prefs);
        return options;
    }
}
