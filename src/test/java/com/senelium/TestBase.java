package com.senelium;

import com.senelium.config.DriverConfig;
import com.senelium.config.TestConfig;
import com.senelium.listener.TestListener;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.*;

@Slf4j
@Listeners(TestListener.class)
public class TestBase {
    protected TestConfig testConfig;
    protected DriverConfig driverConfig;

    protected void open(String url) {
        Sel.open(url);
    }

    @BeforeClass(alwaysRun = true)
    public void initialTest() {
        testConfig = TestConfig.getInstance();
        driverConfig = DriverConfig.getInstance();
        Sel.createDriver(driverConfig);
    }

    @AfterClass(alwaysRun = true)
    public void cleanUp() {
        Sel.closeBrowser();
    }
}
