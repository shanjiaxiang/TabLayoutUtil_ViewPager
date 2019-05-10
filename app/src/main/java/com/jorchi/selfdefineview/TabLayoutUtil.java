package com.jorchi.selfdefineview;

import android.content.res.Resources;
import android.support.design.widget.TabLayout;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Field;

/**
 * 用于设置TabLayout指示线长度
 *
 * private String [] mTitles = {"未支付账单", "支付中账单", "历史账单"};
 * private int[] pics = new int[]{R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher};
*/

public class TabLayoutUtil {

    public static void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }
    }

    // 设置普通指示视图
    public static void addNormalTabWithIcons(TabLayout tabLayout, String[] titles, int[] icons){
        int size = titles.length > icons.length? icons.length: titles.length;
        for (int i = 0; i < size; i++) {
            tabLayout.addTab(tabLayout.newTab());
            tabLayout.getTabAt(i).setText(titles[i]).setIcon(icons[i]);
        }
    }

    public static void addNormalTab(TabLayout tabLayout, String[] titles){
        int size = titles.length;
        for (int i = 0; i < size; i++) {
            tabLayout.addTab(tabLayout.newTab());
            tabLayout.getTabAt(i).setText(titles[i]);
        }
    }

    //设置指示条长度 注意TabLayout版本，不通用
    public static void setIndicatorLength(final TabLayout tabLayout, final int leftDip, final int rightDip){
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                setIndicator(tabLayout, leftDip, rightDip);
            }
        });
    }

    // 获得自定义tab视图
    private static View getTabItemView(TabLayout tabLayout, int position, String[] titles, int[] pics){
        View tabView = LayoutInflater.from(tabLayout.getContext()).inflate(R.layout.tab_text_icon,null);
        TextView textView = tabView.findViewById(R.id.textview);
        ImageView imageView = tabView.findViewById(R.id.imageview);
        textView.setText(titles[position]);
        imageView.setImageResource(pics[position]);
        return tabView;
    }


    // 设置自定义tab视图
    public static void setCustomIconView(TabLayout tabLayout, String[] titles, int[] pics) {
        int size = titles.length;
        for(int i=0;i<size;i++){
            tabLayout.addTab(tabLayout.newTab());
            tabLayout.getTabAt(i).setCustomView(getTabItemView(tabLayout, i, titles, pics));
        }
    }

}
