package com.jorchi.selfdefineview;

import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String[] mTitles = {"未支付账单", "支付中账单", "历史账单"};
    private int[] pics = new int[]{R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
    MyFragmentPagerAdapter mAdapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private View view1;
    private View view2;
    private View view3;
    private List<View> views = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = findViewById(R.id.tab_layout);
//        for(int i=0;i<mTitles.length;i++){
//            tabLayout.addTab(tabLayout.newTab());
//        }
//
//        for(int i=0;i<mTitles.length;i++){
//            tabLayout.getTabAt(i).setText(mTitles[i]).setIcon(pics[i]);
//        }


        viewPager = findViewById(R.id.view_pager);
        setupWithViewPager();


    }

    private void addTabToTabLayout() {
//        TabLayoutUtil.addNormalTabWithIcons(tabLayout, mTitles, pics);
        TabLayoutUtil.setCustomIconView(tabLayout, mTitles, pics);
    }


    private void setupWithViewPager() {
        addTabToTabLayout();
        List<Fragment> mFragments = new ArrayList<>();
        mFragments.add(new FristFragment());
        mFragments.add(new FristFragment());
        mFragments.add(new FristFragment());

        mAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        mAdapter.addTitlesAndFragments(mTitles, mFragments);

        viewPager.setAdapter(mAdapter); // 给ViewPager设置适配器
        tabLayout.setupWithViewPager(viewPager, false); //关联TabLayout和ViewPager
        tabLayout.removeAllTabs();
        addTabToTabLayout();

    }
}
