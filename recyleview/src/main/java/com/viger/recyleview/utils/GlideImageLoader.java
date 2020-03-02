package com.viger.recyleview.utils;

import android.content.Context;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.viger.recyleview.R;
import com.viger.recyleview.adapter.ViewHolder;

public class GlideImageLoader extends ViewHolder.HolderImageLoader {

    public GlideImageLoader(String imagePath) {
        super(imagePath);
    }

    @Override
    public void displayImage(Context context, ImageView imageView, String imagePath) {
        Glide.with(context).load(imagePath).placeholder(R.drawable.ic_discovery_default_channel).centerCrop().into(imageView);
    }
}
