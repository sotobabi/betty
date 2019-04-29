package com.codingnomads.betty.data.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "tweets")
public class Tweet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 15)
    private String language;
    @Column(length = 512)
    private String text;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    private String keywordUsed;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getKeywordUsed() {
        return keywordUsed;
    }

    public void setKeywordUsed(String keywordUsed) {
        this.keywordUsed = keywordUsed;
    }
}
