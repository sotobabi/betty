package com.codingnomads.betty.logic.models;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class SentimentResult {

    private String sentimentType;
    private Integer sentimentScore;
    private SentimentClassification sentimentClass;

    public String getSentimentType() {
        return sentimentType;
    }

    public void setSentimentType(String sentimentType) {
        this.sentimentType = sentimentType;
    }

    public Integer getSentimentScore() {
        return sentimentScore;
    }

    public void setSentimentScore(Integer sentimentScore) {
        this.sentimentScore = sentimentScore;
    }

    public SentimentClassification getSentimentClass() {
        return sentimentClass;
    }

    public void setSentimentClass(SentimentClassification sentimentClass) {
        this.sentimentClass = sentimentClass;
    }

    @Override
    public String toString() {
        return "SentimentResult{" +
                "sentimentType='" + sentimentType + '\'' +
                ", sentimentScore=" + sentimentScore +
                ", sentimentClass=" + sentimentClass +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        SentimentResult that = (SentimentResult) o;

        return new EqualsBuilder()
                .append(sentimentType, that.sentimentType)
                .append(sentimentScore, that.sentimentScore)
                .append(sentimentClass, that.sentimentClass)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(sentimentType)
                .append(sentimentScore)
                .append(sentimentClass)
                .toHashCode();
    }
}
