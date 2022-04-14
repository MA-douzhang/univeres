package com.example.mainnavigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainPlaceActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //星球聚集地的 更多（跳转）
        TextView gatherPlace=findViewById(R.id.gatherPlace);
        gatherPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent();
                i.setClass(MainPlaceActivity.this,patherPlaceActivity.class);
                startActivity(i);
            }
        });
        //星球活动的 更多（跳转）
        TextView activity=findViewById(R.id.activity);
        activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent();
                i.setClass(MainPlaceActivity.this,patherPlaceActivity.class);
                startActivity(i);
            }
        });
        //热门星球的 更多（跳转）
        TextView hot=findViewById(R.id.hot);
        hot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent();
                i.setClass(MainPlaceActivity.this,patherPlaceActivity.class);
                startActivity(i);
            }
        });
        //viewpaper2 的实例化
//        initPager();
//        initTabView();
    }
}
