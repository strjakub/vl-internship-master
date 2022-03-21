package com.virtuslab.internship.api;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class BasketJSON {

    @NotNull
    private final List<ProductJSON> products;

    public BasketJSON() {
        products = new ArrayList<>();
    }

    public List<ProductJSON> getProducts() {
        return products;
    }

}
