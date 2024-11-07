package com.senelium.theinternet;

import com.senelium.pom.theinternet.EntryAdPage;
import org.testng.annotations.Test;

public class EntryAdTests extends TheInternetTestBase {
    EntryAdPage entryAdPage = new EntryAdPage();

    @Test()
    void entryAdTest() {
        homePage.openPage("Entry Ad");
        entryAdPage.closeEntryAdModal();
        entryAdPage.restartEntryAd();
        entryAdPage.expectEntryModalVisible();
    }
}
