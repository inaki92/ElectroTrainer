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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gymapi.Adapter.BodyPartAdapter;
import com.example.gymapi.Model.Workout.Days.Muscle.MuscleObject;
import com.example.gymapi.ViewModel.FullViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DaysFragment extends Fragment implements BodyPartAdapter.OnItemClickListener{

    private static final String TAG = DaysFragment.class.getSimpleName();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "rutinaID";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String rutinaID;

    private OnFragmentInteractionListener mListener;


    @BindView(R.id.swipe_days)
    SwipeRefreshLayout swipeRefreshDays;

    @BindView(R.id.day_recycler)
    RecyclerView dayRecycler;

    BodyPartAdapter bodyPartAdapter;
    MuscleObject muscleObject;

    public DaysFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static DaysFragment newInstance(String param1, String param2, String rutinaID) {
        DaysFragment fragment = new DaysFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, rutinaID);
        Log.d(TAG,"rutinaTransfer: "+rutinaID);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View dayView = inflater.inflate(R.layout.fragment_days, container, false);

        ButterKnife.bind(this,dayView);

        rutinaID = getArguments() != null ? getArguments().getString("routine"): rutinaID;

        dayRecycler.setHasFixedSize(true);
        dayRecycler.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));
        loadWorkoutDays();

        swipeRefreshDays.setColorSchemeResources(R.color.colorPrimaryDark);
        swipeRefreshDays.setProgressBackgroundColorSchemeResource(android.R.color.holo_red_dark);


        swipeRefreshDays.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadWorkoutDays();
            }
        });
        return dayView;
    }

    private void loadWorkoutDays(){
        FullViewModel partModel = ViewModelProviders.of(this).get(FullViewModel.class);

        partModel.getBodyPart().observe(this, new Observer<MuscleObject>() {
            @Override
            public void onChanged(@Nullable MuscleObject partObject) {

                muscleObject = partObject;
                bodyPartAdapter = new BodyPartAdapter(getContext(),muscleObject,rutinaID);
                dayRecycler.setAdapter(bodyPartAdapter);
                bodyPartAdapter.setOnItemClickListener(DaysFragment.this);
            }
        });
        swipeRefreshDays.setRefreshing(false);
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
        ExercisesRoutineFragment exercisesRoutineFragmentFragment = new ExercisesRoutineFragment();
        FragmentTransaction trans = manager.beginTransaction();
        trans.addToBackStack(null);
        trans.replace(R.id.contenedor,exercisesRoutineFragmentFragment).commit();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
