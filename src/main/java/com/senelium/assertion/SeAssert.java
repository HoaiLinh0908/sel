package com.senelium.assertion;

import com.senelium.Sel;
import com.senelium.config.DriverConfig;
import com.senelium.element.Element;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeAssert {
    private static final ThreadLocal<Assertion> threadAssert = new ThreadLocal<>();

    public static Assertion expect(Element element) {
        if (threadAssert.get() == null) {
            threadAssert.set(new Assertion(false));
        }
        threadAssert.get().setElement(element);
        return threadAssert.get();
    }

    public static Assertion softAssert() {
        return new Assertion(true);
    }

    //TODO: improve this -> have a class for Alert assertions?
    public static void expectAlertToBeVisible() {
        try {
            Sel.toAlert();
        } catch (TimeoutException e) {
            String message = composeMessage(
                    "visible",
                    "invisible",
                    "Expect an alert is visible but found invisible.",
                    null);
            throw new AssertionError(message);
        }
    }

    public static void expectAlertHasText(String text) {
        try {
            String actualText = Sel.toAlert().getText();
            if (text == null || !text.equals(actualText)) {
                String message = composeMessage(
                        "has text {" + text + "}",
                        "has text {" + actualText + "}",
                        String.format("Expect an alert with text {%s} but found a different text.", text),
                        null);
                throw new AssertionError(message);
            }
        } catch (TimeoutException e) {
            String message = composeMessage(
                    "has text {" + text + "}",
                    "invisible",
                    String.format("Expect an alert with text {%s} but found alert invisible.", text),
                    null);
            throw new AssertionError(message);
        }
    }

    public static FileAssertion expectFile(String path) {
        return new FileAssertion(path);
    }

    public static <T> String composeMessage(T expected, T actual, String message, Integer timeout) {
        int to = timeout == null ? getDefaultTimeout() : timeout;
        String logMsg = message + " Timeout " + to + " millisecond(s).";
        return logMsg + "\nExpected: " + expected + "\nActual:   " + actual;
    }

    public static int getDefaultTimeout() {
        return DriverConfig.getInstance().getTimeout().getElementWait();
    }

    public static WebDriverWait waiter(Integer mil) {
        return mil != null ? Sel.getWaiter(mil) : Sel.getDefaultWaiter();
    }
}
