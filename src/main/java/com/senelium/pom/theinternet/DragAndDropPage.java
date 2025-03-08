package com.senelium.pom.theinternet;

import com.senelium.assertion.SelAssert;
import com.senelium.element.Element;

public class DragAndDropPage {
    private Element columnA;
    private Element columnB;

    public DragAndDropPage() {
        columnA = Element.byId("column-a");
        columnB = Element.byId("column-b");
    }

    public Element columnA() {
        return columnA;
    }

    public Element columnB() {
        return columnB;
    }

    public void dragDropItem(Element item, Element target) {
        item.dragAndDropTo(target);
    }

    public void expectColumnHasText(Element column, String text) {
        SelAssert.element(column).toHaveText(text);
    }
}
