package com.example.gymapi;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ExercisesFragment.OnFragmentInteractionListener,
        NutritionFragment.OnFragmentInteractionListener,
        WorkoutFragment.OnFragmentInteractionListener, DaysFragment.OnFragmentInteractionListener,
        ExercisesRoutineFragment.OnFragmentInteractionListener{

    private static final String TAG = MainActivity.class.getSimpleName();
    Boolean tog_state;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        tog_state = getIntent().getExtras().getBoolean("toggle state");
    }

    @Override
    public void onBackPressed() {

        if (getFragmentManager().getBackStackEntryCount() > 0 ){
            getFragmentManager().popBackStack();

        } else {
            super.onBackPressed();
        }

        /*if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction;
        NutritionFragment nutritionFragment;
        ExercisesFragment exercisesFragment;
        WorkoutFragment workoutFragment;

        if (id == R.id.nav_workouts) {

            transaction = fragmentManager.beginTransaction();
            workoutFragment = new WorkoutFragment();
            transaction.replace(R.id.contenedor, workoutFragment);
            transaction.commit();

            Log.d(TAG, "onCreate: workouts");

        } else if (id == R.id.nav_diets) {

            transaction = fragmentManager.beginTransaction();
            nutritionFragment = new NutritionFragment();
            transaction.replace(R.id.contenedor,nutritionFragment).commit();

            Log.d(TAG, "onCreate: diet");

        } else if (id == R.id.nav_exercises) {

            transaction = fragmentManager.beginTransaction();
            exercisesFragment = new ExercisesFragment();
            transaction.replace(R.id.contenedor, exercisesFragment);
            transaction.commit();

            Log.d(TAG, "onCreate: exercise");
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
