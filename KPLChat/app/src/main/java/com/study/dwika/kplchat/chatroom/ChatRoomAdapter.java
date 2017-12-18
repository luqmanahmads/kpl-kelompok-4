package com.study.dwika.kplchat.chatroom;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.study.dwika.kplchat.R;
import com.study.dwika.kplchat.model.Messages;

import java.util.List;

import butterknife.BindView;

import butterknife.ButterKnife;

/**
 * Created by A.I on 16/12/2017.
 */

public class ChatRoomAdapter extends RecyclerView.Adapter<ChatRoomAdapter.ChatViewHolder>{

    private List<Messages> messagesList;
    private Context context;

    public ChatRoomAdapter(List<Messages> messagesList, Context context) {
        this.messagesList = messagesList;
        this.context = context;
    }


    @Override
    public ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_chat, null);
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ChatViewHolder holder, int position) {
        holder.tvChat.setText(messagesList.get(position).getMessage());
    }

    @Override
    public int getItemCount() {
        return messagesList.size();

    }

    public class ChatViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tvChat)
        TextView tvChat;


        public ChatViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
