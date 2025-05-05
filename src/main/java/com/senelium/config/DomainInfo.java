package com.senelium.config;

import com.senelium.utils.ConfigUtils;
import lombok.Getter;

@Getter
public class DomainInfo {
    private final String swagLabsUrl;
    private final String theInternetUrl;

    public DomainInfo() {
        this.swagLabsUrl = ConfigUtils.get("swagLabs", "SWAG_LABS_URL", "https://www.saucedemo.com");
        this.theInternetUrl = ConfigUtils.get("theInternet", "THE_INTERNET_URL", "https://the-internet.herokuapp.com");
        //TODO: variable for report. Currently, only support Allure.
    }

    public static DomainInfo getInfo() {
        return new DomainInfo();
    }
}
