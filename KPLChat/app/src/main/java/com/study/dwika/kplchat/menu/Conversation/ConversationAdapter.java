package com.study.dwika.kplchat.menu.Conversation;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.study.dwika.kplchat.R;
import com.study.dwika.kplchat.model.Conversation;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by A.I on 16/12/2017.
 */

public class ConversationAdapter extends RecyclerView.Adapter<ConversationAdapter.ConversationViewHolder>{

    private List<Conversation> conversationList;
    private Context context;

    public ConversationAdapter(List<Conversation> conversationList, Context context) {
        this.conversationList = conversationList;
        this.context = context;
    }

    @Override
    public ConversationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_conversation, null);
        return new ConversationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ConversationViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return conversationList.size();
    }

    public class ConversationViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tvConversationName)
        TextView tvConversationName;

        @BindView(R.id.tvConvsationChat)
        TextView tvConversationChat;

        public ConversationViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
