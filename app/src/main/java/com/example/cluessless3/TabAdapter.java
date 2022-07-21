package com.example.cluessless3;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class TabAdapter extends FragmentStatePagerAdapter {

    int mTabs;


    public TabAdapter(@NonNull FragmentManager fm, int Tabs) {
        super(fm);
        this.mTabs = Tabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                ScanFragment Tab1 = new ScanFragment();
                return Tab1;
            case 1:
                ViewFragment Tab2 = new ViewFragment();
                return Tab2;
            case 3:
                ClothingFragment Tab3 = new ClothingFragment();
                return Tab3;
            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return mTabs;
    }
}
