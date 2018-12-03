package com.example.pochekuev.myapplication;

import android.app.Activity;
import android.content.Intent;

public class Utils {
    private static int sTheme;

    public final static int BlueTheme = 0;
    public final static int DarkTheme = 1;

    public static void changeToTheme(Activity activity, int theme) {
        sTheme = theme;
        activity.finish();
        activity.startActivity(new Intent(activity, activity.getClass()));
        activity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public static void onActivityCreateSetTheme(Activity activity) {
        switch (sTheme) {
            default:
            case BlueTheme:
                activity.setTheme(R.style.BlueTheme);
                break;
            case DarkTheme:
                activity.setTheme(R.style.DarkTheme);
                break;
        }
    }
}
