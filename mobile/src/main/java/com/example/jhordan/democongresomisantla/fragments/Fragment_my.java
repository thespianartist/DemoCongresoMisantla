package com.example.jhordan.democongresomisantla.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.example.jhordan.democongresomisantla.adapters.PageAdapter;
import com.example.jhordan.democongresomisantla.R;
import com.example.jhordan.democongresomisantla.ui.BusProvider;
import com.squareup.otto.Produce;


import java.util.ArrayList;
import java.util.List;


/**
 * Created by Jhordan on 07/11/14.
 */


public class Fragment_my extends android.support.v4.app.Fragment {


    private List<android.support.v4.app.Fragment> listaFragments;
    private PagerAdapter mPagerAdapter;
String dato;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_my, container, false);

        Toolbar toolbar = (Toolbar) v.findViewById(R.id.toolbar);

        toolbar.setLogo(R.drawable.powered_by_google_dark);


        listaFragments = new ArrayList<android.support.v4.app.Fragment>();
        listaFragments.add(FragmentoOne.newInstance(0));
        listaFragments.add(FragmentoTwo.newInstance(1));

        // Creamos nuestro Adapter
        mPagerAdapter = new PageAdapter(getFragmentManager(), listaFragments);


        // Initialize the ViewPager and set an adapter
        final ViewPager pager = (ViewPager) v.findViewById(R.id.pager);


        pager.setAdapter(mPagerAdapter);


        BusProvider.getInstance().register(this);

        // Bind the tabs to the ViewPager
        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) v.findViewById(R.id.tabs);

        tabs.setIndicatorColor(getResources().getColor(R.color.accent));

        tabs.setShouldExpand(true);

        tabs.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {
                String value = Integer.toString(i);
                Log.i("pager Onpage Scrolled", value);


            }

            @Override
            public void onPageSelected(int i) {
                String value = Integer.toString(i);
                Log.i("pager selected", value);
               // Toast.makeText(getActivity(), value, Toast.LENGTH_SHORT).show();
                  dato = value;
                BusProvider.getInstance().post(produceCustomString());


            }

            @Override
            public void onPageScrollStateChanged(int i) {
                String value = Integer.toString(i);
                Log.i("pager onpageScrollState", value);

            }
        });
        // tabs.setTextColorResource(R.color.white);
        // tabs.setDividerColor(getResources().getColor(R.color.accent));
        tabs.setViewPager(pager);


        return v;
    }

    @Produce
    public String produceCustomString() {
        Log.i("otto sender", "enviado");
        return dato;

    }

}
