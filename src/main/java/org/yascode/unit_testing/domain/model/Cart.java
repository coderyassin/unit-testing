package org.yascode.unit_testing.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Cart {
    private Map<Product, Line> lines;
    private double reduction;

    public Cart() {
        this.lines = new HashMap<>();
        this.reduction = 0;
    }
}
