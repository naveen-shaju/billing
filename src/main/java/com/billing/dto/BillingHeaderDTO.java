package com.billing.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record BillingHeaderDTO(

        Long id,
        String billingId,
        BigDecimal totalInvoiceAmount,
        LocalDate invoiceDate
) {}

