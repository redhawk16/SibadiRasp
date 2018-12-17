package com.example.pochekuev.myapplication.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import com.example.pochekuev.myapplication.R;
import com.example.pochekuev.myapplication.adapter.BasePreferenceFragmentCompat;

public class SettingsFragment extends BasePreferenceFragmentCompat {

    SharedPreferences mSettings;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        try {
            Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
            toolbar.setTitle("Настройки");
        }
        catch (Exception e){
            Log.d("Error", e.toString());
        }
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String s) {
        setPreferencesFromResource(R.xml.app_preferences, s);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        //inflater.inflate(R.menu.toolbar_lessons, menu);
    }

    //READ DATA FROM PREFERENCE ANYWHERE
    /*SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
    String strUserName = SP.getString("username", "NA");
    boolean bAppUpdates = SP.getBoolean("applicationUpdates",false);
    String downloadType = SP.getString("downloadType","1");*/

}
