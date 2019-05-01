package com.codingnomads.betty.logic.models;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class SentimentClassification {

    private int veryPositive;
    private int positive;
    private int neutral;
    private int negative;
    private int veryNegative;

    public int getVeryPositive() {
        return veryPositive;
    }

    public void setVeryPositive(int veryPositive) {
        this.veryPositive = veryPositive;
    }

    public int getPositive() {
        return positive;
    }

    public void setPositive(int positive) {
        this.positive = positive;
    }

    public int getNeutral() {
        return neutral;
    }

    public void setNeutral(int neutral) {
        this.neutral = neutral;
    }

    public int getNegative() {
        return negative;
    }

    public void setNegative(int negative) {
        this.negative = negative;
    }

    public int getVeryNegative() {
        return veryNegative;
    }

    public void setVeryNegative(int veryNegative) {
        this.veryNegative = veryNegative;
    }

    @Override
    public String toString() {
        return "SentimentClassification{" +
                "veryPositive=" + veryPositive +
                ", positive=" + positive +
                ", neutral=" + neutral +
                ", negative=" + negative +
                ", veryNegative=" + veryNegative +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        SentimentClassification that = (SentimentClassification) o;

        return new EqualsBuilder()
                .append(veryPositive, that.veryPositive)
                .append(positive, that.positive)
                .append(neutral, that.neutral)
                .append(negative, that.negative)
                .append(veryNegative, that.veryNegative)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(veryPositive)
                .append(positive)
                .append(neutral)
                .append(negative)
                .append(veryNegative)
                .toHashCode();
    }
}
