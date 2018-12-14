package com.example.pochekuev.myapplication;

/*  Author:
*   Date: December 2018
*   Info: Sibadi, 3-year student
* */

import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
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
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.pochekuev.myapplication.items.Lessons;
import com.example.pochekuev.myapplication.adapter.ListLessonsAdapter;
import com.example.pochekuev.myapplication.database.DatabaseHelper;
import com.example.pochekuev.myapplication.fragments.AboutFragment;
import com.example.pochekuev.myapplication.fragments.LessonsFragment;
import com.example.pochekuev.myapplication.fragments.SettingsFragment;

import java.util.List;

/** TODO:
 *  1. ListView Adapter + customize
 *  2. Database Adapter + Create DB
 *  3. Add another NavDrawer Item's
 * */

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, SharedPreferences.OnSharedPreferenceChangeListener {

    private static final String LOG_TAG = "TAG";
    //Переменная для работы с БД
    private DatabaseHelper mDBHelper;
    private static SQLiteDatabase mDb;

    //
    private ListView lvLessons;
    private ListLessonsAdapter lessonsAdapter;
    private List<Lessons> mLessonsList;

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
            setTheme(R.style.BlueTheme);
        } else if (value.equals("Dark")) {
            setTheme(R.style.DarkTheme);
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

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.getMenu().getItem(0).setChecked(true);
        onNavigationItemSelected(navigationView.getMenu().getItem(0)); //Set MenuItem on ActivityStart

        //Lessons - start
       // lvLessons = (ListView) findViewById(R.id.listview_raspzan);
        //mDBHelper = new DatabaseHelper(this);

//        mLessonsList = mDBHelper.getListLessons();
        //Init adapter
      //  lessonsAdapter = new ListLessonsAdapter(this, mLessonsList);
        //Set adapter for listview
        //lvLessons.setAdapter(lessonsAdapter);
        //Lessons - end
    }

    public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
        // Проверять общие настройки, ключевые параметры и изменять UI
        if (key.equals("themes")) {
            String value = prefs.getString(key,"name");
            if (value.equals("Default")) {
                ThemeChanger.changeToTheme(this, 0);
            } else if (value.equals("Dark")) {
                ThemeChanger.changeToTheme(this, 1);
            }
        }
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
