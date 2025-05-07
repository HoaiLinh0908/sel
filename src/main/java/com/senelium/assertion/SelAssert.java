package com.senelium.assertion;

import com.senelium.assertion.core.AlertAssertion;
import com.senelium.assertion.core.ElementAssertion;
import com.senelium.assertion.core.FileAssertion;
import com.senelium.element.Element;
import com.senelium.reports.SelReport;

public class SelAssert {
    // Store in ThreadLocal to reduce the number of created objects.
    private static final ThreadLocal<ElementAssertion> elementAssertionThread = new ThreadLocal<>();
    private static final ThreadLocal<FileAssertion> fileAssertionThread = new ThreadLocal<>();
    // AlertAssertion with isSoft = false does not hold any risk -> One object is sufficient.
    private static final AlertAssertion alertAssertion = new AlertAssertion(false);

    public static ElementAssertion element(Element element) {
        initThreadLocal(elementAssertionThread, new ElementAssertion(false));
        elementAssertionThread.get().setElement(element);
        return elementAssertionThread.get();
    }

    public static AlertAssertion alert() {
        return alertAssertion;
    }

    public static FileAssertion file(String path) {
        initThreadLocal(fileAssertionThread, new FileAssertion(false));
        fileAssertionThread.get().setPath(path);
        return fileAssertionThread.get();
    }

    private static <T> void initThreadLocal(ThreadLocal<T> threadLocal, T value) {
        if (threadLocal.get() == null) {
            threadLocal.set(value);
        }
    }

    public static void assertTrue(boolean actual, String message) {
        if (!actual) {
            onFailedCheck(composeMessage(true, false, message));
        }
    }

    public static void assertFalse(boolean actual, String message) {
        if (actual) {
            onFailedCheck(composeMessage(false, true, message));
        }
    }

    public static <T> void assertEqual(T actual, T expected, String message) {
        if (actual.equals(expected)) {
            onFailedCheck(composeMessage(actual, expected, message));
        }
    }

    private static void onFailedCheck(String message) {
        SelReport.takeScreenshot();
        SelReport.attachLog(message);
        throw new AssertionError(message);
    }

    private static <T> String composeMessage(T expected, T actual, String message) {
        return message + "\nExpected: " + expected + "\nActual:   " + actual;
    }
}
