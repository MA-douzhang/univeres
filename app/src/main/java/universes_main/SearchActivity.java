package universes_main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.mainnavigation.R;
import com.hyphenate.EMCallBack;
import com.hyphenate.EMContactListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import java.util.List;

public class SearchActivity extends AppCompatActivity {
    //好友添加按钮
    private Button buttonAddFriend;
    //搜索框
    private EditText editTextSearchFriend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_page);
        initView();
        initData();
    }

    private void initView() {
        buttonAddFriend = findViewById(R.id.buttonAddFriend);
        editTextSearchFriend = findViewById(R.id.editTextSearchFriend);
    }

    private void initData() {
        buttonAddFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> usernames;
                String userId = editTextSearchFriend.getText().toString();
                if (userId.equals("")) {
                    Toast.makeText(SearchActivity.this, "账号不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        EMCallBack back = new EMCallBack() {
                            @Override
                            public void onSuccess() {
                                Log.e("TAG", "成功");
                            }

                            @Override
                            public void onError(int code, String error) {
                                Log.e("TAG", "error" + error);
                            }
                        };
                        EMClient.getInstance().contactManager().aysncAddContact(userId, "请求添加好友", back);
                    }
                });
                try {
                    usernames = EMClient.getInstance().contactManager().getContactsFromLocal();
                } catch (HyphenateException e) {
                    throw new RuntimeException(e);
                }
                if(!usernames.contains(userId)){
                    thread.start();
                    Toast.makeText(SearchActivity.this, "请求已经发送", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(SearchActivity.this, "已经添加了该好友", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
