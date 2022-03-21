package com.virtuslab.internship.api;

import com.virtuslab.internship.basket.Basket;
import com.virtuslab.internship.product.Product;
import com.virtuslab.internship.product.ProductDb;
import com.virtuslab.internship.receipt.Receipt;
import com.virtuslab.internship.receipt.ReceiptGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class BasketEndpoint {

    private final ReceiptGenerator generator;
    private final ProductDb db;

    @Autowired
    public BasketEndpoint(ReceiptGenerator generator, ProductDb db){
        this.db = db;
        this.generator = generator;
    }

    @PostMapping(value = "/receipts", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ReceiptJSON getBasketReceipt(@Valid @RequestBody BasketJSON basket) {
        Basket realBasket = new Basket();
        for(ProductJSON productJSON : basket.getProducts()){
            Product realProduct = db.getProduct(productJSON.getName());
            realBasket.addProduct(realProduct);
        }
        Receipt receipt = generator.generate(realBasket);
        return new ReceiptJSON(receipt.entries(), receipt.discounts(), receipt.totalPrice());
    }
}
