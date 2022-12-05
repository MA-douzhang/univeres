package universes_main;

import adapter.UserAdapter;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mainnavigation.R;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import enity.User;

import java.util.ArrayList;
import java.util.List;


public class FriendsFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private RecyclerView recyclerViewFriends;
    private Button buttonFreshen;
    List<String> usernames;
    View rootView;
    public FriendsFragment() {
        // Required empty public constructor
    }

    public static FriendsFragment newInstance(String param1, String param2) {
        FriendsFragment fragment = new FriendsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(rootView==null)
            rootView = inflater.inflate(R.layout.friends_blank, container, false);
        initView();
        initData();
        return rootView;
    }
    private void initView() {
        recyclerViewFriends = rootView.findViewById(R.id.recyclerViewFriends);
        buttonFreshen = rootView.findViewById(R.id.buttonFresh);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    usernames = EMClient.getInstance().contactManager().getAllContactsFromServer();
                } catch (HyphenateException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.start();
    }
    private void getFriends(){
        try {
            usernames = EMClient.getInstance().contactManager().getContactsFromLocal();
        } catch (HyphenateException e) {
            throw new RuntimeException(e);
        }
        List<User> userList = new ArrayList<>();
        for (String username : usernames) {
            User user = new User(username);
            userList.add(user);
        }
        UserAdapter userAdapter = new UserAdapter(userList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerViewFriends.setLayoutManager(linearLayoutManager);
        recyclerViewFriends.setAdapter(userAdapter);
    }
    private void initData() {
        buttonFreshen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFriends();
            }
        });
    }

}
