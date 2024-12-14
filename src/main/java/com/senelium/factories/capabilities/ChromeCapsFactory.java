package com.senelium.factories.capabilities;

import com.senelium.utils.FileUtils;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

public class ChromeCapsFactory implements CapabilitiesFactory {

    @Override
    public ChromeOptions createCapabilities() {
        var options = new ChromeOptions();
        options.setCapability(ChromeOptions.LOGGING_PREFS, getLoggingPreferences());
        options.addArguments("--disable-extensions");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-search-engine-choice-screen");
        options.addArguments("--disable-features=OptimizationGuideModelDownloading,OptimizationHintsFetching,OptimizationTargetPrediction,OptimizationHints");
        //options.setCapability("webSocketUrl", true); //Issue with alert and right click when turn this on (ContextMenuTests class)

        // Set download directory
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", FileUtils.getDownloadDir());
        options.setExperimentalOption("prefs", prefs);
        return options;
    }
}
