package com.redhawk.pochekuev.sibadirasp.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.redhawk.pochekuev.sibadirasp.MainActivity;
import com.redhawk.pochekuev.sibadirasp.R;
import com.redhawk.pochekuev.sibadirasp.adapter.BasePreferenceFragmentCompat;

public class SettingsFragment extends BasePreferenceFragmentCompat {

    SharedPreferences mSettings;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String s) {
        setPreferencesFromResource(R.xml.app_preferences, s);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((MainActivity)getActivity()).getSupportActionBar().setTitle("Настройки");
        View v = super.onCreateView(inflater, container, savedInstanceState);

        return v;
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
