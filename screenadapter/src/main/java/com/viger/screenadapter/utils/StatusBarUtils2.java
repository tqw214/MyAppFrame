package com.viger.screenadapter.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import androidx.annotation.ColorInt;

/**
 * 其他沉浸式状态栏实现方案，未验证
 * 仅供代码参考
 */
public class StatusBarUtils2 {

    /**
     * 自定义颜色的状态栏和导航栏
     * 使用：oncreate()方法中调用
     * UltimateBar ultimateBar = new UltimateBar(this);
     * ultimateBar.setColorBar(ContextCompat.getColor(this, R.color.DeepSkyBlue));
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void setColorBar(@ColorInt int color, int alpha, Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            int alphaColor = alpha == 0 ? color : calculateColor(color, alpha);
            window.setStatusBarColor(alphaColor);
            window.setNavigationBarColor(alphaColor);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            int alphaColor = alpha == 0 ? color : calculateColor(color, alpha);
            ViewGroup decorView = (ViewGroup) window.getDecorView();
            decorView.addView(createStatusBarView(activity, alphaColor));
            if (navigationBarExist(activity)) {
                decorView.addView(createNavigationBarView(activity, alphaColor));
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            }
            setRootView(activity, true);
        }
    }

    private int calculateColor(int color, int alpha) {
        return 0;
    }

    private View createNavigationBarView(Context context, int color) {
        View navBarView = new View(context);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                getNavigationBarHeight(context));
        params.gravity = Gravity.BOTTOM;
        navBarView.setLayoutParams(params);
        navBarView.setBackgroundColor(color);
        return navBarView;
    }

    private View createStatusBarView(Context context, int color) {
        View statusBarView = new View(context);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                getStatusBarHeight(context));
        params.gravity = Gravity.TOP;
        statusBarView.setLayoutParams(params);
        statusBarView.setBackgroundColor(color);
        return statusBarView;
    }

    private int getNavigationBarHeight(Context context) {
        return getBarHeight(context, "navigation_bar_height");
    }

    private int getStatusBarHeight(Context context) {
        return getBarHeight(context, "status_bar_height");
    }

    private int getBarHeight(Context context,String name) {
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier(name, "dimen", "android");
        return resources.getDimensionPixelSize(identifier);
    }

    private void setRootView(Activity activity, boolean fit) {
        ViewGroup parent = (ViewGroup) activity.findViewById(android.R.id.content);
        for (int i = 0, count = parent.getChildCount(); i < count; i++) {
            View childView = parent.getChildAt(i);
            if (childView instanceof ViewGroup) {
                childView.setFitsSystemWindows(fit);
                ((ViewGroup)childView).setClipToPadding(fit);
            }
        }
    }

//    @TargetApi(Build.VERSION_CODES.KITKAT)
//    public void setColorBar(@ColorInt int color) {
//        setColorBar(color, 0);
//    }

    // 导航栏是否存在
    private boolean navigationBarExist(Activity activity) {

        WindowManager windowManager = activity.getWindowManager();
        Display d = windowManager.getDefaultDisplay();
        DisplayMetrics realDisplayMetrics = new DisplayMetrics();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) { //17,android 4.2
            d.getRealMetrics(realDisplayMetrics);
        }

        int realWidth = realDisplayMetrics.widthPixels;
        int realHeight = realDisplayMetrics.heightPixels;

        DisplayMetrics displayMetrics = new DisplayMetrics();
        d.getMetrics(displayMetrics);

        int displayWidth = displayMetrics.widthPixels;
        int displayHeight = displayMetrics.heightPixels;

        return realWidth - displayWidth > 0 || realHeight - displayHeight > 0;
    }

    //半透明的状态栏和导航栏
//    UltimateBar ultimateBar = new UltimateBar(this);
//ultimateBar.setTransparentBar(Color.BLUE, 50);



//    @TargetApi(Build.VERSION_CODES.KITKAT)
//    public void setTransparentBar(@ColorInt int color, int alpha) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Window window = activity.getWindow();
//            View decorView = window.getDecorView();
//            int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
//            decorView.setSystemUiVisibility(option);
//
//            int finalColor = alpha == 0 ? Color.TRANSPARENT :
//                    Color.argb(alpha, Color.red(color), Color.green(color), Color.blue(color));
//
//            window.setNavigationBarColor(finalColor);
//            window.setStatusBarColor(finalColor);
//
//        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
//            Window window = activity.getWindow();
//            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            ViewGroup decorView = (ViewGroup) window.getDecorView();
//            int finalColor = alpha == 0 ? Color.TRANSPARENT :
//                    Color.argb(alpha, Color.red(color), Color.green(color), Color.blue(color));
//            decorView.addView(createStatusBarView(activity, finalColor));
//            if (navigationBarExist(activity)) {
//                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//                decorView.addView(createNavBarView(activity, finalColor));
//            }
//        }
//
//    }

    //完全透明的状态栏和导航栏
//    @TargetApi(Build.VERSION_CODES.KITKAT)
//    public void setImmersionBar() {
//        setTransparentBar(Color.TRANSPARENT, 0);
//    }

    //隐藏状态栏和导航栏
    //这种情况比较常见了，一般玩游戏，看视频就是这种效果，这种效果的实现有点特殊，
    // 必须重写 Activity 的 onWindowFocusChanged 方法，如下：
//    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        super.onWindowFocusChanged(hasFocus);
//        if (hasFocus) {
//            UltimateBar ultimateBar = new UltimateBar(this);
//            ultimateBar.setHintBar();
//        }
//    }
//
//    @TargetApi(Build.VERSION_CODES.KITKAT)
//    public void setHintBar() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            View decorView = activity.getWindow().getDecorView();
//            decorView.setSystemUiVisibility(
//                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                            | View.SYSTEM_UI_FLAG_FULLSCREEN
//                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
//        }
//    }


    //针对 DrawerLayout 的实现
//    UltimateBar ultimateBar = new UltimateBar(this);
//ultimateBar.setColorBarForDrawer(ContextCompat.getColor(this, R.color.DeepSkyBlue));
   // 要在布局文件中在 DawerLayout 的子 view 的主界面添加 android:fitsSystemWindows="true"

//    <android.support.v4.widget.DrawerLayout
//    xmlns:android="http://schemas.android.com/apk/res/android"
//    android:id="@+id/drawer_layout"
//    android:layout_width="match_parent"
//    android:layout_height="match_parent">
//
//    <LinearLayout
//    android:layout_width="match_parent"
//    android:layout_height="match_parent"
//    android:fitsSystemWindows="true"
//    android:orientation="vertical">
//    </LinearLayout>
//
//    <FrameLayout
//    android:layout_width="match_parent"
//    android:layout_height="match_parent"
//    android:background="@color/SpringGreen"
//    android:layout_gravity="left"/>
//
//</android.support.v4.widget.DrawerLayout>

//    UltimateBar ultimateBar = new UltimateBar(this);
//ultimateBar.setColorBarForDrawer(ContextCompat.getColor(this, R.color.DeepSkyBlue));

//    @TargetApi(Build.VERSION_CODES.KITKAT)
//    public void setColorBarForDrawer(@ColorInt int color, int alpha) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Window window = activity.getWindow();
//            ViewGroup decorView = (ViewGroup) window.getDecorView();
//            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
//            if (navigationBarExist(activity)) {
//                option = option | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
//            }
//            decorView.setSystemUiVisibility(option);
//            window.setNavigationBarColor(Color.TRANSPARENT);
//            window.setStatusBarColor(Color.TRANSPARENT);
//            int alphaColor = alpha == 0 ? color : calculateColor(color, alpha);
//            decorView.addView(createStatusBarView(activity, alphaColor), 0);
//            if (navigationBarExist(activity)) {
//                decorView.addView(createNavBarView(activity, alphaColor), 1);
//            }
//        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            Window window = activity.getWindow();
//            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            ViewGroup decorView = (ViewGroup) window.getDecorView();
//            int alphaColor = alpha == 0 ? color : calculateColor(color, alpha);
//            decorView.addView(createStatusBarView(activity, alphaColor), 0);
//            if (navigationBarExist(activity)) {
//                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//                decorView.addView(createNavBarView(activity, alphaColor), 1);
//            }
//        }
//    }
//
//
//
//    @TargetApi(Build.VERSION_CODES.KITKAT)
//    public void setColorBarForDrawer(@ColorInt int color) {
//        setColorBarForDrawer(color, 0);
//    }
//对于 Android 5.0 以上的情况，首先添加前面两个 flag 保证布局内容能够覆盖到状态栏上面，然后判断是否存在导航栏，
// 如果存在，再添加第三个 flag 保证布局内容可以覆盖到导航栏上面，然后状态栏和导航栏都设为透明色，
// 此时相当于上面的完全透明的状态栏和导航栏，最后再分别在状态栏和导航栏上面添加一个 view 保证状态栏和导航栏有颜色，
// 这样就既保证了 DrawerLayout 可以覆盖到状态栏和导航栏上面，又保证了 DrawerLayout 下面的主布局内容不会覆盖到状态栏和导航栏上面，
// 最后的效果就是抽屉的内容是覆盖到状态栏和导航栏上面的，而住布局的内容不会覆盖到状态栏和导航栏的上面，然后对于 Android 4.4，
// 其实和前面正常情况的设置自定义颜色的状态栏和导航栏是一样的，只是这里没有调用 setRootView 方法，
// 而是在 DrawerLayout 下面的主布局中添加了 android:fitsSystemWindows="true"，同样实现了抽屉的内容可以覆盖到状态栏和导航栏上面，
// 而主布局的内容不会覆盖到状态栏和导航栏上面
//
}
