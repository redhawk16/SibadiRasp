package com.example.pochekuev.myapplication.fonts;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextView;

public class fa_solid extends android.support.v7.widget.AppCompatTextView {


    public fa_solid(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public fa_solid(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public fa_solid(Context context) {
        super(context);
        init();
    }

    private void init() {

        //Font name should not contain "/".
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "FA5Solid400.otf");
        setTypeface(tf);
    }

}
