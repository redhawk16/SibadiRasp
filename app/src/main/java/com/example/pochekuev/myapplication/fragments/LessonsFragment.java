package com.example.pochekuev.myapplication.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pochekuev.myapplication.FRasp;
import com.example.pochekuev.myapplication.MainActivity;
import com.example.pochekuev.myapplication.MainActivity.*;
import com.example.pochekuev.myapplication.R;
import com.example.pochekuev.myapplication.adapter.ListLessonsAdapter;
import com.example.pochekuev.myapplication.adapter.SectionsPagerAdapter;
import com.example.pochekuev.myapplication.database.DatabaseHelper;
import com.example.pochekuev.myapplication.items.Lessons;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class LessonsFragment extends Fragment {

    public static Object selectedWeek="нечетная неделя";
    SharedPreferences mSettings;

    //private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    PagerAdapter pagerAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_lessons, container, false);
        super.onCreate(savedInstanceState);

        /* ViewPager - START */
        pagerAdapter = new SectionsPagerAdapter(getFragmentManager());
        mViewPager = (ViewPager) v.findViewById(R.id.container);
        mViewPager.setAdapter(pagerAdapter);

        TabLayout tabLayout = (TabLayout) v.findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
        /* ViewPager - END */
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        //Поиск эл-та меню и define его
        final MenuItem alertMenuItem = menu.findItem(R.id.toolbar_lessons_custom);
        RelativeLayout rootView = (RelativeLayout) alertMenuItem.getActionView();

        /* Установка текущего дня в Today */
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE", new Locale("ru"));
        Date date = new Date();
        TextView today = (TextView) rootView.findViewById(R.id.today);
        today.setText("Сегодня " + sdf.format(date) + ",");

        /* Spinner - тип недели*/
        String[] typeweek = {"обе недели", "нечетная неделя", "четная неделя"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_item, typeweek);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final Spinner spinner = (Spinner) rootView.findViewById(R.id.spinner);
        spinner.setAdapter(adapter);

        //spinner.setPrompt("Title"); // заголовок
        spinner.setSelection(1); // старт с 0 элемента(обе недели)
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // устанавливаем обработчик нажатия
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), "Position = " + spinner.getSelectedItem(), Toast.LENGTH_SHORT).show();
                selectedWeek=spinner.getSelectedItem();
                pagerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.toolbar_lessons, menu);
    }
}
