package com.viger.myappframe.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * 根据imageview要求的尺寸压缩图片大小
 */
public class ImageResize {

    public static Bitmap resizeBitmap(Context context, int imageResourceId, int requestWidth, int requestHeight, boolean hasAlpha) {

        Resources resources = context.getResources();
        BitmapFactory.Options options = new BitmapFactory.Options();
        //需要拿得到系统处理的信息  比如解码出宽高,....
        options.inJustDecodeBounds = true;
        //我们把原来的解码参数改了再去生成bitmap
        BitmapFactory.decodeResource(resources, imageResourceId, options);
        //取到宽高
        int w = options.outWidth;
        int h = options.outHeight;
        //设置缩放系数
        options.inSampleSize = calcuteInSampleSize(w, h, requestWidth, requestHeight);

        if (!hasAlpha) {
            options.inPreferredConfig = Bitmap.Config.RGB_565;
        }

        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(resources, imageResourceId, options);

    }

    //返回结果是原来解码的图片的大小  是我们需要的大小的   最接近2的几次方倍
    private static int calcuteInSampleSize(int w, int h, int requestWidth, int requestHeight) {

        int inSampleSize = 1;
        if (w > requestWidth && h > requestHeight) {
            inSampleSize = 2;
            while (w / inSampleSize > requestWidth && h / inSampleSize > requestHeight) {
                inSampleSize *= 2;
            }
        }
        inSampleSize /= 2;
        return inSampleSize;
    }

}
