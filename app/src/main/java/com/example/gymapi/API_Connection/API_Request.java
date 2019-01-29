package com.example.gymapi.API_Connection;

import com.example.gymapi.Model.ExerciseCategory.ExerciseList;
import com.example.gymapi.Model.NutritionPlan.NutritionObject;
import com.example.gymapi.Model.Workout.Days.Muscle.Exercises.ExercisesRoutineOb;
import com.example.gymapi.Model.Workout.Days.Muscle.MuscleObject;
import com.example.gymapi.Model.Workout.Wresult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface API_Request {

    String BASE_URL = "https://wger.de/";
    String Exercises_url = "api/v2/exercisecategory/";
    String Workout_url = "api/v2/workout/";
    String Diets_url = "api/v2/nutritionplan/";
    String Muscle_url = "api/v2/day/";
    String Exercises_routine_url = "api/v2/set/";

    @GET(Exercises_url)
    Call<ExerciseList> getExercises();

    @Headers("Authorization: Token 3c89a48079cf871cd0afa83d368eef78fca86219")
    @GET(Workout_url)
    Call<Wresult> getWorkouts();

    @Headers("Authorization: Token 3c89a48079cf871cd0afa83d368eef78fca86219")
    @GET(Diets_url)
    Call<NutritionObject> getNutritionPlans();

    @Headers("Authorization: Token 3c89a48079cf871cd0afa83d368eef78fca86219")
    @GET(Muscle_url)
    Call<MuscleObject> getBodyPart();

    @Headers("Authorization: Token 3c89a48079cf871cd0afa83d368eef78fca86219")
    @GET(Exercises_routine_url)
    Call<ExercisesRoutineOb> getExercisesRoutine();

}
