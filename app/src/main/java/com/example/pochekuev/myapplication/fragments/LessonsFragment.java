package com.example.pochekuev.myapplication.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.pochekuev.myapplication.R;
import com.example.pochekuev.myapplication.adapter.SectionsPagerAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class LessonsFragment extends Fragment {

    public static boolean startActivity = false;
    public static Object selectedWeek = "нечетная неделя";
    public static Object selectedSubgroup = "первая подгруппа";
    SharedPreferences sharedPreferences;

    public static ViewPager mViewPager;
    public static PagerAdapter pagerAdapter;

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

        startActivity = true;

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

        /* SPINNER - тип недели*/
        String[] typeweek = {"обе недели", "нечетная неделя", "четная неделя"};

        ArrayAdapter<String> adapterTypeeweek = new ArrayAdapter<String>(getContext(), R.layout.spinner_item, typeweek);
        adapterTypeeweek.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final Spinner spinnerTypeeweek = (Spinner) rootView.findViewById(R.id.spinner);
        spinnerTypeeweek.setAdapter(adapterTypeeweek);

        //spinner.setPrompt("Title"); // заголовок
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getBaseContext());
        int typeWeek = sharedPreferences.getInt("spinner", 1);
        spinnerTypeeweek.setSelection(typeWeek); // старт с 0 элемента(нечетная неделя)
        spinnerTypeeweek.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // устанавливаем обработчик нажатия
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getContext(), "Position = " + spinner.getSelectedItem(), Toast.LENGTH_SHORT).show();
                selectedWeek=spinnerTypeeweek.getSelectedItem();
                pagerAdapter.notifyDataSetChanged();

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("spinner", spinnerTypeeweek.getSelectedItemPosition());
                editor.apply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });


        /* SPINNER - SUBGROUP */
        /*String[] subgroup = {"первая подгруппа", "вторая подгруппа"};

        ArrayAdapter<String> adapterSubgroup = new ArrayAdapter<String>(getContext(), R.layout.spinner_item, subgroup);
        adapterSubgroup.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final Spinner spinnerSubgroup = (Spinner) rootView.findViewById(R.id.subgroup);
        spinnerSubgroup.setAdapter(adapterSubgroup);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getBaseContext());
        int subGroup = Integer.parseInt(sharedPreferences.getString("subgroup", "0"));
        spinnerSubgroup.setSelection(subGroup);
        spinnerSubgroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedSubgroup=spinnerSubgroup.getSelectedItem();
                pagerAdapter.notifyDataSetChanged();

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("subgroup", String.valueOf(spinnerSubgroup.getSelectedItemPosition()));
                editor.apply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });*/


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.toolbar_lessons, menu);
    }
}