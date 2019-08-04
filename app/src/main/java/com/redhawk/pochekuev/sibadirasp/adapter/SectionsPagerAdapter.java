package com.redhawk.pochekuev.sibadirasp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.redhawk.pochekuev.sibadirasp.FRasp;
import com.redhawk.pochekuev.sibadirasp.fragments.LessonsFragment;

public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

    public static String sqlQuery;

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        FRasp rasp= new FRasp();
        sqlQuery = "SELECT _id, Time_Start, Time_End, Name_Discipline, Name_Typelesson, Name_Teacher, Number_Auditory "
                + "FROM Schedules "
                + "INNER JOIN Lessons ON Schedules.Number_Lesson=Lessons.Number_Lesson "
                + "INNER JOIN Disciplines ON Schedules.Code_Discipline=Disciplines.Code_Discipline "
                + "INNER JOIN Typelessons ON Schedules.Code_Typelesson=Typelessons.Code_Typelesson "
                + "INNER JOIN Teachers ON Schedules.Code_Teacher=Teachers.Code_Teacher "
                + "INNER JOIN Auditories ON Schedules.Code_Auditory=Auditories.Code_Auditory "
                + "INNER JOIN Typeweeks ON Schedules.Code_Typeweek=Typeweeks.Code_Typeweek "
                + "INNER JOIN Dayweeks ON Schedules.Code_Dayweek=Dayweeks.Code_Dayweek "
                + "INNER JOIN Subgroups ON Schedules.Code_Subgroup=Subgroups.Code_Subgroup "
                + "INNER JOIN Groups ON Schedules.Code_Group=Groups.Code_Group "
                + "INNER JOIN Faculties ON Schedules.Code_Faculty=Faculties.Code_Faculty "
                + "WHERE (Name_Typeweek='"+LessonsFragment.selectedWeek+"' OR Name_Typeweek='обе недели') "
                //+ "AND (Name_Subgroup='обе подгруппы' OR Num_Subgroup='"+LessonsFragment.selectedSubgroup+"') "
                + "AND Num_Dayweek='"+position+"' ORDER BY Time_Start, Time_End";
        switch (position){
            case 0:
                rasp.sqlQuery=sqlQuery;
                return rasp;
            case 1:
                 rasp.sqlQuery=sqlQuery;
                return rasp;
            case 2:
                rasp.sqlQuery=sqlQuery;
                return rasp;
            case 3:
                rasp.sqlQuery=sqlQuery;
                return rasp;
            case 4:
                rasp.sqlQuery=sqlQuery;
                return rasp;
            case 5:
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
