package com.senelium.assertion.core;

import lombok.Getter;

import java.util.function.Supplier;

@Getter
public class AssertMessage {
    private String detail;
    private String expected;
    private Supplier<String> actual; // Why Supplier? -> actual should be generated when composing message

    public AssertMessage(String detail, String expected, Supplier<String> actual) {
        this.detail = detail;
        this.expected = expected;
        this.actual = actual;
    }

    public AssertMessage(String detail, String expected, String actual) {
        this.detail = detail;
        this.expected = expected;
        this.actual = () -> actual;
    }
}
