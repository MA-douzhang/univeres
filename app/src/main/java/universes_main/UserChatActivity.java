package universes_main;

import adapter.MessageAdapter;
import adapter.UserAdapter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mainnavigation.R;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;
import org.jetbrains.annotations.NotNull;
import rx.Observable;
import rx.functions.Action1;

import java.util.List;


public class UserChatActivity extends AppCompatActivity implements View.OnClickListener, EMMessageListener {
    private RecyclerView tvMessageData;
    private EditText etChatmess;
    private Button btSent;
    private String userId;
    MessageAdapter userAdapter;
    List<EMMessage> messages;
    private Handler handler;
    private TextView textViewTopBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);
        //获得ui中userid
        userId = getIntent().getExtras().getString("userid");
        initView();
        initData();
        EMClient.getInstance().chatManager().addMessageListener(this);
        handler = new Handler(){

            @Override
            public void handleMessage(@NonNull @NotNull Message msg) {
                if(msg.what == 0x10){
                    getHistory();
                }
                super.handleMessage(msg);
            }
        };

    }

    private void initData() {
        //聊天界面顶部
        textViewTopBar.setText("正在和"+userId+"聊天");
        //聊天历史记录
        EMConversation conversation = EMClient.getInstance().chatManager().getConversation(userId);
        if (conversation != null) {
// 获取此会话的所有消息。
            //更新缓存信息
            EMMessage lastMessage = conversation.getLastMessage();
            String lastMessageId = lastMessage.getMsgId();
            int allMsgCount = conversation.getAllMsgCount();
            messages = conversation.loadMoreMsgFromDB(lastMessageId, allMsgCount);
            messages.add(lastMessage);
            //从内存里面更新信息
            userAdapter = new MessageAdapter(messages);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(UserChatActivity.this);
            tvMessageData.setLayoutManager(linearLayoutManager);
            tvMessageData.setAdapter(userAdapter);
        }
// SDK 初始化时，为每个会话加载 1 条聊天记录。如需更多消息，请到数据库中获取。该方法获取 `startMsgId` 之前的 `pagesize` 条消息，SDK 会将这些消息自动存入此会话，app 无需添加到会话中。
//        List<EMMessage> messages = conversation.loadMoreMsgFromDB(startMsgId, pagesize);
    }
    private void getHistory() {
        EMConversation conversation = EMClient.getInstance().chatManager().getConversation(userId);
        if (conversation != null) {
// 获取此会话的所有消息。
            //更新缓存信息
            EMMessage lastMessage = conversation.getLastMessage();
            String lastMessageId = lastMessage.getMsgId();
            int allMsgCount = conversation.getAllMsgCount();
            List<EMMessage> message = conversation.loadMoreMsgFromDB(lastMessageId, allMsgCount);
            messages.clear();
            messages.addAll(message);
            messages.add(lastMessage);
            //从内存里面更新信息
            userAdapter.setListView(messages);
            userAdapter.notifyDataSetChanged();
            if(userAdapter.getItemCount()>0){
                tvMessageData.smoothScrollToPosition(userAdapter.getItemCount()-1);
            }
        }
    }

    private void initView() {
        tvMessageData = findViewById(R.id.recyclerViewChat);
        etChatmess = findViewById(R.id.id_et_chatmessage);
        btSent = findViewById(R.id.id_bt_sent);
        textViewTopBar = findViewById(R.id.topBar);
        btSent.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //发送按钮点击监听
            case R.id.id_bt_sent:
                sendMessage();
                break;
        }
    }

    private void sendMessage() {
        String messageStr = etChatmess.getText().toString();
        EMMessage messagelay = EMMessage.createTxtSendMessage(messageStr, userId);
        EMClient.getInstance().chatManager().sendMessage(messagelay);
        etChatmess.setText("");
        getHistory();
//        tvMessageData.("我："+messageStr+"\n");
    }

    @Override
    public void onMessageReceived(List<EMMessage> messages) {
        Observable.from(messages).subscribe(new Action1<EMMessage>() {
            @Override
            public void call(EMMessage emMessage) {
                EMTextMessageBody emtextmessage = (EMTextMessageBody) emMessage.getBody();
//                tvMessageData.append(emMessage.getFrom()+"："+emtextmessage.getMessage()+"\n");
                Message message = new Message();
                message.what = 0x10;
                handler.sendMessage(message);
            }
        });
    }

    @Override
    public void onCmdMessageReceived(List<EMMessage> messages) {

    }

    @Override
    public void onMessageRead(List<EMMessage> messages) {

    }

    @Override
    public void onMessageDelivered(List<EMMessage> messages) {

    }

    @Override
    public void onMessageChanged(EMMessage message, Object change) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EMClient.getInstance().chatManager().removeMessageListener(this);
    }
}
