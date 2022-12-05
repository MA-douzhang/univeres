package com.example.mainnavigation;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.hyphenate.chat.EMClient;
import universes_main.MainUniverseActivity;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(EMClient.getInstance().isConnected()){
                    //已登录
                    EMClient.getInstance().groupManager().loadAllGroups();
                    EMClient.getInstance().chatManager().loadAllConversations();
                    startActivity(new Intent(SplashActivity.this, MainUniverseActivity.class));
                    finish();
                }else {
                    //未登录
                    startActivity(new Intent(SplashActivity.this,MainLoginActivity.class));
                    finish();
                }
            }
        },2000);
    }
}
