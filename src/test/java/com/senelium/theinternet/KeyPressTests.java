package com.senelium.theinternet;

import com.senelium.pom.theinternet.KeyPressPage;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

public class KeyPressTests extends TheInternetTestBase {
    KeyPressPage keyPressPage = new KeyPressPage();

    @Test
    void testKeyPressFunction() {
        homePage.openPage("Key Presses");
        keyPressPage.fillInput(Keys.ALT.toString());
        keyPressPage.verifyKeyIsDisplayed(Keys.ALT);
    }
}
