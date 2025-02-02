package org.yascode.unit_testing.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.yascode.unit_testing.domain.model.Line;
import org.yascode.unit_testing.domain.model.Product;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LineServiceImplTest {
    @Mock
    private Line line;

    @Mock
    Product product;

    @InjectMocks
    private LineServiceImpl lineService;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("Should calculate total price correctly")
    void calculateTotalPrice_ShouldReturnCorrectTotalPrice() {

        when(line.getProduct()).thenReturn(product);
        when(product.getPrice()).thenReturn(10.0);
        when(line.getQuantity()).thenReturn(5);

        double totalPrice = lineService.calculateTotalPrice(line);

        assertEquals(50.0, totalPrice, 0.001);
    }

    @Test
    @DisplayName("When quantity is zero, should return zero")
    void calculateTotalPrice_WhenQuantityIsZero_ShouldReturnZero() {
        when(line.getProduct()).thenReturn(product);
        when(product.getPrice()).thenReturn(10.0);
        when(line.getQuantity()).thenReturn(0);

        double totalPrice = lineService.calculateTotalPrice(line);

        assertEquals(0.0, totalPrice, 0.001);
    }

    @Test
    @DisplayName("When price is zero, should return zero")
    void calculateTotalPrice_WhenPriceIsZero_ShouldReturnZero() {
        when(line.getProduct()).thenReturn(product);
        when(product.getPrice()).thenReturn(0.0);
        when(line.getQuantity()).thenReturn(5);

        double totalPrice = lineService.calculateTotalPrice(line);

        assertEquals(0.0, totalPrice, 0.001);
    }
}