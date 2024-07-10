package org.philbour.converter.controller;

import org.philbour.converter.exception.CurrencyNotFoundException;
import org.philbour.converter.service.BalanceConverterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@RestController
@Validated
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class BalanceController {

    private static final Logger LOG = LoggerFactory.getLogger(BalanceController.class);

    @Autowired
    private BalanceConverterService balanceConverterService;

    @GetMapping("/currency/{code}/convert")
    @Operation(summary = "Get the balance denomination result", description = "Gets the demonination value of a balance for a particular currency")
    @ApiResponse(responseCode = "200", description = "request was successful")
    @ApiResponse(responseCode = "400", description = "bad request")
    @ApiResponse(responseCode = "404", description = "not found")
    @ApiResponse(responseCode = "500", description = "internal server error")
    ResponseEntity<String> convertBalance(
            @PathVariable("code") @Parameter(description = "The currency code") @Nonnull @NotBlank String code,
            @RequestParam("balance") @Parameter(description = "The balance to calculate") @Positive(message = "balance must be greater 0") @Max(value = 100000000, message = "balance cannot be greater than 100,000,000") long balance) {
        LOG.debug("Get request received for {} with balance of {}", code, balance);
        try {
            String result = balanceConverterService.convertBalance(code, balance);
            return ResponseEntity.ok(result);
        } catch (CurrencyNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("%s currency not found", code));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("something went wrong");
        }
    }

}
