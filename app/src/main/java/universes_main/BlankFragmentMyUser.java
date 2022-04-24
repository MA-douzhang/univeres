package universes_main;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.mainnavigation.R;

import static android.view.animation.Animation.RELATIVE_TO_SELF;

/**
*   个人页面实现
 */
public class BlankFragmentMyUser extends Fragment implements View.OnClickListener{


    View rootView;
    private ImageView iv_set;
    private ImageView iv_fead;
    private ImageView iv_mess;
    private ImageView iv_menu;

    private ImageView iv_shop;
    private ImageView iv_state;
    private ImageView iv_dress;
    private ImageView iv_planet;
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

    private boolean side_isClose = true;//侧边菜单是否已打开

    private boolean cri_isClose1 = true;//圆形菜单是否已打开



    public BlankFragmentMyUser() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(rootView==null)
            rootView = inflater.inflate(R.layout.my_crimenu, container, false);
        initView();
        return rootView;
    }
    public void onClick(View v) {
        switch (v.getId()){
            default:
                break;
            case R.id.iv_menu:
                if (side_isClose) {
                    initStartAnim(360,-150,-150,-20);//展开菜单
                    side_isClose = false;
                }else {
                    initStopAnim(-360,-150,-150,-20);//关闭菜单
                    side_isClose = true;
                }
                break;
        }

    }

    public void menuOnClick(View view) {
        switch (view.getId()){
            default:
                break;
            case R.id.menu1:
                if (cri_isClose1){
                    initStartAnim1(360,-1500,-100,-20);//展开菜单
                    cri_isClose1 = false;
                }else{
                    initStopAnim1(-360,-150,-150,-20);//关闭菜单
                }
        }
    }



    private void initStartAnim1(int toDegrees1, int height1, int viewHeight1, int margin1) {
        animationSet1 = new AnimationSet(true);
        roate = new RotateAnimation(0,toDegrees1,RELATIVE_TO_SELF,0.5f,RELATIVE_TO_SELF,0.5f);
        roate.setDuration(1000);
        roate.setRepeatCount(0);
        animationSet1.addAnimation(roate);
        animationSet1.setFillAfter(true);
        iv_cirmenu.startAnimation(animationSet);
        //cricularmenu  第一个子菜单
        animationSet1 = new AnimationSet(true);
        roate = new RotateAnimation(0,toDegrees1,RELATIVE_TO_SELF,0.5f,RELATIVE_TO_SELF,0.5f);
        roate.setDuration(1000);
        roate.setRepeatCount(0);

        tranlate = new TranslateAnimation(0,0,0,height1+margin1);
        tranlate.setFillAfter(true);
        tranlate.setDuration(1000);

        animationSet.addAnimation(roate);
        animationSet.addAnimation(tranlate);
        animationSet.setFillAfter(true);
        iv_shop.startAnimation(animationSet);



    }

   private void initStopAnim1(int i, int i1, int i2, int i3) {

    }


    //初始动画
    //sideview展开动画
    private void initStartAnim(int toDegrees, int height, int viewHeight, int margin) {
        //menu
        animationSet = new AnimationSet(true);
        //旋转动画
        roate = new RotateAnimation(0,toDegrees,RELATIVE_TO_SELF,0.5f,RELATIVE_TO_SELF,0.5f);
        roate.setDuration(1000);
        roate.setRepeatCount(0);
        animationSet.addAnimation(roate);
        animationSet.setFillAfter(true);
        iv_menu.startAnimation(animationSet);
        //menu1  第一个子菜单
        animationSet = new AnimationSet(true);
        //旋转
        roate = new RotateAnimation(0,toDegrees,RELATIVE_TO_SELF,0.5f,RELATIVE_TO_SELF,0.5f);
        roate.setDuration(1000);
        roate.setRepeatCount(0);
        //移动
        tranlate = new TranslateAnimation(0,0,0,height+margin);
        tranlate.setFillAfter(true);
        tranlate.setDuration(1000);

        animationSet.addAnimation(roate);
        animationSet.addAnimation(tranlate);
        animationSet.setFillAfter(true);
        iv_set.startAnimation(animationSet);
        //menu2 第二个子菜单
        animationSet = new AnimationSet(true);
        //旋转
        roate = new RotateAnimation(0,toDegrees,RELATIVE_TO_SELF,0.5f,RELATIVE_TO_SELF,0.5f);
        roate.setDuration(1000);
        roate.setRepeatCount(0);
        //移动
        tranlate = new TranslateAnimation(0,0,0,height+viewHeight+margin*2);
        tranlate.setFillAfter(true);
        tranlate.setDuration(1000);

        animationSet.addAnimation(roate);
        animationSet.addAnimation(tranlate);
        animationSet.setFillAfter(true);
        iv_mess.startAnimation(animationSet);
        //menu3 第三个子菜单
        animationSet = new AnimationSet(true);
        //旋转
        roate = new RotateAnimation(0,toDegrees,RELATIVE_TO_SELF,0.5f,RELATIVE_TO_SELF,0.5f);
        roate.setDuration(1000);
        roate.setRepeatCount(0);
        //移动
        tranlate = new TranslateAnimation(0,0,0,height+viewHeight*2+margin*3);
        tranlate.setFillAfter(true);
        tranlate.setDuration(1000);

        animationSet.addAnimation(roate);
        animationSet.addAnimation(tranlate);
        animationSet.setFillAfter(true);
        iv_fead.startAnimation(animationSet);


    }
    //sideview关闭动画
    private void initStopAnim(int toDegrees,int height,int viewHeight,int margin) {
        //menu
        animationSet = new AnimationSet(true);
        roate = new RotateAnimation(0,toDegrees,RELATIVE_TO_SELF,0.5f,RELATIVE_TO_SELF,0.5f);
        roate.setDuration(1000);
        roate.setRepeatCount(0);
        animationSet.addAnimation(roate);
        animationSet.setFillAfter(true);
        iv_menu.startAnimation(animationSet);

        //menu1
        animationSet = new AnimationSet(true);
        //旋转
        roate = new RotateAnimation(0,toDegrees, RELATIVE_TO_SELF,0.5f, RELATIVE_TO_SELF,0.5f);
        roate.setDuration(1000);
        roate.setRepeatCount(0);
        //移动
        tranlate = new TranslateAnimation(0,0,height+margin,0);
        tranlate.setFillAfter(true);
        tranlate.setDuration(1000);

        animationSet.addAnimation(roate);
        animationSet.addAnimation(tranlate);
        animationSet.setFillAfter(true);
        iv_set.startAnimation(animationSet);
        //menu2
        animationSet = new AnimationSet(true);
        //旋转
        roate = new RotateAnimation(0,toDegrees,RELATIVE_TO_SELF,0.5f,RELATIVE_TO_SELF,0.5f);
        roate.setDuration(1000);
        roate.setRepeatCount(0);
        //移动
        tranlate = new TranslateAnimation(0,0,height+viewHeight+margin*2,0);
        tranlate.setFillAfter(true);
        tranlate.setDuration(1000);

        animationSet.addAnimation(roate);
        animationSet.addAnimation(tranlate);
        animationSet.setFillAfter(true);
        iv_mess.startAnimation(animationSet);
        //menu3
        animationSet = new AnimationSet(true);
        //旋转
        roate = new RotateAnimation(0,toDegrees,RELATIVE_TO_SELF,0.5f,RELATIVE_TO_SELF,0.5f);
        roate.setDuration(1000);
        roate.setRepeatCount(0);
        //移动
        tranlate = new TranslateAnimation(0,0,height+viewHeight*2+margin*3,0);
        tranlate.setFillAfter(true);
        tranlate.setDuration(1000);

        animationSet.addAnimation(roate);
        animationSet.addAnimation(tranlate);
        animationSet.setFillAfter(true);
        iv_fead.startAnimation(animationSet);

    }

    private void initView() {
        iv_fead = rootView.findViewById(R.id.iv_fead);
        iv_mess = rootView.findViewById(R.id.iv_mess);
        iv_set = rootView.findViewById(R.id.iv_set);
        iv_menu =  rootView.findViewById(R.id.iv_menu);

        iv_shop = rootView.findViewById(R.id.iv_shop);
//        iv_state = findViewById(R.id.iv_state);
//        iv_dress = findViewById(R.id.iv_dress);
//        iv_planet =  findViewById(R.id.iv_planet);
//        iv_more = findViewById(R.id.iv_more);

        //测试中间弹出
        iv_cirmenu = rootView.findViewById(R.id.iv_menu);

        iv_menu.setOnClickListener(this);
        iv_cirmenu.setOnClickListener(this);

    }



}