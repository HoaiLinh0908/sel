package com.senelium.utils;

import java.nio.file.Paths;

public abstract class FileUtils {
    public static String getTempDirectory() {
        return System.getProperty("java.io.tmpdir");
    }

    public static String getDownloadDir() {
        return System.getProperty("downloadDir", Paths.get(FileUtils.getTempDirectory(), "Downloads").toString());
    }

    public static String getCwd() {
        return System.getProperty("user.dir");
    }
}
