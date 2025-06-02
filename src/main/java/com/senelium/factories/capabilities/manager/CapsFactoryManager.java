package com.senelium.factories.capabilities.manager;

import com.senelium.factories.capabilities.*;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.MutableCapabilities;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@Slf4j
public class CapsFactoryManager {
    private final Map<String, Supplier<CapabilitiesFactory<? extends MutableCapabilities>>> factories;

    private CapsFactoryManager() {
        factories = new HashMap<>();
        factories.put("chrome", ChromeCapsFactory::new);
        factories.put("firefox", FirefoxCapsFactory::new);
        factories.put("edge", EdgeCapsFactory::new);
        factories.put("chromeMobile", ChromeMobileCapsFactory::new);
    }

    private final static class InstanceHolder {
        private static final CapsFactoryManager instance = new CapsFactoryManager();
    }

    public static CapsFactoryManager getInstance() {
        return InstanceHolder.instance;
    }

    public static CapabilitiesFactory<? extends MutableCapabilities> findFactory(String browser) {
        Supplier<CapabilitiesFactory<? extends MutableCapabilities>> temp = getFactories().get(browser);
        if (temp == null) {
            log.warn(String.format("No available Capabilities factory for \"%s\". " +
                            "Will return a MutableCapabilities object. " +
                            "To remove this warning, register a new one with method registerFactory. " +
                            "Or refer to these available factories: %s.",
                    browser, getAvailableFactory()));
            temp = () -> MutableCapabilities::new;
        }
        return temp.get();
    }

    public static synchronized void registerFactory(String key, Supplier<CapabilitiesFactory<? extends MutableCapabilities>> supplier) {
        if (getFactories().containsKey(key.toLowerCase())) {
            throw new RuntimeException("The key \"" + key + "\" already exists. Existing key(s) are" + getAvailableFactory());
        }
        getFactories().put(key, supplier);
    }

    private static Map<String, Supplier<CapabilitiesFactory<? extends MutableCapabilities>>> getFactories() {
        return getInstance().factories;
    }

    public static String getAvailableFactory() {
        return String.join(", ", getFactories().keySet());
    }
}
