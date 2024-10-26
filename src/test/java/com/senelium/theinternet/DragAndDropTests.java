package com.senelium.theinternet;

import com.senelium.element.Element;
import com.senelium.pom.theinternet.DragAndDropPage;
import org.testng.annotations.Test;

public class DragAndDropTests extends TheInternetTestBase {
    DragAndDropPage dragAndDropPage = new DragAndDropPage();

    @Test
    void dragAtoBTests() {
        homePage.openPage("Drag and Drop");
        Element columnA = dragAndDropPage.columnA();
        Element columnB = dragAndDropPage.columnB();
        dragAndDropPage.dragDropItem(columnA, columnB);
        dragAndDropPage.expectColumnHasText(columnA, "B");
        dragAndDropPage.expectColumnHasText(columnB, "A");
    }
}
