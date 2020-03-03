package com.viger.recyleview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.viger.recyleview.basicUse.BasicUseActivity;
import com.viger.recyleview.commonAdapterUse.CommonAdapterActivity;
import com.viger.recyleview.dragItemAnimatorUse.DragItemAnimatorActivity;
import com.viger.recyleview.headerFooterUse.HeaderFooterActivity;
import com.viger.recyleview.refreshLoad.RefreshLoadActivity;
import com.viger.recyleview.wannengadapter.RecyclerViewUniversalAdapter;
import com.viger.recyleview.xrecylerview.XRecyclerViewActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void basicUse(View view) {
        Intent intent = new Intent(this, BasicUseActivity.class);
        startActivity(intent);
    }

    public void commonAdapter(View view) {
        Intent intent = new Intent(this, CommonAdapterActivity.class);
        startActivity(intent);
    }

    public void dragItemAnimator(View view) {
        Intent intent = new Intent(this, DragItemAnimatorActivity.class);
        startActivity(intent);
    }

    public void headerFooter(View view) {
        Intent intent = new Intent(this, HeaderFooterActivity.class);
        startActivity(intent);
    }

    public void refreshLoad(View view){
        Intent intent = new Intent(this, RefreshLoadActivity.class);
        startActivity(intent);
    }

    public void doXRecyclerView(View view) {
        Intent intent = new Intent(this, XRecyclerViewActivity.class);
        startActivity(intent);
    }

}
