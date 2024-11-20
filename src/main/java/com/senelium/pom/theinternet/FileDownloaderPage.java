package com.senelium.pom.theinternet;

import com.senelium.element.Element;

public class FileDownloaderPage {
    public void downloadPdfFile() {
        Element link = Element.byXpath("//a[text()='samplePDF.pdf']");
        link.click();
    }

    public void downloadFile(String name) {
        Element link = Element.byXpath(String.format("//a[text()=\"%s\"]", name));
        link.click();
    }
}
