package org.yascode.unit_testing.domain.model;

import lombok.Getter;

@Getter
public class Line {
    private int quantity;
    private Product product;

    public Line(int quantity, Product product) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("The quantity must be positive.");
        } else {
            this.quantity = quantity;
            this.product = product;
        }
    }

    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("The quantity must be positive.");
        } else {
            this.quantity = quantity;
        }
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Line && this.hashCode() == o.hashCode();
    }

    @Override
    public int hashCode() {
        return String.format("%s,%d", this.product.getReference(), this.quantity).hashCode();
    }
}
