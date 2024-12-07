package com.senelium.assertion;

import com.senelium.assertion.core.AlertAssertion;
import com.senelium.assertion.core.ElementAssertion;
import com.senelium.assertion.core.FileAssertion;
import com.senelium.element.Element;

public class SelAssert {
    // Store in ThreadLocal to reduce the number of created objects.
    private static final ThreadLocal<ElementAssertion> elementAssertionThread = new ThreadLocal<>();
    private static final ThreadLocal<FileAssertion> fileAssertionThread = new ThreadLocal<>();
    // AlertAssertion with isSoft = false does not hold any risk -> One object is sufficient.
    private static final AlertAssertion alertAssertion = new AlertAssertion(false);

    public static ElementAssertion expect(Element element) {
        initThreadLocal(elementAssertionThread, new ElementAssertion(false));
        elementAssertionThread.get().setElement(element);
        return elementAssertionThread.get();
    }

    public static AlertAssertion expectAlert() {
        return alertAssertion;
    }

    public static FileAssertion expectFile(String path) {
        initThreadLocal(fileAssertionThread, new FileAssertion(false));
        fileAssertionThread.get().setPath(path);
        return fileAssertionThread.get();
    }

    private static <T> void initThreadLocal(ThreadLocal<T> threadLocal, T value) {
        if (threadLocal.get() == null) {
            threadLocal.set(value);
        }
    }
}
