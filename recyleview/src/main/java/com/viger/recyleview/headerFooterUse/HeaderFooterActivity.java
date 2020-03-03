package com.viger.recyleview.headerFooterUse;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.viger.recyleview.R;
import com.viger.recyleview.adapter.OnViewItemClickListener;
import com.viger.recyleview.commonAdapterUse.CategoryItemDecoration;
import com.viger.recyleview.commonAdapterUse.CategoryListAdapter;
import com.viger.recyleview.commonAdapterUse.ChannelListResult;
import com.viger.recyleview.library.BannerAdapter;
import com.viger.recyleview.library.BannerView;
import com.viger.recyleview.widget.WrapRecyclerView;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Description:  头部和底部添加
 */
public class HeaderFooterActivity extends AppCompatActivity implements OnViewItemClickListener {

    private WrapRecyclerView mRecyclerView;
    private OkHttpClient mOkHttpClient;
    private static Handler mHandler = new Handler();

    List<ChannelListResult.DataBean.CategoriesBean.CategoryListBean> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        mRecyclerView = (WrapRecyclerView) findViewById(R.id.recycler_view);
        // 设置显示分割 ListView样式
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // 添加分割线
        mRecyclerView.addItemDecoration(new CategoryItemDecoration(ContextCompat.getDrawable(this, R.drawable.category_list_divider)));
        mOkHttpClient = new OkHttpClient();
        requestListData();
    }

    /**
     * 请求列表数据
     */
    private void requestListData() {

        // 利用Okhttp去获取网络数据
        Request.Builder builder = new Request.Builder();
        builder.url("http://is.snssdk.com/2/essay/discovery/v3/?iid=6152551759&channel=360&aid=7" +
                "&app_name=joke_essay&version_name=5.7.0&ac=wifi&device_id=30036118478&device_brand=Xiaomi&update_version_code=5701&" +
                "manifest_version_code=570&longitude=113.000366&latitude=28.171377&device_platform=android");

        mOkHttpClient.newCall(builder.build()).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                // Gson 解析成对象
                final ChannelListResult channelList = new Gson().fromJson(result, ChannelListResult.class);
                // 获取列表数据
                final List<ChannelListResult.DataBean.CategoriesBean.CategoryListBean> categoryList =
                        channelList.getData().getCategories().getCategory_list();
                // 该方法不是在主线程中
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        showListData(categoryList);
                        addBannerView(channelList.getData().getRotate_banner().getBanners());
                    }
                });
            }
        });
    }

    /**
     * 添加Banner轮播图
     */
    private void addBannerView(final List<ChannelListResult.DataBean.BannerBean> banners) {
        BannerView bannerView = (BannerView) LayoutInflater.from(this)
                .inflate(R.layout.layout_banner_view, mRecyclerView, false);

        Log.e("TAG", "banners --> " + banners.size());

        bannerView.setAdapter(new BannerAdapter() {
            @Override
            public View getView(int position, View convertView) {
                if (convertView == null) {
                    convertView = new ImageView(HeaderFooterActivity.this);
                }
                ((ImageView) convertView).setScaleType(ImageView.ScaleType.CENTER_CROP);

                Glide.with(HeaderFooterActivity.this).load(banners.get(position).banner_url.url_list.get(0).url).into((ImageView) convertView);
                return convertView;
            }

            @Override
            public int getCount() {
                return banners.size();
            }

            @Override
            public String getBannerDesc(int position) {
                return banners.get(position).banner_url.title;
            }
        });

        mRecyclerView.addHeaderView(bannerView);
    }

    /**
     * 显示列表数据
     *
     * @param categoryList
     */
    private void showListData(List<ChannelListResult.DataBean.CategoriesBean.CategoryListBean> categoryList) {
        mData = categoryList;

        final CategoryListAdapter listAdapter = new CategoryListAdapter(this, categoryList);
        listAdapter.setOnItemClickListener(this);

        // 添加头部和底部 需要 包裹Adapter，才能添加头部和底部
        /*WrapRecyclerAdapter wrapRecyclerAdapter = new WrapRecyclerAdapter(listAdapter);
        mRecyclerView.setAdapter(wrapRecyclerAdapter);

        // 添加头部和底部
        wrapRecyclerAdapter.addHeaderView(LayoutInflater.from(this).inflate(R.layout.layout_header_footer,mRecyclerView,false));
        wrapRecyclerAdapter.addHeaderView(LayoutInflater.from(this).inflate(R.layout.layout_header_footer,mRecyclerView,false));
        wrapRecyclerAdapter.addFooterView(LayoutInflater.from(this).inflate(R.layout.layout_header_footer,mRecyclerView,false));
        */

        mRecyclerView.setAdapter(listAdapter);
        // 只能写在setAdapter之后，否则没效果，也可以选择抛异常
        /*mRecyclerView.addHeaderView(LayoutInflater.from(this).inflate(R.layout.layout_header_footer,mRecyclerView,false));
        mRecyclerView.addHeaderView(LayoutInflater.from(this).inflate(R.layout.layout_header_footer,mRecyclerView,false));
        mRecyclerView.addFooterView(LayoutInflater.from(this).inflate(R.layout.layout_header_footer,mRecyclerView,false));*/
    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(this, "" + mData.get(position).getName(), Toast.LENGTH_SHORT).show();
    }
}
