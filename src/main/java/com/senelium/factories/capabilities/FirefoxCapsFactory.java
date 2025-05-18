package com.senelium.factories.capabilities;

import com.senelium.utils.FileUtils;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxCapsFactory implements CapabilitiesFactory<FirefoxOptions> {
    @Override
    public FirefoxOptions createCapabilities() {
        var options = new FirefoxOptions();
        options.addArguments("--disable-gpu");
        options.addPreference("browser.download.dir", FileUtils.getDownloadDir());
        options.addPreference("browser.download.folderList", 2); // 0 = desktop, 1 = default download, 2 = custom
        options.setEnableDownloads(true); // For files download in Grid
        return options;
    }
}
