package org.yascode.unit_testing.service;

import org.yascode.unit_testing.domain.model.Line;

public interface LineService {
    double calculateTotalPrice(Line line);
}
