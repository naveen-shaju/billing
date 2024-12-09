package com.billing.service;


import com.billing.dto.PaymentInformationDTO;
import com.billing.entity.BillingHeader;
import com.billing.entity.PaymentInformation;
import com.billing.repo.BillingHeaderRepository;
import com.billing.repo.PaymentInformationRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BillingServiceImp implements BillingService{

    private final RestTemplate restTemplate;

    private final PaymentInformationRepository paymentInformationRepo;

    private final BillingHeaderRepository billingHeaderRepo;

    @Value("${paymentApi.service.url}")
    private String url;


    public BillingServiceImp(RestTemplate restTemplate, PaymentInformationRepository paymentInformationRepo, BillingHeaderRepository billingHeaderRepo) {
        this.restTemplate = restTemplate;
        this.paymentInformationRepo = paymentInformationRepo;
        this.billingHeaderRepo = billingHeaderRepo;
    }

    @Retry(name = "paymentRetry", fallbackMethod = "fallbackPayment")
    @CircuitBreaker(name = "paymentCircuitBreaker", fallbackMethod = "fallbackPayment")
    public void getBillDetails(PaymentInformationDTO paymentInformationDTO) {
        PaymentInformation paymentInformation = new PaymentInformation();
        BillingHeader billingHeader = new BillingHeader();
        BeanUtils.copyProperties(paymentInformationDTO.billingHeader(), billingHeader);
        BeanUtils.copyProperties(paymentInformationDTO, paymentInformation);
        paymentInformation.setBillingHeader(this.billingHeaderRepo.save(billingHeader));
        paymentInformation = this.paymentInformationRepo.save(paymentInformation);

       var paymentUrl =url+ "/checkpayment?payment=" + paymentInformation.getPaymentMethod();

        var response = restTemplate.getForObject(paymentUrl, String.class);


    }

    public void fallbackPayment(PaymentInformationDTO paymentInformationDTO, Throwable ex) {
        System.out.println("Error calling payment service: " + ex.getMessage());
        // "Fallback response: Unable to process payment for " + paymentInformationDTO.paymentMethod();
    }
}
