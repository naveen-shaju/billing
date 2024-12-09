package com.billing.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "billing_header")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillingHeader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "billing_id", nullable = false)
    private String billingId;

    @Column(name = "total_invoice_amount", nullable = false)
    private BigDecimal totalInvoiceAmount;

    @Column(name = "invoice_date", nullable = false)
    private LocalDate invoiceDate;
}

