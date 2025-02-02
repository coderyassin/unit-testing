package org.yascode.unit_testing.service;

import org.yascode.unit_testing.domain.model.Cart;
import org.yascode.unit_testing.domain.model.Line;
import org.yascode.unit_testing.domain.model.Product;

public interface CartService {
    Line add(Cart cart, Product product, int quantity);

    void decrease(Cart cart, Product product);

    boolean isCartEmpty(Cart cart);

    double calculateTotalPrice(Cart cart);

    void showCart(Cart cart);
}
