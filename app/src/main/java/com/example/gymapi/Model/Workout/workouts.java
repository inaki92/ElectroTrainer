package com.example.gymapi.Model.Workout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class workouts {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("creation_date")
    @Expose
    private String creationDate;
    @SerializedName("comment")
    @Expose
    private String comment;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
