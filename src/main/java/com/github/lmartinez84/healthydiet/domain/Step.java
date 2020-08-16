package com.github.lmartinez84.healthydiet.domain;

import lombok.RequiredArgsConstructor;
import lombok.Value;

@RequiredArgsConstructor
@Value
public class Step {
    private final String description;

    public static Step of(String description) {
        return new Step(description);
    }
}
