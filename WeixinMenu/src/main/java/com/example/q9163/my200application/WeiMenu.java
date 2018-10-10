package com.example.q9163.my200application;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;

public class WeiMenu extends AppCompatActivity {

    private PopupWindow popupWindow;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wei_menu);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnMenu();
            }
        });
    }

    private void OnMenu() {
        //获取自定义的菜单布局文件
        final View popuWindow_view = getLayoutInflater().inflate(R.layout.menu, null, false);
        //创建popupWindow实例，设置菜单宽度与高度为包裹自身
        popupWindow = new PopupWindow(popuWindow_view, ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.WRAP_CONTENT, true);
        //设置菜单显示在按钮上面

        popupWindow.showAsDropDown(button,0,0);
        //单击其他地方消失
        popuWindow_view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                //如果菜单存在并且为显示状态，就关闭菜单并初始化
                if(popupWindow !=null && popupWindow.isShowing()){
                    popupWindow.dismiss();
                    popupWindow = null;
                }
                return false;
            }
        });

    }


}
