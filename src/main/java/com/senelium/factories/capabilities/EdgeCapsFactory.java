package com.senelium.factories.capabilities;

import org.openqa.selenium.edge.EdgeOptions;

public class EdgeCapsFactory implements CapabilitiesFactory<EdgeOptions> {
    @Override
    public EdgeOptions createCapabilities() {
        var options = new EdgeOptions();
        options.setEnableDownloads(true); // For files download in Grid
        return options;
    }
}
