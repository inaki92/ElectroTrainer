package com.example.gymapi.Model.Workout.Days.Muscle.Exercises;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ExercisesRoutineResult {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("order")
    @Expose
    private String order;
    @SerializedName("sets")
    @Expose
    private Integer sets;
    @SerializedName("exerciseday")
    @Expose
    private String exerciseday;
    @SerializedName("exercises")
    @Expose
    private List<Integer> exercises = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Integer getSets() {
        return sets;
    }

    public void setSets(Integer sets) {
        this.sets = sets;
    }

    public String getExerciseday() {
        return exerciseday;
    }

    public void setExerciseday(String exerciseday) {
        this.exerciseday = exerciseday;
    }

    public List<Integer> getExercises() {
        return exercises;
    }

    public void setExercises(List<Integer> exercises) {
        this.exercises = exercises;
    }
}
