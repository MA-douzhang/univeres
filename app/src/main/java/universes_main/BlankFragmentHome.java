package universes_main;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.mainnavigation.R;

/**
 * home页面控件的实现
 */
public class BlankFragmentHome extends Fragment {
    //页面变量 rootView
    View rootView;

    public BlankFragmentHome() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    //页面初始化
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(rootView==null)
            rootView = inflater.inflate(R.layout.home_page, container, false);
        return rootView;
    }
}