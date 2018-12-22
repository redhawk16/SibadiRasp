package com.redhawk.pochekuev.sibadirasp;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.redhawk.pochekuev.sibadirasp.adapter.ListLessonsAdapter;
import com.redhawk.pochekuev.sibadirasp.database.DatabaseHelper;
import com.redhawk.pochekuev.sibadirasp.items.Lessons;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Objects;


public class FRasp extends Fragment {

    public String sqlQuery;
    private ListView lvLessons;
    private ListLessonsAdapter adapter;
    private List<Lessons> mLessonsList;
    private DatabaseHelper mDBHelper;
    private static final String LOG_TAG = "eee";

    public FRasp() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.lessons_listview, container, false);
        super.onCreate(savedInstanceState);

        lvLessons = (ListView) v.findViewById(R.id.listview_product);
        mDBHelper = new DatabaseHelper(getContext());

        //Check exists database
        File database = Objects.requireNonNull(getActivity()).getApplicationContext().getDatabasePath(DatabaseHelper.DBNAME);
        if(!database.exists()) {
            mDBHelper.getReadableDatabase();
            //Copy db
            if(!copyDatabase(getContext())) Toast.makeText(getContext(), "Ошибка! БД не найдена.", Toast.LENGTH_SHORT).show();
        }

        try {
            mLessonsList = mDBHelper.getListProduct(sqlQuery);
            //Init adapter
            adapter = new ListLessonsAdapter(getContext(), mLessonsList);
            //Set adapter for listview
            lvLessons.setAdapter(adapter);
        }
        catch (Exception e){
            Log.d("Error", e.toString());
        }
        return v;
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
