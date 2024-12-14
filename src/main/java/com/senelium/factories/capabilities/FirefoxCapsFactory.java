package com.senelium.factories.capabilities;

import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxCapsFactory implements CapabilitiesFactory {
    @Override
    public FirefoxOptions createCapabilities() {
        var options = new FirefoxOptions();
        options.setCapability("gpu", false);

        String downloadFilePath = "path/to/download/directory";
        options.addPreference("browser.download.dir", downloadFilePath);
        options.addPreference("browser.download.folderList", 2); // 0 = desktop, 1 = default download, 2 = custom

        return options;
    }
}
