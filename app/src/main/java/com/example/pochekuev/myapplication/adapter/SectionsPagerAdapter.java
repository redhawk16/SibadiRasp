package com.example.pochekuev.myapplication.adapter;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.widget.TextView;

import com.example.pochekuev.myapplication.FRasp;
import com.example.pochekuev.myapplication.R;
import com.example.pochekuev.myapplication.database.DatabaseHelper;
import com.example.pochekuev.myapplication.fragments.LessonsFragment;

public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

    private DatabaseHelper mDBHelper;
    private static SQLiteDatabase mDb;

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                FRasp rasp= new FRasp();
                String sqlQuery = "select  _id, Time_Start, Time_End, Name_Discipline, Name_Typelesson, Name_Teacher, Number_Auditory "
                        + "from Schedules "
                        + "inner join Lessons on Schedules.Number_Lesson=Lessons.Number_Lesson "
                        + "inner join Disciplines on Schedules.Code_Discipline=Disciplines.Code_Discipline "
                        + "inner join Typelessons on Schedules.Code_Typelesson=Typelessons.Code_Typelesson "
                        + "inner join Teachers on Schedules.Code_Teacher=Teachers.Code_Teacher "
                        + "inner join Auditories on Schedules.Code_Auditory=Auditories.Code_Auditory "
                        + "inner join Typeweeks on Schedules.Code_Typeweek=Typeweeks.Code_Typeweek "
                        + "inner join Dayweeks on Schedules.Code_Dayweek=Dayweeks.Code_Dayweek "
                        + "where Name_Typeweek='"+LessonsFragment.selectedWeek+"' AND Name_Dayweek='Понедельник' "
                        + "order by Time_Start, Time_End";
                rasp.sqlQuery=sqlQuery;
                return rasp;
            case 1:
                rasp= new FRasp();
                sqlQuery = "select  _id, Time_Start, Time_End, Name_Discipline, Name_Typelesson, Name_Teacher, Number_Auditory "
                        + "from Schedules "
                        + "inner join Lessons on Schedules.Number_Lesson=Lessons.Number_Lesson "
                        + "inner join Disciplines on Schedules.Code_Discipline=Disciplines.Code_Discipline "
                        + "inner join Typelessons on Schedules.Code_Typelesson=Typelessons.Code_Typelesson "
                        + "inner join Teachers on Schedules.Code_Teacher=Teachers.Code_Teacher "
                        + "inner join Auditories on Schedules.Code_Auditory=Auditories.Code_Auditory "
                        + "inner join Typeweeks on Schedules.Code_Typeweek=Typeweeks.Code_Typeweek "
                        + "inner join Dayweeks on Schedules.Code_Dayweek=Dayweeks.Code_Dayweek "
                        + "where Name_Typeweek='"+LessonsFragment.selectedWeek+"' AND Name_Dayweek='Вторник' "
                        + "order by Time_Start, Time_End";
                 rasp.sqlQuery=sqlQuery;
                return rasp;
            case 2:
                rasp= new FRasp();
                sqlQuery = "select  _id, Time_Start, Time_End, Name_Discipline, Name_Typelesson, Name_Teacher, Number_Auditory "
                        + "from Schedules "
                        + "inner join Lessons on Schedules.Number_Lesson=Lessons.Number_Lesson "
                        + "inner join Disciplines on Schedules.Code_Discipline=Disciplines.Code_Discipline "
                        + "inner join Typelessons on Schedules.Code_Typelesson=Typelessons.Code_Typelesson "
                        + "inner join Teachers on Schedules.Code_Teacher=Teachers.Code_Teacher "
                        + "inner join Auditories on Schedules.Code_Auditory=Auditories.Code_Auditory "
                        + "inner join Typeweeks on Schedules.Code_Typeweek=Typeweeks.Code_Typeweek "
                        + "inner join Dayweeks on Schedules.Code_Dayweek=Dayweeks.Code_Dayweek "
                        + "where Name_Typeweek='"+LessonsFragment.selectedWeek+"' AND Name_Dayweek='Среда' "
                        + "order by Time_Start, Time_End";
                rasp.sqlQuery=sqlQuery;
                return rasp;
            case 3:
                rasp= new FRasp();
                sqlQuery = "select  _id, Time_Start, Time_End, Name_Discipline, Name_Typelesson, Name_Teacher, Number_Auditory "
                        + "from Schedules "
                        + "inner join Lessons on Schedules.Number_Lesson=Lessons.Number_Lesson "
                        + "inner join Disciplines on Schedules.Code_Discipline=Disciplines.Code_Discipline "
                        + "inner join Typelessons on Schedules.Code_Typelesson=Typelessons.Code_Typelesson "
                        + "inner join Teachers on Schedules.Code_Teacher=Teachers.Code_Teacher "
                        + "inner join Auditories on Schedules.Code_Auditory=Auditories.Code_Auditory "
                        + "inner join Typeweeks on Schedules.Code_Typeweek=Typeweeks.Code_Typeweek "
                        + "inner join Dayweeks on Schedules.Code_Dayweek=Dayweeks.Code_Dayweek "
                        + "where Name_Typeweek='"+LessonsFragment.selectedWeek+"' AND Name_Dayweek='Четверг' "
                        + "order by Time_Start, Time_End";
                rasp.sqlQuery=sqlQuery;
                return rasp;
            case 4:
                rasp= new FRasp();
                sqlQuery = "select  _id, Time_Start, Time_End, Name_Discipline, Name_Typelesson, Name_Teacher, Number_Auditory "
                        + "from Schedules "
                        + "inner join Lessons on Schedules.Number_Lesson=Lessons.Number_Lesson "
                        + "inner join Disciplines on Schedules.Code_Discipline=Disciplines.Code_Discipline "
                        + "inner join Typelessons on Schedules.Code_Typelesson=Typelessons.Code_Typelesson "
                        + "inner join Teachers on Schedules.Code_Teacher=Teachers.Code_Teacher "
                        + "inner join Auditories on Schedules.Code_Auditory=Auditories.Code_Auditory "
                        + "inner join Typeweeks on Schedules.Code_Typeweek=Typeweeks.Code_Typeweek "
                        + "inner join Dayweeks on Schedules.Code_Dayweek=Dayweeks.Code_Dayweek "
                        + "where Name_Typeweek='"+LessonsFragment.selectedWeek+"' AND Name_Dayweek='Пятница' "
                        + "order by Time_Start, Time_End";
                rasp.sqlQuery=sqlQuery;
                return rasp;
            case 5:
                rasp= new FRasp();
                sqlQuery = "select  _id, Time_Start, Time_End, Name_Discipline, Name_Typelesson, Name_Teacher, Number_Auditory "
                        + "from Schedules "
                        + "inner join Lessons on Schedules.Number_Lesson=Lessons.Number_Lesson "
                        + "inner join Disciplines on Schedules.Code_Discipline=Disciplines.Code_Discipline "
                        + "inner join Typelessons on Schedules.Code_Typelesson=Typelessons.Code_Typelesson "
                        + "inner join Teachers on Schedules.Code_Teacher=Teachers.Code_Teacher "
                        + "inner join Auditories on Schedules.Code_Auditory=Auditories.Code_Auditory "
                        + "inner join Typeweeks on Schedules.Code_Typeweek=Typeweeks.Code_Typeweek "
                        + "inner join Dayweeks on Schedules.Code_Dayweek=Dayweeks.Code_Dayweek "
                        + "where Name_Typeweek='"+LessonsFragment.selectedWeek+"' AND Name_Dayweek='Суббота' "
                        + "order by Time_Start, Time_End";
                rasp.sqlQuery=sqlQuery;
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

    @Override
    public int getItemPosition(Object object) { return POSITION_NONE; }
}
