package com.virtuslab.internship.basket;

import com.virtuslab.internship.product.ProductDb;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BasketTest {
    @Test
    void testBasket() throws Exception {
        //given
        var productDb = new ProductDb();
        var cart = new Basket();
        var milk = productDb.getProduct("Milk");
        var bread = productDb.getProduct("Bread");
        var apple = productDb.getProduct("Apple");

        //when
        cart.addProduct(milk);
        cart.addProduct(bread);
        cart.addProduct(apple);

        //then
        assertEquals(3, cart.getProducts().size());
    }
}
