package com.senelium.assertion.core;

import com.senelium.element.Element;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Getter
@Setter
public class ElementAssertion extends Assertion {
    private Element element;

    public ElementAssertion(boolean isSoft) {
        super(isSoft);
    }

    public void toBeVisible() {
        toBeVisible("");
    }

    public void toBeVisible(String message) {
        toBeVisible(message, null);
    }

    public void toBeVisible(String message, Integer timeout) {
        this.toBe(
                ExpectedConditions.visibilityOfElementLocated(element.getLocator()),
                timeout,
                message + "\nElement [" + element.getLocator() + "] is expected to be visible but found invisible.",
                "visible",
                "invisible"
        );
    }

    public void toBeInvisible() {
        toBeInvisible("");
    }

    public void toBeInvisible(String message) {
        toBeInVisible(message, null);
    }

    public void toBeInVisible(String message, Integer timeout) {
        this.toBe(
                ExpectedConditions.invisibilityOfElementLocated(element.getLocator()),
                timeout,
                message + "\nElement [" + element.getLocator() + "] is expected to be invisible but found visible.",
                "invisible",
                "visible"
        );
    }

    public void toHaveText(String expectedText) {
        toHaveText(expectedText, "", null);
    }

    //Get text already get the visible text
    public void toHaveText(String expectedText, String message, Integer timeout) {
        this.toBe(
                ExpectedConditions.textToBe(element.getLocator(), expectedText),
                timeout,
                String.format("%s\nElement [%s] is expected to have text {%s} but it does not.", message, element.getLocator(), expectedText),
                expectedText,
                element.getText(true)
        );
    }

    public void toNotHaveText(String oldText) {
        toNotHaveText(oldText, "", null);
    }

    public void toNotHaveText(String oldText, String message, Integer timeout) {
        this.toBe(
                ExpectedConditions.not(ExpectedConditions.textToBe(element.getLocator(), oldText)),
                timeout,
                String.format("%s\nElement [%s] is expected to not have text {%s} but it does.", message, element.getLocator(), oldText),
                "not have text " + oldText,
                element.getText()
        );
    }

    public void toContainText(String expectedText) {
        toContainText(expectedText, "", null);
    }

    public void toContainText(String expectedText, Integer timeout) {
        toContainText(expectedText, "", timeout);
    }

    public void toContainText(String expectedText, String message) {
        toContainText(expectedText, message, null);
    }

    public void toContainText(String expectedText, String message, Integer timeout) {
        this.toBe(
                ExpectedConditions.textToBePresentInElementLocated(element.getLocator(), expectedText),
                timeout,
                String.format("%s\nElement [%s] is expected to contains {%s} but it does not.", message, element.getLocator(), expectedText),
                expectedText,
                element.getText(true)
        );
    }

    public void imgToBeVisible() {
        this.imgToBeVisible("", null);
    }

    public void imgToBeVisible(String message, Integer timeout) {
        this.toBe(
                ExpectedConditions.not(ExpectedConditions.domPropertyToBe(element.findVisibleElement(), "naturalWidth", "0")),
                timeout,
                message + "\nImage element [" + element.getLocator() + "] is expected to be visible but found invisible or broken.",
                "visible",
                "invisible or broken"
        );
    }

    public void toBeSelected() {
        toBeSelected("", null);
    }

    public void toBeSelected(String message) {
        toBeSelected(message, null);
    }

    public void toBeSelected(Integer timeout) {
        toBeSelected("", timeout);
    }

    public void toBeSelected(String message, Integer timeout) {
        this.toBe(
                ExpectedConditions.elementToBeSelected(element.getLocator()),
                timeout,
                message + "\nElement [" + element.getLocator() + "] is expected to be selected but found unselected.",
                "selected",
                "not selected"
        );
    }

    public void toBeUnselected() {
        toBeUnselected("", null);
    }

    public void toBeUnselected(String message) {
        toBeUnselected(message, null);
    }

    public void toBeUnselected(Integer timeout) {
        toBeUnselected("", timeout);
    }

    public void toBeUnselected(String message, Integer timeout) {
        this.toBe(
                ExpectedConditions.elementSelectionStateToBe(this.element.getLocator(), false),
                timeout,
                message + "\nElement [" + element.getLocator() + "] is expected to be unselected but found selected.",
                "unselected",
                "selected"
        );
    }

    public void toBeEnabled() {
        this.toBeEnabled("", null);
    }

    public void toBeEnabled(String message) {
        this.toBeEnabled(message, null);
    }

    public void toBeEnabled(Integer timeout) {
        this.toBeEnabled("", timeout);
    }

    public void toBeEnabled(String message, Integer timeout) {
        this.toBe(
                ExpectedConditions.elementToBeClickable(element.getLocator()),
                timeout,
                message + "\nElement [" + element.getLocator() + "] is expected to be enabled but found disabled.",
                "enabled",
                "disabled"
        );
    }

    public void toBeDisabled() {
        this.toBeDisabled("", null);
    }

    public void toBeDisabled(String message) {
        this.toBeDisabled(message, null);
    }

    public void toBeDisabled(Integer timeout) {
        this.toBeDisabled("", timeout);
    }

    public void toBeDisabled(String message, Integer timeout) {
        this.toBe(
                ExpectedConditions.not(ExpectedConditions.elementToBeClickable(element.getLocator())),
                timeout,
                message + "\nElement [" + element.getLocator() + "] is expected to be disabled but found enabled.",
                "disabled",
                "enabled"
        );
    }

    public void toBeExisting() {
        toBeExisting("", null);
    }

    public void toBeExisting(String message) {
        toBeExisting(message, null);
    }

    public void toBeExisting(Integer timeout) {
        toBeExisting("", timeout);
    }

    public void toBeExisting(String message, Integer timeout) {
        this.toBe(
                ExpectedConditions.presenceOfElementLocated(element.getLocator()),
                timeout,
                message + "\nElement [" + element.getLocator() + "] is expected to be existing but not.",
                "present in the DOM",
                "not presenting"
        );
    }

    public void toBeNotExisting() {
        toBeNotExisting("", null);
    }

    public void toBeNotExisting(String message) {
        toBeExisting(message, null);
    }

    public void toBeNotExisting(Integer timeout) {
        toBeNotExisting("", timeout);
    }

    public void toBeNotExisting(String message, Integer timeout) {
        this.toBe(
                //From findElement document: ...findElement should not be used to look for non-present elements,
                //use findElements(By) and assert zero length response instead.
                ExpectedConditions.not(ExpectedConditions.presenceOfAllElementsLocatedBy(element.getLocator())),
                timeout,
                message + "\nElement [" + element.getLocator() + "] is expected to be not existing in the DOM but existing.",
                "not presenting in the DOM",
                "presenting"
        );
    }

    private <T> T waitFor(ExpectedCondition<T> expectedCondition, Integer timeout) {
        return this.waiter(timeout).until(expectedCondition);
    }

    private void toBe(ExpectedCondition<?> expectedCondition, Integer timeout, String message, String expected, String actual) {
        super.toBe(() -> this.waitFor(expectedCondition, timeout), timeout, message, expected,actual);
    }
}
