package com.example.gymapi.ViewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.example.gymapi.API_Connection.API_Request;
import com.example.gymapi.Model.ExerciseCategory.ExerciseList;
import com.example.gymapi.Model.NutritionPlan.NutritionObject;
import com.example.gymapi.Model.Workout.Days.Muscle.Exercises.ExercisesRoutineOb;
import com.example.gymapi.Model.Workout.Days.Muscle.MuscleObject;
import com.example.gymapi.Model.Workout.Wresult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FullViewModel extends ViewModel {
    public static final String TAG = FullViewModel.class.getSimpleName();

    private MutableLiveData<ExerciseList> ExerciseList;
    private MutableLiveData<NutritionObject> DietList;
    private MutableLiveData<Wresult> WorkoutsList;
    private MutableLiveData<MuscleObject> BodyPartList;
    private MutableLiveData<ExercisesRoutineOb> ExercisesRoutineList;

    public LiveData<ExerciseList> getExercises(){

        if (ExerciseList == null){
            ExerciseList = new MutableLiveData<ExerciseList>();
            loadExercises();
        }
        return ExerciseList;
    }

    public LiveData<NutritionObject> getNutritionPlans(){

        if (DietList == null){
            DietList = new MutableLiveData<NutritionObject>();
            loadPlans();
        }
        return DietList;
    }

    public LiveData<Wresult> getWorkouts(){

        if (WorkoutsList == null){
            WorkoutsList = new MutableLiveData<Wresult>();
            loadWorkouts();
        }
        return WorkoutsList;
    }

    public LiveData<MuscleObject> getBodyPart(){

        if (BodyPartList == null){
            BodyPartList = new MutableLiveData<MuscleObject>();
            loadBodyPart();
        }
        return BodyPartList;
    }

    public LiveData<ExercisesRoutineOb> getExercisesRoutine(){
        if (ExercisesRoutineList == null){
            ExercisesRoutineList = new MutableLiveData<ExercisesRoutineOb>();
            loadExerciseRoutine();
        }
        return ExercisesRoutineList;
    }



    private void loadExercises(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(API_Request.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API_Request api = retrofit.create(API_Request.class);
        Call<ExerciseList> call = api.getExercises();

        call.enqueue(new Callback<ExerciseList>() {
            @Override
            public void onResponse(Call<ExerciseList> call, Response<ExerciseList> response) {
                //setting the list to our MutableLiveData
                ExerciseList.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ExerciseList> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getMessage());
            }
        });
    }

    private void loadPlans(){

        Retrofit retrofit = new Retrofit.Builder().baseUrl(API_Request.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API_Request nutritionAPI = retrofit.create(API_Request.class);
        Call<NutritionObject> call = nutritionAPI.getNutritionPlans();

        call.enqueue(new Callback<NutritionObject>() {
            @Override
            public void onResponse(Call<NutritionObject> call, Response<NutritionObject> response) {
                //setting the list to our MutableLiveData
                DietList.setValue(response.body());
            }

            @Override
            public void onFailure(Call<NutritionObject> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getMessage());
            }
        });

    }

    private void loadWorkouts(){

        Retrofit retrofit = new Retrofit.Builder().baseUrl(API_Request.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API_Request workoutsAPI = retrofit.create(API_Request.class);
        Call<Wresult> callWorkout = workoutsAPI.getWorkouts();

        callWorkout.enqueue(new Callback<Wresult>() {
            @Override
            public void onResponse(Call<Wresult> call, Response<Wresult> response) {
                //setting the list to our MutableLiveData
                WorkoutsList.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Wresult> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getMessage());
            }
        });
    }

    private void loadBodyPart(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(API_Request.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        API_Request bodyAPI = retrofit.create(API_Request.class);
        Call<MuscleObject> call = bodyAPI.getBodyPart();

        call.enqueue(new Callback<MuscleObject>() {
            @Override
            public void onResponse(Call<MuscleObject> call, Response<MuscleObject> response) {
                BodyPartList.setValue(response.body());
            }

            @Override
            public void onFailure(Call<MuscleObject> call, Throwable t) {
                Log.e(TAG,"onFailure: "+t.getMessage());
            }
        });
    }

    private void loadExerciseRoutine(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(API_Request.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        API_Request routineExAPI = retrofit.create(API_Request.class);
        Call<ExercisesRoutineOb> call = routineExAPI.getExercisesRoutine();

        call.enqueue(new Callback<ExercisesRoutineOb>() {
            @Override
            public void onResponse(Call<ExercisesRoutineOb> call, Response<ExercisesRoutineOb> response) {
                ExercisesRoutineList.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ExercisesRoutineOb> call, Throwable t) {
                Log.e(TAG,"onFailure: "+t.getMessage());
            }
        });

    }
}
