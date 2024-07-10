package org.philbour.converter.model;

import static java.util.stream.Collectors.joining;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

/**
 * Currency represents a currency and its denominations
 */
public class Currency implements DenominationCalculator {

    private final String code;
    private final String description;
    private final Set<Denomination> denominations;

    @JsonCreator
    public Currency(@JsonProperty("code") String code, @JsonProperty("description") String description,
            @JsonProperty("denominations") Set<Denomination> denominations) {
        this.code = code;
        this.description = description;
        this.denominations = new TreeSet<>(denominations);
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public Set<Denomination> getDenominations() {
        return denominations;
    }

    @Override
    public String calculateDenominations(long balance) {
        Map<String, Long> result = new LinkedHashMap<>();
        long remaining = balance;

        while (remaining != 0) {
            for (Denomination unit : denominations) {
                if (unit.getValue() <= remaining) {
                    long times = remaining / unit.getValue();
                    remaining -= times * unit.getValue();
                    result.put(unit.getDescription(), times);
                }
            }
        }

        return prettyPrint(result);
    }

    private String prettyPrint(Map<String, Long> resultMap) {
        return resultMap.entrySet()
                .stream()
                .map(e -> e.getValue() + " " + e.getKey())
                .collect(joining(", "))
                .concat(" coins");
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, denominations, description);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if ((obj == null) || (getClass() != obj.getClass())) return false;
        Currency other = (Currency)obj;
        return Objects.equals(code, other.code) && Objects.equals(denominations, other.denominations) && Objects.equals(
                description, other.description);
    }

    @Override
    public String toString() {
        return "Currency [code=" + code + ", description=" + description + ", denominations=" + denominations + "]";
    }

}
