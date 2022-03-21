package com.virtuslab.internship.api;

import com.virtuslab.internship.receipt.ReceiptEntry;

import java.math.BigDecimal;
import java.util.List;

public record ReceiptJSON(
    List<ReceiptEntry> entries,
    List<String> discounts,
    BigDecimal totalPrice)
{
}
