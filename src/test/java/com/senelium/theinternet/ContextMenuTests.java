package com.senelium.theinternet;

import com.senelium.pom.theinternet.ContextMenuPage;
import org.testng.annotations.Test;

public class ContextMenuTests extends TheInternetTestBase {
    ContextMenuPage contextMenuPage = new ContextMenuPage();

    @Test(description = "Test the Context Menu page")
    void contextMenuTests() {
        homePage.openPage("Context Menu");
        contextMenuPage.rightClickOnHotSpot();
        contextMenuPage.shouldAlertDisplayWithMessage("You selected a context menu");
        contextMenuPage.closeAlert(true);
    }
}
