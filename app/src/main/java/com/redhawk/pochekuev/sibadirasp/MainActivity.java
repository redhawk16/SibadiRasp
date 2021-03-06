package com.redhawk.pochekuev.sibadirasp;

/*  Author:
*   Date: December 2018
*   Info: Sibadi, 3-year student
* */

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.redhawk.pochekuev.sibadirasp.fragments.AboutFragment;
import com.redhawk.pochekuev.sibadirasp.fragments.LessonsFragment;
import com.redhawk.pochekuev.sibadirasp.fragments.SettingsFragment;

/** TODO:
 *  1. ListView Adapter + customize
 *  2. Database Adapter + Create DB
 *  3. Add another NavDrawer Item's
 * */

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, SharedPreferences.OnSharedPreferenceChangeListener {

    private static final String LOG_TAG = "TAG";
    public static NavigationView navigationView;

    static {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /** Load UI Theme - START **/
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean firstRun = prefs.getBoolean("firstRun", false); // Check first run app
        if(!firstRun){
            SharedPreferences.Editor e = prefs.edit();
            e.putBoolean("firstRun", true);
            e.putString("themes", "Default"); //Set Default Theme in first time
            e.apply();
        }

        String value = prefs.getString("themes", "themes_value");
        prefs.registerOnSharedPreferenceChangeListener(this);

        if (value.equals("Default")) {
            setTheme(R.style.AppTheme_BlueTheme);
        } else if (value.equals("Dark")) {
            setTheme(R.style.DarkTheme);
        }else if (value.equals("Violet")) {
            setTheme(R.style.AppTheme_VioletTheme);
        }
        /** Load UI Theme - END **/

        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Delete AppBar OutlineShadow
        AppBarLayout appbarLayout = (AppBarLayout) findViewById(R.id.appBar);
        appbarLayout.setOutlineProvider(null);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if(!LessonsFragment.startActivity) {
            setSelectedItemNavDrawer(0);
        }
    }

    public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
        // Проверять общие настройки, ключевые параметры и изменять UI
        if (key.equals("themes")) {
            String value = prefs.getString(key,"name");
            LessonsFragment.startActivity = false;
            if (value.equals("Default")) {
                ThemeChanger.changeToTheme(this, 0);
            } else if (value.equals("Dark")) {
                ThemeChanger.changeToTheme(this, 1);
            } else if (value.equals("Violet")) {
                ThemeChanger.changeToTheme(this, 2);
            }
        }
    }

    public void setSelectedItemNavDrawer(int position){
        navigationView.getMenu().getItem(position).setChecked(true);
        onNavigationItemSelected(navigationView.getMenu().getItem(position)); //Set MenuItem on ActivityStart
    }

    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy");
    }

    protected void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "onPause");
    }

    protected void onRestart() {
        super.onRestart();
        Log.d(LOG_TAG, "onRestart");
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(LOG_TAG, "onRestoreInstanceState");
    }

    protected void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "onResume ");
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(LOG_TAG, "onSaveInstanceState");
    }

    protected void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "onStart");
    }

    protected void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "onStop");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            LessonsFragment.startActivity = false;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        Fragment fragment = null;

        int id = item.getItemId();

        if (id == R.id.nav_lessons) {
            fragment = new LessonsFragment();
        } else if (id == R.id.nav_teachers) {

        } else if (id == R.id.nav_session) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_settings) {
            fragment = new SettingsFragment();
        } else if (id == R.id.nav_about) {
            fragment = new AboutFragment();
        }

        if(fragment != null){
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            fragmentTransaction.replace(R.id.fragment_area, fragment);
            fragmentTransaction.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
