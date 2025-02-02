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

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    private Cart cart;

    @BeforeEach
    void setUp() {
        p1 = new Product("AT23", "Cinder block", 0.50);
        p2 = new Product("AT12", "Cement", 7.50);

        cart = new Cart();
        cart.setLines(Map.of(
                p1, new Line(11, p1),
                p2, new Line(9, p2)
        ));
    }

    @Test
    @DisplayName("Should calculate total price correctly when cart has multiple products")
    void should_CalculateTotalPrice_When_CartHasMultipleProducts() {
        // Arrange
        when(lineServiceMock.calculateTotalPrice(any(Line.class))).thenAnswer(invocation -> {
            Line line = invocation.getArgument(0);
            return line.getQuantity() * line.getProduct().getPrice();
        });

        // Act
        double totalPrice = cartService.calculateTotalPrice(cart);

        // Assert
        assertEquals(73.0, totalPrice);
    }
}