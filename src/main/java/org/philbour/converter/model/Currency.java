package org.philbour.converter.model;

import static java.util.stream.Collectors.joining;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Currency {

    private String code;
    private String description;
    private Set<Denomination> denominations;

    public Currency() {
        System.out.println("default");
    }

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

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public Set<Denomination> getDenominations() {
        return denominations;
    }

    public String calculate(long balance) {
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

    private String prettyPrint(Map<String, Long> map) {
        return map.entrySet()
                .stream()
                .map(e -> e.getValue() + " " + e.getKey())
                .collect(joining(", "))
                .concat(" coins");
    }

}
