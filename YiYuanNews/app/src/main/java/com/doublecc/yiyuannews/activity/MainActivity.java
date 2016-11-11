package com.doublecc.yiyuannews.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.astuetz.PagerSlidingTabStrip;
import com.baidu.apistore.sdk.ApiCallBack;
import com.baidu.apistore.sdk.ApiStoreSDK;
import com.baidu.apistore.sdk.network.Parameters;
import com.doublecc.yiyuannews.R;
import com.doublecc.yiyuannews.adapter.MainViewPagerAdapter;
import com.doublecc.yiyuannews.bean.BaseBeanChannel;
import com.doublecc.yiyuannews.bean.BeanChannel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private PagerSlidingTabStrip mPagerSlidingTabStrip;
    private ViewPager mMainViewpager;
    private MainViewPagerAdapter mMainViewPagerAdapter;

    private BaseBeanChannel channelListbase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        getData();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void getData() {
        Parameters param = new Parameters();
        ApiStoreSDK.execute("http://apis.baidu.com/showapi_open_bus/channel_news/channel_news",ApiStoreSDK.GET,param,new ApiCallBack(){
            @Override
            public void onSuccess(int i, String s) {
                super.onSuccess(i, s);
                channelListbase = JSON.parseObject(s, BaseBeanChannel.class);
                mMainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager(),channelListbase.showapi_res_body.channelList);
                mMainViewpager.setAdapter(mMainViewPagerAdapter);
                mPagerSlidingTabStrip.setViewPager(mMainViewpager);
                mPagerSlidingTabStrip.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {

                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });
                initTabsValue();
            }

            @Override
            public void onComplete() {
                super.onComplete();
            }

            @Override
            public void onError(int i, String s, Exception e) {
                super.onError(i, s, e);
            }
        });
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("易新闻"); //设置主标题
        toolbar.setTitleTextColor(ContextCompat.getColor(this,R.color.colorAccent));//设置主标题的颜色新的调用方法
        setSupportActionBar(toolbar);
        mPagerSlidingTabStrip = (PagerSlidingTabStrip) findViewById(R.id.tabStrip);
        mMainViewpager = (ViewPager) findViewById(R.id.viewpager);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        mDrawerToggle.syncState();
        drawerLayout.setDrawerListener(mDrawerToggle);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * mPagerSlidingTabStrip默认值配置
     *
     */
    private void initTabsValue() {
        // 底部游标颜色
        mPagerSlidingTabStrip.setIndicatorColor(Color.BLUE);
        // tab的分割线颜色
        mPagerSlidingTabStrip.setDividerColor(Color.TRANSPARENT);
        // tab背景
        mPagerSlidingTabStrip.setBackgroundColor(ContextCompat.getColor(this,R.color.tabStrip_bg));
        // tab底线高度
        mPagerSlidingTabStrip.setUnderlineHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                1, getResources().getDisplayMetrics()));
        // 游标高度
        mPagerSlidingTabStrip.setIndicatorHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                5, getResources().getDisplayMetrics()));

        // 正常文字颜色
        mPagerSlidingTabStrip.setTextColor(Color.WHITE);
    }
}
