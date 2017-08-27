package com.example.lzl.popupwindowedittext;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
   PopupWindow popWindow;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv= (TextView) findViewById(R.id.test);

    }

    public void test(View mview){
        //得到弹出菜单的view，login_setting_popup是弹出菜单的布局文件
        View view = getLayoutInflater().inflate(R.layout.login_setting_popup, null);
        TextView tv= (TextView) view.findViewById(R.id.tv_on);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"test", Toast.LENGTH_LONG).show();
            }
        });
        //初始化弹出菜单
        popWindow = new PopupWindow(view, WindowManager.LayoutParams.FILL_PARENT,WindowManager.LayoutParams.WRAP_CONTENT,false);
        //设置可以获取焦点，否则弹出菜单中的EditText是无法获取输入的
        popWindow.setFocusable(true);
        //这句是为了防止弹出菜单获取焦点之后，点击activity的其他组件没有响应
        popWindow.setBackgroundDrawable(new BitmapDrawable());
        //防止虚拟软键盘被弹出菜单遮住
        popWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        //在底部显示
        popWindow.showAtLocation(tv, Gravity.BOTTOM, 0, 0);

    }
}
