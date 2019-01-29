package com.example.gymapi.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gymapi.Model.Workout.Days.Muscle.Exercises.ExercisesRoutineOb;
import com.example.gymapi.R;

public class ExercisesRoutinAdapter extends RecyclerView.Adapter<ExercisesRoutinAdapter.RoutineExViewHolder> {

    private Context mCtx;
    private ExercisesRoutineOb routineOb;

    //private ExerciseAdapter.OnItemClickListener exercisesListener;

    public ExercisesRoutinAdapter(Context mCtx, ExercisesRoutineOb routineOb){
        this.mCtx = mCtx;
        this.routineOb = routineOb;

    }

    class RoutineExViewHolder extends RecyclerView.ViewHolder{

        TextView exerciseName;

        public RoutineExViewHolder(@NonNull View itemView) {
            super(itemView);

            exerciseName = itemView.findViewById(R.id.workout_name);
        }
    }

    @NonNull
    @Override
    public RoutineExViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View routview = LayoutInflater.from(mCtx).inflate(R.layout.recycler_workouts,viewGroup,false);
        return new RoutineExViewHolder(routview);
    }

    @Override
    public void onBindViewHolder(@NonNull RoutineExViewHolder routineExViewHolder, int i) {



        if (routineOb.getResults().get(i).getExerciseday().equals("86567") && routineOb.getResults().get(i)
            .getOrder().equals("1")){

            routineExViewHolder.exerciseName.setText("Leg and Chest");
        }

        //routineExViewHolder.exerciseName.setText(routineOb.getResults().get(i).getExerciseday().toString());


//        if (routineOb.getResults().get(i).getExerciseday() == 86567){
//            routineExViewHolder.exerciseName.setText(routineOb.getResults().get(i).getOrder().toString());
//        }

    }

    @Override
    public int getItemCount() {
        return routineOb.getResults().size();
    }

}
