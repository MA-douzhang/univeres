package universes_main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.mainnavigation.R;
import com.hyphenate.EMValueCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMContactManager;
import com.hyphenate.chat.EMUserInfo;
import com.hyphenate.exceptions.HyphenateException;

import java.util.List;
import java.util.Map;

/**
 * home页面控件的实现
 */
public class BlankFragmentHome extends Fragment {
    //页面变量 rootView
    View rootView;
    Button buttonMenu;
    public BlankFragmentHome() {
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
            rootView = inflater.inflate(R.layout.home_page, container, false);
        initView();
        initData();
        return rootView;
    }
    private void initData() {
        buttonMenu.setOnClickListener(new View.OnClickListener() {
            List<String> username;

            @Override
            public void onClick(View v) {
                boolean loggedInBefore = EMClient.getInstance().isLoggedInBefore();
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
//                    username = EMClient.getInstance().contactManager().getContactsFromLocal();
                            username = EMClient.getInstance().contactManager().getAllContactsFromServer();
                        } catch (HyphenateException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
                thread.start();
                Log.e("TAG","user"+username+"是否登录"+loggedInBefore);

            }
        });
    }

    private void initView() {
        buttonMenu = rootView.findViewById(R.id.idMenuButton);
    }
}
    //页面初始化

