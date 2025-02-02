package org.yascode.unit_testing.service.impl;

import org.yascode.unit_testing.domain.model.Line;
import org.yascode.unit_testing.service.LineService;

public class LineServiceImpl implements LineService {
    @Override
    public double calculateTotalPrice(Line line) {
        return line.getProduct().getPrice() * line.getQuantity();
    }
}
