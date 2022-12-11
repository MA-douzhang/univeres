package universes_main;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.mainnavigation.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_IMAGE = "param1";

    // TODO: Rename and change types of parameters
    private String mTextString;
    View rootView;

    public BlankFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankFragment newInstance(String param1) {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putString(ARG_IMAGE,param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTextString = getArguments().getString(ARG_IMAGE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView=inflater.inflate(R.layout.fragment_first, container, false);
//        if(rootView==null)
//            if(mTextString=="主页")
////            rootView=inflater.inflate(R.layout.content_main, container, false);
//            else if(mTextString=="宇宙世界") {
//                rootView = inflater.inflate(R.layout.navigation_top_universe, container, false);
//            }
//            else if(mTextString=="聊天")
//                rootView=inflater.inflate(R.layout.fragment_first, container, false);
//            else if(mTextString=="我的")
//                rootView=inflater.inflate(R.layout.fragment_first, container, false);
//            else if(mTextString=="好友")
//                rootView=inflater.inflate(R.layout.fragment_first, container, false);
//            else if(mTextString=="推荐")
//                rootView=inflater.inflate(R.layout.fragment_first, container, false);
//            else if(mTextString=="发现")
//                rootView=inflater.inflate(R.layout.universe_world, container, false);
        initView();
        return rootView;
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    //点击时的改变
    private void initView() {
    }
}
