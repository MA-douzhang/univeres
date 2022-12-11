package adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mainnavigation.R;
import com.hyphenate.chat.EMMessage;
import org.jetbrains.annotations.NotNull;
import universes_main.UserChatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageReceiveAdapter extends RecyclerView.Adapter<MessageReceiveAdapter.ViewHolder> {
    private List<EMMessage> listView;
    public MessageReceiveAdapter(List<EMMessage> listView) {
        listView = getMessageList(listView);
        this.listView = listView;

    }

    private List<EMMessage> getMessageList(List<EMMessage> listView) {
        Map<String,EMMessage> messageMap = new HashMap<>();
        for (EMMessage emMessage : listView) {
            String name = emMessage.getUserName();
            messageMap.put(name,emMessage);
        }
        return new ArrayList<>(messageMap.values());
    }

    @NonNull
    @NotNull
    @Override
    public MessageReceiveAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.relativeMessages.setOnClickListener(v -> {
            int position = viewHolder.getAdapterPosition();
            Intent intent = new Intent(v.getContext(), UserChatActivity.class);
            intent.putExtra("userid", listView.get(position).getUserName());
           //信息被点击后该条信息,信息数变成0
            viewHolder.textViewMessageCount.setText("0");
            v.getContext().startActivity(intent);
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MessageReceiveAdapter.ViewHolder holder, int position) {
        EMMessage emMessage = listView.get(position);
        String name = emMessage.getUserName();
        String msg = emMessage.getBody().toString().split(":")[1]+"\n";
        int count = Integer.valueOf(holder.textViewMessageCount.getText().toString())+1;
        //send我发的 false收到的信息
        holder.textViewMessage.setText(msg);
        holder.textViewMessageUserName.setText(name);
        holder.textViewMessageCount.setText(""+count);
    }
    @Override
    public int getItemCount() {
        return listView.size();
    }
    public void setListView(List<EMMessage> listView) {
        listView = getMessageList(listView);
        this.listView = listView;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textViewMessage;
        TextView textViewMessageUserName;
        TextView textViewMessageCount;
        RelativeLayout relativeMessages;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            textViewMessage = itemView.findViewById(R.id.textviewMessageReceive);
            textViewMessageUserName = itemView.findViewById(R.id.textViewMessageUserName);
            textViewMessageCount = itemView.findViewById(R.id.textViewMessageCount);
            relativeMessages = itemView.findViewById(R.id.relativeMessages);
        }
    }
}
