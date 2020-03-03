package com.viger.recyleview.wannengadapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.viger.recyleview.R;

import java.util.List;

/**
 * 类似的开源框架 ## BaseRecyclerViewAdapterHelper  ##
 */
public class Test {

    public void test(Context context) {
        RecyclerView recyclerView;
        RecyclerViewUniversalAdapter<DataBean> adapter;
        List<DataBean> datas = null;
        adapter = new RecyclerViewUniversalAdapter<DataBean>(context, datas, R.layout.item_home) {
            @Override
            public void convert(UniversalViewHolder viewHolder, DataBean dataBean) {
                viewHolder.setText(R.id.item_text, dataBean.message);
                //viewHolder.getLayoutPosition();
                //Glide.with(context).load("").into((Imageview)viewholder.getView(R.id.iv));
            }
        };
        adapter.setOnItemClickListener(new RecyclerViewUniversalAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup var1, View var2, Object var3, int var4) {

            }

            @Override
            public boolean onItemLongClick(ViewGroup var1, View var2, Object var3, int var4) {
                return false;
            }
        });

    }

    class DataBean {
        private String message;
        private int code;
    }

    class MyAdapter extends RecyclerViewUniversalAdapter<DataBean> {


        public MyAdapter(Context context, List<DataBean> datas, int layoutId) {
            super(context, datas, layoutId);
        }

        @Override
        public void convert(UniversalViewHolder var1, DataBean var2) {

        }
    }

}
