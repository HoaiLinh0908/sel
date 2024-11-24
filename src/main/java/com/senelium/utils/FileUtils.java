package com.senelium.utils;

import java.io.File;

public abstract class FileUtils {
    public static String getTempDirectory() {
        return System.getProperty("java.io.tmpdir");
    }

    public static String getDownloadDir() {
        return System.getProperty("downloadDir", FileUtils.getTempDirectory() + "Downloads" + File.separator);
    }

    public static String getCwd() {
        return System.getProperty("user.dir");
    }
}
