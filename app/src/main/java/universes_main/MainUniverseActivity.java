package universes_main;

import adapter.MyFragmentPagerAdapter;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;
import com.example.mainnavigation.*;
import com.hyphenate.EMCallBack;
import com.hyphenate.EMContactListener;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.exceptions.HyphenateException;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/*主宇宙类
    作用: 作为其他星系的跳转类
*/
public class MainUniverseActivity extends AppCompatActivity implements View.OnClickListener {
    /*
    home 我的星球
    campusUniverse 校园宇宙
    chat 聊天
    my 个人页面
     */
    private LinearLayout home, campusUniverse, chat, my;//部件id
    private Button buttonExit;
    private Handler handler;
    //导航栏图片 currentImage 当前被点击的图片
    private ImageView homeImage, campusUniverseImage, chatImage, myImage, currentImage;//图片
    //页面转换显示的ViewPager viewPager
    ViewPager2 viewPager;

    //Universe 初始化

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //横屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        setContentView(R.layout.navigation);
        Toast.makeText(MainUniverseActivity.this, "欢迎回来", Toast.LENGTH_SHORT).show();
        //viewPaper2实现
        initPager();
        //导航栏滑动和点击的实现方法
        initTabView();
        initData();

        handler = new Handler(){

            @Override
            public void handleMessage(@NonNull @NotNull Message msg) {
                    if (msg.what ==0x10){
                        String username = (String) msg.obj;
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainUniverseActivity.this);
                        builder.setMessage(username + "请求添加好友");
                        Log.e("TAG", "收到请求");
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    EMClient.getInstance().contactManager().acceptInvitation(username);
                                } catch (HyphenateException e) {
                                    throw new RuntimeException(e);
                                }
                                Toast.makeText(MainUniverseActivity.this, username + "好友已经添加", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        });
                        builder.setNegativeButton("拒接", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                try {
                                    EMClient.getInstance().contactManager().declineInvitation(username);
                                    dialog.dismiss();
                                } catch (HyphenateException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        });
                        builder.create().show();
                }
                super.handleMessage(msg);
            }
        };
    }

    private void initData() {
        EMClient.getInstance().contactManager().setContactListener(new EMContactListener() {
            // 对方同意了好友请求。
            @Override
            public void onFriendRequestAccepted(String username) {
                Toast.makeText(MainUniverseActivity.this, username + "好友已经添加", Toast.LENGTH_SHORT).show();
            }

            // 对方拒绝了好友请求。
            @Override
            public void onFriendRequestDeclined(String username) {
                Toast.makeText(MainUniverseActivity.this, username + "已经拒接", Toast.LENGTH_SHORT).show();
            }

            // 接收到好友请求。
            @Override
            public void onContactInvited(String username, String reason) {
                Log.e("TAG", "收到请求");
                Message message = handler.obtainMessage();
                message.what = 0x10;
                message.obj = username;
                handler.sendMessage(message);
            }

            // 联系人被删除。
            @Override
            public void onContactDeleted(String username) {
            }

            // 联系人已添加。
            @Override
            public void onContactAdded(String username) {
            }
        });
    }


    //实现campusUniverse页面星球活动 "更多"按钮的跳转
//    public void otherClickActivity(View v) {
//        TextView textView= findViewById(R.id.idMorePlanetsActivity);
//        //建立监听器对 星球活动更多的点击监听
//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i=new Intent();
//                i.setClass(MainUniverseActivity.this, planeActivityActivity.class);
//                startActivity(i);
//            }
//        });
//    }
    //实现campusUniverse页面中热门星球 "更多"按钮的跳转
//    public void otherClickHot(View v){
//        TextView textView= findViewById(R.id.idMorePlanetsHot);
//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i=new Intent();
//                i.setClass(MainUniverseActivity.this, planeActivityActivity.class);
//                startActivity(i);
//            }
//        });
//    }

    private void initPager() {
        //我的星球，校园宇宙，聊天，个人页面滑动
        viewPager = findViewById(R.id.idViewPager);
        //建立fragment链表
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new BlankFragmentHome());
        fragments.add(new BlankFragmentUniverse());
        //聊天页面还未做出
        fragments.add(new BlankFragmentChatFind());

        fragments.add(new BlankFragmentMyUser());
        //适配器
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

    //导航栏点击和滑动的变化
    private void changeTab(int position) {
        currentImage.setSelected(false);
        switch (position) {
            case R.id.idHome:
                viewPager.setCurrentItem(0);
            case 0:
                homeImage.setSelected(true);
                currentImage = homeImage;
                break;
            case R.id.idCampusUniverse:
                viewPager.setCurrentItem(1);
            case 1:
                campusUniverseImage.setSelected(true);
                currentImage = campusUniverseImage;
                break;
            case R.id.idChat:
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
        home = findViewById(R.id.idHome);
        home.setOnClickListener(this);
        campusUniverse = findViewById(R.id.idCampusUniverse);
        campusUniverse.setOnClickListener(this);
        chat = findViewById(R.id.idChat);
        chat.setOnClickListener(this);
        my = findViewById(R.id.idMy);
        my.setOnClickListener(this);
        //部件图片
        homeImage = findViewById(R.id.idHomeImage);
        campusUniverseImage = findViewById(R.id.idCampusUniverseImage);
        chatImage = findViewById(R.id.idChatImage);
        myImage = findViewById(R.id.idMyImage);
        home.setSelected(true);
        currentImage = homeImage;
    }

    @Override
    public void onClick(View view) {
        changeTab(view.getId());
    }

}
