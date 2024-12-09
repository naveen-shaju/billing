package com.billing.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "payment_information")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "payment_method", nullable = false)
    private String paymentMethod;

    @Column(name = "card_number_type")
    private String cardNumberType;

    @Column(name = "form_of_payment_id")
    private String formOfPaymentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "billing_header_id", nullable = false)
    private BillingHeader billingHeader;
}

