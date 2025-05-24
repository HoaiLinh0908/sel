package com.senelium;

import com.senelium.config.DriverConfig;
import com.senelium.factories.driver.DriverFactory;
import com.senelium.factories.driver.SelDriver;
import com.senelium.factories.driver.manager.DriverFactoryManager;
import com.senelium.utils.UrlUtils;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class Sel {
    private static final ThreadLocal<SelDriver> threadWebDriver = new ThreadLocal<>();

    private Sel() {
    }

    public static void createDriver(DriverConfig config) {
        DriverFactory<?> driverFactory = DriverFactoryManager.findFactory(config.browser());
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

    public static Actions actions() {
        return selDriver().getActions();
    }

    public static WebDriverWait defaultWaiter() {
        return selDriver().getDefaultWaiter();
    }

    public static WebDriverWait waiter(int mil) {
        if (mil < 0) throw new InvalidArgumentException("Waiter timeout must be or greater than 0.");
        return Sel.selDriver().getWaiter(Duration.ofMillis(mil));
    }

    public static DriverConfig driverConfig() {
        return selDriver().getDriverConfig();
    }

    public static void open(String url) {
        String completeUrl = System.getProperty("baseUrl", "") + url;
        log.info("Navigate to [{}]", completeUrl);
        webDriver().get(completeUrl);
    }

    public static void open(String url, Credentials credentials) {
        registerAuthentication(url, credentials);
        open(url);
    }

    public static void registerAuthentication(String url, Credentials credentials) {
        HasAuthentication authentication = (HasAuthentication) webDriver();
        String host = UrlUtils.newUri(url).getHost();
        authentication.register(uri -> uri.getHost().equals(host), () -> credentials);
    }

    public static void closeBrowser() {
        webDriver().quit();
        threadWebDriver.remove();
    }

    public static void closeWindow() {
        webDriver().close();
    }

    public static void freeze(long mil) {
        try {
            Thread.sleep(mil);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String getWindowTitle() {
        return webDriver().getTitle();
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
        return Sel.defaultWaiter().until(ExpectedConditions.alertIsPresent());
    }

    public static Alert toAlert(int timeout) {
        return Sel.waiter(timeout).until(ExpectedConditions.alertIsPresent());
    }

    public static Object executeJavascript(String script, Object... args) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver();
        return jsExecutor.executeScript(script, args);
    }

    public static Object executeAsyncJavascript(String script, Object... args) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver();
        return jsExecutor.executeAsyncScript(script, args);
    }

    public static void openNewTabAndSwitchToIt() {
        webDriver().switchTo().newWindow(WindowType.TAB);
    }

    public static void openNewWindowAndSwitchToIt() {
        webDriver().switchTo().newWindow(WindowType.WINDOW);
    }

    public static void switchToWindow(String handle) {
        webDriver().switchTo().window(handle);
    }

    public static String currentWindowHandle() {
        return webDriver().getWindowHandle();
    }

    public static List<String> getWindowHandles() {
        return new ArrayList<>(webDriver().getWindowHandles());
    }

    public static List<String> getWindowHandles(String title) {
        var origin = currentWindowHandle();
        var handles = webDriver().getWindowHandles();
        var results = handles.stream().filter(h -> {
            switchToWindow(h);
            return getWindowTitle().equals(title);
        }).collect(Collectors.toList());
        // Switch back to the original window
        switchToWindow(origin);
        return results;
    }

    public static void switchToWindowWithTitle(String title) {
        var handles = webDriver().getWindowHandles();
        handles.stream()
                .filter(h -> {
                    switchToWindow(h);
                    return getWindowTitle().equals(title);
                })
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Cannot find any window with title %s".formatted(title)));
    }

    public static void scrollToElement(WebElement element) {
        actions().scrollToElement(element).perform();
    }

    public static void toFrame(int index) {
        webDriver().switchTo().frame(index);
    }

    public static void toFrame(String nameOrId) {
        webDriver().switchTo().frame(nameOrId);
    }

    public static void toFrame(WebElement frameElement) {
        webDriver().switchTo().frame(frameElement);
    }

    public static void switchToMainDocument() {
        webDriver().switchTo().defaultContent();
    }

    public static void sendKey(Keys key) {
        actions().sendKeys(key).perform();
    }

    public static void sendKeys(String keys) {
        actions().sendKeys(keys).perform();
    }
}
