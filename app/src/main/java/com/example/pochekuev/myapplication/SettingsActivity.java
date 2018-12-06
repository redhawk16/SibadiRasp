package com.example.pochekuev.myapplication;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;

public class SettingsActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    SharedPreferences mSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemeChanger.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_settings);

        getActionBar().setDisplayHomeAsUpEnabled(true);

        mSettings = getSharedPreferences("mysett", Context.MODE_PRIVATE);

        Switch switch1=(Switch) findViewById(R.id.switch1);
        if(switch1!=null){
            switch1.setOnCheckedChangeListener(this);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        SharedPreferences.Editor editor = mSettings.edit();
        editor.clear();
        editor.putString("THEME", "DarkTheme");
        editor.apply();

        //MainActivity cls2 = new MainActivity();
        //cls2.recreateActivity();
        ThemeChanger.changeToTheme(SettingsActivity.this, 1);
        Intent intent=new Intent(SettingsActivity.this, MainActivity.class);
       // startActivity(intent);
    }
}
