package com.abhijith.wellness.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DietRecommendation {

    private String breakfast;
    private String lunch;
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
        return "DietPlanResponse{" +
                "breakfast='" + breakfast + '\'' +
                ", lunch='" + lunch + '\'' +
                ", dinner='" + dinner + '\'' +
                '}';
    }
}

