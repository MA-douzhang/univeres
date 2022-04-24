package com.example.mainnavigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import universes_main.MainUniverseActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    @Override
    //初始化页面为登录页面 login_user.xml
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_user);
    }
    @Override
    //点击跳转到主导航页面 主宇宙
    public void onClick(View view) {
        Intent i=new Intent();
        i.setClass(MainActivity.this, MainUniverseActivity.class);
        startActivity(i);
    }
}
