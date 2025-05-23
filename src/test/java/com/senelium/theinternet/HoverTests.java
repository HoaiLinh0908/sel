package com.senelium.theinternet;

import com.senelium.pom.theinternet.HoverPage;
import org.testng.annotations.Test;

public class HoverTests extends TheInternetTestBase{
    HoverPage hoverPage = new HoverPage();

    @Test
    void testHoverFunction() {
        homePage.openPage("Hovers");
        hoverPage.hoverOnImage();
        hoverPage.verifyCaptionIsDisplayed();
    }
}
