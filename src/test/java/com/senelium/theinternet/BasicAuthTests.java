package com.senelium.theinternet;

import com.senelium.pom.theinternet.BasicAuthPage;
import org.openqa.selenium.UsernameAndPassword;
import org.testng.annotations.Test;

public class BasicAuthTests extends TheInternetTestBase {
    final BasicAuthPage basicAuthPage = new BasicAuthPage();

    @Override
    public void openTheInternet() {
        open(domainInfo.getTheInternetUrl(), new UsernameAndPassword("admin", "admin"));
    }

    @Test
    void basicAuthTest() {
        homePage.openPage("Basic Auth");
        basicAuthPage.shouldBasicAuthCongratulationDisplay();
    }
}
