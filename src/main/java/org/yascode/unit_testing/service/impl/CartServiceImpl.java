package org.yascode.unit_testing.service.impl;

import org.yascode.unit_testing.domain.factory.LineFactory;
import org.yascode.unit_testing.domain.model.Cart;
import org.yascode.unit_testing.domain.model.Line;
import org.yascode.unit_testing.domain.model.Product;
import org.yascode.unit_testing.service.CartService;
import org.yascode.unit_testing.service.LineService;

import java.util.Objects;

public class CartServiceImpl implements CartService {
    private final LineService lineService;

    public CartServiceImpl(LineService lineService) {
        this.lineService = lineService;
    }

    @Override
    public Line add(Cart cart, Product product, int quantity) {
        if (Objects.isNull(product)) {
            throw new NullPointerException("Product cannot be null");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }

        var line = cart.getLines().get(product);
        if (Objects.isNull(line)) {
            line = LineFactory.createLine(product, quantity);
            cart.getLines().put(product, line);
        } else {
            line.setQuantity(line.getQuantity() + quantity);
        }
        return line;
    }

    @Override
    public void decrease(Cart cart, Product product) {
        var line = cart.getLines().get(product);
        if (Objects.isNull(line)) {
            throw new IllegalArgumentException("Reference missing from cart");
        }

        line.setQuantity(line.getQuantity() - 1);

        if (line.getQuantity() == 0) {
            cart.getLines().remove(product);
        }
    }

    @Override
    public boolean isCartEmpty(Cart cart) {
        return cart.getLines().isEmpty();
    }

    @Override
    public double calculateTotalPrice(Cart cart) {
        return cart.getLines()
                .values()
                .stream()
                .mapToDouble(lineService::calculateTotalPrice)
                .sum();
    }

    @Override
    public void showCart(Cart cart) {
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");
        cart.getLines()
            .entrySet()
            .forEach(entry -> System.out.println("Quantity: " + entry.getValue().getQuantity() + " *** Product: " +entry.getKey().toString()));
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");
    }
}
