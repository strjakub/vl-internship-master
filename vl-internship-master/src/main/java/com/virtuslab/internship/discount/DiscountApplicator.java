package com.virtuslab.internship.discount;

import com.virtuslab.internship.receipt.Receipt;

import java.util.ArrayList;
import java.util.List;

public class DiscountApplicator {

    private final List<IDiscount> discounts = new ArrayList<>(){{add(new TenPercentDiscount()); add(new FifteenPercentDiscount());}};

    public Receipt applyDiscounts(Receipt receipt){
        for(IDiscount discount : discounts){
            receipt = discount.apply(receipt);
        }
        return receipt;
    }
}

