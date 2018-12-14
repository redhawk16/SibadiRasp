package com.example.pochekuev.myapplication;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pochekuev.myapplication.adapter.ListLessonsAdapter;
import com.example.pochekuev.myapplication.database.DatabaseHelper;
import com.example.pochekuev.myapplication.items.Lessons;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Objects;


public class FRasp extends Fragment {

    private ListView lvLessons;
    private ListLessonsAdapter adapter;
    private List<Lessons> mLessonsList;
    private DatabaseHelper mDBHelper;

    public FRasp() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.lessons_item, container, false);
        View b = inflater.inflate(R.layout.lessons_listview, container, false);


        /*s1=(TextView) v.findViewById(R.id.name_disciplines);
        s1.setText(ss);*/


        lvLessons = (ListView) b.findViewById(R.id.listview_product);
        mDBHelper = new DatabaseHelper(getContext());

        //Check exists database
        File database = Objects.requireNonNull(getActivity()).getApplicationContext().getDatabasePath(DatabaseHelper.DBNAME);
        if(!database.exists()) {
            mDBHelper.getReadableDatabase();
            //Copy db
            if(copyDatabase(getContext())) {
                Toast.makeText(getContext(), "Copy database succes", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Copy data error", Toast.LENGTH_SHORT).show();
            }
        }
        //Get product list in db when db exists
        mLessonsList = mDBHelper.getListProduct();
        //Init adapter
        adapter = new ListLessonsAdapter(getContext(), mLessonsList);
        //Set adapter for listview
        lvLessons.setAdapter(adapter);
        return b;
    }

    private boolean copyDatabase(Context context) {
        try {

            InputStream inputStream = context.getAssets().open(DatabaseHelper.DBNAME);
            String outFileName = DatabaseHelper.DBLOCATION + DatabaseHelper.DBNAME;
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte[]buff = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(buff)) > 0) {
                outputStream.write(buff, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            Log.w("MainActivity","DB copied");
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
