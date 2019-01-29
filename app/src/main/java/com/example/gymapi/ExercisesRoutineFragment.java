package com.example.gymapi;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gymapi.Adapter.ExerciseAdapter;
import com.example.gymapi.Adapter.ExercisesRoutinAdapter;
import com.example.gymapi.Model.ExerciseCategory.ExerciseList;
import com.example.gymapi.Model.Workout.Days.Muscle.Exercises.ExercisesRoutineOb;
import com.example.gymapi.ViewModel.FullViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ExercisesRoutineFragment extends Fragment {

    @BindView(R.id.swipe_exerciserout)
    SwipeRefreshLayout swipeRefreshExercises;

    @BindView(R.id.exercise_routine_recycler)
    RecyclerView mExRecycler;

    ExercisesRoutinAdapter mExAdapter;
    ExercisesRoutineOb exercisesRoutineOb;

    private OnFragmentInteractionListener mListener;

    public ExercisesRoutineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_exercises_routine, container, false);

        ButterKnife.bind(this,view);
        mExRecycler.setHasFixedSize(true);
        mExRecycler.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));
        loadRoutine();

        swipeRefreshExercises.setColorSchemeResources(R.color.colorPrimaryDark);
        swipeRefreshExercises.setProgressBackgroundColorSchemeResource(android.R.color.holo_red_dark);


        swipeRefreshExercises.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadRoutine();
            }
        });
        return view;
    }

    private void loadRoutine(){
        FullViewModel Rmodel = ViewModelProviders.of(this).get(FullViewModel.class);

        Rmodel.getExercisesRoutine().observe(this, new Observer<ExercisesRoutineOb>() {
            @Override
            public void onChanged(@Nullable ExercisesRoutineOb exerciseRoutList) {

                exercisesRoutineOb = exerciseRoutList;
                mExAdapter = new ExercisesRoutinAdapter(getContext(), exercisesRoutineOb);
                mExRecycler.setAdapter(mExAdapter);
                //mExAdapter.setOnItemClickListener(ExercisesRoutineFragment.this);
            }
        });
        swipeRefreshExercises.setRefreshing(false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
