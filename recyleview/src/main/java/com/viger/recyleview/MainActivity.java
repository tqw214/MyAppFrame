package com.viger.recyleview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //基本使用
    public void basicUse(View view) {
        Intent intent = new Intent(this, BasicUseActivity.class);
        startActivity(intent);
    }

    //通用万能adapter
    public void commonAdapter(View view) {
        Intent intent = new Intent(this, CommonAdapterActivity.class);
        startActivity(intent);
    }
//
//    public void dragItemAnimator(View view) {
//        Intent intent = new Intent(this, DragItemAnimatorActivity.class);
//        startActivity(intent);
//    }
//
//    public void headerFooter(View view) {
//        Intent intent = new Intent(this, HeaderFooterActivity.class);
//        startActivity(intent);
//    }
//
//    public void refreshLoad(View view){
//        Intent intent = new Intent(this, RefreshLoadActivity.class);
//        startActivity(intent);
//    }


}
