package com.example.pfe_0220.Planning.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.pfe_0220.Planning.SubFragment.SchoolClassFragment;

import java.util.ArrayList;

public class ClassesFragmentViewPagerAdapter extends FragmentPagerAdapter {

    ArrayList<Fragment> classesFragments;

    public ClassesFragmentViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        return new SchoolClassFragment();
    }

    @Override
    public int getCount() {
        return 5;
    }
}
