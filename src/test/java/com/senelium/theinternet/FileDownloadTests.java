package com.senelium.theinternet;

import com.senelium.assertion.SelAssert;
import com.senelium.pom.theinternet.FileDownloaderPage;
import com.senelium.utils.FileUtils;
import org.testng.annotations.Test;

import java.nio.file.Paths;

public class FileDownloadTests extends TheInternetTestBase {
    FileDownloaderPage fileDownloaderPage = new FileDownloaderPage();
    String fileName = "random_data.txt";

    @Test
    void downloadFileTest() {
        homePage.openPage("File Download");
        fileDownloaderPage.downloadFile(fileName);

        String path = Paths.get(FileUtils.getDownloadDir(), fileName).toString();
        SelAssert.file(path).toBeExisting(10000);
    }
}
