package org.yascode.unit_testing.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.yascode.unit_testing.domain.model.Cart;
import org.yascode.unit_testing.domain.model.Line;
import org.yascode.unit_testing.domain.model.Product;
import org.yascode.unit_testing.service.LineService;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CartServiceImplTest {
    @Mock
    LineService lineServiceMock;

    @InjectMocks
    CartServiceImpl cartService;

    private Product p1;
    private Product p2;
    private Product p3;
    private Product p4;
    private Cart cart;

    @BeforeEach
    void setUp() {
        p1 = new Product("AT23", "Cinder block", 0.50);
        p2 = new Product("AT12", "Cement", 7.50);
        p3 = new Product("PL25", "Plaster", 25.75);
        p4 = new Product("SH73", "Shingle", 25.75);

        cart = new Cart();
        Map<Product, Line> lines = new HashMap<>(Map.of(
                p1, new Line(11, p1),
                p2, new Line(9, p2)
        ));
        cart.setLines(lines);
    }

    @Test
    @DisplayName("Should calculate total price correctly when cart has multiple products")
    void should_CalculateTotalPrice_When_CartHasMultipleProducts() {
        when(lineServiceMock.calculateTotalPrice(any(Line.class))).thenAnswer(invocation -> {
            Line line = invocation.getArgument(0);
            return line.getQuantity() * line.getProduct().getPrice();
        });

        double totalPrice = cartService.calculateTotalPrice(cart);

        assertEquals(73.0, totalPrice);
    }

    @Test
    @DisplayName("Should add one product to cart")
    void add_one_product() {
        cartService.add(cart, p3, 10);

        assertEquals(3, cart.getLines().size());
    }

    @Test
    @DisplayName("Should add two products to cart")
    void add_two_product() {
        cartService.add(cart, p3, 10);
        cartService.add(cart, p4, 10);

        assertEquals(4, cart.getLines().size());
    }

    @Test
    @DisplayName("Should add product twice with different quantities")
    void add_product_twice_with_different_quantities() {
        cartService.add(cart, p3, 10);
        cartService.add(cart, p3, 15);

        assertEquals(25, cart.getLines().get(p3).getQuantity());
    }

    @Test
    @DisplayName("It is not acceptable to add a product with a quantity of 0")
    void add_product_with_quantity_0() {
        assertThrows(IllegalArgumentException.class, () -> cartService.add(cart, p3, 0));
    }

}