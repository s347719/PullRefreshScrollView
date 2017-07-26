package com.pullrefreshscrollview.flyjiang.pullrefreshscrollview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.flyjiang.pullrefreshscrollview.PullRefreshBase.ILoadingLayout;
import com.flyjiang.pullrefreshscrollview.PullRefreshBase.LoadingLayout;
import com.flyjiang.pullrefreshscrollview.PullRefreshBase.PullRefreshBase;
import com.flyjiang.pullrefreshscrollview.Widget.PullRefreshScrollView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    PullRefreshScrollView pullRefreshScrollView;
    View view;
    ListView listView;
    private List<Map<String,Object>> mData;
    SimpleAdapter adapter;
    private android.os.Handler handler = new android.os.Handler();
    private LoadingLayout footer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pullRefreshScrollView = (PullRefreshScrollView) findViewById(R.id.pullRefreshScrollView);

        footer =pullRefreshScrollView.getFooterLoadingLayout();

        footer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (footer.getState().equals(ILoadingLayout.State.NetWorkError)){
                    Toast.makeText(MainActivity.this,"刷新",Toast.LENGTH_SHORT).show();
                }

            }
        });

        ScrollView scrollView = pullRefreshScrollView.getRefreshableView();
        view = LayoutInflater.from(MainActivity.this).inflate(R.layout.layout,null);
        listView = (ListView) view.findViewById(R.id.listview);
        mData = new ArrayList<Map<String,Object>>();;
        for(int i =0; i < 10; i++) {
            Map<String,Object> item = new HashMap<String,Object>();
            item.put("image", R.drawable.ic_launcher);
            item.put("text_title", "Test Title");
            item.put("text_msg", "msg");
            mData.add(item);
        }
        adapter = new SimpleAdapter(this,
                (List<Map<String, Object>>) mData, R.layout.layout_item,
                new String[] { "image", "text_title", "text_msg"}, new int[] {
                R.id.image, R.id.text_title, R.id.text_msg});

        listView.setAdapter(adapter);


        scrollView.addView(view);
         pullRefreshScrollView.setPullRefreshEnabled(true);//设置下拉刷新是否可用，默认true
         pullRefreshScrollView.setPullLoadEnabled(true);//设置上拉加载是否可用，默认flase
         pullRefreshScrollView.setScrollLoadEnabled(true);//设置到底部自动加载，默认false
        pullRefreshScrollView.setOnRefreshListener(new PullRefreshBase.OnRefreshListener<ScrollView>() {
            @Override
            public void onPullDownRefresh(PullRefreshBase<ScrollView> refreshView) {

            }

            @Override
            public void onPullUpToRefresh(PullRefreshBase<ScrollView> refreshView) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pullRefreshScrollView.onPullUpRefreshNetError();
                    }
                },2500);

            }
        });
    }
}
