package com.billing.controller;

import com.billing.dto.BillingHeaderDTO;
import com.billing.dto.PaymentInformationDTO;
import com.billing.service.BillingService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BillControllerTest extends BaseAbstractTest {


    @InjectMocks
    BillController billController;
    @Mock
    BillingService billingService;

    @Override
    protected Object getController() {
        return billController;
    }

    @Test
    public void testGetBillDetails() throws Exception {
        PaymentInformationDTO paymentInformationDTO = new PaymentInformationDTO(null, "CreditCard", "VISA",
                "Payment123", new BillingHeaderDTO(null, "INV001", new BigDecimal("1500.75"), LocalDate.of(2024, 12,
                3)));

        doNothing().when(billingService).getBillDetails(Mockito.any(PaymentInformationDTO.class));
        mockMvc.perform(post("/bill").contentType("application/json").content("{\n" + "    \"paymentMethod\": " +
                "\"Credit Card\",\n" + "    \"cardNumberType\": \"VISA\",\n" + "    \"formOfPaymentId\": \"12345\"," +
                "\n" + "    \"billingHeader\": {\n" + "        \"billingId\": \"INV001\",\n" + "        " +
                "\"totalInvoiceAmount\": 1500.75,\n" + "        \"invoiceDate\": \"2024-12-03\"\n" + "    }\n" + "}")).andExpect(status().isOk());

        verify(billingService, times(1)).

                getBillDetails(Mockito.any(PaymentInformationDTO.class));
    }
}
