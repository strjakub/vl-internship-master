package com.virtuslab.internship.discount;

import com.virtuslab.internship.product.ProductDb;
import com.virtuslab.internship.receipt.Receipt;
import com.virtuslab.internship.receipt.ReceiptEntry;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BothDiscountsTest {
    @Test
    void shouldApplyDiscounts() throws Exception {
        //given
        var productDb = new ProductDb();
        var bread = productDb.getProduct("Bread");
        var cereals = productDb.getProduct("Cereals");
        var steak = productDb.getProduct("Steak");
        List<ReceiptEntry> receiptEntries = new ArrayList<>();
        receiptEntries.add(new ReceiptEntry(bread, 2));
        receiptEntries.add(new ReceiptEntry(cereals, 1));
        receiptEntries.add(new ReceiptEntry(steak, 1));

        //when
        var receipt = new Receipt(receiptEntries);
        var discountApplicator = new DiscountApplicator();
        var receiptAfterDiscount = discountApplicator.applyDiscounts(receipt);
        var expectedTotalPrice = bread.price().add(bread.price()).add(cereals.price()).add(steak.price())
                .multiply(BigDecimal.valueOf(0.765));

        //then
        assertEquals(expectedTotalPrice, receiptAfterDiscount.totalPrice());
        assertEquals(2, receiptAfterDiscount.discounts().size());
    }

    @Test
    void shouldNotApplyDiscounts() throws Exception {
        //given
        var productDb = new ProductDb();
        var bread = productDb.getProduct("Bread");
        List<ReceiptEntry> receiptEntries = new ArrayList<>();
        receiptEntries.add(new ReceiptEntry(bread, 2));

        //when
        var receipt = new Receipt(receiptEntries);
        var discountApplicator = new DiscountApplicator();
        var receiptAfterDiscount = discountApplicator.applyDiscounts(receipt);
        var expectedTotalPrice = bread.price().multiply(BigDecimal.valueOf(2));

        //then
        assertEquals(expectedTotalPrice, receiptAfterDiscount.totalPrice());
        assertEquals(0, receiptAfterDiscount.discounts().size());
    }
}
