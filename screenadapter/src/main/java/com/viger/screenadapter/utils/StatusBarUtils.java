package com.viger.screenadapter.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

/**
 * 沉浸式 工具类
 *
 *     @Override 使用方法
 *     protected void onCreate(Bundle savedInstanceState) {
 *         super.onCreate(savedInstanceState);
 *         setContentView(R.layout.activity_main);
 *         StatusBarUtils.drawableStatusBar(this, R.color.colorPrimary);
 *     }
 */
public class StatusBarUtils {

    /**
     * 绘制状态栏
     * @param act
     * @param color
     */
    public static void drawableStatusBar(Activity act, int color) {
        transparentStatusBar(act, true);
        addStatusBar(act, color);
    }

    private static void addStatusBar(Activity act, int color) {
        int resource = act.getResources().getColor(color);
        View statusBar = createStatusBar(act, resource);
        //root是个LinearLayout，里面包裹了我们设置的布局
        ViewGroup root  = act.findViewById(android.R.id.content);
        if(null != root ) {
            //将生成的statusBar追加到布局的头部
            root.addView(statusBar,0);
        }
    }

    public static void transparentStatusBar(Activity act, boolean fitWindow) {
        Window window = act.getWindow();
        //透明状态栏
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //获取根布局
        ViewGroup viewGroup = (ViewGroup)act.findViewById(android.R.id.content);
        View firstChild = viewGroup.getChildAt(0);
        if(null != firstChild) {
            //设置root的第一个子View，使其为系统View预留空间.
            firstChild.setFitsSystemWindows(fitWindow);
        }
    }

    //方法原理:
    //添加一个和状态栏高、宽相同的指定颜色的View来覆盖被透明化的状态栏
    private static View createStatusBar(Context context, int color) {
        int statusBarHeight = getStatusBarHeight(context);
        Log.d("tag","height = " + statusBarHeight);
        View statusBar = new View(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                statusBarHeight);
        statusBar.setLayoutParams(params);
        statusBar.setBackgroundColor(color);
        return statusBar;
    }

    private static int getStatusBarHeight(Context context) {
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("status_bar_height", "dimen", "android");
        return resources.getDimensionPixelSize(identifier);
    }

    /**
    public static int getStatusBarHeight(Context context) {
        int statusBarHeight = -1;
        try {
            Class clazz = Class.forName("com.android.internal.R$dimen");
            //通过反射获取状态栏高度这个成员变量
            Field status_bar_height = clazz.getField("status_bar_height");
            Object obj = clazz.newInstance();
            int height = Integer.parseInt(status_bar_height.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusBarHeight;
    }*/

}
