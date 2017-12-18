package com.study.dwika.kplchat.addmember;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.study.dwika.kplchat.R;
import com.study.dwika.kplchat.model.Users;

import java.util.List;

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