package com.doublecc.yiyuannews.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.doublecc.yiyuannews.R;
import com.doublecc.yiyuannews.bean.BeanNews;

/**
 * Created by Administrator on 2016/11/11 0011.
 */

public class NewsDetailActivity extends Activity {
    public static final String NEWS_DETAIL="news_detail";
    private TextView tvTitle,tvTime,tvFrom,tvContent;
    private BeanNews news;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailactivitylayout);

        news = (BeanNews) getIntent().getSerializableExtra(NEWS_DETAIL);
        initView();
        setData();
    }

    private void initView() {
        tvTime = (TextView) findViewById(R.id.tv_time);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvFrom = (TextView) findViewById(R.id.tv_from);
        tvContent = (TextView) findViewById(R.id.tv_content);
    }

    private void setData() {
        tvTitle.setText(news.title);
        tvTime.setText(news.pubDate);
        tvFrom.setText(news.source);
        tvContent.setText(news.content);
    }
}
