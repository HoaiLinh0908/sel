package com.senelium.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Getter
@Setter
@NoArgsConstructor
public class Timeout {
    private int pageLoad;
    private int elementWait;
    private int interval;
}
