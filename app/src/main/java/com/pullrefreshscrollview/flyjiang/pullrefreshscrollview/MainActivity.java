package com.pullrefreshscrollview.flyjiang.pullrefreshscrollview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ScrollView;

import com.flyjiang.pullrefreshscrollview.Widget.PullRefreshScrollView;

public class MainActivity extends AppCompatActivity {
    PullRefreshScrollView pullRefreshScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pullRefreshScrollView = (PullRefreshScrollView) findViewById(R.id.pullRefreshScrollView);
        ScrollView scrollView = pullRefreshScrollView.getRefreshableView();

    }
}
