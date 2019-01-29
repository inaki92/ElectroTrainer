package com.example.gymapi;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gymapi.Adapter.WorkoutsAdapter;
import com.example.gymapi.Model.Workout.Wresult;
import com.example.gymapi.ViewModel.FullViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WorkoutFragment extends Fragment implements WorkoutsAdapter.OnItemClickListener {

    private static final String TAG = WorkoutFragment.class.getSimpleName();


    private OnFragmentInteractionListener mListener;

    public WorkoutFragment() {
        // Required empty public constructor
    }

    @BindView(R.id.swipe_workout)
    SwipeRefreshLayout swipeRefreshWorkout;

    @BindView(R.id.wrokout_recycler)
    RecyclerView msRecycler;

    WorkoutsAdapter msAdapter;
    Wresult workoutsList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View workoutView = inflater.inflate(R.layout.fragment_workout, container, false);

        ButterKnife.bind(this,workoutView);

        msRecycler.setHasFixedSize(true);
        msRecycler.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));
        cargarDatos();

        swipeRefreshWorkout.setColorSchemeResources(R.color.colorPrimaryDark);
        swipeRefreshWorkout.setProgressBackgroundColorSchemeResource(android.R.color.holo_red_dark);


        swipeRefreshWorkout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                cargarDatos();
            }
        });
        return workoutView;
    }

    private void cargarDatos(){

        FullViewModel model = ViewModelProviders.of(this).get(FullViewModel.class);

        model.getWorkouts().observe(this, new Observer<Wresult>() {
            @Override
            public void onChanged(@Nullable Wresult workoutList) {

                workoutsList = workoutList;
                msAdapter = new WorkoutsAdapter(getContext(),workoutsList);
                msRecycler.setAdapter(msAdapter);
                //msRecycler.addItemDecoration(new DividerItemDecoration(getActivity().getBaseContext(),DividerItemDecoration.VERTICAL));
                msAdapter.setOnItemClickListener(WorkoutFragment.this);
            }
        });

        swipeRefreshWorkout.setRefreshing(false);
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

    @Override
    public void onItemClick(int position) {

        FragmentManager manager = getFragmentManager();
        DaysFragment daysFragment = new DaysFragment();
        String rutinaID = workoutsList.getResults().get(position).getId();
        Bundle args = new Bundle();
        Bundle bool = new Bundle();
        bool.putBoolean("toggle info",false);
        args.putString("routine",rutinaID);
        daysFragment.setArguments(args);
        Log.d(TAG,"sent data: "+rutinaID);
        FragmentTransaction trans = manager.beginTransaction();
        trans.addToBackStack(null);
        trans.isAddToBackStackAllowed();
        trans.replace(R.id.contenedor,daysFragment).commit();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
