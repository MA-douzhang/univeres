package universes_main;

import adapter.MessageAdapter;
import adapter.MessageReceiveAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.example.mainnavigation.R;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragmentChatFind#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragmentChatFind extends Fragment implements View.OnClickListener{

    // TODO: 消息接收页面 EMMessageListener接口
    private static final String ARG_PARAM1 = "param1";
    private List<EMMessage> messagesList;
    private MessageReceiveAdapter messageReceiveAdapter;
    View rootView;
    private RecyclerView recyclerViewMessage;
    private Handler handler;
    public BlankFragmentChatFind() {
        // Required empty public constructor
    }


    public static BlankFragmentChatFind newInstance(String param1, String param2) {
        BlankFragmentChatFind fragment = new BlankFragmentChatFind();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            String mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (rootView == null)
            rootView = inflater.inflate(R.layout.chat_find, container, false);
        initView();
        initData();
        return rootView;
    }

    private void initData() {
        messagesList = new ArrayList<>();
        messageReceiveAdapter = new MessageReceiveAdapter(messagesList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerViewMessage.setLayoutManager(linearLayoutManager);
        recyclerViewMessage.setAdapter(messageReceiveAdapter);
        handler = new Handler(){

            @Override
            public void handleMessage(@NonNull @NotNull Message msg) {
                switch (msg.what){
                    case 0x10:
                        messageReceiveAdapter.notifyDataSetChanged();
                }
                super.handleMessage(msg);
            }
        };
    }

    private void initView() {
        recyclerViewMessage = rootView.findViewById(R.id.recyclerViewMessage);
        EMMessageListener msgListener = new EMMessageListener() {

            // 收到消息，遍历消息队列，解析和显示。
            @Override
            public void onMessageReceived(List<EMMessage> messages) {
                messagesList.addAll(messages);
                messageReceiveAdapter.setListView(messagesList);
                //适配器刷新
                Message message = new Message();
                message.what = 0x10;
                handler.sendMessage(message);
            }
        };
// 注册消息监听
        EMClient.getInstance().chatManager().addMessageListener(msgListener);
    }

    @Override
    public void onClick(View v) {

    }
}
