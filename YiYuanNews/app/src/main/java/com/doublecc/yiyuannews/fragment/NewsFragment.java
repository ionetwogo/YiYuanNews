package com.doublecc.yiyuannews.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.baidu.apistore.sdk.ApiCallBack;
import com.baidu.apistore.sdk.ApiStoreSDK;
import com.baidu.apistore.sdk.network.Parameters;
import com.doublecc.yiyuannews.R;
import com.doublecc.yiyuannews.adapter.NewsListAdapter;
import com.doublecc.yiyuannews.bean.BaseBeanSort;
import com.doublecc.yiyuannews.bean.BeanChannel;

/**
 * 新闻片段
 */

public class NewsFragment extends Fragment {

    private static final String ARG_CHANNEL = "argument_channel";
    private BeanChannel channel;
    private BaseBeanSort baseSort;
    private NewsListAdapter newsListAdapter;
    private View view;
    private TextView textView;
    private RecyclerView recyclerView;

    public static NewsFragment getNewsFragment(BeanChannel channel){
        NewsFragment fragment = new NewsFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG_CHANNEL,channel);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        channel = (BeanChannel) getArguments().getSerializable(ARG_CHANNEL);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.newsfragmentlayout,container,false);
        textView = (TextView) view.findViewById(R.id.tv_news);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        getNewsList(channel.channelId);
        textView.setText(channel.name);
        return view;
    }

    private void getNewsList(String channelId) {
        Parameters param = new Parameters();
        param.put("channelId",channelId);
        param.put("needContent","1");
        ApiStoreSDK.execute("http://apis.baidu.com/showapi_open_bus/channel_news/search_news",ApiStoreSDK.GET,param,new ApiCallBack(){
            @Override
            public void onSuccess(int i, String s) {
                super.onSuccess(i, s);
                baseSort = JSON.parseObject(s,BaseBeanSort.class);
                newsListAdapter = new NewsListAdapter(getContext(),baseSort.showapi_res_body.pagebean.contentlist);
                recyclerView.setAdapter(newsListAdapter);
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


}
