package com.example.gymapi.Model.NutritionPlan;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Diets {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("creation_date")
    @Expose
    private String creationDate;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("has_goal_calories")
    @Expose
    private Boolean hasGoalCalories;
    @SerializedName("language")
    @Expose
    private Integer language;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getHasGoalCalories() {
        return hasGoalCalories;
    }

    public void setHasGoalCalories(Boolean hasGoalCalories) {
        this.hasGoalCalories = hasGoalCalories;
    }

    public Integer getLanguage() {
        return language;
    }

    public void setLanguage(Integer language) {
        this.language = language;
    }
}
