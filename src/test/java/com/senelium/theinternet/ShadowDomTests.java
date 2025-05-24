package com.senelium.theinternet;

import com.senelium.pom.theinternet.ShadowDomPage;
import org.testng.annotations.Test;

public class ShadowDomTests extends TheInternetTestBase {

    ShadowDomPage shadowDomPage = new ShadowDomPage();

    @Test
    void testShadowDom() {
        homePage.openPage("Shadow DOM");
        shadowDomPage.verifyTheShadowElementIsDisplayed();
    }
}
