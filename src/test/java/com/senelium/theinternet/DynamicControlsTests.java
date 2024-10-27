package com.senelium.theinternet;

import com.senelium.pom.theinternet.DynamicControlsPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DynamicControlsTests extends TheInternetTestBase {
    DynamicControlsPage dynamicControlsPage = new DynamicControlsPage();

    @BeforeMethod(alwaysRun = true)
    void beforeDynamicControlsTest() {
        homePage.openPage("Dynamic Controls");
    }

    @Test()
    void testRemoveAdd() {
        dynamicControlsPage.removeCheckbox();
        dynamicControlsPage.expectCheckboxToInvisible();
        dynamicControlsPage.addCheckbox();
        dynamicControlsPage.expectCheckboxToVisible();
    }

    @Test()
    void testEnableDisable() {
        dynamicControlsPage.enableTextBox();
        dynamicControlsPage.expectTextBoxToEnabled();
        dynamicControlsPage.disableTextBox();
        dynamicControlsPage.expectTextBoxToDisabled();
    }
}
