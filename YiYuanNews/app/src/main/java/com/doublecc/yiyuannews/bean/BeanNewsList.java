package com.doublecc.yiyuannews.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 新闻列表
 */

public class BeanNewsList implements Serializable {
    public long allNum;
    public long allPages;
    public ArrayList<BeanNews> contentlist;
    public long currentPage;
    public long maxResult;
}
