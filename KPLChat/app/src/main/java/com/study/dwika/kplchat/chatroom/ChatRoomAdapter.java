package com.study.dwika.kplchat.chatroom;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.study.dwika.kplchat.R;

import butterknife.ButterKnife;

/**
 * Created by A.I on 16/12/2017.
 */

public class ChatRoomAdapter extends RecyclerView.Adapter<ChatRoomAdapter.ChatViewHolder>{


    @Override
    public ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_chat, null);
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ChatViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ChatViewHolder extends RecyclerView.ViewHolder{

        public ChatViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
