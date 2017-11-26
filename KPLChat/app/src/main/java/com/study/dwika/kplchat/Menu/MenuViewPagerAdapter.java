package com.study.dwika.kplchat.Menu;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.study.dwika.kplchat.Menu.Conversation.ConversationFragment;
import com.study.dwika.kplchat.Menu.Friend.FriendFragment;

public class MenuViewPagerAdapter extends FragmentStatePagerAdapter{

    private int tabCount;

    public MenuViewPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        this.tabCount = 0;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return ConversationFragment.newInstance();
            case 1:
                return FriendFragment.newInstance();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return tabCount;
    }

    public void setCount(int count){
        tabCount = count;
    }
}
