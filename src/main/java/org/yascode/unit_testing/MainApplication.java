package org.yascode.unit_testing;

import org.yascode.unit_testing.domain.factory.CartFactory;
import org.yascode.unit_testing.domain.model.Cart;
import org.yascode.unit_testing.domain.model.Product;
import org.yascode.unit_testing.service.CartService;
import org.yascode.unit_testing.service.LineService;
import org.yascode.unit_testing.service.impl.CartServiceImpl;
import org.yascode.unit_testing.service.impl.LineServiceImpl;

public class MainApplication {
    private static final LineService lineService;
    private static final CartService cartService;
    static {
        lineService = new LineServiceImpl();
        cartService = new CartServiceImpl(lineService);
    }

    public static void main(String[] args) {
        Product p1 = new Product("AT23", "Cinder block", 0.50);
        Product p2 = new Product("AT12", "Cement", 7.50);

        Cart cart = CartFactory.createCart();
        cartService.add(cart, p1,5);
        cartService.add(cart, p2,8);
        cartService.add(cart, p1,2);
        cartService.add(cart, p2,3);

        cartService.showCart(cart);
    }
}
