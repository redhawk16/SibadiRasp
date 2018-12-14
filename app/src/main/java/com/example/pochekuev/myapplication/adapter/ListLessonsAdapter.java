package com.example.pochekuev.myapplication.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pochekuev.myapplication.R;
import com.example.pochekuev.myapplication.items.Lessons;

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
        View v = View.inflate(mContext, R.layout.lessons_item, null);
        TextView time_start=(TextView) v.findViewById(R.id.time_start);
        TextView time_end=(TextView) v.findViewById(R.id.time_end);
        TextView disciplines=(TextView) v.findViewById(R.id.name_disciplines);
        TextView typelessons=(TextView) v.findViewById(R.id.type_disciplines);
        TextView teachers=(TextView) v.findViewById(R.id.name_teacher);
        TextView auditories=(TextView) v.findViewById(R.id.num_cab);

        time_start.setText(mLessonsList.get(position).getTimeStart());
        time_end.setText(mLessonsList.get(position).getTimeEnd());
        disciplines.setText(mLessonsList.get(position).getDisciplines());
        typelessons.setText(mLessonsList.get(position).getTypelessons());
        teachers.setText("преп. " + mLessonsList.get(position).getTeachers());
        auditories.setText("ауд. " + mLessonsList.get(position).getAuditories());

        return v;
    }
}
