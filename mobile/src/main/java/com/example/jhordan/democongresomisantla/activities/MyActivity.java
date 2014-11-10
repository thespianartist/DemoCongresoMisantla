package com.example.jhordan.democongresomisantla.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.example.jhordan.democongresomisantla.R;
import com.example.jhordan.democongresomisantla.fragments.Fragment_my;


public class MyActivity extends ActionBarActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);



        if (savedInstanceState == null) {
           getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new Fragment_my() )
                    .commit();
        }
    }



}
