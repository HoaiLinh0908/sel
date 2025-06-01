package com.senelium.pom.theinternet;

import com.senelium.assertion.SelAssert;
import com.senelium.element.Table;

public class ChallengingDomPage {
    private final Table table;

    public ChallengingDomPage() {
        this.table = new Table();
    }

    public void shouldHeaderDisplay(String name) {
        SelAssert.element(table.getHeader(name))
                .toBeVisible(String.format("Table header [%s] is not visible", name));
    }

    public void shouldCellDisplayUnderHeader(String header, String cell) {
        SelAssert.element(table.getCell(header, cell))
                .toBeVisible(String.format("Cell [%s] is not visible under header [%s]", cell, header));
    }
}
