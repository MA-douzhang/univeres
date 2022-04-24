package universes_main;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager2.widget.ViewPager2;
import com.example.mainnavigation.R;
import adapter.UniverseTopAdapter;

import java.util.ArrayList;

/**
    * 校园宇宙页面实现
 */
public class BlankFragmentUniverse extends Fragment implements View.OnClickListener{
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String mTextString;
    View rootView;
    private LinearLayout Friend,Recommend,Find;//部件id
    private TextView FriendTextView,RecommendTextView,FindTextView, currentTextView;//图片
    ViewPager2 viewPager;
    public BlankFragmentUniverse() {
        // Required empty public constructor
    }

    /**
     *二级导航栏
     */
    public static BlankFragmentUniverse newInstance(String param1) {
        BlankFragmentUniverse fragment = new BlankFragmentUniverse();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTextString = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(rootView==null)
                rootView = inflater.inflate(R.layout.navigation_top_universe, container, false);
        initPager();
        initTabView();
        return rootView;
    }
    //与MainUniverseActivity类中实现类似
    private void initPager() {
        //三大板块滑动
        viewPager = rootView.findViewById(R.id.idUniverseViewPager);
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(BlankFragment.newInstance("好友"));
        fragments.add(BlankFragment.newInstance("推荐"));
        fragments.add(BlankFragment.newInstance("发现"));
        UniverseTopAdapter pagerAdapter = new UniverseTopAdapter(getChildFragmentManager(), getLifecycle(), fragments);
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
    // 0x表示十六进制 ff表示alpha值 后面是颜色rgb十六进制
    private void changeTab(int position) {
        currentTextView.setTextColor(0xff000000);
        switch (position) {
            case R.id.idTopUniverseFriend:
                viewPager.setCurrentItem(0);
            case 0:
                Friend.setSelected(true);
                FriendTextView.setTextColor(0xff0099FF);
                currentTextView = FriendTextView;
                break;
            case R.id.idTopUniverseRecommend:
                viewPager.setCurrentItem(1);
            case 1:
                Recommend.setSelected(true);
                RecommendTextView.setTextColor(0xff0099FF);
                currentTextView = RecommendTextView;
                break;
            case R.id.idTopUniverseFind:
                viewPager.setCurrentItem(2);
            case 2:
                Find.setSelected(true);
                FindTextView.setTextColor(0xff0099FF);
                currentTextView = FindTextView;
                break;
        }
    }

    //点击反应 还未实现变化
    @SuppressLint("WrongViewCast")
    private void initTabView() {
        //部件id
        Friend = rootView.findViewById(R.id.idTopUniverseFriend);
        Friend.setOnClickListener(this);
        Recommend = rootView.findViewById(R.id.idTopUniverseRecommend);
        Recommend.setOnClickListener(this);
        Find = rootView.findViewById(R.id.idTopUniverseFind);
        Find.setOnClickListener(this);
        //部件TextView
        FriendTextView = rootView.findViewById(R.id.idTopFriend);
        RecommendTextView = rootView.findViewById(R.id.idTopRecommend);
        FindTextView = rootView.findViewById(R.id.idTopFind);
        Recommend.setSelected(true);
        currentTextView = RecommendTextView;
    }

    public void onClick(View view) {
        changeTab(view.getId());
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

}