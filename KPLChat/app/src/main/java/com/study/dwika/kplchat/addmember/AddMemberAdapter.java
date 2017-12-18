package com.study.dwika.kplchat.addmember;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.study.dwika.kplchat.R;
import com.study.dwika.kplchat.model.Messages;
import com.study.dwika.kplchat.model.Users;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Dwika on 18-Dec-17.
 */

public class AddMemberAdapter extends ArrayAdapter {

    Context context;
    List<Users> usersList;

    public AddMemberAdapter(Context context, List<Users> list) {
        super(context, 0, list);
        usersList = list;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_add_member, parent, false);
// inflate custom layout called row
            holder = new ViewHolder();
            holder.tv = (TextView) convertView.findViewById(R.id.tv_add_member_name_list);
// initialize textview
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Users in = (Users) usersList.get(position);
        holder.tv.setText(in.getName());
        // set the name to the text;

        return convertView;

    }

    static class ViewHolder {
        TextView tv;
    }

    public Users findClicked(int position){
        return usersList.get(position);
    }

}

/*
public class AddMemberAdapter extends RecyclerView.Adapter<AddMemberAdapter.AddMemberHolder>  {

    private List<Users> usersList;
    private Context context;

    public AddMemberAdapter(List<Users> usersList, Context context) {
        this.usersList = usersList;
        this.context = context;
    }

    @Override
    public AddMemberHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_add_member,null);
        return new AddMemberHolder(view);
    }

    @Override
    public void onBindViewHolder(AddMemberHolder holder, int position) {
        holder.tvName.setText(usersList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public Users findClicked(int position){
        return usersList.get(position);
    }


    public class AddMemberHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_add_member_name_list)
        TextView tvName;

        public AddMemberHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
*/