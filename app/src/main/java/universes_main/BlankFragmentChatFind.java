package universes_main;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager2.widget.ViewPager2;
import com.example.mainnavigation.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragmentChatFind#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragmentChatFind extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private String mParam1;
    View rootView;
    ViewPager2 viewPager;
    private EditText etChatId;
    private Button btStartChat, btExit;

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
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (rootView == null)
            rootView = inflater.inflate(R.layout.chat_find, container, false);
        initPager();
        return rootView;
    }

    private void initPager() {
        etChatId = (EditText) rootView.findViewById(R.id.et_chatId);
        btStartChat = (Button) rootView.findViewById(R.id.bt_startChat);
        btStartChat.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_startChat:
                Intent inetent = new Intent(getActivity(), UserChatActivity.class);
                inetent.putExtra("userid", etChatId.getText().toString());
                startActivity(inetent);
                break;
//        }
        }
    }
}