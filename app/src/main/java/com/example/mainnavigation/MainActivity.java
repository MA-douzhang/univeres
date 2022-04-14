package com.example.mainnavigation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout home, universe, chat, my;//部件id
    private ImageView homeImage, universeImage, chatImage, myImage, currentImage;//图片
    ViewPager2 viewPager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation);
        //viewpaper2 的实例化
        initPager();
        initTabView();

    }
    //页面更多的跳转 实现
    public void otherClickActivity(View v){
        TextView button= findViewById(R.id.activity);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent();
                i.setClass(MainActivity.this,patherPlaceActivity.class);
                startActivity(i);
            }
        });
    }
    public void otherClickHot(View v){
        TextView button= findViewById(R.id.hot);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent();
                i.setClass(MainActivity.this,patherPlaceActivity.class);
                startActivity(i);
            }
        });
    }
    public void otherClickGather(View v){
        TextView button= findViewById(R.id.gatherPlace);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent();
                i.setClass(MainActivity.this,patherPlaceActivity.class);
                startActivity(i);
            }
        });
    }

    private void initPager() {
        //三大板块滑动
        viewPager = findViewById(R.id.viewPager);
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(BlankFragment.newInstance("主页"));
        fragments.add(BlankFragment.newInstance("宇宙世界"));
        fragments.add(BlankFragment.newInstance("聊天"));
        fragments.add(BlankFragment.newInstance("我的"));
        MyFragmentPagerAdapter pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), getLifecycle(), fragments);
        viewPager.setAdapter(pagerAdapter);
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                changeTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });

    }

    //    滑动
    private void changeTab(int position) {
        currentImage.setSelected(false);
        switch (position) {
            case R.id.idTopDemo1:
                viewPager.setCurrentItem(0);
            case 0:
                homeImage.setSelected(true);
                currentImage = homeImage;
                break;
            case R.id.idTopDemo2:
                viewPager.setCurrentItem(1);
            case 1:
                universeImage.setSelected(true);
                currentImage = universeImage;
                break;
            case R.id.idTopDemo3:
                viewPager.setCurrentItem(2);
            case 2:
                chatImage.setSelected(true);
                currentImage = chatImage;
                break;
            case R.id.idMy:
                viewPager.setCurrentItem(3);
            case 3:
                myImage.setSelected(true);
                currentImage = myImage;
                break;
        }
    }

    //点击反应
    @SuppressLint("WrongViewCast")
    private void initTabView() {
        //部件id
        home = findViewById(R.id.idTopDemo1);
        home.setOnClickListener(this);
        universe = findViewById(R.id.idTopDemo2);
        universe.setOnClickListener(this);
        chat = findViewById(R.id.idTopDemo3);
        chat.setOnClickListener(this);
        my = findViewById(R.id.idMy);
        my.setOnClickListener(this);
        //部件图片
        homeImage = findViewById(R.id.idTopDemo1Image);
        universeImage = findViewById(R.id.idTopDemo2Image);
        chatImage = findViewById(R.id.idTopDemo3Image);
        myImage = findViewById(R.id.myImage);
        home.setSelected(true);
        currentImage = homeImage;
    }

    public void onClick(View view) {
        changeTab(view.getId());
    }
}
