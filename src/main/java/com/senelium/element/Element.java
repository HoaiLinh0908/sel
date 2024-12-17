package com.senelium.element;

import com.senelium.Sel;
import com.senelium.utils.CustomExpectedConditions;
import lombok.Getter;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.openqa.selenium.support.ui.*;

import java.util.*;
import java.util.stream.Collectors;

@Getter
public class Element {
    private final By locator;

    public Element(By locator) {
        this.locator = locator;
    }

    public static Element by(By locator) {
        return new Element(locator);
    }

    public static Element byText(String text) {
        return new Element(By.xpath("//*[text()=\"" + text + "\"]"));
    }

    public static Element byXpath(String xpath) {
        return new Element(By.xpath(xpath));
    }

    public static Element byXpath(String xpath, String... formatArgs) {
        return new Element(By.xpath(String.format(xpath, (Object[]) formatArgs)));
    }

    public static Element byCssSelector(String cssSelector) {
        return new Element(By.cssSelector(cssSelector));
    }

    public static Element byCssSelector(String cssSelector, String... formatArgs) {
        return new Element(By.cssSelector(String.format(cssSelector, (Object[]) formatArgs)));
    }

    public static Element byId(String id) {
        return new Element(By.id(id));
    }

    public static Element byClass(String className) {
        return new Element(By.className(className));
    }

    public static Element byLinkText(String link) {
        return new Element(By.linkText(link));
    }

    public static Element byPartialLinkText(String partialLinkText) {
        return new Element(By.partialLinkText(partialLinkText));
    }

    public static Element byTag(String tag) {
        return new Element(By.tagName(tag));
    }

    public static Element byName(String name) {
        return new Element(By.name(name));
    }

    public By getLocator() {
        return this.locator;
    }

    public Element getChild(By childLocator) {
        By fullChildLocator = new ByChained(this.locator, childLocator);
        return Element.by(fullChildLocator);
    }

    public WebElement findElement() {
        return findElement(null);
    }

    public WebElement findElement(Integer timeout) {
        return waiter(timeout).until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public WebElement findVisibleElement() {
        return findVisibleElement(null);
    }

    public WebElement findVisibleElement(Integer timeout) {
        return waiter(timeout).until(ExpectedConditions.visibilityOfElementLocated(locator));
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
        return findVisibleElements(null);
    }

    // The ExpectedConditions.visibilityOfAllElementsLocatedBy() will throw TimeoutException if there is any invisible element.
    // That is not what I expect. I expect a list of visible elements even there are some invisible elements on the DOM.
    public List<WebElement> findVisibleElements(Integer timeout) {
        List<WebElement> elements = findElements(timeout);
        List<WebElement> visibleElements = new ArrayList<>();
        for (WebElement element : elements) {
            try {
                waiter(timeout).until(ExpectedConditions.visibilityOf(element));
                visibleElements.add(element);
            } catch (TimeoutException ignored) {
                // Ignore invisible elements
            }
        }
        return visibleElements;
    }

    public int countVisibleElements() {
        return countVisibleElements(null);
    }

    public int countVisibleElements(Integer timeout) {
        return findVisibleElements(timeout).size();
    }

    public boolean isVisible() {
        return isVisible(null);
    }

    public boolean isVisible(Integer timeout) {
        try {
            findVisibleElement(timeout);
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    public boolean isExisted() {
        return isExisted(null);
    }

    public boolean isExisted(Integer timeout) {
        try {
            waiter(timeout).until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    public boolean isEnabled() {
        return isEnabled(null);
    }

    public boolean isEnabled(Integer timeout) {
        try {
            waiter(timeout).until(ExpectedConditions.elementToBeClickable(this.locator));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    public boolean isSelected() {
        return isSelected(null);
    }

    public boolean isSelected(Integer timeout) {
        try {
            waiter(timeout).until(ExpectedConditions.elementToBeSelected(this.locator));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    public boolean isNotSelected() {
        return isNotSelected(null);
    }

    public boolean isNotSelected(Integer timeout) {
        try {
            waiter(timeout).until(ExpectedConditions.elementSelectionStateToBe(this.locator, false));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    public void click() {
        click(null);
    }

    public void click(Integer timeout) {
        findClickableElement(timeout).click();
    }

    public void rightClick() {
        rightClick(null);
    }

    public void rightClick(Integer timeout) {
        WebElement element = findClickableElement(timeout);
        actions().contextClick(element).perform();
    }

    private WebElement findClickableElement(Integer timeout) {
        return waiter(timeout).until(ExpectedConditions.elementToBeClickable(this.locator));
    }

    public void clickByJs() {
        Sel.executeJavascript("arguments[0].click();", findVisibleElement());
    }

    public void clearText() {
        findVisibleElement().clear();
    }

    public void type(String keys) {
        type(keys, false);
    }

    public void clearAndType(String keys) {
        type(keys, true);
    }

    public void type(String keys, boolean clear) {
        if (clear) {
            this.clearText();
        }
        findVisibleElement().sendKeys(keys);
    }

    public void dragAndDropTo(Element targetElement) {
        WebElement element = findVisibleElement();
        actions().dragAndDrop(element, targetElement.findElement()).perform();
    }

    public void dragAndDropTo(int x, int y) {
        WebElement element = findVisibleElement();
        actions().clickAndHold(element).moveToLocation(x, y).release().perform();
    }

    public void setValue(String value) {
        Sel.executeJavascript(String.format("arguments[0].value = \"%s\";", value), findVisibleElement());
    }

    public void scrollToView() {
        actions().scrollToElement(findVisibleElement()).perform();
    }

    public void hover() {
        actions().moveToElement(findVisibleElement());
    }

    public void submitForm() {
        findVisibleElement().submit();
    }

    public String getTagName() {
        return findElement().getTagName();
    }

    public boolean isTag(String tagName) {
        return this.getTagName().equalsIgnoreCase(tagName);
    }

    public String getText() {
        return getText(false);
    }

    // If 'force' is true then do not wait until visible
    public String getText(boolean force) {
        return force ? findElement().getText() : findVisibleElement().getText();
    }

    public List<String> getAllTexts() {
        return getAllTexts(false);
    }

    public List<String> getAllTexts(boolean force) {
        List<WebElement> elements = force ? findElements() : findVisibleElements();
        return elements.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public String getValue() {
        return getAttribute("value");
    }

    public String getAttribute(String name) {
        return findElement().getAttribute(name);
    }

    public String getDomAttribute(String name) {
        return findElement().getDomAttribute(name);
    }

    public String getProperty(String name) {
        return findElement().getDomProperty(name);
    }

    public String getAriaRole() {
        return findElement().getAriaRole();
    }

    public String getAccessibleName() {
        return findElement().getAccessibleName();
    }

    public SearchContext getShadowRoot() {
        return findElement().getShadowRoot();
    }

    public Point getLocation() {
        return findVisibleElement().getLocation();
    }

    public Dimension getSize() {
        return findElement().getSize();
    }

    public Rectangle getRect() {
        return findElement().getRect();
    }

    public String getCssValue(String propertyName) {
        return findElement().getCssValue(propertyName);
    }

    //Apply this for other waits
    public <T> T waitFor(ExpectedCondition<T> expectedCondition, Integer timeout) {
        return waiter(timeout).until(expectedCondition);
    }

    public void waitForExisting() {
        waitForExisting(null);
    }

    public void waitForExisting(Integer timeout) {
        waitFor(ExpectedConditions.presenceOfElementLocated(locator), timeout);
    }

    public void waitForNotExisting() {
        waitForNotExisting(null);
    }

    /*
     From findElement document: ...findElement should not be used to look for non-present elements,
     use findElements(By) and assert zero length response instead.
     */
    public void waitForNotExisting(Integer timeout) {
        waitFor(ExpectedConditions.not(ExpectedConditions.presenceOfAllElementsLocatedBy(locator)), timeout);
    }

    public void waitForVisible() {
        this.waitForVisible(null);
    }

    public void waitForVisible(Integer timeout) {
        this.findVisibleElement(timeout);
    }

    public void waitForEnabled() {
        this.waitForEnabled(null);
    }

    public void waitForEnabled(Integer timeout) {
        this.findClickableElement(timeout);
    }

    public void waitForDisabled() {
        this.findClickableElement(null);
    }

    public void waitForDisabled(Integer timeout) {
        this.waiter(timeout).until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(locator)));
    }

    public void waitForInvisible() {
        this.waitForInvisible(null);
    }

    public void waitForInvisible(Integer timeout) {
        waitFor(ExpectedConditions.invisibilityOfElementLocated(locator), timeout);
    }

    public void waitForTextToBe(String expectText) {
        this.waitForTextToBe(expectText, null);
    }

    public void waitForTextToBe(String expectText, Integer timeout) {
        waiter(timeout).until(ExpectedConditions.textToBePresentInElementLocated(locator, expectText));
    }

    public void waitForStopMoving() {
        waitForStopMoving(null);
    }

    public void waitForStopMoving(Integer timeout) {
        waiter(timeout).until(CustomExpectedConditions.elementToStopMoving(locator));
    }

    private WebDriverWait waiter() {
        return this.waiter(null);
    }

    private WebDriverWait waiter(Integer timeout) {
        return timeout != null ? Sel.getWaiter(timeout) : Sel.getDefaultWaiter();
    }

    private Actions actions() {
        return Sel.getActions();
    }
}
