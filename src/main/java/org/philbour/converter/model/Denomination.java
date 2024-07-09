package org.philbour.converter.model;

import java.util.Objects;

public class Denomination implements Comparable<Denomination> {

    private final String code;
    private final String description;
    private final long value;
    private final DenominationType type;

    public Denomination(String code, String description, long value, DenominationType type) {
        this.code = code;
        this.description = description;
        this.value = value;
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public String getDescrption() {
        return description;
    }

    public long getValue() {
        return value;
    }

    public DenominationType getType() {
        return type;
    }

    @Override
    public int compareTo(Denomination o) {
        // a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than
        // the specified object
        if (o == null || this.getValue() > o.getValue()) {
            return -1;
        }

        if (this.getValue() < o.getValue()) {
            return 1;

        }

        return 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, description, type, value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if ((obj == null) || (getClass() != obj.getClass())) return false;
        Denomination other = (Denomination)obj;
        return Objects.equals(code, other.code) && Objects.equals(description, other.description) && type == other.type
                && value == other.value;
    }

}
