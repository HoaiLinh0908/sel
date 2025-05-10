package com.senelium;

import com.senelium.assertion.SelAssert;
import com.senelium.config.DomainInfo;
import com.senelium.config.DriverConfig;
import com.senelium.listener.TestListener;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Credentials;
import org.testng.annotations.*;

@Slf4j
@Listeners(TestListener.class)
public class TestBase {
    protected DomainInfo domainInfo;
    protected DriverConfig driverConfig;

    protected void open(String url) {
        Sel.open(url);
    }

    protected void open(String url, Credentials credentials) {
        Sel.open(url, credentials);
    }

    protected void refreshPage() {
        Sel.refresh();
    }

    @BeforeClass(alwaysRun = true)
    public void initialTest() {
        domainInfo = getDomainInfo();
        driverConfig = getDriverConfig();
        Sel.createDriver(driverConfig);
    }

    private DriverConfig getDriverConfig() {
        var config = DriverConfig.getInfo();
        updateDriverConfig(config);
        return config;
    }

    // Override this method if your tests need different driver configurations
    protected void updateDriverConfig(DriverConfig config) {
        // Nothing here
    }

    private DomainInfo getDomainInfo() {
        var info = DomainInfo.getInfo();
        updateDomainInfo(info);
        return info;
    }

    // Override this method if you tests need different domain information
    protected void updateDomainInfo(DomainInfo info) {
        // Nothing here
    }

    @AfterClass(alwaysRun = true)
    public void cleanUp() {
        Sel.closeBrowser();
    }
}
