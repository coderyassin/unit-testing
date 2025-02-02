package org.yascode.unit_testing.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private String reference;
    private String libel;
    private double price;

    public Product(String reference, String libel, double price) {
        if (reference.length() != 0 && libel.length() != 0) {
            if (price <= 0.0) {
                throw new IllegalArgumentException("The price must be positive.");
            } else {
                this.reference = reference;
                this.libel = libel;
                this.price = price;
            }
        } else {
            throw new IllegalArgumentException("The reference and label must be non-empty.");
        }
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Product && this.reference == (((Product) o).reference);
    }

    @Override
    public int hashCode() {
        return this.reference.hashCode();
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.reference, this.libel);
    }

    @Override
    public Product clone() {
        return new Product(this.reference, this.libel, this.price);
    }
}
