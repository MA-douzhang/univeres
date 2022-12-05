package adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mainnavigation.R;
import enity.User;
import org.jetbrains.annotations.NotNull;
import universes_main.UserChatActivity;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private List<User> listView;

    public UserAdapter(List<User> listView) {
        this.listView = listView;
    }

    @NonNull
    @NotNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.friends_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.textUserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                Intent intent = new Intent(v.getContext(), UserChatActivity.class);
                intent.putExtra("userid", listView.get(position).getUserName());
                v.getContext().startActivity(intent);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull UserAdapter.ViewHolder holder, int position) {
        User user = listView.get(position);
        holder.textUserName.setText("  用户名："+user.getUserName());
    }

    @Override
    public int getItemCount() {
        return listView.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textUserName;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            textUserName = itemView.findViewById(R.id.textUsername);
        }
    }
}
