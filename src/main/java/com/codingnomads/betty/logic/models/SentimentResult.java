package com.codingnomads.betty.logic.models;

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
}
