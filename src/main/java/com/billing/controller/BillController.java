package com.billing.controller;


import com.billing.dto.PaymentInformationDTO;
import com.billing.service.BillingService;
import com.billing.service.BillingServiceImp;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class BillController {

private final BillingService billingService;

    public BillController(BillingService billingService) {
        this.billingService = billingService;
    }

    @PostMapping("/bill")
    public void getBillDetails(@RequestBody PaymentInformationDTO paymentInformationDTO){
        billingService.getBillDetails(paymentInformationDTO);


    }
}
