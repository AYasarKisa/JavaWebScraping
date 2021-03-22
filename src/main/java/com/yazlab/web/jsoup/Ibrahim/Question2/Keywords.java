package com.yazlab.web.jsoup.Ibrahim.Question2;

public class Keywords {

    private String word;
    private String frequency;
    private double keywords;
    public Keywords(String word, String frequency, double keywords){
        this.word = word;
        this.frequency = frequency;
        this.keywords = keywords;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public double getKeywords() {
        return keywords;
    }

    public void setKeywords(double keywords) {
        this.keywords = keywords;
    }
}
