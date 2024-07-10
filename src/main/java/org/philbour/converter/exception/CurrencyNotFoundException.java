package org.philbour.converter.exception;

/**
 * The CurrencyNotFoundException is used for when a currency does not exist in the list of currencies
 */
public class CurrencyNotFoundException extends Exception {

    private static final long serialVersionUID = -5993892387772572062L;

    private final String currencyCode;

    /**
     * Instantiates a CurrencyNotFoundException with the provided currency code
     *
     * @param currencyCode the not found currency code
     */
    public CurrencyNotFoundException(String currencyCode) {
        super(String.format("%s currency not found", currencyCode));
        this.currencyCode = currencyCode;
    }

    /**
     * Instantiates a CurrencyNotFoundException with the provided currency code and the cause of the exception if
     * applicable
     *
     * @param currencyCode the not found currency code
     * @param cause the cause of the exception
     */
    public CurrencyNotFoundException(String currencyCode, Throwable cause) {
        super(String.format("%s currency not found", currencyCode), cause);
        this.currencyCode = currencyCode;
    }

    /**
     * Gets the code of the not found currency
     *
     * @return the missing currency code
     */
    public String getCurrencyCode() {
        return currencyCode;
    }

}
