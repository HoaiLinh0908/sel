package com.senelium.theinternet;

import com.senelium.Sel;
import com.senelium.pom.theinternet.FramesPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FrameTests extends TheInternetTestBase {
    final FramesPage framesPage = new FramesPage();

    @BeforeMethod
    public void beforeFrameTest() {
        homePage.openPage("Frames");
    }

    @AfterMethod(alwaysRun = true)
    public void switchBackToMainFrame() {
        Sel.switchToMainDocument();
    }

    @Test
    public void testNestedFrames() {
        framesPage.openFrameType("Nested Frames");
        framesPage.shouldLeftFrameDisplay();
        Sel.switchToMainDocument();
        framesPage.shouldBottomFrameDisplay();
    }

    @Test
    public void testIFrame() {
        framesPage.openFrameType("iFrame");
        framesPage.shouldTextInMceFrameDisplay();
    }
}
