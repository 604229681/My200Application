package com.example.qqmenu;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

public class QQMenu extends HorizontalScrollView {
    private int mScreenWidth;
    private int mMenuRightPadding;
    private int mMenuWidth;
    boolean call = false;
    private LinearLayout mScrollView;
    private ViewGroup mMenu, mContent;


    public QQMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        //获取窗口管理器服务
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        //创建显示尺寸对象
        DisplayMetrics outMetrics = new DisplayMetrics();
        //获取当前屏幕的宽高
        wm.getDefaultDisplay().getMetrics(outMetrics);
        //为屏幕宽高赋值
        mScreenWidth = outMetrics.widthPixels;
        //将50dp边距转为像素（px）
        mMenuRightPadding = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 50,
                context.getResources().getDisplayMetrics());
    }

    //设置滚动视图与子视图的宽高
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if(!call){                                               //用于设置一次尺寸
            mScrollView = (LinearLayout) getChildAt(0);     //获取滚动视图中的子视图
            mMenu = (ViewGroup) mScrollView.getChildAt(0);  //获取菜单区域
            mContent = (ViewGroup) mScrollView.getChildAt(1);//获取主显示区域
            //设置菜单宽度
            mMenuWidth = mMenu.getLayoutParams().width = mScreenWidth - mMenuRightPadding;
            //设置主主显示区高度
            mContent.getLayoutParams().width = mScreenWidth;
            call = true;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if(changed){
            this.scrollTo(mMenuWidth, 0);       //滚动条向右移动，主显示区向左移动
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action){
            case MotionEvent.ACTION_UP:
                int scrollX = getScrollX();
                if (scrollX>=mMenuWidth/2){
                    this.scrollTo(mMenuWidth, 0);
                }else{
                    this.scrollTo(0,0);
                }
                return true;
        }
        return super.onTouchEvent(ev);
    }
}
