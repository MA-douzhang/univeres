package adapter;

import android.content.Intent;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mainnavigation.R;
import com.hyphenate.chat.EMMessage;
import enity.Message;
import enity.User;
import org.jetbrains.annotations.NotNull;
import universes_main.UserChatActivity;

import java.text.SimpleDateFormat;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {
    private List<EMMessage> listView;

    public MessageAdapter(List<EMMessage> listView) {
        this.listView = listView;
    }

    @NonNull
    @NotNull
    @Override
    public MessageAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view;
        if(viewType == 1){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_left_list_item, parent, false);
        }else {
            view= LayoutInflater.from(parent.getContext()).inflate(R.layout.message_right_list_item, parent, false);
        }
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MessageAdapter.ViewHolder holder, int position) {
        EMMessage emMessage = listView.get(position);
        EMMessage.Direct direct = emMessage.direct();
        String name = emMessage.getUserName();
        String msg = emMessage.getBody().toString().split(":")[1]+"\n";
        String time = new SimpleDateFormat("HH-mm-ss").format(emMessage.getMsgTime());
        //send我发的 false收到的信息
        if(direct == EMMessage.Direct.SEND){
            holder.textViewMessage.setText(msg+"  ");

        }else {
            holder.textViewMessage.setText("  "+msg);
        }
        holder.textViewMessageTime.setText(time);
    }

    @Override
    public int getItemViewType(int position) {
        EMMessage emMessage = listView.get(position);
        EMMessage.Direct direct = emMessage.direct();
        //接收为1，发出为0
        return direct == EMMessage.Direct.RECEIVE?1:0;
    }

    @Override
    public int getItemCount() {
        return listView.size();
    }

    public void setListView(List<EMMessage> listView) {
        this.listView = listView;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textViewMessage;
        TextView textViewMessageTime;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            textViewMessage = itemView.findViewById(R.id.message);
            textViewMessageTime = itemView.findViewById(R.id.messageTime);
        }
    }
}
