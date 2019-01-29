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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gymapi.Adapter.NutritionAdapter;
import com.example.gymapi.Model.NutritionPlan.NutritionObject;
import com.example.gymapi.ViewModel.FullViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;


public class NutritionFragment extends Fragment implements NutritionAdapter.OnItemClickListener{

    private OnFragmentInteractionListener mListener;

    @BindView(R.id.swipe_nutrition)
    SwipeRefreshLayout nutritionRefresh;

    @BindView(R.id.nutrition_recycler)
    RecyclerView nutRecycler;

    NutritionAdapter nutAdapter;
    NutritionObject nutritionList;

    public NutritionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View nutView = inflater.inflate(R.layout.fragment_nutrition, container, false);

        ButterKnife.bind(this,nutView);

        nutRecycler.setHasFixedSize(true);
        nutRecycler.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));
        loadNutrition();

        nutritionRefresh.setColorSchemeResources(R.color.colorPrimaryDark);
        nutritionRefresh.setProgressBackgroundColorSchemeResource(android.R.color.holo_red_dark);


        nutritionRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadNutrition();
            }
        });
        return nutView;
    }

    private void loadNutrition(){

        FullViewModel model = ViewModelProviders.of(this).get(FullViewModel.class);

        model.getNutritionPlans().observe(this, new Observer<NutritionObject>() {
            @Override
            public void onChanged(@Nullable NutritionObject nutritionPlanList) {

                nutritionList = nutritionPlanList;
                nutAdapter = new NutritionAdapter(getContext(), nutritionList);
                nutRecycler.setAdapter(nutAdapter);
                nutAdapter.setOnItemClickListener(NutritionFragment.this);
            }
        });
        nutritionRefresh.setRefreshing(false);
    }

    @Override
    public void onItemClick(int position) {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction trans = manager.beginTransaction();
        DaysFragment daysFragment = new DaysFragment();
        trans.addToBackStack(null);
        trans.replace(R.id.contenedor,daysFragment).commit();
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
