package com.study.dwika.kplchat.menu.Friend;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.study.dwika.kplchat.R;
import com.study.dwika.kplchat.model.Users;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by A.I on 15/12/2017.
 */

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.FriendViewHolder>{

    private List<Users> usersList;
    private Context context;

    public FriendAdapter(Context context, List<Users> usersList) {
        this.usersList = usersList;
        this.context = context;
    }

    @Override
    public FriendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_friend, null);
        return new FriendViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FriendViewHolder holder, int position) {
        holder.tvName.setText(usersList.get(position).getName());
        holder.tvName.setText("hola");
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public class FriendViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tvName)
        TextView tvName;

        public FriendViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
