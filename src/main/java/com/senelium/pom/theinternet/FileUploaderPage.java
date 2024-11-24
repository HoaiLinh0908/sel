package com.senelium.pom.theinternet;

import com.senelium.assertion.SeAssert;
import com.senelium.element.Element;

public class FileUploaderPage {
    private Element fileInput;
    private Element uploadButton;
    private Element uploadedFileSection;

    public FileUploaderPage() {
        this.fileInput = Element.byId("file-upload");
        this.uploadButton = Element.byId("file-submit");
        this.uploadedFileSection = Element.byId("uploaded-files");
    }

    // TODO: Should be modified to run on Selenium Grid - https://www.selenium.dev/documentation/webdriver/drivers/remote_webdriver/
    public void uploadFile(String filePath) {
        this.fileInput.type(filePath);
        this.uploadButton.click();
    }

    public void shouldUploadedSectionContain(String fileName) {
        SeAssert.expect(this.uploadedFileSection).toContainText(fileName);
    }
}
