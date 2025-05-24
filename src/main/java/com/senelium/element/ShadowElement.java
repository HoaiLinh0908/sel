package com.senelium.element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ShadowElement extends Element {
    private final Element hostLocator;

    // For nestedLocator, you should use By.cssSelector() or By.id(). Using others can lead to InvalidArgumentException
    public ShadowElement(Element hostLocator, By nestedLocator) {
        super(nestedLocator);
        this.hostLocator = hostLocator;
    }

    public static ShadowElement byCssSelector(Element hostLocator, String cssSelector) {
        return new ShadowElement(hostLocator, By.cssSelector(cssSelector));
    }

    public static ShadowElement byId(Element hostLocator, String id) {
        return new ShadowElement(hostLocator, By.id(id));
    }

    public WebElement findShadowElement() {
        return waiter().until(ExpectedConditions.visibilityOf(hostLocator.getShadowRoot().findElement(locator)));
    }

    // TODO: need improvement
    public void click() {
        waiter().until(ExpectedConditions.elementToBeClickable(findShadowElement()));
    }
}
