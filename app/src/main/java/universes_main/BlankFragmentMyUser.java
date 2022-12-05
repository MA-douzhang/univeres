package universes_main;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.*;
import android.widget.*;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.mainnavigation.MainLoginActivity;
import com.example.mainnavigation.R;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

import static android.view.animation.Animation.RELATIVE_TO_SELF;

/**
 * 个人页面实现
 */
public class BlankFragmentMyUser extends Fragment implements View.OnClickListener {


    View rootView;
    private ImageView iv_set;
    private ImageView iv_fead;
    private ImageView iv_mess;
    private ImageView iv_menu;

    private ImageView iv_shop_img;
    private ImageView iv_add_img;
    private ImageView iv_home_img;
    private ImageView iv_menu1;
    private ImageView iv_more;

    private ImageView iv_cirmenu;

    private int intHeight;//菜单按钮高度
    private int intViewHeight;//菜单子按钮高度

    private int intHeight1;//菜单按钮高度
    private int intViewHeight1;//菜单子按钮高度

    private AnimationSet animationSet;//动画组
    private AnimationSet animationSet1;//动画组
    private Animation roate;//旋转
    private Animation tranlate;//一定
    private Animation scale;//缩放
    private Animation alphaAnimation;//淡化
    private TextView my_crimenu_name;
    private boolean side_isClose = true;//侧边菜单是否已打开

    private boolean cri_isClose1 = true;//圆形菜单是否已打开
    private Button buttonExit;


    public BlankFragmentMyUser() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //中间菜单点击监听
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (rootView == null)
            rootView = inflater.inflate(R.layout.my_crimenu, container, false);
        initView();
        initName();
        initOnClickListener();
        return rootView;
    }

    private void initName() {
        my_crimenu_name.setText(EMClient.getInstance().getCurrentUser());
    }

    //点击问题未解决
    public void onClick(View v) {

    }

    protected void initStartCentreAnim1(int toDegrees1, int height1, int viewHeight1, int viewWidth) {
//        animationSet1 = new AnimationSet(true);
//        roate = new RotateAnimation(0,toDegrees1,RELATIVE_TO_SELF,0.5f,RELATIVE_TO_SELF,0.5f);
//        roate.setDuration(1000);
//        roate.setRepeatCount(0);
//
//        animationSet1.addAnimation(roate);
//        animationSet1.setFillAfter(true);
//
//        iv_menu1.startAnimation(animationSet1);
        //cricularmenu  第一个子菜单
        animationSet1 = new AnimationSet(true);
        roate = new RotateAnimation(0, toDegrees1, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        roate.setDuration(1000);
        roate.setRepeatCount(0);
        //移动
        tranlate = new TranslateAnimation(0, -viewWidth / 3, 0, 0);
        tranlate.setFillAfter(true);
        tranlate.setDuration(1000);
        //淡出
        alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setDuration(1000);
        //动画组的添加
        animationSet1.addAnimation(roate);
        animationSet1.addAnimation(tranlate);
        animationSet1.addAnimation(alphaAnimation);
        animationSet1.setFillAfter(true);
        iv_shop_img.startAnimation(animationSet1);
//右边
        animationSet1 = new AnimationSet(true);
        roate = new RotateAnimation(0, toDegrees1, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        roate.setDuration(1000);
        roate.setRepeatCount(0);

        tranlate = new TranslateAnimation(0, viewWidth / 3, 0, 0);
        tranlate.setFillAfter(true);
        tranlate.setDuration(1000);
        //淡出
        alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setDuration(1000);

        animationSet1.addAnimation(roate);
        animationSet1.addAnimation(tranlate);
        animationSet1.addAnimation(alphaAnimation);
        animationSet1.setFillAfter(true);
        iv_add_img.startAnimation(animationSet1);
// 上面
        animationSet1 = new AnimationSet(true);
        roate = new RotateAnimation(0, toDegrees1, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        roate.setDuration(1000);
        roate.setRepeatCount(0);

        tranlate = new TranslateAnimation(0, 0, 0, -viewHeight1 / 3);
        tranlate.setFillAfter(true);
        tranlate.setDuration(1000);
        //淡出
        alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setDuration(1000);
        animationSet1.addAnimation(roate);
        animationSet1.addAnimation(tranlate);
        animationSet1.addAnimation(alphaAnimation);
        animationSet1.setFillAfter(true);
        iv_home_img.startAnimation(animationSet1);


    }

    protected void initStopCentreAnim1(int toDegrees1, int height, int viewHeight1, int viewWidth) {
        animationSet1 = new AnimationSet(true);
        roate = new RotateAnimation(0, toDegrees1, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        roate.setDuration(1000);
        roate.setRepeatCount(0);
        //移动
        tranlate = new TranslateAnimation(-viewWidth / 3, 0, 0, 0);
        tranlate.setFillAfter(true);
        tranlate.setDuration(1000);
        //淡入
        alphaAnimation = new AlphaAnimation(1, 0);
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setDuration(1000);
        //动画组的添加
        animationSet1.addAnimation(roate);
        animationSet1.addAnimation(tranlate);
        animationSet1.addAnimation(alphaAnimation);
        animationSet1.setFillAfter(true);
        iv_shop_img.startAnimation(animationSet1);
//右边
        animationSet1 = new AnimationSet(true);
        roate = new RotateAnimation(0, toDegrees1, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        roate.setDuration(1000);
        roate.setRepeatCount(0);

        tranlate = new TranslateAnimation(viewWidth / 3, 0, 0, 0);
        tranlate.setFillAfter(true);
        tranlate.setDuration(1000);
        //淡入
        alphaAnimation = new AlphaAnimation(1, 0);
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setDuration(1000);

        animationSet1.addAnimation(roate);
        animationSet1.addAnimation(tranlate);
        animationSet1.addAnimation(alphaAnimation);
        animationSet1.setFillAfter(true);
        iv_add_img.startAnimation(animationSet1);
// 上面
        animationSet1 = new AnimationSet(true);
        roate = new RotateAnimation(0, toDegrees1, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        roate.setDuration(1000);
        roate.setRepeatCount(0);

        tranlate = new TranslateAnimation(0, 0, -viewHeight1 / 3, 0);
        tranlate.setFillAfter(true);
        tranlate.setDuration(1000);
        //淡入
        alphaAnimation = new AlphaAnimation(1, 0);
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setDuration(1000);
        animationSet1.addAnimation(roate);
        animationSet1.addAnimation(tranlate);
        animationSet1.addAnimation(alphaAnimation);
        animationSet1.setFillAfter(true);
        iv_home_img.startAnimation(animationSet1);


    }


    //初始动画
    //sideview展开动画
    private void initStartRightAnim(int toDegrees, int height, int viewHeight, int viewWidth) {
        //menu
        animationSet = new AnimationSet(true);
        //旋转动画
        roate = new RotateAnimation(0, toDegrees, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        roate.setDuration(1000);
        roate.setRepeatCount(0);
        animationSet.addAnimation(roate);
        animationSet.setFillAfter(true);
        iv_menu.startAnimation(animationSet);
        //menu1  第一个子菜单
        animationSet = new AnimationSet(true);
        //旋转
        roate = new RotateAnimation(0, toDegrees, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        roate.setDuration(1000);
        roate.setRepeatCount(0);
        //移动
        tranlate = new TranslateAnimation(0, 0, 0, -(viewHeight / 4));
        tranlate.setFillAfter(true);
        tranlate.setDuration(1000);
        // 淡出
        alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setDuration(1000);
        animationSet.addAnimation(alphaAnimation);

        animationSet.addAnimation(roate);
        animationSet.addAnimation(tranlate);
        animationSet.setFillAfter(true);
        iv_set.startAnimation(animationSet);
        //menu2 第二个子菜单
        animationSet = new AnimationSet(true);
        //旋转
        roate = new RotateAnimation(0, toDegrees, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        roate.setDuration(1000);
        roate.setRepeatCount(0);
        //移动
        tranlate = new TranslateAnimation(0, 0, 0, -(viewHeight / 4 * 2));
        tranlate.setFillAfter(true);
        tranlate.setDuration(1000);
        // 淡出
        alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setDuration(1000);

        animationSet.addAnimation(roate);
        animationSet.addAnimation(tranlate);
        animationSet.addAnimation(alphaAnimation);
        animationSet.setFillAfter(true);
        iv_mess.startAnimation(animationSet);
        //menu3 第三个子菜单
        animationSet = new AnimationSet(true);
        //旋转
        roate = new RotateAnimation(0, toDegrees, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        roate.setDuration(1000);
        roate.setRepeatCount(0);
        //移动
        tranlate = new TranslateAnimation(0, 0, 0, -(viewHeight / 4 * 3));
        tranlate.setFillAfter(true);
        tranlate.setDuration(1000);

        // 淡出
        alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setDuration(1000);
        animationSet.addAnimation(alphaAnimation);

        animationSet.addAnimation(roate);
        animationSet.addAnimation(tranlate);
        animationSet.setFillAfter(true);
        iv_fead.startAnimation(animationSet);


    }

    //sideview关闭动画
    private void initStopRightAnim(int toDegrees, int height, int viewHeight, int viewWidth) {
        //menu
        animationSet = new AnimationSet(true);
        roate = new RotateAnimation(0, toDegrees, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        roate.setDuration(1000);
        roate.setRepeatCount(0);
        animationSet.addAnimation(roate);
        animationSet.setFillAfter(true);
        iv_menu.startAnimation(animationSet);

        //menu1
        animationSet = new AnimationSet(true);
        //旋转
        roate = new RotateAnimation(0, toDegrees, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        roate.setDuration(1000);
        roate.setRepeatCount(0);
        //移动
        tranlate = new TranslateAnimation(0, 0, -viewHeight / 4, 0);
        tranlate.setFillAfter(true);
        tranlate.setDuration(1000);
        // 淡入
        alphaAnimation = new AlphaAnimation(1, 0);
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setDuration(1000);

        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(roate);
        animationSet.addAnimation(tranlate);
        animationSet.setFillAfter(true);
        iv_set.startAnimation(animationSet);
        //menu2
        animationSet = new AnimationSet(true);
        //旋转
        roate = new RotateAnimation(0, toDegrees, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        roate.setDuration(1000);
        roate.setRepeatCount(0);
        //移动
        tranlate = new TranslateAnimation(0, 0, -(viewHeight / 4 * 2), 0);
        tranlate.setFillAfter(true);
        tranlate.setDuration(1000);
        // 淡入
        alphaAnimation = new AlphaAnimation(1, 0);
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setDuration(1000);
        animationSet.addAnimation(alphaAnimation);

        animationSet.addAnimation(roate);
        animationSet.addAnimation(tranlate);
        animationSet.setFillAfter(true);
        iv_mess.startAnimation(animationSet);
        //menu3
        animationSet = new AnimationSet(true);
        //旋转
        roate = new RotateAnimation(0, toDegrees, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        roate.setDuration(1000);
        roate.setRepeatCount(0);
        //移动
        tranlate = new TranslateAnimation(0, 0, -(viewHeight / 4 * 3), 0);
        tranlate.setFillAfter(true);
        tranlate.setDuration(1000);
        // 淡入
        alphaAnimation = new AlphaAnimation(1, 0);
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setDuration(1000);
        animationSet.addAnimation(alphaAnimation);

        animationSet.addAnimation(roate);
        animationSet.addAnimation(tranlate);
        animationSet.setFillAfter(true);
        iv_fead.startAnimation(animationSet);

    }

    private void initView() {
        //右下角
        my_crimenu_name = rootView.findViewById(R.id.id_my_crimenu_name);
        iv_fead = rootView.findViewById(R.id.iv_fead);
        iv_mess = rootView.findViewById(R.id.iv_mess);
        iv_set = rootView.findViewById(R.id.iv_set);
        iv_menu = rootView.findViewById(R.id.iv_menu);

        //中心菜单
        iv_shop_img = rootView.findViewById(R.id.id_iv_shop);
        iv_add_img = rootView.findViewById(R.id.id_iv_add);
        iv_home_img = rootView.findViewById(R.id.id_iv_home);
        iv_menu1 = rootView.findViewById(R.id.id_menu1);
        //测试中间弹出
        iv_cirmenu = rootView.findViewById(R.id.iv_menu);
        iv_menu.setOnClickListener(this);
        iv_cirmenu.setOnClickListener(this);

        //监听
        buttonExit = rootView.findViewById(R.id.id_exit);
    }

    //监听动画
    private void initOnClickListener() {
        RelativeLayout relativeLayoutMenu;
        relativeLayoutMenu = rootView.findViewById(R.id.rl_circle_menu);
        //中间菜单按钮
        iv_menu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cri_isClose1) {
                    initStartCentreAnim1(360, -150, relativeLayoutMenu.getHeight(), relativeLayoutMenu.getWidth());//展开菜单
                    cri_isClose1 = false;
                } else {
                    initStopCentreAnim1(-360, -150, relativeLayoutMenu.getHeight(), relativeLayoutMenu.getWidth());//关闭菜单
                    cri_isClose1 = true;
                }
            }
        });
        //右下角监听
        RelativeLayout relativeLayoutRight;
        relativeLayoutRight = rootView.findViewById(R.id.rl_Relative);
        iv_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (side_isClose) {
                    initStartRightAnim(360, -150, relativeLayoutRight.getHeight(), -20);//展开菜单
                    side_isClose = false;
                } else {
                    initStopRightAnim(-360, -150, relativeLayoutRight.getHeight(), -20);//关闭菜单
                    side_isClose = true;
                }
            }
        });
        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //退出登录
                initExit();
                startActivity(new Intent(getActivity(), MainLoginActivity.class));
            }
        });
    }

    public void initExit() {
        EMClient.getInstance().logout(true);
    }
}

