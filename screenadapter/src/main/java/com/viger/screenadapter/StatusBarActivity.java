package com.viger.screenadapter;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.viger.screenadapter.utils.StatusBarUtils;

public class StatusBarActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_bar);
        StatusBarUtils.drawableStatusBar(this, android.R.color.holo_red_light);
        toolbar = findViewById(R.id.tool_bar);

        toolbar.setTitle("沉浸式状态栏展示");




    }
}
