package com.example.pochekuev.myapplication;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class FRasp extends Fragment {

    public String ss;
    private TextView s1;

    public FRasp() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.raspzan_listview, container, false);

        s1=(TextView) v.findViewById(R.id.name_disciplines);
        s1.setText(ss);
        return v;


    }

}
