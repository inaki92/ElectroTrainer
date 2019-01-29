package com.example.gymapi.Adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.gymapi.DaysFragment;
import com.example.gymapi.Model.Workout.Wresult;
import com.example.gymapi.R;
import com.example.gymapi.WorkoutFragment;

public class WorkoutsAdapter extends RecyclerView.Adapter<WorkoutsAdapter.WorkoutViewHolder>{

    private static final String TAG = WorkoutsAdapter.class.getSimpleName();

    private Context mCtx;
    private Wresult workoutList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){

        mListener = listener;
    }

    public WorkoutsAdapter(Context mCtx, Wresult workoutList){
        this.mCtx = mCtx;
        this.workoutList = workoutList;

    }

    class WorkoutViewHolder extends RecyclerView.ViewHolder{

        TextView routineName,rutinaID;

        public WorkoutViewHolder(@NonNull View itemView) {
            super(itemView);
            routineName = itemView.findViewById(R.id.workout_name);
            rutinaID = itemView.findViewById(R.id.muscle_day);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public WorkoutViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recycler_workouts,viewGroup,false);
        return new WorkoutViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutViewHolder workoutViewHolder, int i) {
            workoutViewHolder.routineName.setText(workoutList.getResults().get(i).getComment());
            workoutViewHolder.rutinaID.setText(workoutList.getResults().get(i).getId());
    }

    @Override
    public int getItemCount() {
            return workoutList.getResults().size();
    }
}
