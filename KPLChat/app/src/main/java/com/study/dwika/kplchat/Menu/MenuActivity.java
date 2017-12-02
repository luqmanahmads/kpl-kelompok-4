package com.study.dwika.kplchat.Menu;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.study.dwika.kplchat.Menu.Conversation.ConversationFragment;
import com.study.dwika.kplchat.Menu.Friend.FriendFragment;
import com.study.dwika.kplchat.R;

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


}
