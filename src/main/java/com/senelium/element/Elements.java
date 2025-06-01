package com.senelium.element;

import com.senelium.Sel;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Elements {
    private final By locator;

    public Elements(By locator) {
        this.locator = locator;
    }

    public static Elements by(By locator) {
        return new Elements(locator);
    }

    public static Elements byText(String text) {
        return new Elements(By.xpath("//*[text()=\"" + text + "\"]"));
    }

    public static Elements byXpath(String xpath) {
        return new Elements(By.xpath(xpath));
    }

    public static Elements byXpath(String xpath, String... formatArgs) {
        return new Elements(By.xpath(String.format(xpath, (Object[]) formatArgs)));
    }

    public static Elements byCssSelector(String cssSelector) {
        return new Elements(By.cssSelector(cssSelector));
    }

    public static Elements byCssSelector(String cssSelector, String... formatArgs) {
        return new Elements(By.cssSelector(String.format(cssSelector, (Object[]) formatArgs)));
    }

    public static Elements byId(String id) {
        return new Elements(By.id(id));
    }

    public static Elements byClass(String className) {
        return new Elements(By.className(className));
    }

    public static Elements byLinkText(String link) {
        return new Elements(By.linkText(link));
    }

    public static Elements byPartialLinkText(String partialLinkText) {
        return new Elements(By.partialLinkText(partialLinkText));
    }

    public static Elements byTag(String tag) {
        return new Elements(By.tagName(tag));
    }

    public static Elements byName(String name) {
        return new Elements(By.name(name));
    }

    public By getLocator() {
        return this.locator;
    }

    public List<WebElement> findElements() {
        return findElements(null);
    }

    public List<WebElement> findElements(Integer timeout) {
        try {
            return waiter(timeout).until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        } catch (TimeoutException e) {
            // Return an empty list instead of throwing TimeoutException
            return Collections.emptyList();
        }
    }

    public List<WebElement> findVisibleElements() {
        return this.findVisibleElements(null);
    }

    // Wait for all presented elements are visible, if timeout return the current visible elements.
    public List<WebElement> findVisibleElements(Integer timeout) {
        try {
            return waiter(timeout).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
        } catch (TimeoutException e) {
            return findElements(timeout).stream().filter(WebElement::isDisplayed).collect(Collectors.toList());
        }
    }

    public int count() {
        return this.count(null);
    }

    public int count(Integer timeout) {
        return findElements(timeout).size();
    }

    public int countVisible() {
        return countVisible(null);
    }

    public int countVisible(Integer timeout) {
        return (int) findElements(timeout).stream().filter(WebElement::isDisplayed).count();
    }

    public List<String> getAllTexts() {
        return getAllTexts(false);
    }

    public List<String> getAllTexts(boolean force) {
        List<WebElement> elements = force ? findElements() : findVisibleElements();
        return elements.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    protected WebDriverWait waiter() {
        return this.waiter(null);
    }

    protected WebDriverWait waiter(Integer timeout) {
        return timeout != null ? Sel.waiter(timeout) : Sel.defaultWaiter();
    }

    protected Actions actions() {
        return Sel.actions();
    }
}
