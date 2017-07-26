package com.flyjiang.pullrefreshscrollview.Widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.flyjiang.pullrefreshscrollview.PullRefreshBase.LoadingLayout;
import com.flyjiang.pullrefreshscrollview.PullRefreshBase.PullRefreshBase;
import com.flyjiang.pullrefreshscrollview.Utils.BaseUtils;
import com.flyjiang.pullrefreshscrollview.Utils.PreferenceUtils;


/**
 * Created by ${flyjiang} on 2016/12/8.
 * 文件说明：
 */

public class PullRefreshScrollView extends PullRefreshBase<ScrollView> {
    //implements ScrollView.OnScrollChangeListener
    private ScrollView mScrollView;

    public PullRefreshScrollView(Context context) {
        super(context);
    }

    public PullRefreshScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullRefreshScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    @Override
    protected ScrollView createRefreshableView(Context context, AttributeSet attrs) {
        mScrollView = new ScrollView(context);
        return mScrollView;

    }

    @Override
    protected boolean isReadyForPullDown() {
        return mRefreshableView.getScrollY() == 0;
    }

    @Override
    protected boolean isReadyForPullUp() {
        return  mRefreshableView.getChildAt(0).getHeight() - mRefreshableView.getHeight()
                == mRefreshableView.getScrollY();
    }

   /* @Override
    public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

    }*/

    /**
     * 设置Header样式
     *
     * @param layout
     */
    public void setHeaderLayout(LoadingLayout layout) {
        super.setHeaderLoadingLayout(getContext(), layout);
    }

    /**
     * 设置Footer样式
     *
     * @param layout
     */
    public void setFooterLayout(LoadingLayout layout) {
        super.setFooterLoadingLayout(getContext(), layout);
    }


    /**
     * 设置图标是否可以显示
     *
     * @param value
     */
    public void setIconVisibility(boolean value) {
        if (getHeaderLoadingLayout().getIcon() != null) {
            if (value) {
                getHeaderLoadingLayout().getIcon().setVisibility(VISIBLE);
            } else {
                getHeaderLoadingLayout().getIcon().setVisibility(GONE);
            }
        }
    }

    /**
     * 设置图片
     *
     * @param imageView
     */
    public void setIconImage(int imageView) {
        if (getHeaderLoadingLayout().getIcon() != null) {
            if (imageView != -1) {
                ((ImageView) getHeaderLoadingLayout().getIcon()).setImageResource(imageView);
            }
        }
    }


    /**
     * 设置时间显示
     *
     * @param friendlyTime
     */
    public void setFriendlyTime(boolean friendlyTime) {
        PreferenceUtils.write(getContext(), BaseUtils.Md5(getClass().getName()), getClass().getName(), friendlyTime);
        if (friendlyTime) {
            updateDisplayTime();
        } else {
            updateDisplayTime();
        }
    }


    /**
     * 下拉刷新显示中是否展示时间
     *
     * @param value
     */
    public void setDisplayTime(boolean value) {
        if (getHeaderLoadingLayout().getDisplayTimeLayout() != null) {
            if (value) {
                getHeaderLoadingLayout().getDisplayTimeLayout().setVisibility(VISIBLE);
            } else {
                getHeaderLoadingLayout().getDisplayTimeLayout().setVisibility(GONE);
            }
        }
    }


    @Override
    protected void updateDisplayTime() {
        long time = System.currentTimeMillis();
        if (PreferenceUtils.readBoolean(getContext(), BaseUtils.Md5(getClass().getName()), getClass().getName(), true)) {
            setLastUpdatedLabel(BaseUtils.friendlyTime(time));
        } else {
            setLastUpdatedLabel(BaseUtils.formatDateTime(time));
        }
    }

}
