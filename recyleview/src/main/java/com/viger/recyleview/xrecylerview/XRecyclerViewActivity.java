package com.viger.recyleview.xrecylerview;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.viger.recyleview.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class XRecyclerViewActivity extends AppCompatActivity {

    @BindView(R.id.xRecyclerView)
    XRecyclerView mXRecyclerView;
    MyAdapter myAdapter;
    List<String> data = new ArrayList<>();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_x_recycler_view);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initData() {
        for(int i=0; i<30; i++) {
            data.add("数据 " + i);
        }
        myAdapter = new MyAdapter(data);
        mXRecyclerView.setAdapter(myAdapter);
    }

    private void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mXRecyclerView.setLayoutManager(linearLayoutManager);

        mXRecyclerView.setPullRefreshEnabled(true);
        mXRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mXRecyclerView.setLoadingMoreEnabled(true);
        mXRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        //mXRecyclerView.setArrowImageView(R.drawable.iconfont_downgrey);

        //设置头部
        mXRecyclerView.getDefaultRefreshHeaderView().setRefreshTimeVisible(true);
        //View header = LayoutInflater.from(this).inflate(R.layout.layout_refresh_header_view,
               // (ViewGroup)findViewById(android.R.id.content),false);
        //mXRecyclerView.addHeaderView(header);

        //mXRecyclerView.getDefaultFootView().setLoadingHint("自定义加载中提示");
        mXRecyclerView.getDefaultFootView().setNoMoreHint("自定义加载完毕提示");

        mXRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //下拉刷新回调
                data.clear();
                for(int i=0; i<30; i++) {
                    data.add("下拉刷新de数据 " + i);
                }
                myAdapter.notifyDataSetChanged();
                mXRecyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                //加载更多
                List<String> newData = new ArrayList<>();
                for(int i=0; i<30; i++) {
                    newData.add("加载更多de数据 " + i);
                }
                data.addAll(newData);
                myAdapter.notifyDataSetChanged();
                mXRecyclerView.loadMoreComplete();
            }
        });


    }

    //点击加载数据
    private void onClick() {

    }



}
