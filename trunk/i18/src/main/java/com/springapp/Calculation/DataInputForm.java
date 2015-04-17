package com.springapp.calculation;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

public class DataInputForm implements Serializable {
    private static final long serialVersionUID = 13456L;

    @NotBlank
    private String precision;

    @NotBlank
    private String numberOfThreads;

    private Long elapsedTime;

    public Long getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(Long elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public String getPrecision() {
        return precision;
    }

    public void setPrecision(String precision) {
        this.precision = precision;
    }

    public String getNumberOfThreads() {
        return numberOfThreads;
    }

    public void setNumberOfThreads(String numberOfThreads) {
        this.numberOfThreads = numberOfThreads;
    }
}
