package com.example.pfe_0220.Planning.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.pfe_0220.CommunModels.Person;
import com.example.pfe_0220.Planning.SubFragment.SchoolClassResponsibleAttendencesFragment;
import com.example.pfe_0220.Planning.SubFragment.SchoolClassStudentAttendencesFragment;

import java.util.ArrayList;

public class PersonPagerAdapter extends FragmentPagerAdapter {

    ArrayList<Fragment> fragments = new ArrayList<>();

    public PersonPagerAdapter(@NonNull FragmentManager fm,ArrayList<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
       return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
