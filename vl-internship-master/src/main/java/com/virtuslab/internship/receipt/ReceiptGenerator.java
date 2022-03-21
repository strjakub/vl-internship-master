package com.virtuslab.internship.receipt;

import com.virtuslab.internship.basket.Basket;
import com.virtuslab.internship.discount.DiscountApplicator;
import com.virtuslab.internship.product.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ReceiptGenerator {

    public Receipt generate(Basket basket) {
        DiscountApplicator applicator = new DiscountApplicator();
        return applicator.applyDiscounts(getReceipt(basket));
    }

    private HashMap<String, Integer> mapQuantities(Basket basket){
        //another solution is to create basket entry
        //which contains of product and quantity
        HashMap<String, Integer> quantities = new HashMap<>();
        for(Product product: basket.getProducts()){
            var name = product.name();
            if(quantities.containsKey(name)){
                var quantity = quantities.get(name);
                quantities.put(name, quantity + 1);
            }else{
                quantities.put(name, 1);
            }
        }
        return quantities;
    }

    private Receipt getReceipt(Basket basket) {
        List<ReceiptEntry> receiptEntries = new ArrayList<>();
        HashMap<String, Integer> quantities = mapQuantities(basket);
        for(Product product: basket.getProducts()){
            var name =  product.name();
            if(quantities.containsKey(name)) {
                receiptEntries.add(new ReceiptEntry(product, quantities.get(name)));
                quantities.remove(name);
            }
        }
        return new Receipt(receiptEntries);
    }
}
