package com.example.pochekuev.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pochekuev.myapplication.RaspZan.Lessons;
import com.example.pochekuev.myapplication.adapter.ListLessonsAdapter;
import com.example.pochekuev.myapplication.database.DatabaseHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String LOG_TAG = "TAG";
    //Переменная для работы с БД
    private DatabaseHelper mDBHelper;
    private SQLiteDatabase mDb;

    /* Spinner - тип недели*/
    String[] typeweek = {"обе недели", "нечетная неделя", "четная неделя"};

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    //
    private ListView lvLessons;
    private ListLessonsAdapter lessonsAdapter;
    private List<Lessons> mLessonsList;

    SharedPreferences mSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Change Theme
        ThemeChanger.onActivityCreateSetTheme(this);
        /*SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        int theme = sp.getInt("BlueTheme", R.style.BlueTheme);
        setTheme(theme);*/

        //Lessons - start
        setContentView(R.layout.activity_main);

        mSettings = getSharedPreferences("mysett", Context.MODE_PRIVATE);

        lvLessons = (ListView) findViewById(R.id.listview_raspzan);
        mDBHelper = new DatabaseHelper(this);

        //mLessonsList = mDBHelper.getListLessons();
        //Init adapter
        //lessonsAdapter = new ListLessonsAdapter(this, mLessonsList);
        //Set adapter for listview
        //lvLessons.setAdapter(lessonsAdapter);
        //Lessons - end

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /* Установка текущего дня в Today */
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE", new Locale("ru"));
        Date date = new Date();
        TextView today = (TextView) findViewById(R.id.today);
        today.setText("Сегодня " + sdf.format(date) + ",");

        /* Spinner - тип недели*/
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, typeweek);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(adapter);

        spinner.setPrompt("Title"); // заголовок
        spinner.setSelection(0); // старт с 0 элемента(обе недели)
        /*ColorStateList oldColors =  today.getTextColors(); //save original colors
        today.setTextColor(oldColors);*/
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // устанавливаем обработчик нажатия
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), "Position = " + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

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

        mDBHelper = new DatabaseHelper(this);

        try {
            mDBHelper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }

        try {
            mDb = mDBHelper.getWritableDatabase();
        } catch (SQLException mSQLException) {
            throw mSQLException;
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

        if(mSettings.contains("THEME")) {
            String nametheme=mSettings.getString("THEME", "");
            if(nametheme == "DarkTheme") {
                ThemeChanger.changeToTheme(this, 1);
            } else if (nametheme == "BlueTheme") {
                ThemeChanger.changeToTheme(this, 0);
            } else { return; }
        }

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
            Intent intent=new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
            //Utils.changeToTheme(MainActivity.this, 1);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_lessons) {

        } else if (id == R.id.nav_teachers) {

        } else if (id == R.id.nav_session) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_settings) {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_about) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).

            //return PlaceholderFragment.newInstance(position + 1);

            switch (position){
                case 0:
                    FRasp rasp= new FRasp();
                    return rasp;
                case 1:
                    rasp= new FRasp();
                    /*String product = "";

                    Cursor cursor = mDb.rawQuery("SELECT * FROM Disciplines", null);
                    cursor.moveToFirst();
                    while (!cursor.isAfterLast()) {
                        product += cursor.getString(1) + " | ";
                        cursor.moveToNext();
                    }
                    cursor.close();

                    TextView textView6=(TextView) findViewById(R.id.test);
                    textView6.setText(product);*/
                    return rasp;
                case 2:
                    rasp= new FRasp();
                    return rasp;
                case 3:
                    rasp= new FRasp();
                    return rasp;
                case 4:
                    rasp= new FRasp();
                    return rasp;
                case 5:
                    rasp= new FRasp();
                    return rasp;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 6 total pages.
            return 6;
        }
    }
}
