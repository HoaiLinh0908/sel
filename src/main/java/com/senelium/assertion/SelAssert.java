package com.senelium.assertion;

import com.senelium.assertion.core.AlertAssertion;
import com.senelium.assertion.core.ElementAssertion;
import com.senelium.assertion.core.FileAssertion;
import com.senelium.constant.AssertType;
import com.senelium.element.Element;
import com.senelium.reports.SelReport;

import java.util.ArrayList;
import java.util.List;

// Soft assert need improvement with Allure report
public class SelAssert {
    // Store in ThreadLocal to reduce the number of created objects.
    private static final ThreadLocal<ElementAssertion> elementAssertionThread = new ThreadLocal<>();
    private static final ThreadLocal<FileAssertion> fileAssertionThread = new ThreadLocal<>();
    private static final ThreadLocal<AlertAssertion> alertAssertionThread = new ThreadLocal<>();
    private static final ThreadLocal<List<String>> errorsThread = new ThreadLocal<>();

    public static ElementAssertion element(Element element) {
        return element(element, AssertType.STRICT);
    }

    public static ElementAssertion element(Element element, AssertType type) {
        initThreadLocal(elementAssertionThread, new ElementAssertion());
        elementAssertionThread.get().element(element).isSoft(type == AssertType.SOFT);
        return elementAssertionThread.get();
    }

    public static AlertAssertion alert() {
        return alert(AssertType.STRICT);
    }

    public static AlertAssertion alert(AssertType type) {
        initThreadLocal(alertAssertionThread, new AlertAssertion());
        alertAssertionThread.get().isSoft(type == AssertType.SOFT);
        return alertAssertionThread.get();
    }

    public static FileAssertion file(String path) {
        return file(path, AssertType.STRICT);
    }

    public static FileAssertion file(String path, AssertType type) {
        initThreadLocal(fileAssertionThread, new FileAssertion());
        fileAssertionThread.get().path(path).isSoft(type == AssertType.SOFT);
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

    public static void addError(String message) {
        initThreadLocal(errorsThread, new ArrayList<>());
        errorsThread.get().add(message);
    }

    public static List<String> errors() {
        // Validation before return?
        return errorsThread.get();
    }

    public static void clearErrors() {
        errorsThread.set(new ArrayList<>());
    }
}
