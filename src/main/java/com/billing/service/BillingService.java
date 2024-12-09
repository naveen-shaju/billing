package com.billing.service;

import com.billing.dto.PaymentInformationDTO;

public interface BillingService {

    public void getBillDetails(PaymentInformationDTO paymentInformationDTO);
};
