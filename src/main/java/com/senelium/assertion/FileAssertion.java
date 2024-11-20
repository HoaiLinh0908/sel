package com.senelium.assertion;

import com.senelium.Sel;

import java.io.File;

public class FileAssertion {
    private final File file;

    public FileAssertion(String path) {
        this.file = new File(path);
    }

    public void toBeExisting(int timeout) {
        int interval = 500;
        int remain = timeout;
        boolean fileExists = false;
        while (remain > 0) {
            if (file.exists()) {
                fileExists = true;
                break;
            }
            Sel.freeze(500);
            remain = remain - interval;
        }

        if (!fileExists) {
            String message = SeAssert.composeMessage(
                    "file downloaded",
                    "not found",
                    String.format("Expect file {%s} to be downloaded but not.", this.file),
                    timeout);
            throw new AssertionError(message);
        }
    }
}
