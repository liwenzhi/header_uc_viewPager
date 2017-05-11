#UC头部效果结合ViewPager实现

开发中，如果是4个Fragment使用ViewPager能使用正常显示，并且是头部结合GroupButton来显示，但是如果是更多Fragment，头部就不好不好显示了，我们可以模仿UC浏览器，头部的按钮实现可以水平划动就可以了。具体的实现就是一个水平的线性布局来实现。
效果：

![1](http://i.imgur.com/xOlWj4v.gif)

实现上面功能只要三个类（BounceScrollView实现回弹，RotaImageView实现指示器右边的图像旋转，ViewPagerIndicator实现水平可滑动的头部）就可以了，但是也是需要一些资源文件，详情请看源码资源。

#使用：
##布局文件
```
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
              xmlns:tools="http://schemas.android.com/tools"
              xmlns:mmsx="http://schemas.android.com/apk/res/com.example.uc_viewPager"
              android:layout_width="match_parent"
              android:layout_height="match_parent"
              android:background="#ffffffff"
              android:orientation="vertical" >

    <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:background="#8000bf5f" >

        <com.example.uc_viewPager.view.BounceScrollView
                android:id="@+id/id_scrollview"
                android:layout_width="0dp"
                android:layout_height="45dp"
                android:layout_weight="1"
                android:focusableInTouchMode="false"
                android:scrollbars="none" >

            <com.example.uc_viewPager.view.ViewPagerIndicator
                    android:id="@+id/id_indicator"
                    android:layout_width="match_parent"
                    android:layout_height="45dp"
                    android:orientation="horizontal"
                    mmsx:item_count="4" >
            </com.example.uc_viewPager.view.ViewPagerIndicator>
        </com.example.uc_viewPager.view.BounceScrollView>

        <com.example.uc_viewPager.view.RotatImageView
                android:id="@+id/id_rotat_imageView"
                android:layout_width="50dp"
                android:layout_height="wrap_content"
                android:paddingLeft="30dp"
                android:layout_gravity="center"
                mmsx:src="@drawable/add" >
        </com.example.uc_viewPager.view.RotatImageView>
    </LinearLayout>

    <android.support.v4.view.ViewPager
            android:id="@+id/id_vp"
            android:layout_width="match_parent"
            android:layout_height="0dp"
            android:layout_weight="1" >
    </android.support.v4.view.ViewPager>

</LinearLayout>
```
如果使用要记得改掉命名空间和自定义View的包名哦！

##主方法的代码：
```
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



```

##上面每一个碎片的代码都是差不多的，只是设置一个文字，代码：


```
package com.example.uc_viewPager.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 碎片 1
 */
public class MyFragment extends Fragment {


    TextView textView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        textView = new TextView(getActivity());
        textView.setGravity(Gravity.CENTER);
        textView.setBackgroundColor(Color.BLUE);
        textView.setTextSize(40);
        textView.setText("碎片 1");
        return textView;
    }


}


```

没错就那么简单，既不用监听GroupButton的点击事件，也不用监听ViewPager的滑动事件。就能头部和ViewPager的联动，点击头部的头目是可以指向某个Fragment的！


上面的效果，我感觉右边那个旋转的图片还是比较多余的，你可以改下布局文件。
这里我把旋转部分的View设置为Gone就可以了，代码什么都不用变。
```
     <com.example.uc_viewPager.view.RotatImageView
                android:id="@+id/id_rotat_imageView"
                android:visibility="gone"
                android:layout_width="50dp"
                android:layout_height="wrap_content"
                android:paddingLeft="30dp"
                android:layout_gravity="center"
                mmsx:src="@drawable/add" >
        </com.example.uc_viewPager.view.RotatImageView>
```
当然你也可删掉上面的布局代码，并在主方法中删除一些代码。
不用右边小球的效果：
![2](http://i.imgur.com/UmosvW2.gif)

其实头部右边如果换成一个向右的箭头，并且头部监听滑到右边时，或Fragment滑动到最后一个时，箭头消失，这个效果应该还是不错的。具体实现大家有兴趣就搞呗！
上面项目源码地址：
https://github.com/liwenzhi/header_uc_viewPager
共勉：往白纸或是黄纸上多图点东西呗！
