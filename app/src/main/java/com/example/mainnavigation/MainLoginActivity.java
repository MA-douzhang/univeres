package com.example.mainnavigation;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import universes_main.MainUniverseActivity;


public class MainLoginActivity extends AppCompatActivity{
    //初始化页面为登录页面 login_user.xml
    EditText phoneView;
    EditText passwordView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (EMClient.getInstance().isLoggedIn()){
            Log.e("TAG",EMClient.getInstance().getCurrentUser());
            startActivity(new Intent(MainLoginActivity.this,MainUniverseActivity.class));
        }
        setContentView(R.layout.login_user);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//竖屏
        phoneView=findViewById(R.id.id_phone);
        passwordView=findViewById(R.id.id_password);
        findViewById(R.id.id_registered_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signup();
            }
        });
        findViewById(R.id.id_Login_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sigin();
            }
        });
    }

    /**
     * 注册
     */
    private void signup(){
        String username=phoneView.getText().toString().trim();
        String pwd=passwordView.getText().toString().trim();
        if(TextUtils.isEmpty(username)||TextUtils.isEmpty(pwd)){
            Toast.makeText(MainLoginActivity.this,"电话和密码不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    EMClient.getInstance().createAccount(username,pwd);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainLoginActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                        }
                    });

                }catch (HyphenateException e){
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainLoginActivity.this,"账号已经被注册",Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        }){
        }.start();
    }

    /**
     * 登录
     */
    private void sigin(){
        String username=phoneView.getText().toString().trim();
        String pwd=passwordView.getText().toString().trim();
        if(TextUtils.isEmpty(username)||TextUtils.isEmpty(pwd)){
            Toast.makeText(MainLoginActivity.this,"电话和密码不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        EMClient.getInstance().login(username,pwd,new EMCallBack() {//回调
            @Override
            public void onSuccess() {
                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();
                startActivity(new Intent(MainLoginActivity.this,MainUniverseActivity.class));
                finish();

            }

            @Override
            public void onProgress(int progress, String status) {

            }

            @Override
            public void onError(int code, String message) {
                Looper.prepare();
                Toast.makeText(MainLoginActivity.this,"密码或者账号错误",Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        });
    }
//    @Override
    //点击跳转到主导航页面 主宇宙
//    public void onClick(View view) {
//        Intent i=new Intent();
//        i.setClass(MainLoginActivity.this, MainUniverseActivity.class);
//        startActivity(i);
//    }
}
