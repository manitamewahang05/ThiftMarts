package com.example.thiftmarts;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPageAdapter extends FragmentPagerAdapter {

    private final List<Fragment> fragmentList = new ArrayList<>();
    private  final List<String> fragmentlisttitles = new ArrayList<>();


    public ViewPageAdapter(FragmentManager fm){
        super (fm);
    }
    @Override
    public Fragment getItem(int i) {
        return fragmentList.get(i);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentlisttitles.get(position);
    }

    @Override
    public int getCount() {
        return fragmentlisttitles.size();
    }

    public void AddFragment(Fragment fragment, String Title){
        fragmentList.add(fragment);
        fragmentlisttitles.add(Title);
    }

}
