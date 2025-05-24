package com.senelium.theinternet;

import com.senelium.Sel;
import com.senelium.pom.theinternet.MultipleWindowsPage;
import org.testng.annotations.Test;

public class MultipleWindowsTests extends TheInternetTestBase {

    MultipleWindowsPage multipleWindowsPage = new MultipleWindowsPage();

    @Test
    void testMultipleWindows() {
        homePage.openPage("Multiple Windows");
        multipleWindowsPage.openNewTab();
        Sel.switchToWindowWithTitle("New Window");
        multipleWindowsPage.verifyNewWindowIsDisplayed();
    }
}
