package com.senelium.config;

import lombok.Getter;

@Getter
public class DomainInfo {
    private final String swagLabsUrl;
    private final String theInternetUrl;

    public DomainInfo() {
        this.swagLabsUrl = System.getProperty("swagLabs", "https://www.saucedemo.com");
        this.theInternetUrl = System.getProperty("theInternet", "https://the-internet.herokuapp.com");
        //TODO: variable for report. Currently, only support Allure.
    }

    public static DomainInfo getInfo() {
        return new DomainInfo();
    }
}
