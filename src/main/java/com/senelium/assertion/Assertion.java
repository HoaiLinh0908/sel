package com.senelium.assertion;

import com.senelium.element.Element;
import com.senelium.reports.AllureReport;
import lombok.Setter;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

@Setter
public class Assertion {
    private Element element;
    private boolean isSoft;
    private List<String> errors;

    public Assertion(boolean isSoft) {
        this.isSoft = isSoft;
        if (isSoft) {
            errors = new ArrayList<>();
        }
    }

    public Assertion expect(Element element) {
        this.element = element;
        return this;
    }

    public void assertAll() {
        if (isSoft && errors != null && !errors.isEmpty()) {
            throw new AssertionError(String.join("\n", errors));
        }
    }

    public void mergeAssert(Assertion assertion) {
        this.errors.addAll(assertion.errors);
    }

    public void toBeVisible() {
        toBeVisible("");
    }

    public void toBeVisible(String message) {
        toBeVisible(message, null);
    }

    public void toBeVisible(String message, Integer timeout) {
        try {
            waitFor(ExpectedConditions.visibilityOfElementLocated(element.getLocator()), timeout);
        } catch (TimeoutException e) {
            handleFailedCheck(
                    "visible",
                    "invisible",
                    message + "\nElement [" + element.getLocator() + "] is expected to be visible but found invisible.",
                    timeout);
        }
    }

    private <T> void handleFailedCheck(T expected, T actual, String message, Integer timeout) {
        String logMessage = SeAssert.composeMessage(expected, actual, message, timeout);
        AllureReport.takeScreenshot();
        if (isSoft) {
            errors.add(logMessage);
        } else {
            throw new AssertionError(logMessage);
        }
    }

    public void toBeInvisible() {
        toBeInvisible("");
    }

    public void toBeInvisible(String message) {
        toBeInVisible(message, null);
    }

    public void toBeInVisible(String message, Integer timeout) {
        try {
            waitFor(ExpectedConditions.invisibilityOfElementLocated(element.getLocator()), timeout);
        } catch (TimeoutException e) {
            handleFailedCheck(
                    "invisible",
                    "visible",
                    message + "\nElement [" + element.getLocator() + "] is expected to be invisible but found visible.",
                    timeout);
        }
    }

    public void toHaveText(String expectedText) {
        toHaveText(expectedText, "", null);
    }

    public void toHaveText(String expectedText, String message, Integer timeout) {
        try {
            //Get text already get the visible text
            waitFor(ExpectedConditions.textToBe(element.getLocator(), expectedText), timeout);
        } catch (TimeoutException e) {
            handleFailedCheck(
                    expectedText,
                    element.getText(true),
                    String.format("%s\nElement [%s] is expected to have text {%s} but it does not.", message, element.getLocator(), expectedText),
                    timeout);
        }
    }

    public void toNotHaveText(String oldText) {
        toNotHaveText(oldText, "", null);
    }

    public void toNotHaveText(String oldText, String message, Integer timeout) {
        try {
            waitFor(ExpectedConditions.not(ExpectedConditions.textToBe(element.getLocator(), oldText)), timeout);
        } catch (TimeoutException e) {
            handleFailedCheck(
                    "not have text " + oldText,
                    element.getText(),
                    String.format("%s\nElement [%s] is expected to not have text {%s} but it does.", message, element.getLocator(), oldText),
                    timeout);
        }
    }

    public void imgToBeVisible() {
        this.imgToBeVisible("", null);
    }

    public void imgToBeVisible(String message, Integer timeout) {
        try {
            waitFor(ExpectedConditions.not(ExpectedConditions.domPropertyToBe(element.findVisibleElement(), "naturalWidth", "0")), timeout);
        } catch (TimeoutException e) {
            handleFailedCheck(
                    "visible",
                    "invisible or broken",
                    message + "\nImage element [" + element.getLocator() + "] is expected to be visible but found invisible or broken.",
                    timeout);
        }
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
        try {
            waitFor(ExpectedConditions.elementToBeSelected(element.getLocator()), timeout);
        } catch (TimeoutException e) {
            handleFailedCheck(
                    "selected",
                    "unselected",
                    message + "\nElement [" + element.getLocator() + "] is expected to be selected but found unselected.",
                    timeout);
        }
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
        try {
            waitFor(ExpectedConditions.elementSelectionStateToBe(this.element.getLocator(), false), timeout);
        } catch (TimeoutException e) {
            handleFailedCheck(
                    "unselected",
                    "selected",
                    message + "\nElement [" + element.getLocator() + "] is expected to be unselected but found selected.",
                    timeout);
        }
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
        try {
            waitFor(ExpectedConditions.elementToBeClickable(element.getLocator()), timeout);
        } catch (TimeoutException e) {
            handleFailedCheck(
                    "enabled",
                    "disabled",
                    message + "\nElement [" + element.getLocator() + "] is expected to be enabled but found disabled.",
                    timeout);
        }
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
        try {
            waitFor(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(element.getLocator())), timeout);
        } catch (TimeoutException e) {
            handleFailedCheck(
                    "disabled",
                    "enabled",
                    message + "\nElement [" + element.getLocator() + "] is expected to be disabled but found enabled.",
                    timeout);
        }
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
        try {
            waitFor(ExpectedConditions.presenceOfElementLocated(element.getLocator()), timeout);
        } catch (TimeoutException e) {
            handleFailedCheck(
                    "existing in the DOM",
                    "not existing",
                    message + "\nElement [" + element.getLocator() + "] is expected to be existing but not.",
                    timeout);
        }
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
        try {
            //From findElement document: ...findElement should not be used to look for non-present elements,
            //use findElements(By) and assert zero length response instead.
            waitFor(ExpectedConditions.not(ExpectedConditions.presenceOfAllElementsLocatedBy(element.getLocator())), timeout);
        } catch (TimeoutException e) {
            handleFailedCheck(
                    "not existing in the DOM",
                    "existing",
                    message + "\nElement [" + element.getLocator() + "] is expected to be not existing in the DOM but existing.",
                    timeout);
        }
    }

    public <T> T waitFor(ExpectedCondition<T> expectedCondition, Integer timeout) {
        return SeAssert.waiter(timeout).until(expectedCondition);
    }
}
