package com.senelium.theinternet;

import com.senelium.pom.theinternet.FileUploaderPage;
import com.senelium.utils.FileUtils;
import org.testng.annotations.Test;

public class FileUploadTests extends TheInternetTestBase {
    FileUploaderPage fileUploaderPage = new FileUploaderPage();
    String fileName = "sample_upload.txt";

    @Test
    void testUploadFileWithFileInput() {
        homePage.openPage("File Upload");
        fileUploaderPage.uploadFile(FileUtils.getUploadFilePath(fileName));
        fileUploaderPage.shouldUploadedSectionContain(fileName);
    }

    @Test
    void testUploadFileWithDragDrop() {
    }
}
