package com.example.pochekuev.myapplication.database;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.pochekuev.myapplication.items.Lessons;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NgocTri on 11/7/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "info1.db";
    public static final String DBLOCATION = "/data/data/com.example.pochekuev.myapplication/databases/";
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public DatabaseHelper(Context context) {
        super(context, DBNAME, null, 1);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void openDatabase() {
        String dbPath = mContext.getDatabasePath(DBNAME).getPath();
        if(mDatabase != null && mDatabase.isOpen()) {
            return;
        }
        mDatabase = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public void closeDatabase() {
        if(mDatabase!=null) {
            mDatabase.close();
        }
    }

    public List<Lessons> getListProduct(String sqlQuery) {
        Lessons product = null;
        List<Lessons> productList = new ArrayList<>();
        openDatabase();
        /*sqlQuery = "select  _id, Time_Start, Time_End, Name_Discipline, Name_Typelesson, Name_Teacher, Number_Auditory "
                + "from Schedules "
                + "inner join Lessons on Schedules.Number_Lesson=Lessons.Number_Lesson "
                + "inner join Disciplines on Schedules.Code_Discipline=Disciplines.Code_Discipline "
                + "inner join Typelessons on Schedules.Code_Typelesson=Typelessons.Code_Typelesson "
                + "inner join Teachers on Schedules.Code_Teacher=Teachers.Code_Teacher "
                + "inner join Auditories on Schedules.Code_Auditory=Auditories.Code_Auditory "
                + "inner join Typeweeks on Schedules.Code_Typeweek=Typeweeks.Code_Typeweek "
                + "inner join Dayweeks on Schedules.Code_Dayweek=Dayweeks.Code_Dayweek "
                + "where Name_Typeweek='Нечетная' AND Name_Dayweek='Понедельник'";*/
        Cursor cursor = mDatabase.rawQuery(sqlQuery, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            product = new Lessons(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6));
            productList.add(product);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return productList;
    }
}
