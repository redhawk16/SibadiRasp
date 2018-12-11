package com.example.pochekuev.myapplication.adapter;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.TextView;

import com.example.pochekuev.myapplication.FRasp;
import com.example.pochekuev.myapplication.R;
import com.example.pochekuev.myapplication.database.DatabaseHelper;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private DatabaseHelper mDBHelper;
    private static SQLiteDatabase mDb;

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
                String product = "grg";

                /*Cursor cursor = mDb.rawQuery("SELECT * FROM Product", null);
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    product += cursor.getString(1) + " | ";
                    cursor.moveToNext();
                }
                cursor.close();*/

                //rasp.TextSET(product);
                return rasp;
            case 1:
                rasp= new FRasp();

                 product = "grg";
                 rasp.ss=product;
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
