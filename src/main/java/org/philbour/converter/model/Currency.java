package org.philbour.converter.model;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Currency {

    private final String code;
    private final String description;
    private final Set<Denomination> denominations;

    public Currency(String code, String description, Set<Denomination> denominations) {
        this.code = code;
        this.description = description;
        this.denominations = denominations;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public Set<Denomination> getUnits() {
        return denominations;
    }

    // could have the calculate method here?
    public String calculate(long balance) {
        Map<String, Long> result = new LinkedHashMap<>();
        long remaining = balance;

        while (remaining != 0) {
            for (Denomination unit : denominations) {
                if (unit.getValue() <= remaining) {
                    long times = remaining / unit.getValue();
                    remaining -= times * unit.getValue();
                    result.put(unit.getCode(), times);
                }
            }
        }

        return result.toString();
    }

}
