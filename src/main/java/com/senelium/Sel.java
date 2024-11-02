package com.senelium;

import com.senelium.config.DriverConfig;
import com.senelium.factories.driver.DriverFactory;
import com.senelium.factories.driver.SelDriver;
import com.senelium.factories.driver.manager.DriverFactoryManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Sel {
    private static final ThreadLocal<SelDriver> threadWebDriver = new ThreadLocal<>();

    private Sel() {
    }

    public static void createDriver(DriverConfig config) {
        DriverFactory<?> driverFactory = DriverFactoryManager.findFactory(config.getBrowser());
        threadWebDriver.set(driverFactory.createDriver(config));
        log.info("Successfully created driver with configuration {}", config);
    }

    public static SelDriver selDriver() {
        if (threadWebDriver.get() == null) {
            throw new RuntimeException("Driver not found. Driver might not be initialized.");
        }
        return threadWebDriver.get();
    }

    public static WebDriver webDriver() {
        return selDriver().getWebDriver();
    }

    public static Actions getActions() {
        return selDriver().getActions();
    }

    public static WebDriverWait getDefaultWaiter() {
        return selDriver().getDefaultWaiter();
    }

    public static WebDriverWait getWaiter(int mil) {
        if (mil < 0) throw new InvalidArgumentException("Waiter timeout must be or greater than 0.");
        return Sel.selDriver().getWaiter(Duration.ofMillis(mil));
    }

    public static void open(String url) {
        //support 'baseUrl'?
        log.info("Navigate to [{}]", url);
        webDriver().get(url);
    }

    public static void open(String url, String username, String password) {
        String protocol = url.split(":")[0];
        String authUrl = url.replaceFirst(protocol + "://", protocol + "://" + username + ":" + password + "@");
        log.info("Navigate with basic authentication to [{}]", authUrl);
        webDriver().get(authUrl);
    }

    public static void closeBrowser() {
        log.info("Quit the driver");
        webDriver().quit();
        threadWebDriver.remove();
    }

    public static void closeCurrentTab() {
        webDriver().close();
    }

    public static void freeze(long mil) {
        try {
            Thread.sleep(mil);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void refresh() {
        webDriver().navigate().refresh();
    }

    public static Cookie getCookie(String name) {
        return webDriver().manage().getCookieNamed(name);
    }

    public static List<Cookie> getCookies() {
        return new ArrayList<>(webDriver().manage().getCookies());
    }

    public static void addCookie(Cookie cookie) {
        webDriver().manage().addCookie(cookie);
    }

    public static void clearCookie(String name) {
        webDriver().manage().deleteCookieNamed(name);
    }

    public static void clearCookies() {
        webDriver().manage().deleteAllCookies();
    }

    public static Alert toAlert() {
        return Sel.getDefaultWaiter().until(ExpectedConditions.alertIsPresent());
    }

    public static Object executeJavascript(String script, Object... args) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver();
        return jsExecutor.executeScript(script, args);
    }

    public static Object executeAsyncJavascript(String script, Object... args) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver();
        return jsExecutor.executeAsyncScript(script, args);
    }

    public static void closeCurrentAndSwitchToNewTab() {
        closeCurrentTab();
        webDriver().switchTo().window(new ArrayList<>(webDriver().getWindowHandles()).get(0));
    }

    public static void scrollToElement(WebElement element) {
        getActions().scrollToElement(element).perform();
    }

    public static void switchToFrame(int index) {
        webDriver().switchTo().frame(index);
    }

    public static void switchToMainWindow() {
        webDriver().switchTo().defaultContent();
    }
}
