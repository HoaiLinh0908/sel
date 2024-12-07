package com.senelium.assertion;

import com.senelium.assertion.core.AlertAssertion;
import com.senelium.assertion.core.ElementAssertion;
import com.senelium.assertion.core.FileAssertion;
import com.senelium.element.Element;

import java.util.ArrayList;
import java.util.List;

public class SoftAssert {
    private final List<String> errors;
    private final ElementAssertion elementAssertion;
    private final AlertAssertion alertAssertion;
    private final FileAssertion fileAssertion;

    public SoftAssert() {
        this.errors = new ArrayList<>();
        this.elementAssertion = new ElementAssertion(true);
        this.alertAssertion = new AlertAssertion(true);
        this.fileAssertion = new FileAssertion(true);
    }

    public ElementAssertion expect(Element element) {
        this.elementAssertion.setElement(element);
        return this.elementAssertion;
    }

    public AlertAssertion expectAlert() {
        return this.alertAssertion;
    }

    public FileAssertion expectFile(String path) {
        this.fileAssertion.setPath(path);
        return this.fileAssertion;
    }

    public void release() {
        this.errors.addAll(this.elementAssertion.getErrors());
        this.errors.addAll(this.alertAssertion.getErrors());
        this.errors.addAll(this.fileAssertion.getErrors());
        if (!this.errors.isEmpty()) {
            throw new AssertionError(String.join("\n", errors));
        }
    }
}
