package org.philbour.converter.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.philbour.converter.exception.CurrencyNotFoundException;
import org.philbour.converter.service.BalanceConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@WebMvcTest(controllers = BalanceController.class)
public class BalanceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BalanceConverterService balanceConverterService;

    private static final String RESULT_87_USD = "3 Quarter, 1 Dime, 2 Penny coins";
    private static final String USD = "USD";
    private static final String GBP = "GBP";
    private static final String BALANCE_287 = "287";
    private static final String BALANCE_87 = "87";
    private static final String NEGATIVE_BALANCE = "-2";

    @Test
    void convertBalance_ValidRequest_DenominationsReturnedOk() throws Exception {
        when(balanceConverterService.convertBalance(anyString(), anyLong())).thenReturn(RESULT_87_USD);

        MvcResult mvcResult = mockMvc.perform(get("/currency/{code}/convert", USD).contentType(
                MediaType.APPLICATION_JSON).queryParam("balance", BALANCE_287)).andExpect(status().isOk()).andReturn();

        String result = mvcResult.getResponse().getContentAsString();
        assertEquals(RESULT_87_USD, result);
    }

    @Test
    void convertBalance_InvalidBalance_ReturnsBadRequest() throws Exception {
        mockMvc.perform(get("/currency/{code}/convert", USD).contentType(MediaType.APPLICATION_JSON)
                .queryParam("balance", NEGATIVE_BALANCE))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("balance must be greater than 0"));
    }

    @Test
    void convertBalance_InvalidCurrency_ReturnsNotFound() throws Exception {
        when(balanceConverterService.convertBalance(eq(GBP), anyLong())).thenThrow(new CurrencyNotFoundException(GBP));

        mockMvc.perform(get("/currency/{code}/convert", GBP).contentType(MediaType.APPLICATION_JSON)
                .queryParam("balance", BALANCE_87))
                .andExpect(status().isNotFound())
                .andExpect(content().string(String.format("%s currency not found", GBP)));
    }

    @Test
    void convertBalance_InternalError_ReturnsInternalServerError() throws Exception {
        when(balanceConverterService.convertBalance(anyString(), anyLong())).thenThrow(new RuntimeException(GBP));

        mockMvc.perform(get("/currency/{code}/convert", USD).contentType(MediaType.APPLICATION_JSON)
                .queryParam("balance", BALANCE_287))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("something went wrong"));
    }

}
