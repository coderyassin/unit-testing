package org.yascode.unit_testing.domain.factory;

import org.yascode.unit_testing.domain.model.Line;
import org.yascode.unit_testing.domain.model.Product;

public class LineFactory {

    public static Line createLine(Product product, int quantity) {
        return new Line(quantity, product);
    }
}
