package com.doublecc.yiyuannews.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.doublecc.yiyuannews.R;
import com.doublecc.yiyuannews.activity.NewsDetailActivity;
import com.doublecc.yiyuannews.bean.BeanNews;

import java.util.List;

/**
 * Created by Administrator on 2016/11/11 0011.
 */

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.MyViewHolder> {

    private Context context;
    private List<BeanNews> newsList;
    private LayoutInflater inflater;

    public NewsListAdapter(Context context,List<BeanNews> newsList) {
        this.context = context;
        this.newsList = newsList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.newsitemlayout,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.tvTitle.setText(newsList.get(position).title);
        holder.tvDesc.setText(newsList.get(position).desc);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NewsDetailActivity.class);
                intent.putExtra(NewsDetailActivity.NEWS_DETAIL,newsList.get(position));
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return newsList==null?0:newsList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView tvTitle;
        private TextView tvDesc;
        public MyViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvDesc = (TextView) itemView.findViewById(R.id.tv_desc);
        }
    }
}
