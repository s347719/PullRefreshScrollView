package com.pullrefreshscrollview.flyjiang.pullrefreshscrollview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ScrollView;
import com.flyjiang.pullrefreshscrollview.Widget.PullRefreshScrollView;

public class MainActivity extends AppCompatActivity {
    PullRefreshScrollView pullRefreshScrollView;
    View insideView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pullRefreshScrollView = (PullRefreshScrollView) findViewById(R.id.pullRefreshScrollView);
         ScrollView scrollView = pullRefreshScrollView.getRefreshableView();
         insideView = LayoutInflater.from(MainActivity.this).inflate(R.layout.layout_item,null);
         scrollView.addView(insideView);
         pullRefreshScrollView.setPullRefreshEnabled(true);//设置下拉刷新是否可用，默认true
         pullRefreshScrollView.setPullLoadEnabled(true);//设置上拉加载是否可用，默认flase
         pullRefreshScrollView.setScrollLoadEnabled(true);//设置到底部自动加载，默认false
    }
}
