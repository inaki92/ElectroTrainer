package com.example.gymapi.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gymapi.Model.ExerciseCategory.ExerciseList;
import com.example.gymapi.R;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder> {

    private static final String TAG = ExerciseAdapter.class.getSimpleName();

    private Context mCtx;
    private ExerciseList ExerciseList;

    private ExerciseAdapter.OnItemClickListener exercisesListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(ExerciseAdapter.OnItemClickListener listener){
        exercisesListener = listener;
    }

    public ExerciseAdapter(Context mCtx, ExerciseList excersiseList){
        this.mCtx = mCtx;
        this.ExerciseList = excersiseList;

    }

    class ExerciseViewHolder extends RecyclerView.ViewHolder{

        TextView muscleName;

        public ExerciseViewHolder(@NonNull View itemView) {
            super(itemView);

            muscleName = itemView.findViewById(R.id.muscle_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (exercisesListener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            exercisesListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recycler_exercises_category,viewGroup,false);
        Log.d(TAG, "onCreate: inflate");
        return new ExerciseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseViewHolder exerciseViewHolder, int i) {

        exerciseViewHolder.muscleName.setText(ExerciseList.getResults().get(i).getName());

        Log.d(TAG, "onCreate: holder musculo "+ExerciseList.getResults().get(i).getName());

    }

    @Override
    public int getItemCount() {
        return ExerciseList.getResults().size();
    }
}
