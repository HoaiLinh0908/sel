package com.senelium.assertion.core;

import com.senelium.Sel;
import com.senelium.config.DriverConfig;
import lombok.Getter;
import lombok.Setter;

import java.io.File;

@Getter
@Setter
public class FileAssertion extends Assertion {
    private static final int DEFAULT_TIMEOUT = 150000;
    private String path;

    public FileAssertion(boolean isSoft) {
        super(isSoft);
    }

    public void toBeExisting() {
        this.toBeExisting(DEFAULT_TIMEOUT, "");
    }

    public void toBeExisting(int timeout) {
        this.toBeExisting(timeout, "");
    }

    public void toBeExisting(String message) {
        this.toBeExisting(DEFAULT_TIMEOUT, message);
    }

    public void toBeExisting(int timeout, String message) {
        var interval = DriverConfig.getInfo().getTimeout().getInterval();
        var remain = timeout;
        var fileExists = false;
        var file = new File(this.path);
        while (remain > 0) {
            if (file.exists()) {
                fileExists = true;
                break;
            }
            Sel.freeze(interval);
            remain = remain - interval;
        }

        if (!fileExists) {
            this.onFailedCheck(
                    this.composeMessage(
                            "file downloaded",
                            "not found",
                            String.format("%s\nExpect file {%s} to be downloaded but not.", message, this.path),
                            timeout
                    )
            );
        }
    }
}
