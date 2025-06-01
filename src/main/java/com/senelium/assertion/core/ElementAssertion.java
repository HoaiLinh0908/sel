package com.senelium.assertion.core;

import com.senelium.element.CustomExpectedConditions;
import com.senelium.element.Element;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Getter
@Setter
@Accessors(fluent = true)
public class ElementAssertion extends Assertion {
    private Element element;

    public ElementAssertion() {
        super(false);
    }

    public ElementAssertion(Element element, boolean isSoft) {
        super(isSoft);
        this.element = element;
    }

    public void toBeVisible() {
        toBeVisible("");
    }

    public void toBeVisible(String message) {
        toBeVisible(message, null);
    }

    public void toBeVisible(String message, Integer timeout) {
        this.builder().condition(ExpectedConditions.visibilityOfElementLocated(element.getLocator()))
                .timeout(timeout)
                .message(new AssertMessage("%s\nElement [%s] is expected to be visible but found invisible.".formatted(message, element.getLocator()),
                        "visible", "invisible"))
                .execute();
    }

    public void toBeHidden() {
        toBeHidden("", null);
    }

    public void toBeHidden(String message) {
        toBeHidden(message, null);
    }

    public void toBeHidden(Integer timeout) {
        toBeHidden("", timeout);
    }

    public void toBeHidden(String message, Integer timeout) {
        this.builder().condition(ExpectedConditions.invisibilityOfElementLocated(element.getLocator()))
                .timeout(timeout)
                .message(new AssertMessage("%s\nElement [%s] is expected to be invisible but found visible.".formatted(message, element.getLocator()),
                        "invisible", "visible"))
                .execute();
    }

    public void toHaveText(String expectedText) {
        toHaveText(expectedText, "", null);
    }

    public void toHaveText(String expectedText, String message) {
        toHaveText(expectedText, message, null);
    }

    public void toHaveText(String expectedText, Integer timeout) {
        toHaveText(expectedText, "", timeout);
    }

    public void toHaveText(String expectedText, String message, Integer timeout) {
        this.builder().condition(ExpectedConditions.textToBe(element.getLocator(), expectedText))
                .timeout(timeout)
                .message(new AssertMessage("%s\nElement [%s] is expected to have text {%s} but it does not.".formatted(message, element.getLocator(), expectedText),
                        expectedText, () -> element.getText(true)))
                .execute();
    }

    public void toNotHaveText(String oldText) {
        toNotHaveText(oldText, "", null);
    }

    public void toNotHaveText(String oldText, String message) {
        toNotHaveText(oldText, message, null);
    }

    public void toNotHaveText(String oldText, Integer timeout) {
        toNotHaveText(oldText, "", timeout);
    }

    public void toNotHaveText(String oldText, String message, Integer timeout) {
        this.builder().condition(ExpectedConditions.not(ExpectedConditions.textToBe(element.getLocator(), oldText)))
                .timeout(timeout)
                .message(new AssertMessage("%s\nElement [%s] is expected to NOT have text {%s} but it does.".formatted(message, element.getLocator(), oldText),
                        "NOT have text " + oldText, oldText))
                .execute();
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
        this.builder().condition(ExpectedConditions.textToBePresentInElementLocated(element.getLocator(), expectedText))
                .timeout(timeout)
                .message(new AssertMessage("%s\nElement [%s] is expected to contains {%s} but it does not.".formatted(message, element.getLocator(), expectedText),
                        "contains " + expectedText, () -> element.getText(true)))
                .execute();
    }

    public void imgToBeVisible() {
        this.imgToBeVisible("", null);
    }

    public void imgToBeVisible(String message) {
        this.imgToBeVisible(message, null);
    }

    public void imgToBeVisible(Integer timeout) {
        this.imgToBeVisible("", timeout);
    }

    public void imgToBeVisible(String message, Integer timeout) {
        this.builder().condition(CustomExpectedConditions.imageIsVisible(element.findVisibleElement()))
                .timeout(timeout)
                .message(new AssertMessage("%s\nImage element [%s] is expected to be visible but found invisible or broken.".formatted(message, element.getLocator()),
                        "visible ", "invisible or broken"))
                .execute();
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
        this.builder().condition(ExpectedConditions.elementToBeSelected(element.getLocator()))
                .timeout(timeout)
                .message(new AssertMessage("%s\nElement [%s] is expected to be selected but found unselected.".formatted(message, element.getLocator()),
                        "selected ", "not selected"))
                .execute();
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
        this.builder().condition(ExpectedConditions.elementSelectionStateToBe(this.element.getLocator(), false))
                .timeout(timeout)
                .message(new AssertMessage("%s\nElement [%s] is expected to be unselected but found selected.".formatted(message, element.getLocator()),
                        "unselected ", "selected"))
                .execute();
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
        this.builder().condition(ExpectedConditions.elementToBeClickable(element.getLocator()))
                .timeout(timeout)
                .message(new AssertMessage("%s\nElement [%s] is expected to be enabled but found disabled.".formatted(message, element.getLocator()),
                        "enabled ", "disabled"))
                .execute();
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
        this.builder().condition(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(element.getLocator())))
                .timeout(timeout)
                .message(new AssertMessage("%s\nElement [%s] is expected to be disabled but found enabled.".formatted(message, element.getLocator()),
                        "disabled ", "enabled"))
                .execute();
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
        this.builder().condition(ExpectedConditions.presenceOfElementLocated(element.getLocator()))
                .timeout(timeout)
                .message(new AssertMessage("%s\nElement [%s] is expected to be existing on DOM but not.".formatted(message, element.getLocator()),
                        "present in the DOM ", "not presenting"))
                .execute();
    }

    public void toBeNotExisting() {
        toBeNotExisting("", null);
    }

    public void toBeNotExisting(String message) {
        toBeNotExisting(message, null);
    }

    public void toBeNotExisting(Integer timeout) {
        toBeNotExisting("", timeout);
    }

    //From findElement document: ...findElement should not be used to look for non-present elements,
    //use findElements(By) and assert zero length response instead.
    public void toBeNotExisting(String message, Integer timeout) {
        this.builder().condition(ExpectedConditions.not(ExpectedConditions.presenceOfAllElementsLocatedBy(element.getLocator())))
                .timeout(timeout)
                .message(new AssertMessage("%s\nElement [%s] is expected to be not existing in the DOM but existing.".formatted(message, element.getLocator()),
                        "not presenting in the DOM", "presenting in the DOM"))
                .execute();
    }

    public void toBeInViewport() {
        this.toBeInViewport("", null);
    }

    public void toBeInViewport(String message) {
        this.toBeInViewport(message, null);
    }

    public void toBeInViewport(Integer timeout) {
        this.toBeInViewport("", timeout);
    }

    public void toBeInViewport(String message, Integer timeout) {
        this.builder().condition(CustomExpectedConditions.withinViewport(element.getLocator()))
                .timeout(timeout)
                .message(new AssertMessage("%s\nElement [%s] is not in the viewport.".formatted(message, element.getLocator()),
                        "Included in the viewport", "Not included in the viewport"))
                .execute();
    }

    public void toBeNotInViewport() {
        this.toBeNotInViewport("", null);
    }

    public void toBeNotInViewport(String message) {
        this.toBeNotInViewport(message, null);
    }

    public void toBeNotInViewport(Integer timeout) {
        this.toBeNotInViewport("", timeout);
    }

    public void toBeNotInViewport(String message, Integer timeout) {
        this.builder().condition(ExpectedConditions.not(CustomExpectedConditions.withinViewport(element.getLocator())))
                .timeout(timeout)
                .message(new AssertMessage("%s\nElement [%s] is in the viewport.".formatted(message, element.getLocator()),
                        "Not included in the viewport", "Included in the viewport"))
                .execute();
    }
}
