package com.example.gymapi;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Switch;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BeastActivity extends AppCompatActivity {

    @BindView(R.id.beast_mode1)
            Switch beast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); setContentView(R.layout.activity_beast);

        ButterKnife.bind(this);

        beast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent beastIN = new Intent(getBaseContext(),MainActivity.class);
                beastIN.putExtra("toggle state",beast.isChecked());
                startActivity(beastIN);
            }
        });
        if (beast.isChecked()){
            beast.setChecked(false);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        beast.setChecked(false);
    }

    @Override
    public void onBackPressed(){

    }

}
