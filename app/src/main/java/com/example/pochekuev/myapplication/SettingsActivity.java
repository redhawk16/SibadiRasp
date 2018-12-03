package com.example.pochekuev.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.CompoundButton;
import android.widget.Switch;

public class SettingsActivity extends Activity implements CompoundButton.OnCheckedChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Utils.onActivityCreateSetTheme(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Switch switch1=(Switch) findViewById(R.id.switch1);
        if(switch1!=null){
            switch1.setOnCheckedChangeListener(this);
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);

        String currentTheme = settings.getString("current_theme", "DarkTheme");
        if (currentTheme == "DarkTheme") {
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("current_theme", "DarkTheme");
            editor.apply();
            Utils.changeToTheme(SettingsActivity.this, 1);
        } else {
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("current_theme", "BlueTheme");
            editor.apply();
            Utils.changeToTheme(SettingsActivity.this, 0);
        }
        Intent intent=new Intent(SettingsActivity.this, MainActivity.class);

       // startActivity(intent);
    }
}
