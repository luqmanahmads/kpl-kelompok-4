//package com.study.dwika.kplchat.Menu;
//
//
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentPagerAdapter;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.inject.Inject;
//import javax.inject.Singleton;
//
//@Singleton
//public class MenuViewPagerAdapter extends FragmentPagerAdapter{
//
//    private final List<Fragment> fragmentList = new ArrayList<>();
//    private final List<String> titleList = new ArrayList<>();
//
//    @Inject
//    public MenuViewPagerAdapter(FragmentManager fm) {
//        super(fm);
//    }
//
//    @Override
//    public Fragment getItem(int position) {
//        return fragmentList.get(position);
//    }
//
//    @Override
//    public int getCount() {
//        return fragmentList.size();
//    }
//
//    public void addFragment(Fragment fragment, String title){
//        fragmentList.add(fragment);
//        titleList.add(title);
//    }
//
//    @Override
//    public CharSequence getPageTitle(int position){
//        return titleList.get(position);
//    }
//
//    @Override
//    public int getItemPosition(Object object){
//        return super.getItemPosition(object);
//    }
//
//}