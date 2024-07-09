package org.philbour.converter.controller;

import org.philbour.converter.service.BalanceConverterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@Validated
@RequestMapping(value = "/balance", produces = MediaType.APPLICATION_JSON_VALUE)
public class BalanceController {

    private static final Logger LOG = LoggerFactory.getLogger(BalanceController.class);

    @Autowired
    private BalanceConverterService balanceConverterService;

    @GetMapping("/currency/{code}")
    @Operation(summary = "Get the balance denomination result", description = "Gets the demoniation value of a balance for a particular currency")
    @ApiResponse(responseCode = "200", description = "request was successful")
    @ApiResponse(responseCode = "400", description = "bad request")
    @ApiResponse(responseCode = "404", description = "not found")
    ResponseEntity<String> getValueByMetric(
            @PathVariable("code") @Parameter(description = "The currency code") @Nonnull String code,
            @RequestParam("code") @Parameter(description = "The currency code") @Nonnull String code2,
            @RequestParam("balance") @Parameter(description = "The balance to calculate") @Nonnull long balance) {
        LOG.debug("Get request received for {} with balance of {}", code, balance);
        return ResponseEntity.ok(balanceConverterService.calculate(code, balance));
    }

}
