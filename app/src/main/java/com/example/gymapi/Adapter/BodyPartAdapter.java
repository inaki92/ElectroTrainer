package com.example.gymapi.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gymapi.Model.Workout.Days.Muscle.MuscleObject;
import com.example.gymapi.Model.Workout.Days.Muscle.MuscleResult;
import com.example.gymapi.R;

import java.util.ArrayList;
import java.util.List;

public class BodyPartAdapter extends RecyclerView.Adapter<BodyPartAdapter.BodyViewHolder> {

    private static final String TAG = BodyPartAdapter.class.getSimpleName();

    private Context mCtx;
    private MuscleObject partList;
    private String rutineID;

    private OnItemClickListener bodyListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        bodyListener = listener;
    }

    public BodyPartAdapter(Context mCtx, MuscleObject partList, String rutinaID){
        this.mCtx = mCtx;
        this.rutineID = rutinaID;
        MuscleObject aux = new MuscleObject();
        aux.setResults(new ArrayList<MuscleResult>());
        for(MuscleResult item : partList.getResults()){
            if(!item.getTraining().equals(rutineID)){
                Log.d(TAG, "BodyPartAdapter: training not part of list");
                aux.getResults().add(item);
            }
                
        }
        partList.getResults().removeAll(aux.getResults());
        this.partList = partList;
    }

    class BodyViewHolder extends RecyclerView.ViewHolder{

        TextView bodyPart;
        TextView trainingDay;

        public BodyViewHolder(@NonNull View itemView) {
            super(itemView);

            bodyPart = itemView.findViewById(R.id.muscle_day);
            trainingDay = itemView.findViewById(R.id.workout_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (bodyListener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            bodyListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }


    @NonNull
    @Override
    public BodyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View bodyView = LayoutInflater.from(mCtx).inflate(R.layout.recycler_workouts,viewGroup,false);
        return new BodyViewHolder(bodyView);
    }

    @Override
    public void onBindViewHolder(@NonNull BodyPartAdapter.BodyViewHolder bodyViewHolder, int i) {

        String nameDay = null;

        for (Integer days:partList.getResults().get(i).getDay()) {
                switch (days){
                    case 1:
                        nameDay = "Monday";
                        break;
                    case 2:
                        nameDay = "Tuesday";
                        break;
                    case 3:
                        nameDay = "Wednesday";
                        break;
                    case 4:
                        nameDay = "Thursday";
                        break;
                    case 5:
                        nameDay = "Friday";
                        break;
                    case 6:
                        nameDay = "Saturday";
                        break;
                        default:
                            break;
                }
        }
        Log.d(TAG, "onBindViewHolder: "+!partList.getResults().get(i).getTraining().equals(rutineID));

        if (partList.getResults().get(i).getTraining().equals(rutineID)) {
            Log.d(TAG, "recieving rutina: " + partList.getResults().get(i).getTraining());

            bodyViewHolder.bodyPart.setText(partList.getResults().get(i).getDescription());
            bodyViewHolder.trainingDay.setText(nameDay);
        }
    }

    @Override
    public int getItemCount() {
        return partList.getResults().size();
    }
}
