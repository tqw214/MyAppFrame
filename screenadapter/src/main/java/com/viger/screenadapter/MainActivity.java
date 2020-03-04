package com.viger.screenadapter;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showScreenInfo();
    }

    private void showScreenInfo() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        Log.d("tag","screenWidth = " + displayMetrics.widthPixels);
        Log.d("tag","screenHeight = " + displayMetrics.heightPixels);
    }

}
