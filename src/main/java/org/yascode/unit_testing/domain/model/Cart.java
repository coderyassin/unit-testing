package org.yascode.unit_testing.domain.model;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class Cart {
    private Map<Product, Line> lines;

    public Cart() {
        this.lines = new HashMap<>();
    }
}
