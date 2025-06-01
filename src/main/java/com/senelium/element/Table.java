package com.senelium.element;

import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class Table {
    private final Element table;
    private final Element headerRow;
    private final Element body;

    public Table() {
        this(By.tagName("table"));
    }

    public Table(By locator) {
        this.table = Element.by(locator);
        this.headerRow = this.table.locateChildByCssSelector("thead > tr");
        this.body = this.table.locateChildByCssSelector("tbody");
    }

    public List<Element> getHeaders() {
        var numOfHeaders = this.headerRow.locateChildByCssSelector("th").countVisibleElements();
        return IntStream.range(1, numOfHeaders + 1)
                .mapToObj(index -> this.headerRow.locateChildByCssSelector("th:nth-of-type(%d)".formatted(index)))
                .toList();
    }

    public Element getHeader(int index) {
        return this.headerRow.locateChild(By.cssSelector("th:nth-of-type(" + index + ")"));
    }

    public Element getHeader(String name) {
        return this.table.locateChild(By.xpath("//th[.=\"" + name + "\"]"));
    }

    public Element getCell(int headerIndex, int rowIndex) {
        return this.table.locateChild(By.cssSelector("tr:nth-of-type(" + rowIndex + ") > td:nth-of-type(" + headerIndex + ")"));
    }

    public Element getCell(String headerName, int rowIndex) {
        var headerIndex = this.getHeaderIndex(headerName);
        return this.getCell(headerIndex, rowIndex);
    }

    public Element getCell(String headerName, String rowValue) {
        var headerIndex = this.getHeaderIndex(headerName);
        return this.table.locateChild(By.xpath(String.format("//td[%s][text()=\"%s\"]", headerIndex, rowValue)));
    }

    private int getHeaderIndex(String name) {
        return Integer.parseInt(this.getHeader(name).getProperty("cellIndex")) + 1;
    }

    public Map<String, String> getRowData(int index) {
        var headers = getHeaders().stream().map(Element::getText).toList();
        Map<String, String> data = new HashMap<>();
        headers.forEach(h -> data.put(h, this.getCell(h, index).getText()));
        return data;
    }

    public List<Map<String, String>> getTableData() {
        var numOfRows = this.body.locateChildByCssSelector("tr").countVisibleElements();
        return IntStream.range(1, numOfRows + 1).mapToObj(this::getRowData).toList();
    }
}
