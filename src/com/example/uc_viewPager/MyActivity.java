package com.example.uc_viewPager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Window;
import com.example.uc_viewPager.fragment.*;
import com.example.uc_viewPager.view.BounceScrollView;
import com.example.uc_viewPager.view.RotatImageView;
import com.example.uc_viewPager.view.ViewPagerIndicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * uc的Viewpager效果
 */
public class MyActivity extends FragmentActivity {
    private List<Fragment> mTabContents = new ArrayList<Fragment>();
    private FragmentPagerAdapter mAdapter;
    private ViewPager mViewPager;
    private List<String> mDatas = Arrays.asList("页面1", "页面2", "页面3", "页面4",
            "页面5", "页面6", "页面7");

    private ViewPagerIndicator mIndicator;
    private BounceScrollView mScrollView;
    private RotatImageView mRotatImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        initView();
        initDatas();
        //设置Tab上的标题
        mIndicator.setTabItemTitles(mDatas);
        mViewPager.setAdapter(mAdapter);
        //设置关联的ViewPager
        mIndicator.setViewPager(mViewPager, mScrollView, 0);
        //设置关联的图片旋转，根据需要设置，效果不是很好
        mScrollView.setRotatImageView(mRotatImageView);
    }

    private void initView() {
        mScrollView = (BounceScrollView) findViewById(R.id.id_scrollview);
        mViewPager = (ViewPager) findViewById(R.id.id_vp);
        mIndicator = (ViewPagerIndicator) findViewById(R.id.id_indicator);
        mRotatImageView = (RotatImageView) findViewById(R.id.id_rotat_imageView);

    }

    private void initDatas() {

//        for (String data : mDatas) {
//            VpSimpleFragment fragment = VpSimpleFragment.newInstance(data);
//            mTabContents.add(fragment);
//        }
        mTabContents.add(new MyFragment());
        mTabContents.add(new MyFragment2());
        mTabContents.add(new MyFragment3());
        mTabContents.add(new MyFragment4());
        mTabContents.add(new MyFragment5());
        mTabContents.add(new MyFragment6());
        mTabContents.add(new MyFragment7());


        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return mTabContents.size();
            }

            @Override
            public Fragment getItem(int position) {
                return mTabContents.get(position);
            }
        };
    }


}

