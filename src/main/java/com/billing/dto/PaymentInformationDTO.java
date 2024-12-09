package com.billing.dto;

public record PaymentInformationDTO(
        Long id,
        String paymentMethod,
        String cardNumberType,
        String formOfPaymentId,
        BillingHeaderDTO billingHeader
) {}


