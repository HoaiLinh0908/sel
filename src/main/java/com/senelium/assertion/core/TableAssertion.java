package com.senelium.assertion.core;

import com.senelium.element.Table;

public class TableAssertion extends Assertion {
    private Table table;

    public TableAssertion(boolean isSoft) {
        super(isSoft);
    }

    public void toHaveHeaders(String[] headers) {}

    public void toContainHeaders(String[] headers) {}
}
