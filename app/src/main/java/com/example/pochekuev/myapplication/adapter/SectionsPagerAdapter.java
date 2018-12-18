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

    public static String sqlQuery;

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        FRasp rasp= new FRasp();
        sqlQuery = "select  _id, Time_Start, Time_End, Name_Discipline, Name_Typelesson, Name_Teacher, Number_Auditory "
                + "from Schedules "
                + "inner join Lessons on Schedules.Number_Lesson=Lessons.Number_Lesson "
                + "inner join Disciplines on Schedules.Code_Discipline=Disciplines.Code_Discipline "
                + "inner join Typelessons on Schedules.Code_Typelesson=Typelessons.Code_Typelesson "
                + "inner join Teachers on Schedules.Code_Teacher=Teachers.Code_Teacher "
                + "inner join Auditories on Schedules.Code_Auditory=Auditories.Code_Auditory "
                + "inner join Typeweeks on Schedules.Code_Typeweek=Typeweeks.Code_Typeweek "
                + "inner join Dayweeks on Schedules.Code_Dayweek=Dayweeks.Code_Dayweek "
                + "inner join Subgroups on Schedules.Code_Subgroup=Subgroups.Code_Subgroup ";
        switch (position){
            case 0:
                sqlQuery += "WHERE Name_Typeweek='"+LessonsFragment.selectedWeek+"' AND Name_Subgroup='обе подгруппы' AND Name_Subgroup='"+LessonsFragment.selectedSubgroup+"' AND Name_Dayweek='Понедельник' ORDER BY Time_Start, Time_End";
                rasp.sqlQuery=sqlQuery;
                return rasp;
            case 1:
                sqlQuery += "WHERE Name_Typeweek='"+LessonsFragment.selectedWeek+"' AND Name_Subgroup='обе подгруппы' AND Name_Subgroup='"+LessonsFragment.selectedSubgroup+"' AND Name_Dayweek='Вторник' ORDER BY Time_Start, Time_End";
                 rasp.sqlQuery=sqlQuery;
                return rasp;
            case 2:
                sqlQuery += "WHERE Name_Typeweek='"+LessonsFragment.selectedWeek+"' AND Name_Dayweek='Среда' ORDER BY Time_Start, Time_End";
                rasp.sqlQuery=sqlQuery;
                return rasp;
            case 3:
                sqlQuery += "WHERE Name_Typeweek='"+LessonsFragment.selectedWeek+"' AND Name_Dayweek='Четверг' ORDER BY Time_Start, Time_End";
                rasp.sqlQuery=sqlQuery;
                return rasp;
            case 4:
                sqlQuery += "WHERE Name_Typeweek='"+LessonsFragment.selectedWeek+"' AND Name_Dayweek='Пятница' ORDER BY Time_Start, Time_End";
                rasp.sqlQuery=sqlQuery;
                return rasp;
            case 5:
                sqlQuery += "WHERE Name_Typeweek='"+LessonsFragment.selectedWeek+"' AND Name_Dayweek='Суббота' ORDER BY Time_Start, Time_End";
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
