package com.example.smpnv001;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.example.smpnv001.ui.main.SectionsPagerAdapter;
import com.example.smpnv001.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    public static final String PREF_KEY_FROM_1 = "keyFrom1";
    public static final String PREF_KEY_TO_1 = "keyTo1";
    public static final String PREF_KEY_FROM_2 = "keyFrom2";
    public static final String PREF_KEY_TO_2 = "keyTo2";
    public static final String PREF_KEY_FROM_3 = "keyFrom3";
    public static final String PREF_KEY_TO_3 = "keyTo3";
    public static final String PREF_KEY_FROM_4 = "keyFrom4";
    public static final String PREF_KEY_TO_4 = "keyTo4";
    public static final String PREF_KEY_FROM_5 = "keyFrom5";
    public static final String PREF_KEY_TO_5 = "keyTo5";
    public static final String PREF_KEY_FROM_6 = "keyFrom6";
    public static final String PREF_KEY_TO_6 = "keyTo6";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);
        //FloatingActionButton fab = binding.fab;
        //fab.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View view) {
        //        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        //                .setAction("Action", null).show();
        //    }
        //});

    }
}