package com.senelium.utils;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public abstract class UrlUtils {
    public static URL newUrl(String raw) {
        try {
            return new URL(raw);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public static URI newUri(String raw) {
        try {
            return new URI(raw);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
