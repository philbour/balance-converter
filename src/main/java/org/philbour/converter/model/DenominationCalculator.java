package org.philbour.converter.model;

/**
 * Interface for all {@link Currency} types
 */
public interface DenominationCalculator {

    /**
     * Converts a currency balance into the least number of denominations (bills/coins)
     *
     * @param balance the balance to convert into denominations
     * @return a string representation of the calculated denominations
     */
    String calculateDenominations(long balance);
}
