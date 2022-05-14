package com.example.pfe_0220.Planning.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.pfe_0220.Planning.SubFragment.SchoolClassPersonFragment;

public class PersonPagerAdapter extends FragmentPagerAdapter {

    public PersonPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return new SchoolClassPersonFragment();
    }

    @Override
    public int getCount() {
        return 2;
    }
}
