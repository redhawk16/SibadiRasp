package com.example.pochekuev.myapplication.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pochekuev.myapplication.R;
import com.example.pochekuev.myapplication.RaspZan.Lessons;

import java.util.List;

public class ListLessonsAdapter extends BaseAdapter {
    private Context mContext;
    private List<Lessons> mLessonsList;

    public ListLessonsAdapter(Context mContext, List<Lessons> mLessonsList) {
        this.mContext = mContext;
        this.mLessonsList = mLessonsList;
    }

    @Override
    public int getCount() {
        return mLessonsList.size();
    }

    @Override
    public Object getItem(int position) {
        return mLessonsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mLessonsList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext, R.layout.raspzan_listview, null);
        TextView time=(TextView) v.findViewById(R.id.time);
        TextView name_disciplines=(TextView) v.findViewById(R.id.name_disciplines);
        TextView type_disciplines=(TextView) v.findViewById(R.id.type_disciplines);
        TextView name_prep=(TextView) v.findViewById(R.id.name_prep);
        TextView num_cab=(TextView) v.findViewById(R.id.num_cab);

        time.setText(mLessonsList.get(position).getTime());
        name_disciplines.setText(mLessonsList.get(position).getDisciplines());
        type_disciplines.setText(mLessonsList.get(position).getTypelessons());
        name_prep.setText(mLessonsList.get(position).getTeachers());
        num_cab.setText(mLessonsList.get(position).getAuditories());

        return null;
    }
}
