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
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gymapi.Adapter.ExerciseAdapter;
import com.example.gymapi.Model.ExerciseCategory.ExerciseList;
import com.example.gymapi.ViewModel.FullViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ExercisesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ExercisesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExercisesFragment extends Fragment implements ExerciseAdapter.OnItemClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    @BindView(R.id.swipe_exercises)
    SwipeRefreshLayout swipeRefreshExercises;

    @BindView(R.id.frag_recycler)
    RecyclerView mRecycler;

    ExerciseAdapter mAdapter;
    ExerciseList exercisesCATList;

    public ExercisesFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ExercisesFragment newInstance(String param1, String param2) {
        ExercisesFragment fragment = new ExercisesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
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
    public void onItemClick(int position) {
        FragmentManager manager = getFragmentManager();
        DaysFragment daysFragment = new DaysFragment();
        String mMuscleID = exercisesCATList.getResults().get(position).getId();
        Bundle ID_args = new Bundle();
        ID_args.putString("muscleID",mMuscleID);
        daysFragment.setArguments(ID_args);
        FragmentTransaction trans = manager.beginTransaction();
        trans.addToBackStack(null);
        trans.replace(R.id.contenedor,daysFragment).commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_exercises, container, false);

        ButterKnife.bind(this,view);
        mRecycler.setHasFixedSize(true);
        mRecycler.setLayoutManager(new GridLayoutManager(getActivity().getBaseContext(),2));
        cargarExercises();

        swipeRefreshExercises.setColorSchemeResources(R.color.colorPrimaryDark);
        swipeRefreshExercises.setProgressBackgroundColorSchemeResource(android.R.color.holo_red_dark);


        swipeRefreshExercises.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                cargarExercises();
            }
        });
        return view;
    }

    private void cargarExercises(){
        FullViewModel model = ViewModelProviders.of(this).get(FullViewModel.class);

        model.getExercises().observe(this, new Observer<ExerciseList>() {
            @Override
            public void onChanged(@Nullable ExerciseList exerciseList) {

                exercisesCATList = exerciseList;
                mAdapter = new ExerciseAdapter(getContext(), exercisesCATList);
                mRecycler.setAdapter(mAdapter);
                mAdapter.setOnItemClickListener(ExercisesFragment.this);
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
