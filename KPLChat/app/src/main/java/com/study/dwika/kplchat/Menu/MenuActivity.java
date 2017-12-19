package com.study.dwika.kplchat.menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.study.dwika.kplchat.R;
import com.study.dwika.kplchat.addfriend.AddFriendActivity;
import com.study.dwika.kplchat.menu.Conversation.ConversationFragment;
import com.study.dwika.kplchat.menu.Friend.FriendFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuActivity extends AppCompatActivity {

//    @BindView(R.id.toolbar)
//    Toolbar toolbar;

    @BindView(R.id.menuViewPager)
    ViewPager menuViewPager;

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    private MenuViewPagerAdapter menuViewPagerAdapter;
    private ConversationFragment conversationFragment;
    private FriendFragment friendFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        ButterKnife.bind(this);

        setupTab();

        menuViewPagerAdapter = new MenuViewPagerAdapter(getSupportFragmentManager());

        setupViewPager(menuViewPager);
    }

    private void setupTab(){

        menuViewPager.setOffscreenPageLimit(2);

        tabLayout.setupWithViewPager(menuViewPager);

        menuViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                menuViewPager.setCurrentItem(position, false);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void setupViewPager(ViewPager viewPager){

        menuViewPagerAdapter.addFragment(new FriendFragment(), "Friends");
        menuViewPagerAdapter.addFragment(new ConversationFragment(), "Conversation");

        viewPager.setAdapter(menuViewPagerAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.addFriend) {
            Intent intent = new Intent(MenuActivity.this, AddFriendActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


}
