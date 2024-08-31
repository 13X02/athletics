package com.abhijith.wellness.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DietRecommendation {

    @JsonProperty("breakfast")
    private String breakfast;

    @JsonProperty("lunch")
    private String lunch;

    @JsonProperty("dinner")
    private String dinner;

    // Getters and Setters

    public String getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(String breakfast) {
        this.breakfast = breakfast;
    }

    public String getLunch() {
        return lunch;
    }

    public void setLunch(String lunch) {
        this.lunch = lunch;
    }

    public String getDinner() {
        return dinner;
    }

    public void setDinner(String dinner) {
        this.dinner = dinner;
    }

    @Override
    public String toString() {
        return "DietRecommendation{" +
                "breakfast='" + breakfast + '\'' +
                ", lunch='" + lunch + '\'' +
                ", dinner='" + dinner + '\'' +
                '}';
    }
}

