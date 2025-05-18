package com.senelium.factories.capabilities;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;

import java.util.logging.Level;

@FunctionalInterface
public interface CapabilitiesFactory<T extends MutableCapabilities> {
    default LoggingPreferences getLoggingPreferences() {
        var logPref = new LoggingPreferences();
        logPref.enable(LogType.PERFORMANCE, Level.ALL);
        logPref.enable(LogType.BROWSER, Level.ALL);
        return logPref;
    }

    T createCapabilities();
}
