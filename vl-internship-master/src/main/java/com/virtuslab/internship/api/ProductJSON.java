package com.virtuslab.internship.api;

import com.virtuslab.internship.product.Product;

import java.math.BigDecimal;

public record ProductJSON(
    String name,
    Product.Type type,
    BigDecimal price)
{
    public String getName() {
        return name;
    }
}
